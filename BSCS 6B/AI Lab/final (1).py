import sqlite3

conn = sqlite3.connect("hospital.db")
cursor = conn.cursor()

cursor.execute("""
CREATE TABLE IF NOT EXISTS patients (
    patient_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT,
    age INTEGER,
    gender TEXT,
    contact TEXT
)
""")

cursor.execute("""
CREATE TABLE IF NOT EXISTS doctors (
    doctor_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT,
    specialization TEXT,
    contact TEXT
)
""")

cursor.execute("""
CREATE TABLE IF NOT EXISTS appointments (
    appointment_id INTEGER PRIMARY KEY AUTOINCREMENT,
    patient_id INTEGER,
    doctor_id INTEGER,
    date TEXT,
    time TEXT,
    status TEXT
)
""")

cursor.execute("""
CREATE TABLE IF NOT EXISTS bills (
    bill_id INTEGER PRIMARY KEY AUTOINCREMENT,
    patient_id INTEGER,
    doctor_name TEXT,
    amount REAL,
    payment_status TEXT
)
""")

conn.commit()
conn.close()
