# 2d-Range-Tree-Implementation

This report focuses on the experimental study of the data structure–RangeTree, where we implement two rangetree variances: RangeTree−Org and RangeTree−FC,and design three experiments to show the performance of the data structure on construction and query efﬁciency. RangeTree−Org isconstitutedbyabaseWB−BST witheachinternalnodeassociatedwithasecondaryWB−BST on the y-coordinates of all the points in the sub-tree. RangeTree−FC replaces all the secondary trees in RangeTree−Org with sorted arrays and apply factional cascading techniques to query operation. For Range Tree-Org, we anticipate the consumption of the naïve construction is bounded by O(nlog2 n) and a more efﬁcient construction way consumes O(nlogn) time, and the query time is bounded by O(k + log2 n) where k is the number of points reported. For RangeTree−FC, we expect a construction consumption of O(nlogn) and a query time bound of O(k + logn).

3 Experiments:\n
(1) Experiments on Construction Eﬃciency (Contr-Naive v.s. Contr-Sorted) \n
(2) Experiments on Query Eﬃciency with Fixed points number n and Varying query range s(RangeTree-Org v.s. RangeTree-FC) \n
(3) Experiments on Query Eﬃciency with Fixed s and Varying n (RangeTree-Org v.s. RangeTree-FC) \n



