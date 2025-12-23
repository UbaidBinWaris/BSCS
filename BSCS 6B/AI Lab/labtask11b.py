# 1. Import required libraries
import pandas as pd
import numpy as np
from sklearn.preprocessing import LabelEncoder, StandardScaler
from sklearn.model_selection import train_test_split, LeaveOneOut, cross_val_score
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import accuracy_score, confusion_matrix, classification_report

# 2. Create the dataset
data = {
    'Duration': [130, 95, 110, 140, 120, 105, 150, 100, 115, 125],
    'IMDb': [8.2, 6.5, 7.0, 7.8, 7.5, 6.8, 8.4, 6.2, 7.3, 7.1],
    'Action': [9, 3, 4, 8, 2, 5, 9, 2, 3, 6],
    'Romance': [3, 8, 4, 2, 9, 3, 2, 7, 5, 5],
    'Comedy': [2, 6, 9, 3, 5, 8, 1, 7, 8, 3],
    'Genre': ['Action', 'Romance', 'Comedy', 'Action', 'Romance', 'Comedy', 'Action', 'Romance', 'Comedy', 'Action']
}
df = pd.DataFrame(data)

# Show dataset
print("Dataset:\n", df)

# 3. Data Exploration
print("\nDataset shape:", df.shape)
print("\nFirst rows:\n", df.head())
print("\nSummary statistics:\n", df.describe(include='all'))
print("\nMissing values:\n", df.isnull().sum())
print("\nDuplicate rows:", df.duplicated().sum())

# 4. Preprocessing (Features and Labels)
X = df[['Duration', 'IMDb', 'Action', 'Romance', 'Comedy']].values
y_raw = df['Genre'].values

# Encode string labels to numbers
le = LabelEncoder()
y = le.fit_transform(y_raw)
print("\nLabel Mapping:", dict(zip(le.classes_, le.transform(le.classes_))))

# 5. Feature Scaling (Important for KNN)
scaler = StandardScaler()
X_scaled = scaler.fit_transform(X)

# 6. Train-Test Split
X_train, X_test, y_train, y_test = train_test_split(
    X_scaled, y, test_size=0.4, random_state=42
)
print("\nTrain size:", len(X_train))
print("Test size:", len(X_test))

# 7. Train the KNN Model
k = 3
knn = KNeighborsClassifier(n_neighbors=k)
knn.fit(X_train, y_train)
print("\nKNN Model trained with k =", k)

# 8. Evaluate the Model
y_pred = knn.predict(X_test)
print("\nAccuracy:", accuracy_score(y_test, y_pred))
print("\nConfusion Matrix:\n", confusion_matrix(y_test, y_pred))
print("\nClassification Report:\n", classification_report(y_test, y_pred, target_names=le.classes_, zero_division=0))

# 9. Leave-One-Out Cross Validation (Better for tiny dataset)
loo = LeaveOneOut()
cv_scores = cross_val_score(knn, X_scaled, y, cv=loo, scoring='accuracy')
print("\nLeave-One-Out CV Scores:", cv_scores)
print("Mean LOO Accuracy:", np.mean(cv_scores))

# 10. Predict a New Movie Sample
# Example: Duration 120, IMDb 7.5, Action 8, Romance 3, Comedy 4
new_sample = np.array([[120, 7.5, 8, 3, 4]]) 
# Scale using same scaler
new_sample_scaled = scaler.transform(new_sample)
pred_encoded = knn.predict(new_sample_scaled)[0]
pred_label = le.inverse_transform([pred_encoded])[0]
print("\nNew sample:", new_sample)
print("Predicted Genre:", pred_label)
