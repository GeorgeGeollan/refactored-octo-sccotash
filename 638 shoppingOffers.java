class Solution {
    int ans = 0x3f3f3f3f;
    List<Integer> price1;
    List<List<Integer>> special1;
    List<Integer> needs1;
    HashMap<Integer, Integer> map = new HashMap<>();
    int n, m;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        price1 = price; special1 = special; needs1 = needs;

        n = price.size();
        List<Integer> temp = new ArrayList<>();
        for(int i = 0; i < n; i++)
            temp.add(0);
        
        for(int i = 0; i < n; i++) {
            List<Integer> clone = new ArrayList<>(temp);
            clone.set(i, 1);
            clone.add(price.get(i));
            special.add(clone);
        }

        m = special.size();
        for(int i = 0; i < m; i++) {
            List<Integer> x = special.get(i);
            int max = 0;

            for(int j = 0; j < n; j++) {
                int a = x.get(j), b = needs.get(j);
                if(a == 0 || b == 0)
                    continue;
                
                max = Math.max(max, (int)(Math.ceil(b / a)));
            }

            map.put(i, max);
        }

        dfs(0, needs1, 0);
        return ans;
    }

    void dfs(int u, List<Integer> list, int cur) {
        if (cur >= ans) return ;
        if (u == m) {
            for (int i = 0; i < n; i++) {
                if (list.get(i) != 0) return ;
            }
            ans = Math.min(ans, cur);
            return ;
        }

        List<Integer> x = special1.get(u);
        out:for(int k = 0; k <= map.get(u); k++) {
            List<Integer> clist = new ArrayList<>(list);
            for(int i = 0; i < n; i++) {
                int a = x.get(i), b = clist.get(i);
                if(a * k > b)
                    break out;
                
                clist.set(i, b - a * k);
            }

            dfs(u + 1, clist, cur + k * x.get(n));
        }
    }
}
