// A dynamic programming based
// solution for 0-1 Knapsack problem

#include <bits/stdc++.h>
using namespace std;
 
// A utility function that returns
// maximum of two integers

int max(int a, int b)
{
    return (a > b) ? a : b;
}
 
// Returns the maximum value that
// can be put in a knapsack of capacity W

int knapSack(int W, int wt[], int val[], int n)
{
    int i, w;
    int ctr1, ctr2;
    
    vector<vector<int> > K(n + 1, vector<int>(W + 1));
 
    // Build table K[][] in bottom up manner

    for(i = 0; i <= n; i++)
    {

        for(w = 0; w <= W; w++)
        {
            if (i == 0 || w == 0)
                K[i][w] = 0;
            else if (wt[i - 1] <= w)
                K[i][w] = max(val[i - 1] +
                                K[i - 1][w - wt[i - 1]],
                                K[i - 1][w]);
            else
                K[i][w] = K[i - 1][w];
        }

    // Display table K[][]

	cout << "For i=" << i << endl;
    for(ctr1 = 0; ctr1 <= n; ctr1++)
    {
        for(ctr2 = 0; ctr2 <= W; ctr2++)
        {
            cout << K[ctr1][ctr2] << " ";
        }
        cout << endl;
    }
    cout << endl;
    }
 
    return K[n][W];
}
 
// Driver Code

int main()
{
    int val[] = { 2, 3, 1, 4 };
    int wt[] = { 3, 4, 6, 5 };
    int W = 8;
    int n = sizeof(val) / sizeof(val[0]);
    cout << knapSack(W, wt, val, n);
    return 0;
}

