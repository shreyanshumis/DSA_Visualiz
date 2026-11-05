import java.util.*;

class Solution0 {
    public static Object solve(int[] a, int target) {
        int left = 0;
        int right = (a.length - 1);
        while ((left < right)) {
            int sum = (a[left] + a[right]);
            if (Objects.equals(sum, target)) {
                return Arrays.asList((left + 1), (right + 1));
            }
            if ((sum < target)) {
                left++;
            }
            else {
                right--;
            }
        }
        return Arrays.asList();
    }
}

class Solution1 {
    public static Object solve(int[] a) {
        int left = 0;
        int right = (a.length - 1);
        while ((left < right)) {
            if (Objects.equals(a[left], 0)) {
                left++;
            }
            else {
                if (Objects.equals(a[right], 1)) {
                    right--;
                }
                else {
                    { int swap = a[left]; a[left] = a[right]; a[right] = swap; }
                    left++;
                    right--;
                }
            }
        }
        return a;
    }
}

class Solution2 {
    public static Object solve(int[] a) {
        int limit = (Objects.equals("once", "twice") ? 2 : 1);
        int write = 0;
        for (int read = 0; (read < a.length); read++) {
            boolean keep = ((write < limit) || !Objects.equals(a[read], a[(write - limit)]));
            if (keep) {
                a[write] = a[read];
                write++;
            }
        }
        return Map.of("length", write, "values", Arrays.copyOfRange(a, 0, write));
    }
}

class Solution3 {
    public static Object solve(int[] a) {
        int limit = (Objects.equals("twice", "twice") ? 2 : 1);
        int write = 0;
        for (int read = 0; (read < a.length); read++) {
            boolean keep = ((write < limit) || !Objects.equals(a[read], a[(write - limit)]));
            if (keep) {
                a[write] = a[read];
                write++;
            }
        }
        return Map.of("length", write, "values", Arrays.copyOfRange(a, 0, write));
    }
}

class Solution4 {
    public static Object solve(int[] a) {
        int[] next = new int[a.length];
        for (int i = 0; i < a.length; i++) next[i] = i + 1 < a.length ? i + 1 : -1;
        int current = (a.length > 0 ? 0 : (-1));
        while ((!Objects.equals(current, (-1)) && !Objects.equals(next[current], (-1)))) {
            int neighbor = next[current];
            if (Objects.equals(a[current], a[neighbor])) {
                next[current] = next[neighbor];
            }
            else {
                current = neighbor;
            }
        }
        List<Object> result = new ArrayList<>();
        for (int node = (a.length > 0 ? 0 : (-1)); !Objects.equals(node, (-1)); node = next[node]) {
            result.add(a[node]);
        }
        return result;
    }
}

class Solution5 {
    public static Object solve(int[] a) {
        int left = 0;
        int right = (a.length - 1);
        int[] out = new int[a.length];
        for (int write = (a.length - 1); (write >= 0); write--) {
            int leftSquare = (a[left] * a[left]);
            int rightSquare = (a[right] * a[right]);
            if ((leftSquare > rightSquare)) {
                out[write] = leftSquare;
                left++;
            }
            else {
                out[write] = rightSquare;
                right--;
            }
        }
        return out;
    }
}

class Solution6 {
    public static Object solve(int[] a) {
        int target = 0;
        String mode = "zero";
        Arrays.sort(a);
        int closest = ((a[0] + a[1]) + a[2]);
        int count = 0;
        List<Object> result = new ArrayList<>();
        for (int anchor = 0; (anchor < (a.length - 2)); anchor++) {
            if (((Objects.equals(mode, "zero") && (anchor > 0)) && Objects.equals(a[anchor], a[(anchor - 1)]))) {
                continue;
            }
            int left = (anchor + 1);
            int right = (a.length - 1);
            while ((left < right)) {
                int sum = ((a[anchor] + a[left]) + a[right]);
                if ((Objects.equals(mode, "closest") && (Math.abs((sum - target)) < Math.abs((closest - target))))) {
                    closest = sum;
                }
                if ((Objects.equals(mode, "smaller") && (sum < target))) {
                    count += (right - left);
                    left++;
                }
                else {
                    if ((Objects.equals(mode, "zero") && Objects.equals(sum, 0))) {
                        result.add(Arrays.asList(a[anchor], a[left], a[right]));
                        left++;
                        right--;
                        while (((left < right) && Objects.equals(a[left], a[(left - 1)]))) {
                            left++;
                        }
                        while (((left < right) && Objects.equals(a[right], a[(right + 1)]))) {
                            right--;
                        }
                    }
                    else {
                        if ((sum < target)) {
                            left++;
                        }
                        else {
                            right--;
                        }
                    }
                }
            }
        }
        return (Objects.equals(mode, "zero") ? result : (Objects.equals(mode, "closest") ? closest : count));
    }
}

class Solution7 {
    public static Object solve(int[] a, int target) {
        String mode = "closest";
        Arrays.sort(a);
        int closest = ((a[0] + a[1]) + a[2]);
        int count = 0;
        List<Object> result = new ArrayList<>();
        for (int anchor = 0; (anchor < (a.length - 2)); anchor++) {
            if (((Objects.equals(mode, "zero") && (anchor > 0)) && Objects.equals(a[anchor], a[(anchor - 1)]))) {
                continue;
            }
            int left = (anchor + 1);
            int right = (a.length - 1);
            while ((left < right)) {
                int sum = ((a[anchor] + a[left]) + a[right]);
                if ((Objects.equals(mode, "closest") && (Math.abs((sum - target)) < Math.abs((closest - target))))) {
                    closest = sum;
                }
                if ((Objects.equals(mode, "smaller") && (sum < target))) {
                    count += (right - left);
                    left++;
                }
                else {
                    if ((Objects.equals(mode, "zero") && Objects.equals(sum, 0))) {
                        result.add(Arrays.asList(a[anchor], a[left], a[right]));
                        left++;
                        right--;
                        while (((left < right) && Objects.equals(a[left], a[(left - 1)]))) {
                            left++;
                        }
                        while (((left < right) && Objects.equals(a[right], a[(right + 1)]))) {
                            right--;
                        }
                    }
                    else {
                        if ((sum < target)) {
                            left++;
                        }
                        else {
                            right--;
                        }
                    }
                }
            }
        }
        return (Objects.equals(mode, "zero") ? result : (Objects.equals(mode, "closest") ? closest : count));
    }
}

class Solution8 {
    public static Object solve(int[] a, int target) {
        String mode = "smaller";
        Arrays.sort(a);
        int closest = ((a[0] + a[1]) + a[2]);
        int count = 0;
        List<Object> result = new ArrayList<>();
        for (int anchor = 0; (anchor < (a.length - 2)); anchor++) {
            if (((Objects.equals(mode, "zero") && (anchor > 0)) && Objects.equals(a[anchor], a[(anchor - 1)]))) {
                continue;
            }
            int left = (anchor + 1);
            int right = (a.length - 1);
            while ((left < right)) {
                int sum = ((a[anchor] + a[left]) + a[right]);
                if ((Objects.equals(mode, "closest") && (Math.abs((sum - target)) < Math.abs((closest - target))))) {
                    closest = sum;
                }
                if ((Objects.equals(mode, "smaller") && (sum < target))) {
                    count += (right - left);
                    left++;
                }
                else {
                    if ((Objects.equals(mode, "zero") && Objects.equals(sum, 0))) {
                        result.add(Arrays.asList(a[anchor], a[left], a[right]));
                        left++;
                        right--;
                        while (((left < right) && Objects.equals(a[left], a[(left - 1)]))) {
                            left++;
                        }
                        while (((left < right) && Objects.equals(a[right], a[(right + 1)]))) {
                            right--;
                        }
                    }
                    else {
                        if ((sum < target)) {
                            left++;
                        }
                        else {
                            right--;
                        }
                    }
                }
            }
        }
        return (Objects.equals(mode, "zero") ? result : (Objects.equals(mode, "closest") ? closest : count));
    }
}

class Solution9 {
    public static Object solve(int[] a, int k) {
        if ((k <= 1)) {
            return 0;
        }
        int left = 0;
        long product = 1;
        int count = 0;
        for (int right = 0; (right < a.length); right++) {
            product *= a[right];
            while (((product >= k) && (left <= right))) {
                product /= a[left];
                left++;
            }
            count += ((right - left) + 1);
        }
        return count;
    }
}

class Solution10 {
    public static Object solve(int[] a) {
        int low = 0;
        int mid = 0;
        int high = (a.length - 1);
        while ((mid <= high)) {
            int color = a[mid];
            if (Objects.equals(color, 0)) {
                { int swap = a[low]; a[low] = a[mid]; a[mid] = swap; }
                low++;
                mid++;
            }
            else {
                if (Objects.equals(color, 1)) {
                    mid++;
                }
                else {
                    { int swap = a[mid]; a[mid] = a[high]; a[high] = swap; }
                    high--;
                }
            }
        }
        return a;
    }
}

class Solution11 {
    public static Object solve(int[] a, int target) {
        Arrays.sort(a);
        List<Object> result = new ArrayList<>();
        for (int i = 0; (i < (a.length - 3)); i++) {
            if (((i > 0) && Objects.equals(a[i], a[(i - 1)]))) {
                continue;
            }
            for (int j = (i + 1); (j < (a.length - 2)); j++) {
                if (((j > (i + 1)) && Objects.equals(a[j], a[(j - 1)]))) {
                    continue;
                }
                int left = (j + 1);
                int right = (a.length - 1);
                while ((left < right)) {
                    int sum = (((a[i] + a[j]) + a[left]) + a[right]);
                    if (Objects.equals(sum, target)) {
                        result.add(Arrays.asList(a[i], a[j], a[left], a[right]));
                        left++;
                        right--;
                        while (((left < right) && Objects.equals(a[left], a[(left - 1)]))) {
                            left++;
                        }
                        while (((left < right) && Objects.equals(a[right], a[(right + 1)]))) {
                            right--;
                        }
                    }
                    else {
                        if ((sum < target)) {
                            left++;
                        }
                        else {
                            right--;
                        }
                    }
                }
            }
        }
        return result;
    }
}

class Solution12 {
    public static Object solve(String s, String t) {
        int left = (s.length() - 1);
        int right = (t.length() - 1);
        while (((left >= 0) || (right >= 0))) {
            int skip = 0;
            while ((left >= 0)) {
                if (Objects.equals(s.charAt(left), '#')) {
                    skip++;
                }
                else {
                    if ((skip > 0)) {
                        skip--;
                    }
                    else {
                        break;
                    }
                }
                left--;
            }
            skip = 0;
            while ((right >= 0)) {
                if (Objects.equals(t.charAt(right), '#')) {
                    skip++;
                }
                else {
                    if ((skip > 0)) {
                        skip--;
                    }
                    else {
                        break;
                    }
                }
                right--;
            }
            Character x = ((left >= 0) ? s.charAt(left) : null);
            Character y = ((right >= 0) ? t.charAt(right) : null);
            if (!Objects.equals(x, y)) {
                return false;
            }
            left--;
            right--;
        }
        return true;
    }
}

class Solution13 {
    public static Object solve(int[] a) {
        int left = 0;
        int right = (a.length - 1);
        while (((left < (a.length - 1)) && (a[left] <= a[(left + 1)]))) {
            left++;
        }
        if ((left >= (a.length - 1))) {
            return 0;
        }
        while (((right > 0) && (a[right] >= a[(right - 1)]))) {
            right--;
        }
        int min = Integer.MAX_VALUE;
        int max = (-Integer.MAX_VALUE);
        for (int scan = left; (scan <= right); scan++) {
            min = Math.min(min, a[scan]);
            max = Math.max(max, a[scan]);
        }
        while (((left > 0) && (a[(left - 1)] > min))) {
            left--;
        }
        while (((right < (a.length - 1)) && (a[(right + 1)] < max))) {
            right++;
        }
        return ((right - left) + 1);
    }
}

class Solution14 {
    public static Object solve(int[] a, int pos) {
        boolean findStart = Objects.equals("detect", "start");
        int[] next = new int[a.length];
        for (int i = 0; i < a.length; i++) next[i] = i + 1 < a.length ? i + 1 : pos;
        int slow = (a.length > 0 ? 0 : (-1));
        int fast = slow;
        while ((!Objects.equals(fast, (-1)) && !Objects.equals(next[fast], (-1)))) {
            slow = next[slow];
            fast = next[fast];
            fast = next[fast];
            if (Objects.equals(slow, fast)) {
                if ((!findStart)) {
                    return true;
                }
                slow = 0;
                while (!Objects.equals(slow, fast)) {
                    slow = next[slow];
                    fast = next[fast];
                }
                return Map.of("index", slow, "value", a[slow]);
            }
        }
        return (findStart ? null : false);
    }
}

class Solution15 {
    public static Object solve(int[] a, int pos) {
        boolean findStart = Objects.equals("start", "start");
        int[] next = new int[a.length];
        for (int i = 0; i < a.length; i++) next[i] = i + 1 < a.length ? i + 1 : pos;
        int slow = (a.length > 0 ? 0 : (-1));
        int fast = slow;
        while ((!Objects.equals(fast, (-1)) && !Objects.equals(next[fast], (-1)))) {
            slow = next[slow];
            fast = next[fast];
            fast = next[fast];
            if (Objects.equals(slow, fast)) {
                if ((!findStart)) {
                    return true;
                }
                slow = 0;
                while (!Objects.equals(slow, fast)) {
                    slow = next[slow];
                    fast = next[fast];
                }
                return Map.of("index", slow, "value", a[slow]);
            }
        }
        return (findStart ? null : false);
    }
}

class Solution16 {
    public static Object solve(int n) {
        java.util.function.IntUnaryOperator next = value -> {
            return String.valueOf(value).chars().map(digit -> (digit - '0') * (digit - '0')).sum();
        };
        int slow = n;
        int fast = n;
        do {
            slow = next.applyAsInt(slow);
            fast = next.applyAsInt(fast);
            fast = next.applyAsInt(fast);
        } while (!Objects.equals(slow, fast));
        return Objects.equals(slow, 1);
    }
}

class Solution17 {
    public static Object solve(int[] a) {
        int slow = 0;
        int fast = 0;
        do {
            slow = a[slow];
            fast = a[fast];
            fast = a[fast];
        } while (!Objects.equals(slow, fast));
        slow = 0;
        while (!Objects.equals(slow, fast)) {
            slow = a[slow];
            fast = a[fast];
        }
        return slow;
    }
}

class Solution18 {
    public static Object solve(int[] a) {
        int[] next = new int[a.length];
        for (int i = 0; i < a.length; i++) next[i] = i + 1 < a.length ? i + 1 : -1;
        int slow = (a.length > 0 ? 0 : (-1));
        int fast = slow;
        while ((!Objects.equals(fast, (-1)) && !Objects.equals(next[fast], (-1)))) {
            slow = next[slow];
            fast = next[fast];
            fast = next[fast];
        }
        return (Objects.equals(slow, (-1)) ? null : Map.of("index", slow, "value", a[slow]));
    }
}

class Solution19 {
    public static Object solve(int[] a) {
        int[] next = new int[a.length];
        for (int i = 0; i < a.length; i++) next[i] = i + 1 < a.length ? i + 1 : -1;
        int slow = (a.length > 0 ? 0 : (-1));
        int fast = slow;
        while ((!Objects.equals(fast, (-1)) && !Objects.equals(next[fast], (-1)))) {
            slow = next[slow];
            fast = next[next[fast]];
        }
        int previous = (-1);
        int current = slow;
        while (!Objects.equals(current, (-1))) {
            int following = next[current];
            next[current] = previous;
            previous = current;
            current = following;
        }
        int left = (a.length > 0 ? 0 : (-1));
        int right = previous;
        while (!Objects.equals(right, (-1))) {
            boolean equal = Objects.equals(a[left], a[right]);
            if ((!equal)) {
                return false;
            }
            left = next[left];
            right = next[right];
        }
        return true;
    }
}

class Solution20 {
    public static Object solve(int[] a) {
        int[] next = new int[a.length];
        for (int i = 0; i < a.length; i++) next[i] = i + 1 < a.length ? i + 1 : -1;
        if ((a.length < 2)) {
            return a;
        }
        int slow = 0;
        int fast = 0;
        while ((!Objects.equals(next[fast], (-1)) && !Objects.equals(next[next[fast]], (-1)))) {
            slow = next[slow];
            fast = next[next[fast]];
        }
        int current = next[slow];
        int previous = (-1);
        next[slow] = (-1);
        while (!Objects.equals(current, (-1))) {
            int following = next[current];
            next[current] = previous;
            previous = current;
            current = following;
        }
        int left = 0;
        int right = previous;
        while (!Objects.equals(right, (-1))) {
            int nextLeft = next[left];
            int nextRight = next[right];
            next[left] = right;
            next[right] = nextLeft;
            left = nextLeft;
            right = nextRight;
        }
        List<Object> result = new ArrayList<>();
        for (int node = 0; !Objects.equals(node, (-1)); node = next[node]) {
            result.add(a[node]);
        }
        return result;
    }
}

class Solution21 {
    public static Object solve(int[] a) {
        int n = a.length;
        for (int start = 0; (start < n); start++) {
            boolean positive = (a[start] > 0);
            java.util.function.IntUnaryOperator next = i -> {
                if ((Objects.equals(i, (-1)) || !Objects.equals((a[i] > 0), positive))) {
                    return (-1);
                }
                int j = ((((i + a[i]) % n) + n) % n);
                return (Objects.equals(j, i) ? (-1) : j);
            };
            int slow = start;
            int fast = start;
            do {
                slow = next.applyAsInt(slow);
                fast = next.applyAsInt(fast);
                fast = next.applyAsInt(fast);
                if ((Objects.equals(slow, (-1)) || Objects.equals(fast, (-1)))) {
                    break;
                }
                if (Objects.equals(slow, fast)) {
                    return true;
                }
            } while (!Objects.equals(slow, fast));
        }
        return false;
    }
}

class Solution22 {
    public static Object solve(int[] a, int k) {
        int left = 0;
        int sum = 0;
        int best = (-Integer.MAX_VALUE);
        for (int right = 0; (right < a.length); right++) {
            sum += a[right];
            if (Objects.equals(((right - left) + 1), k)) {
                best = Math.max(best, sum);
                sum -= a[left];
                left++;
            }
        }
        return best;
    }
}

class Solution23 {
    public static Object solve(int[] a, int target) {
        int left = 0;
        int sum = 0;
        int best = Integer.MAX_VALUE;
        for (int right = 0; (right < a.length); right++) {
            sum += a[right];
            while ((sum >= target)) {
                best = Math.min(best, ((right - left) + 1));
                sum -= a[left];
                left++;
            }
        }
        return (Objects.equals(best, Integer.MAX_VALUE) ? 0 : best);
    }
}

class Solution24 {
    public static Object solve(String s, int k) {
        char[] a = s.toCharArray();
        boolean exact = Objects.equals("exact", "exact");
        int left = 0;
        int best = (exact ? (-1) : 0);
        Map<Object, Integer> freq = new HashMap<>();
        for (int right = 0; (right < a.length); right++) {
            freq.put(a[right], (freq.getOrDefault(a[right], 0) + 1));
            while ((freq.size() > k)) {
                freq.put(a[left], (freq.getOrDefault(a[left], 0) - 1));
                if (Objects.equals(freq.getOrDefault(a[left], 0), 0)) {
                    freq.remove(a[left]);
                }
                left++;
            }
            if (((!exact) || Objects.equals(freq.size(), k))) {
                best = Math.max(best, ((right - left) + 1));
            }
        }
        return best;
    }
}

class Solution25 {
    public static Object solve(int[] a) {
        int k = 2;
        boolean exact = Objects.equals("fruit", "exact");
        int left = 0;
        int best = (exact ? (-1) : 0);
        Map<Object, Integer> freq = new HashMap<>();
        for (int right = 0; (right < a.length); right++) {
            freq.put(a[right], (freq.getOrDefault(a[right], 0) + 1));
            while ((freq.size() > k)) {
                freq.put(a[left], (freq.getOrDefault(a[left], 0) - 1));
                if (Objects.equals(freq.getOrDefault(a[left], 0), 0)) {
                    freq.remove(a[left]);
                }
                left++;
            }
            if (((!exact) || Objects.equals(freq.size(), k))) {
                best = Math.max(best, ((right - left) + 1));
            }
        }
        return best;
    }
}


class Solution26 {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int left = 0, maxLen = 0;
        Map<Character, Integer> lastIndex = new HashMap<>();

        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);

            // if char already seen, move left pointer after its last occurrence
            if (lastIndex.containsKey(c)) {
                left = Math.max(left, lastIndex.get(c) + 1);
            }

            lastIndex.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}

class Solution27 {
    public static Object solve(String s, int k) {
        char[] a = s.toCharArray();
        Map<Object, Integer> freq = new HashMap<>();
        int left = 0;
        int best = 0;
        for (int right = 0; (right < a.length); right++) {
            freq.put(a[right], (freq.getOrDefault(a[right], 0) + 1));
            int maxFreq = Collections.max(freq.values());
            while (((((right - left) + 1) - maxFreq) > k)) {
                freq.put(a[left], (freq.getOrDefault(a[left], 0) - 1));
                left++;
                maxFreq = Collections.max(freq.values());
            }
            best = Math.max(best, ((right - left) + 1));
        }
        return best;
    }
}

class Solution28 {
    public static Object solve(int[] a, int k) {
        int left = 0;
        int zeros = 0;
        int best = 0;
        for (int right = 0; (right < a.length); right++) {
            if (Objects.equals(a[right], 0)) {
                zeros++;
            }
            while ((zeros > k)) {
                if (Objects.equals(a[left], 0)) {
                    zeros--;
                }
                left++;
            }
            best = Math.max(best, ((right - left) + 1));
        }
        return best;
    }
}

class Solution29 {
    public static Object solve(String s, String t) {
        if (t.length() == 0) {
            return "";
        }
        char[] a = s.toCharArray();
        Map<Object, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, (need.getOrDefault(c, 0) + 1));
        }
        int left = 0;
        int missing = t.length();
        int bestStart = 0;
        int bestLength = Integer.MAX_VALUE;
        for (int right = 0; (right < a.length); right++) {
            char c = a[right];
            if (need.containsKey(c)) {
                if ((need.getOrDefault(c, 0) > 0)) {
                    missing--;
                }
                need.put(c, (need.getOrDefault(c, 0) - 1));
            }
            while (Objects.equals(missing, 0)) {
                if ((((right - left) + 1) < bestLength)) {
                    bestStart = left;
                    bestLength = ((right - left) + 1);
                }
                char outgoing = a[left];
                left++;
                if (need.containsKey(outgoing)) {
                    need.put(outgoing, (need.getOrDefault(outgoing, 0) + 1));
                    if ((need.getOrDefault(outgoing, 0) > 0)) {
                        missing++;
                    }
                }
            }
        }
        return (Objects.equals(bestLength, Integer.MAX_VALUE) ? "" : s.substring(bestStart, (bestStart + bestLength)));
    }
}

class Solution30 {
    public static Object solve(String s, String t) {
        String mode = "permutation";
        if (t.length() == 0) {
            return (Objects.equals(mode, "permutation") ? true : Arrays.asList());
        }
        char[] a = s.toCharArray();
        Map<Object, Integer> need = new HashMap<>();
        List<Object> result = new ArrayList<>();
        for (char c : t.toCharArray()) {
            need.put(c, (need.getOrDefault(c, 0) + 1));
        }
        int left = 0;
        int missing = t.length();
        for (int right = 0; (right < a.length); right++) {
            char c = a[right];
            if (need.containsKey(c)) {
                if ((need.getOrDefault(c, 0) > 0)) {
                    missing--;
                }
                need.put(c, (need.getOrDefault(c, 0) - 1));
            }
            if ((((right - left) + 1) > t.length())) {
                char outgoing = a[left];
                left++;
                if (need.containsKey(outgoing)) {
                    need.put(outgoing, (need.getOrDefault(outgoing, 0) + 1));
                    if ((need.getOrDefault(outgoing, 0) > 0)) {
                        missing++;
                    }
                }
            }
            if ((Objects.equals(((right - left) + 1), t.length()) && Objects.equals(missing, 0))) {
                result.add(left);
                if (Objects.equals(mode, "permutation")) {
                    return true;
                }
            }
        }
        return (Objects.equals(mode, "permutation") ? false : result);
    }
}

class Solution31 {
    public static Object solve(String s, String t) {
        String mode = "anagrams";
        if (t.length() == 0) {
            return (Objects.equals(mode, "permutation") ? true : Arrays.asList());
        }
        char[] a = s.toCharArray();
        Map<Object, Integer> need = new HashMap<>();
        List<Object> result = new ArrayList<>();
        for (char c : t.toCharArray()) {
            need.put(c, (need.getOrDefault(c, 0) + 1));
        }
        int left = 0;
        int missing = t.length();
        for (int right = 0; (right < a.length); right++) {
            char c = a[right];
            if (need.containsKey(c)) {
                if ((need.getOrDefault(c, 0) > 0)) {
                    missing--;
                }
                need.put(c, (need.getOrDefault(c, 0) - 1));
            }
            if ((((right - left) + 1) > t.length())) {
                char outgoing = a[left];
                left++;
                if (need.containsKey(outgoing)) {
                    need.put(outgoing, (need.getOrDefault(outgoing, 0) + 1));
                    if ((need.getOrDefault(outgoing, 0) > 0)) {
                        missing++;
                    }
                }
            }
            if ((Objects.equals(((right - left) + 1), t.length()) && Objects.equals(missing, 0))) {
                result.add(left);
                if (Objects.equals(mode, "permutation")) {
                    return true;
                }
            }
        }
        return (Objects.equals(mode, "permutation") ? false : result);
    }
}

class Solution32 {
    public static Object solve(String s, String[] words) {
        int width = words[0].length();
        Map<Object, Integer> need = new HashMap<>();
        List<Object> result = new ArrayList<>();
        char[] a = s.toCharArray();
        for (String word : words) {
            need.put(word, (need.getOrDefault(word, 0) + 1));
        }
        for (int offset = 0; (offset < width); offset++) {
            int left = offset;
            int count = 0;
            Map<Object, Integer> seen = new HashMap<>();
            for (int right = offset; ((right + width) <= s.length()); right += width) {
                String word = s.substring(right, (right + width));
                if ((!need.containsKey(word))) {
                    seen.clear();
                    count = 0;
                    left = (right + width);
                    continue;
                }
                seen.put(word, (seen.getOrDefault(word, 0) + 1));
                count++;
                while ((seen.getOrDefault(word, 0) > need.getOrDefault(word, 0))) {
                    String outgoing = s.substring(left, (left + width));
                    seen.put(outgoing, (seen.getOrDefault(outgoing, 0) - 1));
                    left += width;
                    count--;
                }
                if (Objects.equals(count, words.length)) {
                    result.add(left);
                }
            }
        }
        return result;
    }
}

class Solution33 {
    public static Object solve(int[] a) {
        String mode = "max";
        int ending = a[0];
        int best = a[0];
        int start = 0;
        for (int read = 1; (read < a.length); read++) {
            int extended = (ending + a[read]);
            if (((Objects.equals(mode, "max") && (a[read] > extended)) || (Objects.equals(mode, "min") && (a[read] < extended)))) {
                ending = a[read];
                start = read;
            }
            else {
                ending = extended;
            }
            best = (Objects.equals(mode, "max") ? Math.max(best, ending) : Math.min(best, ending));
        }
        return best;
    }
}

class Solution34 {
    public static Object solve(int[] a) {
        String mode = "min";
        int ending = a[0];
        int best = a[0];
        int start = 0;
        for (int read = 1; (read < a.length); read++) {
            int extended = (ending + a[read]);
            if (((Objects.equals(mode, "max") && (a[read] > extended)) || (Objects.equals(mode, "min") && (a[read] < extended)))) {
                ending = a[read];
                start = read;
            }
            else {
                ending = extended;
            }
            best = (Objects.equals(mode, "max") ? Math.max(best, ending) : Math.min(best, ending));
        }
        return best;
    }
}

class Solution35 {
    public static Object solve(int[] a) {
        int largest = a[0];
        int smallest = a[0];
        int best = a[0];
        for (int read = 1; (read < a.length); read++) {
            int x = a[read];
            int fromLargest = (largest * x);
            int fromSmallest = (smallest * x);
            largest = Math.max(x, Math.max(fromLargest, fromSmallest));
            smallest = Math.min(x, Math.min(fromLargest, fromSmallest));
            best = Math.max(best, largest);
        }
        return best;
    }
}

class Solution36 {
    public static Object solve(int[] a) {
        int keep = a[0];
        int drop = (-1000000000);
        int best = a[0];
        for (int read = 1; (read < a.length); read++) {
            drop = Math.max(keep, (drop + a[read]));
            keep = Math.max(a[read], (keep + a[read]));
            best = Math.max(best, Math.max(keep, drop));
        }
        return best;
    }
}

class Solution37 {
    public static Object solve(int[] a) {
        int high = 0;
        int low = 0;
        int best = 0;
        for (int read = 0; (read < a.length); read++) {
            high = Math.max(0, (high + a[read]));
            low = Math.min(0, (low + a[read]));
            best = Math.max(best, Math.max(high, (-low)));
        }
        return best;
    }
}

class Solution38 {
    public static Object solve(int[] a) {
        int high = a[0];
        int low = a[0];
        int maximum = a[0];
        int minimum = a[0];
        int total = a[0];
        for (int read = 1; (read < a.length); read++) {
            high = Math.max(a[read], (high + a[read]));
            low = Math.min(a[read], (low + a[read]));
            maximum = Math.max(maximum, high);
            minimum = Math.min(minimum, low);
            total += a[read];
        }
        return ((maximum < 0) ? maximum : Math.max(maximum, (total - minimum)));
    }
}

class Solution39 {
    public static Object solve(int[] a, int k) {
        int prefix = 0;
        int count = 0;
        Map<Object, Integer> freq = new HashMap<>();
        freq.put(0, 1);
        for (int read = 0; (read < a.length); read++) {
            prefix += a[read];
            int needed = (prefix - k);
            int matches = freq.getOrDefault(needed, 0);
            count += matches;
            freq.put(prefix, (freq.getOrDefault(prefix, 0) + 1));
        }
        return count;
    }
}

class Solution40 {
    public static Object solve(int[] a) {
        int total = 0;
        int left = 0;
        for (int read = 0; (read < a.length); read++) {
            total += a[read];
        }
        for (int read = 0; (read < a.length); read++) {
            int right = ((total - left) - a[read]);
            if (Objects.equals(left, right)) {
                return read;
            }
            left += a[read];
        }
        return (-1);
    }
}

class Solution41 {
    public static Object solve(int[] a, int k) {
        int remainder = 0;
        int count = 0;
        Map<Object, Integer> freq = new HashMap<>();
        freq.put(0, 1);
        for (int read = 0; (read < a.length); read++) {
            remainder = ((((remainder + a[read]) % k) + k) % k);
            int matches = freq.getOrDefault(remainder, 0);
            count += matches;
            freq.put(remainder, (matches + 1));
        }
        return count;
    }
}

class Solution42 {
    public static Object solve(int[] a) {
        int balance = 0;
        int best = 0;
        Map<Object, Integer> last = new HashMap<>();
        last.put(0, (-1));
        for (int read = 0; (read < a.length); read++) {
            balance += (Objects.equals(a[read], 0) ? (-1) : 1);
            if (last.containsKey(balance)) {
                int start = (last.getOrDefault(balance, 0) + 1);
                best = Math.max(best, ((read - start) + 1));
            }
            else {
                last.put(balance, read);
            }
        }
        return best;
    }
}

class Solution43 {
    public static Object solve(int[] a, int k) {
        int[] prefix = new int[(a.length + 1)];
        int[] deque = new int[(a.length + 1)];
        for (int read = 0; (read < a.length); read++) {
            prefix[(read + 1)] = (prefix[read] + a[read]);
        }
        int head = 0;
        int tail = 0;
        int best = (a.length + 1);
        for (int end = 0; (end < prefix.length); end++) {
            while (((head < tail) && ((prefix[end] - prefix[deque[head]]) >= k))) {
                best = Math.min(best, (end - deque[head]));
                head++;
            }
            while (((head < tail) && (prefix[end] <= prefix[deque[(tail - 1)]]))) {
                tail--;
            }
            deque[tail] = end;
            tail++;
        }
        return ((best <= a.length) ? best : (-1));
    }
}

class Solution44 {
    public static Object solve(int[] a, int lower, int upper) {
        int[] prefix = new int[(a.length + 1)];
        int[] buffer = new int[(a.length + 1)];
        for (int read = 0; (read < a.length); read++) {
            prefix[(read + 1)] = (prefix[read] + a[read]);
        }
        int count = 0;
        for (int width = 1; (width < prefix.length); width *= 2) {
            for (int start = 0; (start < prefix.length); start += (2 * width)) {
                int mid = Math.min((start + width), prefix.length);
                int end = Math.min((start + (2 * width)), prefix.length);
                int low = mid;
                int high = mid;
                for (int left = start; (left < mid); left++) {
                    while (((low < end) && ((prefix[low] - prefix[left]) < lower))) {
                        low++;
                    }
                    while (((high < end) && ((prefix[high] - prefix[left]) <= upper))) {
                        high++;
                    }
                    count += (high - low);
                }
                int left = start;
                int right = mid;
                for (int write = start; (write < end); write++) {
                    if (((left < mid) && ((right >= end) || (prefix[left] <= prefix[right])))) {
                        buffer[write] = prefix[left];
                        left++;
                    }
                    else {
                        buffer[write] = prefix[right];
                        right++;
                    }
                }
                for (int write = start; (write < end); write++) {
                    prefix[write] = buffer[write];
                }
            }
        }
        return count;
    }
}

class Solution45 {
    public static Object solve(int[][] intervals) {
        String mode = "merge";
        Arrays.sort(intervals, (x, y) -> Integer.compare(x[0], y[0]));
        List<Object> result = new ArrayList<>();
        if (Objects.equals(intervals.length, 0)) {
            return result;
        }
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int read = 1; (read < intervals.length); read++) {
            if ((intervals[read][0] <= end)) {
                end = Math.max(end, intervals[read][1]);
            }
            else {
                if (Objects.equals(mode, "merge")) {
                    result.add(Arrays.asList(start, end));
                }
                else {
                    result.add(Arrays.asList(end, intervals[read][0]));
                }
                start = intervals[read][0];
                end = intervals[read][1];
            }
        }
        if (Objects.equals(mode, "merge")) {
            result.add(Arrays.asList(start, end));
        }
        return result;
    }
}

class Solution46 {
    public static Object solve(int[][] intervals) {
        String mode = "gaps";
        Arrays.sort(intervals, (x, y) -> Integer.compare(x[0], y[0]));
        List<Object> result = new ArrayList<>();
        if (Objects.equals(intervals.length, 0)) {
            return result;
        }
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int read = 1; (read < intervals.length); read++) {
            if ((intervals[read][0] <= end)) {
                end = Math.max(end, intervals[read][1]);
            }
            else {
                if (Objects.equals(mode, "merge")) {
                    result.add(Arrays.asList(start, end));
                }
                else {
                    result.add(Arrays.asList(end, intervals[read][0]));
                }
                start = intervals[read][0];
                end = intervals[read][1];
            }
        }
        if (Objects.equals(mode, "merge")) {
            result.add(Arrays.asList(start, end));
        }
        return result;
    }
}

class Solution47 {
    public static Object solve(int[][] intervals, int[] newInterval) {
        List<Object> result = new ArrayList<>();
        int read = 0;
        int start = newInterval[0];
        int end = newInterval[1];
        while (((read < intervals.length) && (intervals[read][1] < start))) {
            result.add(intervals[read]);
            read++;
        }
        while (((read < intervals.length) && (intervals[read][0] <= end))) {
            start = Math.min(start, intervals[read][0]);
            end = Math.max(end, intervals[read][1]);
            read++;
        }
        result.add(Arrays.asList(start, end));
        while ((read < intervals.length)) {
            result.add(intervals[read]);
            read++;
        }
        return result;
    }
}

class Solution48 {
    public static Object solve(int[][] intervals, int[][] other) {
        int left = 0;
        int right = 0;
        List<Object> result = new ArrayList<>();
        while (((left < intervals.length) && (right < other.length))) {
            int start = Math.max(intervals[left][0], other[right][0]);
            int end = Math.min(intervals[left][1], other[right][1]);
            if ((start <= end)) {
                result.add(Arrays.asList(start, end));
            }
            if ((intervals[left][1] < other[right][1])) {
                left++;
            }
            else {
                right++;
            }
        }
        return result;
    }
}

class Solution49 {
    public static Object solve(int[][] intervals) {
        Arrays.sort(intervals, (x, y) -> Integer.compare(x[0], y[0]));
        if ((intervals.length < 2)) {
            return false;
        }
        int end = intervals[0][1];
        for (int read = 1; (read < intervals.length); read++) {
            if ((intervals[read][0] <= end)) {
                return true;
            }
            end = Math.max(end, intervals[read][1]);
        }
        return false;
    }
}

class Solution50 {
    public static Object solve(int[][] intervals) {
        String mode = "rooms";
        Arrays.sort(intervals, (x, y) -> Integer.compare(x[0], y[0]));
        int[][] endings = Arrays.copyOfRange(intervals, 0, intervals.length);
        Arrays.sort(endings, (x, y) -> Integer.compare(x[1], y[1]));
        int right = 0;
        int active = 0;
        int best = 0;
        for (int left = 0; (left < intervals.length); left++) {
            while (((right < endings.length) && (endings[right][1] <= intervals[left][0]))) {
                active -= (Objects.equals(mode, "rooms") ? 1 : endings[right][2]);
                right++;
            }
            active += (Objects.equals(mode, "rooms") ? 1 : intervals[left][2]);
            best = Math.max(best, active);
        }
        return best;
    }
}

class Solution51 {
    public static Object solve(int[][] intervals) {
        String mode = "load";
        Arrays.sort(intervals, (x, y) -> Integer.compare(x[0], y[0]));
        int[][] endings = Arrays.copyOfRange(intervals, 0, intervals.length);
        Arrays.sort(endings, (x, y) -> Integer.compare(x[1], y[1]));
        int right = 0;
        int active = 0;
        int best = 0;
        for (int left = 0; (left < intervals.length); left++) {
            while (((right < endings.length) && (endings[right][1] <= intervals[left][0]))) {
                active -= (Objects.equals(mode, "rooms") ? 1 : endings[right][2]);
                right++;
            }
            active += (Objects.equals(mode, "rooms") ? 1 : intervals[left][2]);
            best = Math.max(best, active);
        }
        return best;
    }
}

class Solution52 {
    public static Object solve(int[] a) {
        int k = 2;
        int left = 1;
        int right = a.length;
        String mode = "full";
        int[] next = new int[a.length];
        for (int i = 0; i < a.length; i++) next[i] = i + 1 < a.length ? i + 1 : -1;
        int head = (a.length > 0 ? 0 : (-1));
        int before = (-1);
        int current = head;
        int planned = (Objects.equals(mode, "full") ? a.length : (Objects.equals(mode, "sub") ? ((right - left) + 1) : (Objects.equals(mode, "even") ? 1 : k)));
        if (Objects.equals(mode, "sub")) {
            for (int position = 1; (position < left); position++) {
                before = current;
                current = next[current];
            }
        }
        while (!Objects.equals(current, (-1))) {
            int after = current;
            int count = 0;
            int last = current;
            while ((!Objects.equals(after, (-1)) && (count < planned))) {
                last = after;
                after = next[after];
                count++;
            }
            boolean reverse = (Objects.equals(mode, "even") ? Objects.equals((count % 2), 0) : Objects.equals(count, planned));
            if (reverse) {
                int tail = current;
                int previous = after;
                while (!Objects.equals(current, after)) {
                    int following = next[current];
                    next[current] = previous;
                    previous = current;
                    current = following;
                }
                if (Objects.equals(before, (-1))) {
                    head = previous;
                }
                else {
                    next[before] = previous;
                }
                before = tail;
            }
            else {
                before = last;
                current = after;
            }
            if ((Objects.equals(mode, "sub") || Objects.equals(mode, "full"))) {
                break;
            }
            if (Objects.equals(mode, "even")) {
                planned++;
            }
        }
        List<Object> result = new ArrayList<>();
        for (int node = head; !Objects.equals(node, (-1)); node = next[node]) {
            result.add(a[node]);
        }
        return result;
    }
}

class Solution53 {
    public static Object solve(int[] a, int left, int right) {
        int k = 2;
        String mode = "sub";
        int[] next = new int[a.length];
        for (int i = 0; i < a.length; i++) next[i] = i + 1 < a.length ? i + 1 : -1;
        int head = (a.length > 0 ? 0 : (-1));
        int before = (-1);
        int current = head;
        int planned = (Objects.equals(mode, "full") ? a.length : (Objects.equals(mode, "sub") ? ((right - left) + 1) : (Objects.equals(mode, "even") ? 1 : k)));
        if (Objects.equals(mode, "sub")) {
            for (int position = 1; (position < left); position++) {
                before = current;
                current = next[current];
            }
        }
        while (!Objects.equals(current, (-1))) {
            int after = current;
            int count = 0;
            int last = current;
            while ((!Objects.equals(after, (-1)) && (count < planned))) {
                last = after;
                after = next[after];
                count++;
            }
            boolean reverse = (Objects.equals(mode, "even") ? Objects.equals((count % 2), 0) : Objects.equals(count, planned));
            if (reverse) {
                int tail = current;
                int previous = after;
                while (!Objects.equals(current, after)) {
                    int following = next[current];
                    next[current] = previous;
                    previous = current;
                    current = following;
                }
                if (Objects.equals(before, (-1))) {
                    head = previous;
                }
                else {
                    next[before] = previous;
                }
                before = tail;
            }
            else {
                before = last;
                current = after;
            }
            if ((Objects.equals(mode, "sub") || Objects.equals(mode, "full"))) {
                break;
            }
            if (Objects.equals(mode, "even")) {
                planned++;
            }
        }
        List<Object> result = new ArrayList<>();
        for (int node = head; !Objects.equals(node, (-1)); node = next[node]) {
            result.add(a[node]);
        }
        return result;
    }
}

class Solution54 {
    public static Object solve(int[] a) {
        int k = 2;
        int left = 1;
        int right = a.length;
        String mode = "pairs";
        int[] next = new int[a.length];
        for (int i = 0; i < a.length; i++) next[i] = i + 1 < a.length ? i + 1 : -1;
        int head = (a.length > 0 ? 0 : (-1));
        int before = (-1);
        int current = head;
        int planned = (Objects.equals(mode, "full") ? a.length : (Objects.equals(mode, "sub") ? ((right - left) + 1) : (Objects.equals(mode, "even") ? 1 : k)));
        if (Objects.equals(mode, "sub")) {
            for (int position = 1; (position < left); position++) {
                before = current;
                current = next[current];
            }
        }
        while (!Objects.equals(current, (-1))) {
            int after = current;
            int count = 0;
            int last = current;
            while ((!Objects.equals(after, (-1)) && (count < planned))) {
                last = after;
                after = next[after];
                count++;
            }
            boolean reverse = (Objects.equals(mode, "even") ? Objects.equals((count % 2), 0) : Objects.equals(count, planned));
            if (reverse) {
                int tail = current;
                int previous = after;
                while (!Objects.equals(current, after)) {
                    int following = next[current];
                    next[current] = previous;
                    previous = current;
                    current = following;
                }
                if (Objects.equals(before, (-1))) {
                    head = previous;
                }
                else {
                    next[before] = previous;
                }
                before = tail;
            }
            else {
                before = last;
                current = after;
            }
            if ((Objects.equals(mode, "sub") || Objects.equals(mode, "full"))) {
                break;
            }
            if (Objects.equals(mode, "even")) {
                planned++;
            }
        }
        List<Object> result = new ArrayList<>();
        for (int node = head; !Objects.equals(node, (-1)); node = next[node]) {
            result.add(a[node]);
        }
        return result;
    }
}

class Solution55 {
    public static Object solve(int[] a, int k) {
        int left = 1;
        int right = a.length;
        char mode = 'k';
        int[] next = new int[a.length];
        for (int i = 0; i < a.length; i++) next[i] = i + 1 < a.length ? i + 1 : -1;
        int head = (a.length > 0 ? 0 : (-1));
        int before = (-1);
        int current = head;
        int planned = (Objects.equals(mode, "full") ? a.length : (Objects.equals(mode, "sub") ? ((right - left) + 1) : (Objects.equals(mode, "even") ? 1 : k)));
        if (Objects.equals(mode, "sub")) {
            for (int position = 1; (position < left); position++) {
                before = current;
                current = next[current];
            }
        }
        while (!Objects.equals(current, (-1))) {
            int after = current;
            int count = 0;
            int last = current;
            while ((!Objects.equals(after, (-1)) && (count < planned))) {
                last = after;
                after = next[after];
                count++;
            }
            boolean reverse = (Objects.equals(mode, "even") ? Objects.equals((count % 2), 0) : Objects.equals(count, planned));
            if (reverse) {
                int tail = current;
                int previous = after;
                while (!Objects.equals(current, after)) {
                    int following = next[current];
                    next[current] = previous;
                    previous = current;
                    current = following;
                }
                if (Objects.equals(before, (-1))) {
                    head = previous;
                }
                else {
                    next[before] = previous;
                }
                before = tail;
            }
            else {
                before = last;
                current = after;
            }
            if ((Objects.equals(mode, "sub") || Objects.equals(mode, "full"))) {
                break;
            }
            if (Objects.equals(mode, "even")) {
                planned++;
            }
        }
        List<Object> result = new ArrayList<>();
        for (int node = head; !Objects.equals(node, (-1)); node = next[node]) {
            result.add(a[node]);
        }
        return result;
    }
}

class Solution56 {
    public static Object solve(int[] a) {
        int k = 2;
        int left = 1;
        int right = a.length;
        String mode = "even";
        int[] next = new int[a.length];
        for (int i = 0; i < a.length; i++) next[i] = i + 1 < a.length ? i + 1 : -1;
        int head = (a.length > 0 ? 0 : (-1));
        int before = (-1);
        int current = head;
        int planned = (Objects.equals(mode, "full") ? a.length : (Objects.equals(mode, "sub") ? ((right - left) + 1) : (Objects.equals(mode, "even") ? 1 : k)));
        if (Objects.equals(mode, "sub")) {
            for (int position = 1; (position < left); position++) {
                before = current;
                current = next[current];
            }
        }
        while (!Objects.equals(current, (-1))) {
            int after = current;
            int count = 0;
            int last = current;
            while ((!Objects.equals(after, (-1)) && (count < planned))) {
                last = after;
                after = next[after];
                count++;
            }
            boolean reverse = (Objects.equals(mode, "even") ? Objects.equals((count % 2), 0) : Objects.equals(count, planned));
            if (reverse) {
                int tail = current;
                int previous = after;
                while (!Objects.equals(current, after)) {
                    int following = next[current];
                    next[current] = previous;
                    previous = current;
                    current = following;
                }
                if (Objects.equals(before, (-1))) {
                    head = previous;
                }
                else {
                    next[before] = previous;
                }
                before = tail;
            }
            else {
                before = last;
                current = after;
            }
            if ((Objects.equals(mode, "sub") || Objects.equals(mode, "full"))) {
                break;
            }
            if (Objects.equals(mode, "even")) {
                planned++;
            }
        }
        List<Object> result = new ArrayList<>();
        for (int node = head; !Objects.equals(node, (-1)); node = next[node]) {
            result.add(a[node]);
        }
        return result;
    }
}

class Solution57 {
    public static Object solve(int[] a, int k) {
        int[] next = new int[a.length];
        for (int i = 0; i < a.length; i++) next[i] = i + 1 < a.length ? i + 1 : -1;
        int head = (a.length > 0 ? 0 : (-1));
        if ((a.length < 2)) {
            return a;
        }
        int tail = head;
        int length = 1;
        while (!Objects.equals(next[tail], (-1))) {
            tail = next[tail];
            length++;
        }
        int shift = (k % length);
        if (Objects.equals(shift, 0)) {
            return a;
        }
        next[tail] = head;
        int newTail = head;
        for (int step = 0; (step < ((length - shift) - 1)); step++) {
            newTail = next[newTail];
        }
        head = next[newTail];
        next[newTail] = (-1);
        List<Object> result = new ArrayList<>();
        for (int node = head; !Objects.equals(node, (-1)); node = next[node]) {
            result.add(a[node]);
        }
        return result;
    }
}

class Solution58 {
    public static Object solve(String s) {
        int k = 2;
        char[] a = s.toCharArray();
        char[] stack = Arrays.copyOfRange(a, 0, a.length);
        int[] counts = new int[s.length()];
        int size = 0;
        for (int read = 0; (read < a.length); read++) {
            int count = (((size > 0) && Objects.equals(stack[(size - 1)], a[read])) ? (counts[(size - 1)] + 1) : 1);
            stack[size] = a[read];
            counts[size] = count;
            size++;
            if (Objects.equals(count, k)) {
                size -= k;
            }
        }
        List<Object> result = new ArrayList<>();
        for (int i = 0; (i < size); i++) {
            result.add(stack[i]);
        }
        return result.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(""));
    }
}

class Solution59 {
    public static Object solve(String s, int k) {
        char[] a = s.toCharArray();
        char[] stack = Arrays.copyOfRange(a, 0, a.length);
        int[] counts = new int[s.length()];
        int size = 0;
        for (int read = 0; (read < a.length); read++) {
            int count = (((size > 0) && Objects.equals(stack[(size - 1)], a[read])) ? (counts[(size - 1)] + 1) : 1);
            stack[size] = a[read];
            counts[size] = count;
            size++;
            if (Objects.equals(count, k)) {
                size -= k;
            }
        }
        List<Object> result = new ArrayList<>();
        for (int i = 0; (i < size); i++) {
            result.add(stack[i]);
        }
        return result.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(""));
    }
}

class Solution60 {
    public static Object solve(String s) {
        char[] a = s.toCharArray();
        char[] stack = Arrays.copyOfRange(a, 0, a.length);
        int size = 0;
        for (int read = 0; (read < a.length); read++) {
            char ch = a[read];
            if (((Objects.equals(ch, '(') || Objects.equals(ch, '[')) || Objects.equals(ch, '{'))) {
                stack[size] = (Objects.equals(ch, '(') ? ')' : (Objects.equals(ch, '[') ? ']' : '}'));
                size++;
            }
            else {
                if ((Objects.equals(size, 0) || !Objects.equals(stack[(size - 1)], ch))) {
                    return false;
                }
                size--;
            }
        }
        return Objects.equals(size, 0);
    }
}

class Solution61 {
    public static Object solve(String s) {
        char[] a = s.toCharArray();
        char[] stack = Arrays.copyOfRange(a, 0, a.length);
        int size = 0;
        List<Object> result = new ArrayList<>();
        for (int read = 0; (read < a.length); read++) {
            stack[size] = a[read];
            size++;
        }
        while ((size > 0)) {
            size--;
            result.add(stack[size]);
        }
        return result.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(""));
    }
}

class Solution62 {
    public static Object solve(int[] a) {
        String mode = "greater";
        int[] stack = new int[a.length];
        int[] answer = new int[a.length];
        int size = 0;
        for (int i = 0; (i < a.length); i++) {
            answer[i] = (Objects.equals(mode, "greater") ? (-1) : 0);
        }
        int limit = (Objects.equals(mode, "greater") ? (2 * a.length) : a.length);
        for (int step = 0; (step < limit); step++) {
            int read = (step % a.length);
            while (((size > 0) && (a[stack[(size - 1)]] < a[read]))) {
                size--;
                int waiting = stack[size];
                answer[waiting] = (Objects.equals(mode, "greater") ? a[read] : (read - waiting));
            }
            if ((step < a.length)) {
                stack[size] = read;
                size++;
            }
        }
        return answer;
    }
}

class Solution63 {
    public static Object solve(int[] a) {
        String mode = "days";
        int[] stack = new int[a.length];
        int[] answer = new int[a.length];
        int size = 0;
        for (int i = 0; (i < a.length); i++) {
            answer[i] = (Objects.equals(mode, "greater") ? (-1) : 0);
        }
        int limit = (Objects.equals(mode, "greater") ? (2 * a.length) : a.length);
        for (int step = 0; (step < limit); step++) {
            int read = (step % a.length);
            while (((size > 0) && (a[stack[(size - 1)]] < a[read]))) {
                size--;
                int waiting = stack[size];
                answer[waiting] = (Objects.equals(mode, "greater") ? a[read] : (read - waiting));
            }
            if ((step < a.length)) {
                stack[size] = read;
                size++;
            }
        }
        return answer;
    }
}

class Solution64 {
    public static Object solve(int[] a) {
        int[] next = new int[a.length];
        for (int i = 0; i < a.length; i++) next[i] = i + 1 < a.length ? i + 1 : -1;
        int[] stack = new int[a.length];
        int size = 0;
        int head = (a.length > 0 ? 0 : (-1));
        for (int current = head; !Objects.equals(current, (-1)); current = next[current]) {
            while (((size > 0) && (a[stack[(size - 1)]] < a[current]))) {
                size--;
            }
            stack[size] = current;
            size++;
        }
        for (int i = 0; (i < size); i++) {
            next[stack[i]] = (((i + 1) < size) ? stack[(i + 1)] : (-1));
        }
        head = ((size > 0) ? stack[0] : (-1));
        List<Object> result = new ArrayList<>();
        for (int node = head; !Objects.equals(node, (-1)); node = next[node]) {
            result.add(a[node]);
        }
        return result;
    }
}

class Solution65 {
    public static Object solve(String s) {
        String[] a = s.split("/", -1);
        String[] stack = Arrays.copyOfRange(a, 0, a.length);
        int size = 0;
        for (int read = 0; (read < a.length); read++) {
            String part = a[read];
            if ((Objects.equals(part, "") || Objects.equals(part, "."))) {
                continue;
            }
            if (Objects.equals(part, "..")) {
                if ((size > 0)) {
                    size--;
                }
            }
            else {
                stack[size] = part;
                size++;
            }
        }
        List<Object> result = new ArrayList<>();
        for (int i = 0; (i < size); i++) {
            result.add(stack[i]);
        }
        return ('/' + result.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining("/")));
    }
}

class Solution66 {
    public static Object solve(String s, int k) {
        char[] a = s.toCharArray();
        char[] stack = Arrays.copyOfRange(a, 0, a.length);
        int size = 0;
        int remaining = k;
        for (int read = 0; (read < a.length); read++) {
            while ((((remaining > 0) && (size > 0)) && (stack[(size - 1)] > a[read]))) {
                size--;
                remaining--;
            }
            stack[size] = a[read];
            size++;
        }
        while ((remaining > 0)) {
            size--;
            remaining--;
        }
        int start = 0;
        List<Object> result = new ArrayList<>();
        while (((start < size) && Objects.equals(stack[start], '0'))) {
            start++;
        }
        for (int i = start; (i < size); i++) {
            result.add(stack[i]);
        }
        return (Objects.equals(result.size(), 0) ? '0' : result.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining("")));
    }
}

class Solution67 {
    public static Object solve(String s) {
        char[] a = s.toCharArray();
        Map<Object, Integer> freq = new HashMap<>();
        for (int read = 0; (read < a.length); read++) {
            freq.put(a[read], (freq.getOrDefault(a[read], 0) + 1));
        }
        for (int read = 0; (read < a.length); read++) {
            if (Objects.equals(freq.getOrDefault(a[read], 0), 1)) {
                return read;
            }
        }
        return (-1);
    }
}

class Solution68 {
    public static Object solve(String s) {
        char[] a = s.toCharArray();
        Map<Object, Integer> freq = new HashMap<>();
        for (int read = 0; (read < a.length); read++) {
            freq.put(a[read], (freq.getOrDefault(a[read], 0) + 1));
        }
        String letters = "balon";
        int best = s.length();
        for (int read = 0; (read < letters.length()); read++) {
            char letter = letters.charAt(read);
            int required = ((Objects.equals(letter, 'l') || Objects.equals(letter, 'o')) ? 2 : 1);
            int available = freq.getOrDefault(letter, 0);
            int copies = ((int) Math.floor((available / required)));
            best = Math.min(best, copies);
        }
        return best;
    }
}

class Solution69 {
    public static Object solve(String s) {
        char[] a = s.toCharArray();
        Map<Object, Integer> freq = new HashMap<>();
        int paired = 0;
        for (int read = 0; (read < a.length); read++) {
            int count = (freq.getOrDefault(a[read], 0) + 1);
            freq.put(a[read], count);
            if (Objects.equals((count % 2), 0)) {
                paired += 2;
            }
        }
        int center = ((paired < s.length()) ? 1 : 0);
        return (paired + center);
    }
}

class Solution70 {
    public static Object solve(String s, String t) {
        char[] a = s.toCharArray();
        char[] magazine = t.toCharArray();
        Map<Object, Integer> freq = new HashMap<>();
        for (int read = 0; (read < magazine.length); read++) {
            freq.put(magazine[read], (freq.getOrDefault(magazine[read], 0) + 1));
        }
        for (int read = 0; (read < a.length); read++) {
            int available = freq.getOrDefault(a[read], 0);
            if (Objects.equals(available, 0)) {
                return false;
            }
            freq.put(a[read], (available - 1));
        }
        return true;
    }
}

class Solution71 {
    public static Object solve(int[] a, int target) {
        int left = 0;
        int right = (a.length - 1);
        while ((left <= right)) {
            int mid = (left + ((int) Math.floor(((right - left) / 2))));
            if (Objects.equals(a[mid], target)) {
                return mid;
            }
            if ((a[mid] < target)) {
                left = (mid + 1);
            }
            else {
                right = (mid - 1);
            }
        }
        return (-1);
    }
}

class Solution72 {
    public static Object solve(int[] a, int target, String bound) {
        int left = 0;
        int right = a.length;
        while ((left < right)) {
            int mid = (left + ((int) Math.floor(((right - left) / 2))));
            boolean qualifies = (Objects.equals(bound, "upper") ? (a[mid] > target) : (a[mid] >= target));
            if (qualifies) {
                right = mid;
            }
            else {
                left = (mid + 1);
            }
        }
        return ((left < a.length) ? left : (-1));
    }
}

class Solution73 {
    public static Object solve(int[] a, int target) {
        String mode = "positions";
        int first = 0;
        int past = 0;
        for (int phase = 0; (phase < 2); phase++) {
            int left = 0;
            int right = a.length;
            while ((left < right)) {
                int mid = (left + ((int) Math.floor(((right - left) / 2))));
                if (((a[mid] < target) || (Objects.equals(phase, 1) && Objects.equals(a[mid], target)))) {
                    left = (mid + 1);
                }
                else {
                    right = mid;
                }
            }
            if (Objects.equals(phase, 0)) {
                first = left;
            }
            else {
                past = left;
            }
        }
        if (Objects.equals(mode, "count")) {
            return (past - first);
        }
        return (Objects.equals(first, past) ? Arrays.asList((-1), (-1)) : Arrays.asList(first, (past - 1)));
    }
}

class Solution74 {
    public static Object solve(int[] a, int target) {
        String mode = "count";
        int first = 0;
        int past = 0;
        for (int phase = 0; (phase < 2); phase++) {
            int left = 0;
            int right = a.length;
            while ((left < right)) {
                int mid = (left + ((int) Math.floor(((right - left) / 2))));
                if (((a[mid] < target) || (Objects.equals(phase, 1) && Objects.equals(a[mid], target)))) {
                    left = (mid + 1);
                }
                else {
                    right = mid;
                }
            }
            if (Objects.equals(phase, 0)) {
                first = left;
            }
            else {
                past = left;
            }
        }
        if (Objects.equals(mode, "count")) {
            return (past - first);
        }
        return (Objects.equals(first, past) ? Arrays.asList((-1), (-1)) : Arrays.asList(first, (past - 1)));
    }
}

class Solution75 {
    public static Object solve(int[] a, int target) {
        int left = 0;
        int right = 1;
        while (((right < a.length) && (a[right] < target))) {
            left = (right + 1);
            right *= 2;
        }
        while ((left <= right)) {
            int mid = (left + ((int) Math.floor(((right - left) / 2))));
            int value = ((mid < a.length) ? a[mid] : Integer.MAX_VALUE);
            if (Objects.equals(value, target)) {
                return mid;
            }
            if ((value < target)) {
                left = (mid + 1);
            }
            else {
                right = (mid - 1);
            }
        }
        return (-1);
    }
}

class Solution76 {
    public static Object solve(int[] a) {
        int left = 0;
        int right = (a.length - 1);
        while ((left < right)) {
            int mid = (left + ((int) Math.floor(((right - left) / 2))));
            boolean rising = (a[mid] < a[(mid + 1)]);
            if (rising) {
                left = (mid + 1);
            }
            else {
                right = mid;
            }
        }
        return left;
    }
}

class Solution77 {
    public static Object solve(int[] a) {
        int left = 0;
        int right = (a.length - 1);
        while ((left < right)) {
            int mid = (left + ((int) Math.floor(((right - left) / 2))));
            boolean rising = (a[mid] < a[(mid + 1)]);
            if (rising) {
                left = (mid + 1);
            }
            else {
                right = mid;
            }
        }
        return left;
    }
}

class Solution78 {
    public static Object solve(int[] a) {
        String mode = "value";
        int left = 0;
        int right = (a.length - 1);
        while ((left < right)) {
            int mid = (left + ((int) Math.floor(((right - left) / 2))));
            if ((a[mid] > a[right])) {
                left = (mid + 1);
            }
            else {
                right = mid;
            }
        }
        return (Objects.equals(mode, "value") ? a[left] : left);
    }
}

class Solution79 {
    public static Object solve(int[] a) {
        String mode = "index";
        int left = 0;
        int right = (a.length - 1);
        while ((left < right)) {
            int mid = (left + ((int) Math.floor(((right - left) / 2))));
            if ((a[mid] > a[right])) {
                left = (mid + 1);
            }
            else {
                right = mid;
            }
        }
        return (Objects.equals(mode, "value") ? a[left] : left);
    }
}

class Solution80 {
    public static Object solve(int[] a, int target) {
        int left = 0;
        int right = (a.length - 1);
        while ((left <= right)) {
            int mid = (left + ((int) Math.floor(((right - left) / 2))));
            if (Objects.equals(a[mid], target)) {
                return mid;
            }
            if ((a[left] <= a[mid])) {
                if (((a[left] <= target) && (target < a[mid]))) {
                    right = (mid - 1);
                }
                else {
                    left = (mid + 1);
                }
            }
            else {
                if (((a[mid] < target) && (target <= a[right]))) {
                    left = (mid + 1);
                }
                else {
                    right = (mid - 1);
                }
            }
        }
        return (-1);
    }
}

class Solution81 {
    public static Object solve(int[] a, int h) {
        if ((h < a.length)) {
            return (-1);
        }
        int low = 1;
        int high = 1;
        for (int i = 0; (i < a.length); i++) {
            high = Math.max(high, a[i]);
        }
        while ((low < high)) {
            int mid = (low + ((int) Math.floor(((high - low) / 2))));
            int hours = 0;
            for (int read = 0; (read < a.length); read++) {
                hours += ((int) Math.floor((((a[read] + mid) - 1) / mid)));
            }
            boolean feasible = (hours <= h);
            if (feasible) {
                high = mid;
            }
            else {
                low = (mid + 1);
            }
        }
        return low;
    }
}

class Solution82 {
    public static Object solve(int[] a, int m, int k) {
        if ((m > ((int) Math.floor((a.length / k))))) {
            return (-1);
        }
        int low = 1;
        int high = 1;
        for (int i = 0; (i < a.length); i++) {
            high = Math.max(high, a[i]);
        }
        while ((low < high)) {
            int mid = (low + ((int) Math.floor(((high - low) / 2))));
            int run = 0;
            int made = 0;
            for (int read = 0; (read < a.length); read++) {
                if ((a[read] <= mid)) {
                    run++;
                }
                else {
                    run = 0;
                }
                if (Objects.equals(run, k)) {
                    made++;
                    run = 0;
                }
            }
            boolean feasible = (made >= m);
            if (feasible) {
                high = mid;
            }
            else {
                low = (mid + 1);
            }
        }
        return low;
    }
}

class Solution83 {
    public static Object solve(int[] a, int k) {
        Arrays.sort(a);
        int low = 0;
        int high = (a[(a.length - 1)] - a[0]);
        while ((low < high)) {
            int mid = (low + ((int) Math.floor((((high - low) + 1) / 2))));
            int placed = 1;
            int previous = 0;
            for (int read = 1; (read < a.length); read++) {
                if (((a[read] - a[previous]) >= mid)) {
                    placed++;
                    previous = read;
                }
            }
            boolean feasible = (placed >= k);
            if (feasible) {
                low = mid;
            }
            else {
                high = (mid - 1);
            }
        }
        return low;
    }
}

class Solution84 {
    public static Object solve(int[] a) {
        int left = 0;
        int right = a.length;
        while ((left < right)) {
            int mid = (left + ((int) Math.floor(((right - left) / 2))));
            int papers = (a.length - mid);
            if ((a[mid] >= papers)) {
                right = mid;
            }
            else {
                left = (mid + 1);
            }
        }
        return (a.length - left);
    }
}

class Solution85 {
    public static Object solve(int[] a, int k) {
        int low = 0;
        int high = 0;
        for (int i = 0; (i < a.length); i++) {
            high = Math.max(high, a[i]);
        }
        while ((low < high)) {
            int mid = (low + ((int) Math.floor((((high - low) + 1) / 2))));
            int portions = 0;
            for (int read = 0; (read < a.length); read++) {
                portions += ((int) Math.floor((a[read] / mid)));
            }
            boolean feasible = (portions >= k);
            if (feasible) {
                low = mid;
            }
            else {
                high = (mid - 1);
            }
        }
        return low;
    }
}

class Solution86 {
    public static Object solve(int[] a, int k) {
        String mode = "ship";
        if ((!Objects.equals(mode, "ship") && (k > a.length))) {
            return (-1);
        }
        int low = 0;
        int high = 0;
        for (int i = 0; (i < a.length); i++) {
            low = Math.max(low, a[i]);
            high += a[i];
        }
        while ((low < high)) {
            int mid = (low + ((int) Math.floor(((high - low) / 2))));
            int groupsUsed = 1;
            int load = 0;
            for (int read = 0; (read < a.length); read++) {
                if (((load + a[read]) > mid)) {
                    groupsUsed++;
                    load = 0;
                }
                load += a[read];
            }
            boolean feasible = (groupsUsed <= k);
            if (feasible) {
                high = mid;
            }
            else {
                low = (mid + 1);
            }
        }
        return low;
    }
}

class Solution87 {
    public static Object solve(int[] a, int k) {
        String mode = "books";
        if ((!Objects.equals(mode, "ship") && (k > a.length))) {
            return (-1);
        }
        int low = 0;
        int high = 0;
        for (int i = 0; (i < a.length); i++) {
            low = Math.max(low, a[i]);
            high += a[i];
        }
        while ((low < high)) {
            int mid = (low + ((int) Math.floor(((high - low) / 2))));
            int groupsUsed = 1;
            int load = 0;
            for (int read = 0; (read < a.length); read++) {
                if (((load + a[read]) > mid)) {
                    groupsUsed++;
                    load = 0;
                }
                load += a[read];
            }
            boolean feasible = (groupsUsed <= k);
            if (feasible) {
                high = mid;
            }
            else {
                low = (mid + 1);
            }
        }
        return low;
    }
}

class Solution88 {
    public static Object solve(int[] a, int k) {
        String mode = "split";
        if ((!Objects.equals(mode, "ship") && (k > a.length))) {
            return (-1);
        }
        int low = 0;
        int high = 0;
        for (int i = 0; (i < a.length); i++) {
            low = Math.max(low, a[i]);
            high += a[i];
        }
        while ((low < high)) {
            int mid = (low + ((int) Math.floor(((high - low) / 2))));
            int groupsUsed = 1;
            int load = 0;
            for (int read = 0; (read < a.length); read++) {
                if (((load + a[read]) > mid)) {
                    groupsUsed++;
                    load = 0;
                }
                load += a[read];
            }
            boolean feasible = (groupsUsed <= k);
            if (feasible) {
                high = mid;
            }
            else {
                low = (mid + 1);
            }
        }
        return low;
    }
}

class Solution89 {
    public static Object solve(int[][] matrix, int target) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int left = 0;
        int right = ((rows * columns) - 1);
        while ((left <= right)) {
            int mid = (left + ((int) Math.floor(((right - left) / 2))));
            int row = ((int) Math.floor((mid / columns)));
            int column = (mid % columns);
            int value = matrix[row][column];
            if (Objects.equals(value, target)) {
                return true;
            }
            if ((value < target)) {
                left = (mid + 1);
            }
            else {
                right = (mid - 1);
            }
        }
        return false;
    }
}

class Solution90 {
    public static Object solve(int[][] matrix, int target) {
        int row = 0;
        int column = (matrix[0].length - 1);
        while (((row < matrix.length) && (column >= 0))) {
            int value = matrix[row][column];
            if (Objects.equals(value, target)) {
                return true;
            }
            if ((value > target)) {
                column--;
            }
            else {
                row++;
            }
        }
        return false;
    }
}

class Solution91 {
    public static Object solve(int[][] matrix, int k) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int low = matrix[0][0];
        int high = matrix[(rows - 1)][(columns - 1)];
        while ((low < high)) {
            int mid = (low + ((int) Math.floor(((high - low) / 2))));
            int row = (rows - 1);
            int column = 0;
            int count = 0;
            while (((row >= 0) && (column < columns))) {
                if ((matrix[row][column] <= mid)) {
                    count += (row + 1);
                    column++;
                }
                else {
                    row--;
                }
            }
            boolean feasible = (count >= k);
            if (feasible) {
                high = mid;
            }
            else {
                low = (mid + 1);
            }
        }
        return low;
    }
}

class Solution92 {
    public static Object solve(int m, int n, int k) {
        int low = 1;
        int high = (m * n);
        while ((low < high)) {
            int mid = (low + ((int) Math.floor(((high - low) / 2))));
            int count = 0;
            for (int row = 1; (row <= m); row++) {
                int contributed = Math.min(n, ((int) Math.floor((mid / row))));
                count += contributed;
            }
            boolean feasible = (count >= k);
            if (feasible) {
                high = mid;
            }
            else {
                low = (mid + 1);
            }
        }
        return low;
    }
}

class Solution93 {
    public static Object solve(int[] a, int[] b) {
        if ((a.length > b.length)) {
            int[] swap = a;
            a = b;
            b = swap;
        }
        int half = ((int) Math.floor((((a.length + b.length) + 1) / 2)));
        int left = 0;
        int right = a.length;
        while ((left <= right)) {
            int cutA = (left + ((int) Math.floor(((right - left) / 2))));
            int cutB = (half - cutA);
            int aLeft = (Objects.equals(cutA, 0) ? (-Integer.MAX_VALUE) : a[(cutA - 1)]);
            int aRight = (Objects.equals(cutA, a.length) ? Integer.MAX_VALUE : a[cutA]);
            int bLeft = (Objects.equals(cutB, 0) ? (-Integer.MAX_VALUE) : b[(cutB - 1)]);
            int bRight = (Objects.equals(cutB, b.length) ? Integer.MAX_VALUE : b[cutB]);
            if (((aLeft <= bRight) && (bLeft <= aRight))) {
                if (Objects.equals(((a.length + b.length) % 2), 1)) {
                    return Math.max(aLeft, bLeft);
                }
                return ((Math.max(aLeft, bLeft) + Math.min(aRight, bRight)) * 0.5);
            }
            if ((aLeft > bRight)) {
                right = (cutA - 1);
            }
            else {
                left = (cutA + 1);
            }
        }
        return 0;
    }
}

class Solution94 {
    public static Object solve(int[] a, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        int sign = (Objects.equals("smallest", "smallest") ? (-1) : 1);
        for (int i = 0; (i < a.length); i++) {
            heap.add(new int[]{(sign * a[i])});
            if ((heap.size() > k)) {
                heap.poll();
            }
        }
        return (sign * heap.peek()[0]);
    }
}

class Solution95 {
    public static Object solve(int[] a, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        int sign = (Objects.equals("largest", "smallest") ? (-1) : 1);
        for (int i = 0; (i < a.length); i++) {
            heap.add(new int[]{(sign * a[i])});
            if ((heap.size() > k)) {
                heap.poll();
            }
        }
        return (sign * heap.peek()[0]);
    }
}

class Solution96 {
    public static Object solve(int[] a, int k) {
        Map<Object, Integer> freq = new HashMap<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        List<Object> result = new ArrayList<>();
        for (int i = 0; (i < a.length); i++) {
            freq.put(a[i], (freq.getOrDefault(a[i], 0) + 1));
        }
        Map<Object, Integer> seen = new HashMap<>();
        for (int i = 0; (i < a.length); i++) {
            if ((!seen.containsKey(a[i]))) {
                heap.add(new int[]{(-freq.getOrDefault(a[i], 0)), a[i]});
                seen.put(a[i], 1);
            }
        }
        while ((result.size() < k)) {
            int[] entry = heap.poll();
            result.add(entry[1]);
        }
        return result;
    }
}

class Solution97 {
    public static Object solve(String[] words, int k) {
        Arrays.sort(words);
        Map<Object, Integer> freq = new HashMap<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        List<Object> result = new ArrayList<>();
        for (int i = 0; (i < words.length); i++) {
            freq.put(words[i], (freq.getOrDefault(words[i], 0) + 1));
        }
        for (int i = 0; (i < words.length); i++) {
            if ((Objects.equals(i, 0) || !Objects.equals(words[i], words[(i - 1)]))) {
                heap.add(new int[]{(-freq.getOrDefault(words[i], 0)), i});
            }
        }
        while ((result.size() < k)) {
            int[] entry = heap.poll();
            result.add(words[entry[1]]);
        }
        return result;
    }
}

class Solution98 {
    public static Object solve(int[][] points, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        List<Object> result = new ArrayList<>();
        for (int i = 0; (i < points.length); i++) {
            int distance = ((points[i][0] * points[i][0]) + (points[i][1] * points[i][1]));
            heap.add(new int[]{distance, i});
        }
        while ((result.size() < k)) {
            int[] entry = heap.poll();
            result.add(points[entry[1]]);
        }
        return result;
    }
}

class Solution99 {
    public static Object solve(int[] a, int k, int target) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        PriorityQueue<int[]> selected = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        List<Object> result = new ArrayList<>();
        for (int i = 0; (i < a.length); i++) {
            heap.add(new int[]{Math.abs((a[i] - target)), a[i]});
        }
        for (int i = 0; (i < k); i++) {
            int[] entry = heap.poll();
            selected.add(new int[]{entry[1]});
        }
        while ((selected.size() > 0)) {
            result.add(selected.poll()[0]);
        }
        return result;
    }
}

class Solution100 {
    public static Object solve(int[][] matrix, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        List<Object> result = new ArrayList<>();
        for (int r = 0; (r < matrix.length); r++) {
            int soldiers = 0;
            for (int c = 0; (c < matrix[r].length); c++) {
                soldiers += matrix[r][c];
            }
            heap.add(new int[]{soldiers, r});
        }
        while ((result.size() < k)) {
            result.add(heap.poll()[1]);
        }
        return result;
    }
}

class Solution101 {
    public static Object solve(int[][] matrix, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        List<Object> result = new ArrayList<>();
        for (int r = 0; (r < matrix.length); r++) {
            if ((matrix[r].length > 0)) {
                heap.add(new int[]{matrix[r][0], r, 0});
            }
        }
        while ((heap.size() > 0)) {
            int[] entry = heap.poll();
            int value = entry[0];
            int r = entry[1];
            int c = entry[2];
            result.add(value);
            if ((Objects.equals("merge", "kth") && Objects.equals(result.size(), k))) {
                return value;
            }
            if (((c + 1) < matrix[r].length)) {
                heap.add(new int[]{matrix[r][(c + 1)], r, (c + 1)});
            }
        }
        return result;
    }
}

class Solution102 {
    public static Object solve(int[][] matrix, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        List<Object> result = new ArrayList<>();
        for (int r = 0; (r < matrix.length); r++) {
            if ((matrix[r].length > 0)) {
                heap.add(new int[]{matrix[r][0], r, 0});
            }
        }
        while ((heap.size() > 0)) {
            int[] entry = heap.poll();
            int value = entry[0];
            int r = entry[1];
            int c = entry[2];
            result.add(value);
            if ((Objects.equals("kth", "kth") && Objects.equals(result.size(), k))) {
                return value;
            }
            if (((c + 1) < matrix[r].length)) {
                heap.add(new int[]{matrix[r][(c + 1)], r, (c + 1)});
            }
        }
        return result;
    }
}

class Solution103 {
    public static Object solve(int[] a) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        for (int i = 0; (i < a.length); i++) {
            heap.add(new int[]{(-a[i])});
        }
        while ((heap.size() > 1)) {
            int first = (-heap.poll()[0]);
            int second = (-heap.poll()[0]);
            int remaining = (first - second);
            if ((remaining > 0)) {
                heap.add(new int[]{(-remaining)});
            }
        }
        return (Objects.equals(heap.size(), 0) ? 0 : (-heap.peek()[0]));
    }
}

class Solution104 {
    public static Object solve(String s, int k) {
        Map<Object, Integer> freq = new HashMap<>();
        Map<Object, Integer> seen = new HashMap<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        PriorityQueue<int[]> cooling = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        List<Object> result = new ArrayList<>();
        for (int i = 0; (i < s.length()); i++) {
            freq.put(s.charAt(i), (freq.getOrDefault(s.charAt(i), 0) + 1));
        }
        for (int i = 0; (i < s.length()); i++) {
            if ((!seen.containsKey(s.charAt(i)))) {
                heap.add(new int[]{(-freq.getOrDefault(s.charAt(i), 0)), i});
                seen.put(s.charAt(i), 1);
            }
        }
        int time = 0;
        while (((heap.size() > 0) || (cooling.size() > 0))) {
            while (((cooling.size() > 0) && (cooling.peek()[0] <= time))) {
                int[] ready = cooling.poll();
                heap.add(new int[]{ready[1], ready[2]});
            }
            if ((heap.size() > 0)) {
                int[] entry = heap.poll();
                int remaining = ((-entry[0]) - 1);
                result.add(s.charAt(entry[1]));
                if ((remaining > 0)) {
                    cooling.add(new int[]{((time + k) + 1), (-remaining), entry[1]});
                }
            }
            else {
                result.add("idle");
            }
            time++;
        }
        return time;
    }
}

class Solution105 {
    public static Object solve(String s) {
        Map<Object, Integer> freq = new HashMap<>();
        Map<Object, Integer> seen = new HashMap<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        String result = "";
        int heldCount = 0;
        int heldIndex = (-1);
        for (int i = 0; (i < s.length()); i++) {
            freq.put(s.charAt(i), (freq.getOrDefault(s.charAt(i), 0) + 1));
        }
        for (int i = 0; (i < s.length()); i++) {
            if ((!seen.containsKey(s.charAt(i)))) {
                heap.add(new int[]{(-freq.getOrDefault(s.charAt(i), 0)), i});
                seen.put(s.charAt(i), 1);
            }
        }
        while ((heap.size() > 0)) {
            int[] entry = heap.poll();
            result += s.charAt(entry[1]);
            if ((heldCount > 0)) {
                heap.add(new int[]{(-heldCount), heldIndex});
            }
            heldCount = ((-entry[0]) - 1);
            heldIndex = entry[1];
        }
        return (Objects.equals(heldCount, 0) ? result : "");
    }
}

class Solution106 {
    public static Object solve(int[][] stations, int target, int fuel) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        int reach = fuel;
        int stops = 0;
        int i = 0;
        while ((reach < target)) {
            while (((i < stations.length) && (stations[i][0] <= reach))) {
                heap.add(new int[]{(-stations[i][1]), i});
                i++;
            }
            if (Objects.equals(heap.size(), 0)) {
                return (-1);
            }
            reach -= heap.poll()[0];
            stops++;
        }
        return stops;
    }
}

class Solution107 {
    public static Object solve(int[][] projects, int k, int capital) {
        Arrays.sort(projects, (x, y) -> Integer.compare(x[0], y[0]));
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        int i = 0;
        for (int chosen = 0; (chosen < k); chosen++) {
            while (((i < projects.length) && (projects[i][0] <= capital))) {
                heap.add(new int[]{(-projects[i][1]), i});
                i++;
            }
            if (Objects.equals(heap.size(), 0)) {
                return capital;
            }
            capital -= heap.poll()[0];
        }
        return capital;
    }
}

class Solution108 {
    public static Object solve(int[][] courses) {
        Arrays.sort(courses, (x, y) -> Integer.compare(x[1], y[1]));
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        int time = 0;
        for (int i = 0; (i < courses.length); i++) {
            time += courses[i][0];
            heap.add(new int[]{(-courses[i][0]), i});
            if ((time > courses[i][1])) {
                time += heap.poll()[0];
            }
        }
        return heap.size();
    }
}

class Solution109 {
    public static Object solve(int[] a, int k) {
        List<Object> result = new ArrayList<>();
        PriorityQueue<int[]> lower = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        PriorityQueue<int[]> upper = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        for (int right = 0; (right < a.length); right++) {
            int left = (Objects.equals("stream", "window") ? Math.max(0, ((right - k) + 1)) : 0);
            if (Objects.equals("stream", "window")) {
                while ((lower.size() > 0)) {
                    lower.poll();
                }
                while ((upper.size() > 0)) {
                    upper.poll();
                }
            }
            int start = (Objects.equals("stream", "window") ? left : right);
            for (int i = start; (i <= right); i++) {
                if ((Objects.equals(lower.size(), 0) || (a[i] <= (-lower.peek()[0])))) {
                    lower.add(new int[]{(-a[i])});
                }
                else {
                    upper.add(new int[]{a[i]});
                }
                if ((lower.size() > (upper.size() + 1))) {
                    upper.add(new int[]{(-lower.poll()[0])});
                }
                if ((upper.size() > lower.size())) {
                    lower.add(new int[]{(-upper.poll()[0])});
                }
            }
            if ((Objects.equals("stream", "stream") || Objects.equals(((right - left) + 1), k))) {
                double median = (Objects.equals(lower.size(), upper.size()) ? (((-lower.peek()[0]) + upper.peek()[0]) * 0.5) : (-lower.peek()[0]));
                result.add(median);
            }
        }
        return result;
    }
}

class Solution110 {
    public static Object solve(int[] a, int k) {
        List<Object> result = new ArrayList<>();
        PriorityQueue<int[]> lower = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        PriorityQueue<int[]> upper = new PriorityQueue<>((x, y) -> Arrays.compare(x, y));
        for (int right = 0; (right < a.length); right++) {
            int left = (Objects.equals("window", "window") ? Math.max(0, ((right - k) + 1)) : 0);
            if (Objects.equals("window", "window")) {
                while ((lower.size() > 0)) {
                    lower.poll();
                }
                while ((upper.size() > 0)) {
                    upper.poll();
                }
            }
            int start = (Objects.equals("window", "window") ? left : right);
            for (int i = start; (i <= right); i++) {
                if ((Objects.equals(lower.size(), 0) || (a[i] <= (-lower.peek()[0])))) {
                    lower.add(new int[]{(-a[i])});
                }
                else {
                    upper.add(new int[]{a[i]});
                }
                if ((lower.size() > (upper.size() + 1))) {
                    upper.add(new int[]{(-lower.poll()[0])});
                }
                if ((upper.size() > lower.size())) {
                    lower.add(new int[]{(-upper.poll()[0])});
                }
            }
            if ((Objects.equals("window", "stream") || Objects.equals(((right - left) + 1), k))) {
                double median = (Objects.equals(lower.size(), upper.size()) ? (((-lower.peek()[0]) + upper.peek()[0]) * 0.5) : (-lower.peek()[0]));
                result.add(median);
            }
        }
        return result;
    }
}

class Solution111 {
    public static Object solve(int n) {
        int answer = visit(n, 0);
        return answer;
    }
    static int visit(int value, int depth) {
        if ((value <= 1)) {
            return value;
        }
        int left = visit((value - 1), (depth + 1));
        int right = visit((value - 2), (depth + 1));
        int total = (left + right);
        return total;
    }
}

class Solution112 {
    public static Object solve(String s) {
        boolean answer = visit(s, 0, (s.length() - 1));
        return answer;
    }
    static boolean visit(String s, int left, int right) {
        if ((left >= right)) {
            return true;
        }
        if (!Objects.equals(s.charAt(left), s.charAt(right))) {
            return false;
        }
        boolean matches = visit(s, (left + 1), (right - 1));
        return matches;
    }
}

class Solution113 {
    public static Object solve(int[] a) {
        boolean answer = visit(a, 0);
        return answer;
    }
    static boolean visit(int[] a, int i) {
        if (((i + 1) >= a.length)) {
            return true;
        }
        if ((a[i] > a[(i + 1)])) {
            return false;
        }
        boolean ordered = visit(a, (i + 1));
        return ordered;
    }
}

class Solution114 {
    public static Object solve(int n) {
        int answer = visit(n, 0);
        return answer;
    }
    static int visit(int value, int depth) {
        if ((value < 10)) {
            return value;
        }
        int digit = (value % 10);
        int rest = visit(((int) Math.floor((value / 10))), (depth + 1));
        int total = (digit + rest);
        return total;
    }
}

class Solution115 {
    public static Object solve(String s, String t) {
        String answer = visit(s, t, 0);
        return answer;
    }
    static String visit(String s, String t, int i) {
        if (Objects.equals(i, s.length())) {
            return "";
        }
        String suffix = visit(s, t, (i + 1));
        String cleaned = (Objects.equals(s.charAt(i), t.charAt(0)) ? suffix : (s.charAt(i) + suffix));
        return cleaned;
    }
}

class Solution116 {
    public static Object solve(int n) {
        List<Object> result = new ArrayList<>();
        visit(n, "", 0, 0, result);
        return result;
    }
    static int visit(int n, String path, int opened, int closed, List<Object> result) {
        if (Objects.equals(closed, n)) {
            result.add(path);
            return 0;
        }
        if ((opened < n)) {
            visit(n, (path + '('), (opened + 1), closed, result);
        }
        if ((closed < opened)) {
            visit(n, (path + ')'), opened, (closed + 1), result);
        }
        return 0;
    }
}

class Solution117 {
    public static Object solve(String s) {
        List<Object> result = new ArrayList<>();
        String[] letters = "abc def ghi jkl mno pqrs tuv wxyz".split(" ", -1);
        if ((s.length() > 0)) {
            visit(s, letters, 0, "", result);
        }
        return result;
    }
    static int visit(String s, String[] letters, int depth, String path, List<Object> result) {
        if (Objects.equals(depth, s.length())) {
            result.add(path);
            return 0;
        }
        String choices = letters["23456789".indexOf(s.charAt(depth))];
        for (int i = 0; (i < choices.length()); i++) {
            visit(s, letters, (depth + 1), (path + choices.charAt(i)), result);
        }
        return 0;
    }
}

class Solution118 {
    public static Object solve(int[] a) {
        List<Object> result = new ArrayList<>();
        int[] used = new int[a.length];
        int[] path = new int[a.length];
        visit(a, used, path, 0, result);
        return result;
    }
    static int visit(int[] a, int[] used, int[] path, int depth, List<Object> result) {
        if (Objects.equals(depth, a.length)) {
            result.add(Arrays.copyOfRange(path, 0, depth));
            return 0;
        }
        for (int i = 0; (i < a.length); i++) {
            if (Objects.equals(used[i], 0)) {
                used[i] = 1;
                path[depth] = a[i];
                visit(a, used, path, (depth + 1), result);
                used[i] = 0;
            }
        }
        return 0;
    }
}

class Solution119 {
    public static Object solve(int[] a, int target) {
        Arrays.sort(a);
        List<Object> result = new ArrayList<>();
        List<Object> path = new ArrayList<>();
        visit(a, 0, target, path, result);
        return result;
    }
    static int visit(int[] a, int start, int remaining, List<Object> path, List<Object> result) {
        if (Objects.equals(remaining, 0)) {
            result.add(new ArrayList<>(path.subList(0, path.size())));
            return 0;
        }
        for (int i = start; (i < a.length); i++) {
            if ((a[i] > remaining)) {
                break;
            }
            path.add(a[i]);
            visit(a, i, (remaining - a[i]), path, result);
            path.remove(path.size() - 1);
        }
        return 0;
    }
}

class Solution120 {
    public static Object solve(String s) {
        List<Object> result = new ArrayList<>();
        List<Object> path = new ArrayList<>();
        visit(s, 0, path, result);
        return result;
    }
    static int visit(String s, int start, List<Object> path, List<Object> result) {
        if (Objects.equals(start, s.length())) {
            result.add(new ArrayList<>(path.subList(0, path.size())));
            return 0;
        }
        for (int end = start; (end < s.length()); end++) {
            int left = start;
            int right = end;
            while (((left < right) && Objects.equals(s.charAt(left), s.charAt(right)))) {
                left++;
                right--;
            }
            if ((left >= right)) {
                path.add(s.substring(start, (end + 1)));
                visit(s, (end + 1), path, result);
                path.remove(path.size() - 1);
            }
        }
        return 0;
    }
}
class LanguageCheck {
static String json(Object value) {
 if (value == null) return "null";
 if (value instanceof String || value instanceof Character) return "\""+value.toString().replace("\\","\\\\").replace("\"","\\\"")+"\"";
 if (value instanceof Map<?,?> map) { List<String> parts=new ArrayList<>(); for(var e:map.entrySet()) parts.add(json(e.getKey())+":"+json(e.getValue())); return "{"+String.join(",",parts)+"}"; }
 if (value instanceof Iterable<?> list) { List<String> parts=new ArrayList<>(); for(Object e:list)parts.add(json(e));return "["+String.join(",",parts)+"]"; }
 if (value.getClass().isArray()) { List<String> parts=new ArrayList<>(); for(int i=0;i<java.lang.reflect.Array.getLength(value);i++)parts.add(json(java.lang.reflect.Array.get(value,i)));return "["+String.join(",",parts)+"]"; }
 return value.toString();
}
static void batch0() {
System.out.println(json(Solution0.solve(new int[]{1, 2, 4, 6, 8, 9}, 12)));
System.out.println(json(Solution1.solve(new int[]{1, 0, 1, 0, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{0, 0, 0, 1, 1, 2, 2, 3})));
System.out.println(json(Solution3.solve(new int[]{0, 0, 0, 1, 1, 2, 2, 3})));
System.out.println(json(Solution4.solve(new int[]{1, 1, 2, 3, 3, 3, 4})));
System.out.println(json(Solution5.solve(new int[]{-7, -3, -1, 4, 8})));
System.out.println(json(Solution6.solve(new int[]{-1, 0, 1, 2, -1, -4})));
System.out.println(json(Solution7.solve(new int[]{-1, 2, 1, -4}, 1)));
System.out.println(json(Solution8.solve(new int[]{-1, 0, 2, 3}, 3)));
System.out.println(json(Solution9.solve(new int[]{10, 5, 2, 6}, 100)));
System.out.println(json(Solution10.solve(new int[]{2, 0, 2, 1, 1, 0})));
System.out.println(json(Solution11.solve(new int[]{1, 0, -1, 0, -2, 2}, 0)));
System.out.println(json(Solution12.solve("ab##c", "ad#c")));
System.out.println(json(Solution13.solve(new int[]{1, 3, 2, 0, -1, 7, 10})));
System.out.println(json(Solution14.solve(new int[]{3, 2, 0, -4, 7}, 1)));
System.out.println(json(Solution15.solve(new int[]{3, 2, 0, -4, 7}, 1)));
System.out.println(json(Solution16.solve(19)));
System.out.println(json(Solution17.solve(new int[]{1, 3, 4, 2, 2})));
System.out.println(json(Solution18.solve(new int[]{1, 2, 3, 4, 5, 6})));
System.out.println(json(Solution19.solve(new int[]{1, 2, 3, 2, 1})));
System.out.println(json(Solution20.solve(new int[]{1, 2, 3, 4, 5})));
System.out.println(json(Solution21.solve(new int[]{2, -1, 1, 2, 2})));
System.out.println(json(Solution22.solve(new int[]{2, 1, 5, 1, 3, 2}, 3)));
System.out.println(json(Solution23.solve(new int[]{2, 3, 1, 2, 4, 3}, 7)));
System.out.println(json(Solution24.solve("aabacbebebe", 3)));
System.out.println(json(Solution25.solve(new int[]{1, 2, 1, 2, 3, 2, 2})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("abcabcbb")));
System.out.println(json(Solution27.solve("AABABBA", 1)));
System.out.println(json(Solution28.solve(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2)));
System.out.println(json(Solution29.solve("ADOBECODEBANC", "ABC")));
System.out.println(json(Solution30.solve("eidbaooo", "ab")));
System.out.println(json(Solution31.solve("cbaebabacd", "abc")));
System.out.println(json(Solution32.solve("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"})));
System.out.println(json(Solution33.solve(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})));
System.out.println(json(Solution34.solve(new int[]{3, -4, 2, -3, -1, 7, -5})));
System.out.println(json(Solution35.solve(new int[]{2, 3, -2, 4, -2})));
System.out.println(json(Solution36.solve(new int[]{1, -2, 0, 3})));
System.out.println(json(Solution37.solve(new int[]{1, -3, 2, 3, -4})));
System.out.println(json(Solution38.solve(new int[]{5, -3, 5})));
System.out.println(json(Solution39.solve(new int[]{1, 2, 1, -1, 2}, 3)));
System.out.println(json(Solution40.solve(new int[]{1, 7, 3, 6, 5, 6})));
System.out.println(json(Solution41.solve(new int[]{4, 5, 0, -2, -3, 1}, 5)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 0, 0, 1, 1, 0})));
System.out.println(json(Solution43.solve(new int[]{2, -1, 2, 1}, 3)));
System.out.println(json(Solution44.solve(new int[]{-2, 5, -1}, -2, 2)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{1, 3}, new int[]{2, 6}, new int[]{8, 10}, new int[]{15, 18}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{1, 2}, new int[]{5, 6}, new int[]{1, 3}, new int[]{4, 10}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{1, 3}, new int[]{6, 9}}, new int[]{2, 5})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{0, 2}, new int[]{5, 10}, new int[]{13, 23}, new int[]{24, 25}}, new int[][]{new int[]{1, 5}, new int[]{8, 12}, new int[]{15, 24}, new int[]{25, 26}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{1, 3}, new int[]{7, 9}, new int[]{2, 4}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{0, 30}, new int[]{5, 10}, new int[]{15, 20}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{1, 4, 3}, new int[]{2, 5, 4}, new int[]{7, 9, 6}})));
System.out.println(json(Solution52.solve(new int[]{1, 2, 3, 4, 5})));
System.out.println(json(Solution53.solve(new int[]{1, 2, 3, 4, 5}, 2, 4)));
System.out.println(json(Solution54.solve(new int[]{1, 2, 3, 4, 5})));
System.out.println(json(Solution55.solve(new int[]{1, 2, 3, 4, 5}, 3)));
System.out.println(json(Solution56.solve(new int[]{5, 2, 6, 3, 9, 1, 7, 3, 8, 4})));
System.out.println(json(Solution57.solve(new int[]{1, 2, 3, 4, 5}, 2)));
System.out.println(json(Solution58.solve("abbaca")));
System.out.println(json(Solution59.solve("deeedbbcccbdaa", 3)));
System.out.println(json(Solution60.solve("{[()]}")));
System.out.println(json(Solution61.solve("hello")));
System.out.println(json(Solution62.solve(new int[]{1, 2, 1})));
System.out.println(json(Solution63.solve(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
System.out.println(json(Solution64.solve(new int[]{5, 2, 13, 3, 8})));
System.out.println(json(Solution65.solve("/a/./b/../../c/")));
System.out.println(json(Solution66.solve("1432219", 3)));
System.out.println(json(Solution67.solve("loveleetcode")));
System.out.println(json(Solution68.solve("loonbalxballpoon")));
System.out.println(json(Solution69.solve("abccccdd")));
System.out.println(json(Solution70.solve("aa", "aab")));
System.out.println(json(Solution71.solve(new int[]{-1, 0, 3, 5, 9, 12}, 9)));
System.out.println(json(Solution72.solve(new int[]{1, 2, 2, 4, 6, 8}, 2, "ceiling")));
System.out.println(json(Solution73.solve(new int[]{5, 7, 7, 8, 8, 10}, 8)));
System.out.println(json(Solution74.solve(new int[]{1, 2, 2, 2, 3, 4}, 2)));
System.out.println(json(Solution75.solve(new int[]{3, 5, 7, 9, 10, 90, 100, 130, 140, 160}, 100)));
System.out.println(json(Solution76.solve(new int[]{0, 2, 5, 8, 6, 3, 1})));
System.out.println(json(Solution77.solve(new int[]{1, 2, 1, 3, 5, 6, 4})));
System.out.println(json(Solution78.solve(new int[]{4, 5, 6, 7, 0, 1, 2})));
System.out.println(json(Solution79.solve(new int[]{15, 18, 2, 3, 6, 12})));
System.out.println(json(Solution80.solve(new int[]{4, 5, 6, 7, 0, 1, 2}, 0)));
System.out.println(json(Solution81.solve(new int[]{3, 6, 7, 11}, 8)));
System.out.println(json(Solution82.solve(new int[]{1, 10, 3, 10, 2}, 3, 1)));
System.out.println(json(Solution83.solve(new int[]{1, 2, 4, 8, 9}, 3)));
System.out.println(json(Solution84.solve(new int[]{0, 1, 3, 5, 6})));
System.out.println(json(Solution85.solve(new int[]{5, 8, 6}, 3)));
System.out.println(json(Solution86.solve(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5)));
System.out.println(json(Solution87.solve(new int[]{12, 34, 67, 90}, 2)));
System.out.println(json(Solution88.solve(new int[]{7, 2, 5, 10, 8}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{1, 3, 5, 7}, new int[]{10, 11, 16, 20}, new int[]{23, 30, 34, 60}}, 3)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{1, 4, 7, 11}, new int[]{2, 5, 8, 12}, new int[]{3, 6, 9, 16}}, 6)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{1, 5, 9}, new int[]{10, 11, 13}, new int[]{12, 13, 15}}, 8)));
System.out.println(json(Solution92.solve(3, 3, 5)));
System.out.println(json(Solution93.solve(new int[]{1, 3}, new int[]{2, 4})));
System.out.println(json(Solution94.solve(new int[]{7, 10, 4, 3, 20, 15}, 3)));
System.out.println(json(Solution95.solve(new int[]{3, 2, 1, 5, 6, 4}, 2)));
System.out.println(json(Solution96.solve(new int[]{1, 1, 1, 2, 2, 3}, 2)));
System.out.println(json(Solution97.solve(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2)));
System.out.println(json(Solution98.solve(new int[][]{new int[]{1, 3}, new int[]{-2, 2}, new int[]{2, -2}}, 2)));
System.out.println(json(Solution99.solve(new int[]{1, 2, 3, 4, 5}, 4, 3)));
}
static void batch100() {
System.out.println(json(Solution100.solve(new int[][]{new int[]{1, 1, 0, 0}, new int[]{1, 1, 1, 1}, new int[]{1, 0, 0, 0}, new int[]{1, 1, 0, 0}}, 2)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{1, 4, 7}, new int[]{2, 5, 8}, new int[]{3, 6, 9}}, 9)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{1, 5, 9}, new int[]{10, 11, 13}, new int[]{12, 13, 15}}, 8)));
System.out.println(json(Solution103.solve(new int[]{2, 7, 4, 1, 8, 1})));
System.out.println(json(Solution104.solve("AAABBB", 2)));
System.out.println(json(Solution105.solve("aaabbc")));
System.out.println(json(Solution106.solve(new int[][]{new int[]{10, 60}, new int[]{20, 30}, new int[]{30, 30}, new int[]{60, 40}}, 100, 10)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{0, 1}, new int[]{1, 2}, new int[]{1, 3}}, 2, 0)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{100, 200}, new int[]{200, 1300}, new int[]{1000, 1250}, new int[]{2000, 3200}})));
System.out.println(json(Solution109.solve(new int[]{5, 2, 8, 1, 3}, 1)));
System.out.println(json(Solution110.solve(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
System.out.println(json(Solution111.solve(5)));
System.out.println(json(Solution112.solve("racecar")));
System.out.println(json(Solution113.solve(new int[]{1, 2, 2, 5, 8})));
System.out.println(json(Solution114.solve(472)));
System.out.println(json(Solution115.solve("banana", "a")));
System.out.println(json(Solution116.solve(3)));
System.out.println(json(Solution117.solve("23")));
System.out.println(json(Solution118.solve(new int[]{1, 2, 3})));
System.out.println(json(Solution119.solve(new int[]{2, 3, 6, 7}, 7)));
System.out.println(json(Solution120.solve("aab")));
System.out.println(json(Solution0.solve(new int[]{-4, -4, -3, -2, 1}, 2)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, -4, -3, -2, 1})));
System.out.println(json(Solution3.solve(new int[]{-4, -4, -3, -2, 1})));
System.out.println(json(Solution4.solve(new int[]{-4, -4, -3, -2, 1})));
System.out.println(json(Solution5.solve(new int[]{-4, -4, -3, -2, 1})));
System.out.println(json(Solution6.solve(new int[]{1, -3, -4, -2, -4})));
System.out.println(json(Solution7.solve(new int[]{1, -3, -4, -2, -4}, 2)));
System.out.println(json(Solution8.solve(new int[]{1, -3, -4, -2, -4}, 2)));
System.out.println(json(Solution9.solve(new int[]{1, 1, 7}, 40)));
System.out.println(json(Solution10.solve(new int[]{})));
System.out.println(json(Solution11.solve(new int[]{1, -3, -4, -2, -4}, 2)));
System.out.println(json(Solution12.solve("a##ba", "#")));
System.out.println(json(Solution13.solve(new int[]{1, -3, -4, -2, -4})));
System.out.println(json(Solution14.solve(new int[]{1, -3, -4, -2, -4}, 0)));
System.out.println(json(Solution15.solve(new int[]{1, -3, -4, -2, -4}, 0)));
System.out.println(json(Solution16.solve(1693)));
System.out.println(json(Solution17.solve(new int[]{3, 4, 2, 3, 1})));
System.out.println(json(Solution18.solve(new int[]{1, -3, -4, -2, -4})));
System.out.println(json(Solution19.solve(new int[]{1, -3, -4, -2, -4})));
System.out.println(json(Solution20.solve(new int[]{1, -3, -4, -2, -4})));
System.out.println(json(Solution21.solve(new int[]{1, -3, -4, -2, -4})));
System.out.println(json(Solution22.solve(new int[]{1, -3, -4, -2, -4}, 2)));
System.out.println(json(Solution23.solve(new int[]{1, 1, 7}, 9)));
System.out.println(json(Solution24.solve("aacccbbb", 3)));
System.out.println(json(Solution25.solve(new int[]{1, -3, -4, -2, -4})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("aacccbbb")));
System.out.println(json(Solution27.solve("AACCCBBB", 1)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0}, 1)));
System.out.println(json(Solution29.solve("aacccbbb", "bba")));
System.out.println(json(Solution30.solve("aacccbbb", "bba")));
System.out.println(json(Solution31.solve("aacccbbb", "bba")));
System.out.println(json(Solution32.solve("cbccabbccbbaab", new String[]{"a", "a"})));
System.out.println(json(Solution0.solve(new int[]{-4, -2, 0, 1, 1, 3, 4}, -2)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, -2, 0, 1, 1, 3, 4})));
System.out.println(json(Solution3.solve(new int[]{-4, -2, 0, 1, 1, 3, 4})));
System.out.println(json(Solution4.solve(new int[]{-4, -2, 0, 1, 1, 3, 4})));
System.out.println(json(Solution5.solve(new int[]{-4, -2, 0, 1, 1, 3, 4})));
System.out.println(json(Solution6.solve(new int[]{-2, -4, 1, 4, 3, 0, 1})));
System.out.println(json(Solution7.solve(new int[]{-2, -4, 1, 4, 3, 0, 1}, -2)));
System.out.println(json(Solution8.solve(new int[]{-2, -4, 1, 4, 3, 0, 1}, -2)));
System.out.println(json(Solution9.solve(new int[]{5, 4, 4, 5, 7, 3, 4}, 16)));
System.out.println(json(Solution10.solve(new int[]{2, 2, 0, 0, 2, 2})));
System.out.println(json(Solution11.solve(new int[]{-2, -4, 1, 4, 3, 0, 1}, -2)));
System.out.println(json(Solution12.solve("#ba", "#")));
System.out.println(json(Solution13.solve(new int[]{-2, -4, 1, 4, 3, 0, 1})));
System.out.println(json(Solution14.solve(new int[]{-2, -4, 1, 4, 3, 0, 1}, 4)));
System.out.println(json(Solution15.solve(new int[]{-2, -4, 1, 4, 3, 0, 1}, 4)));
System.out.println(json(Solution16.solve(1761)));
System.out.println(json(Solution17.solve(new int[]{3, 8, 2, 3, 4, 1, 7, 5, 6})));
System.out.println(json(Solution18.solve(new int[]{-2, -4, 1, 4, 3, 0, 1})));
System.out.println(json(Solution19.solve(new int[]{-2, -4, 1, 4, 3, 0, 1})));
System.out.println(json(Solution20.solve(new int[]{-2, -4, 1, 4, 3, 0, 1})));
System.out.println(json(Solution21.solve(new int[]{-2, -4, 1, 4, 3, 1, 1})));
System.out.println(json(Solution22.solve(new int[]{-2, -4, 1, 4, 3, 0, 1}, 6)));
System.out.println(json(Solution23.solve(new int[]{5, 4, 4, 5, 7, 3, 4}, 9)));
System.out.println(json(Solution24.solve("bcccbcbb", 3)));
System.out.println(json(Solution25.solve(new int[]{-2, -4, 1, 4, 3, 0, 1})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("bcccbcbb")));
System.out.println(json(Solution27.solve("BCCCBCBB", 1)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0}, 1)));
System.out.println(json(Solution29.solve("bcccbcbb", "aaa")));
System.out.println(json(Solution30.solve("bcccbcbb", "aaa")));
System.out.println(json(Solution31.solve("bcccbcbb", "aaa")));
System.out.println(json(Solution32.solve("bcaccccaababca", new String[]{"c", "c"})));
System.out.println(json(Solution0.solve(new int[]{-4, -4, -1, -1, -1, 0, 1}, 5)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, -4, -1, -1, -1, 0, 1})));
System.out.println(json(Solution3.solve(new int[]{-4, -4, -1, -1, -1, 0, 1})));
System.out.println(json(Solution4.solve(new int[]{-4, -4, -1, -1, -1, 0, 1})));
System.out.println(json(Solution5.solve(new int[]{-4, -4, -1, -1, -1, 0, 1})));
System.out.println(json(Solution6.solve(new int[]{-1, -1, 1, -4, 0, -1, -4})));
System.out.println(json(Solution7.solve(new int[]{-1, -1, 1, -4, 0, -1, -4}, 5)));
System.out.println(json(Solution8.solve(new int[]{-1, -1, 1, -4, 0, -1, -4}, 5)));
System.out.println(json(Solution9.solve(new int[]{3, 6, 7, 2, 4, 4, 7}, 8)));
System.out.println(json(Solution10.solve(new int[]{1, 1, 2, 0})));
System.out.println(json(Solution11.solve(new int[]{-1, -1, 1, -4, 0, -1, -4}, 5)));
System.out.println(json(Solution12.solve("a##", "a")));
}
static void batch200() {
System.out.println(json(Solution13.solve(new int[]{-1, -1, 1, -4, 0, -1, -4})));
System.out.println(json(Solution14.solve(new int[]{-1, -1, 1, -4, 0, -1, -4}, 2)));
System.out.println(json(Solution15.solve(new int[]{-1, -1, 1, -4, 0, -1, -4}, 2)));
System.out.println(json(Solution16.solve(1271)));
System.out.println(json(Solution17.solve(new int[]{2, 4, 3, 1, 6, 3, 5})));
System.out.println(json(Solution18.solve(new int[]{-1, -1, 1, -4, 0, -1, -4})));
System.out.println(json(Solution19.solve(new int[]{-1, -1, 1, -4, 0, -1, -4})));
System.out.println(json(Solution20.solve(new int[]{-1, -1, 1, -4, 0, -1, -4})));
System.out.println(json(Solution21.solve(new int[]{-1, -1, 1, -4, 1, -1, -4})));
System.out.println(json(Solution22.solve(new int[]{-1, -1, 1, -4, 0, -1, -4}, 4)));
System.out.println(json(Solution23.solve(new int[]{3, 6, 7, 2, 4, 4, 7}, 13)));
System.out.println(json(Solution24.solve("b", 2)));
System.out.println(json(Solution25.solve(new int[]{-1, -1, 1, -4, 0, -1, -4})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("b")));
System.out.println(json(Solution27.solve("B", 0)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0}, 0)));
System.out.println(json(Solution29.solve("b", "c")));
System.out.println(json(Solution30.solve("b", "c")));
System.out.println(json(Solution31.solve("b", "c")));
System.out.println(json(Solution32.solve("ccabcbbcbbabba", new String[]{"ba"})));
System.out.println(json(Solution0.solve(new int[]{-4, -4, -2, 0, 3, 3, 4, 4}, -3)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, -4, -2, 0, 3, 3, 4, 4})));
System.out.println(json(Solution3.solve(new int[]{-4, -4, -2, 0, 3, 3, 4, 4})));
System.out.println(json(Solution4.solve(new int[]{-4, -4, -2, 0, 3, 3, 4, 4})));
System.out.println(json(Solution5.solve(new int[]{-4, -4, -2, 0, 3, 3, 4, 4})));
System.out.println(json(Solution6.solve(new int[]{3, -4, -4, 0, -2, 4, 3, 4})));
System.out.println(json(Solution7.solve(new int[]{3, -4, -4, 0, -2, 4, 3, 4}, -3)));
System.out.println(json(Solution8.solve(new int[]{3, -4, -4, 0, -2, 4, 3, 4}, -3)));
System.out.println(json(Solution9.solve(new int[]{2, 7, 1, 2, 4}, 20)));
System.out.println(json(Solution10.solve(new int[]{0, 1, 1, 2})));
System.out.println(json(Solution11.solve(new int[]{3, -4, -4, 0, -2, 4, 3, 4}, -3)));
System.out.println(json(Solution12.solve("a", "#")));
System.out.println(json(Solution13.solve(new int[]{3, -4, -4, 0, -2, 4, 3, 4})));
System.out.println(json(Solution14.solve(new int[]{3, -4, -4, 0, -2, 4, 3, 4}, 5)));
System.out.println(json(Solution15.solve(new int[]{3, -4, -4, 0, -2, 4, 3, 4}, 5)));
System.out.println(json(Solution16.solve(521)));
System.out.println(json(Solution17.solve(new int[]{3, 4, 2, 6, 8, 1, 7, 5, 3})));
System.out.println(json(Solution18.solve(new int[]{3, -4, -4, 0, -2, 4, 3, 4})));
System.out.println(json(Solution19.solve(new int[]{3, -4, -4, 0, -2, 4, 3, 4})));
System.out.println(json(Solution20.solve(new int[]{3, -4, -4, 0, -2, 4, 3, 4})));
System.out.println(json(Solution21.solve(new int[]{3, -4, -4, 1, -2, 4, 3, 4})));
System.out.println(json(Solution22.solve(new int[]{3, -4, -4, 0, -2, 4, 3, 4}, 2)));
System.out.println(json(Solution23.solve(new int[]{2, 7, 1, 2, 4}, 9)));
System.out.println(json(Solution24.solve("", 3)));
System.out.println(json(Solution25.solve(new int[]{3, -4, -4, 0, -2, 4, 3, 4})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("")));
System.out.println(json(Solution27.solve("", 1)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0}, 1)));
System.out.println(json(Solution29.solve("", "cb")));
System.out.println(json(Solution30.solve("", "cb")));
System.out.println(json(Solution31.solve("", "cb")));
System.out.println(json(Solution32.solve("ccabacccaacabc", new String[]{"ca", "cb"})));
System.out.println(json(Solution0.solve(new int[]{-3, -2, -1, -1, 3, 4}, 5)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-3, -2, -1, -1, 3, 4})));
System.out.println(json(Solution3.solve(new int[]{-3, -2, -1, -1, 3, 4})));
System.out.println(json(Solution4.solve(new int[]{-3, -2, -1, -1, 3, 4})));
System.out.println(json(Solution5.solve(new int[]{-3, -2, -1, -1, 3, 4})));
System.out.println(json(Solution6.solve(new int[]{4, -1, 3, -1, -3, -2})));
System.out.println(json(Solution7.solve(new int[]{4, -1, 3, -1, -3, -2}, 5)));
System.out.println(json(Solution8.solve(new int[]{4, -1, 3, -1, -3, -2}, 5)));
System.out.println(json(Solution9.solve(new int[]{4, 2, 3}, 24)));
System.out.println(json(Solution10.solve(new int[]{0, 0, 0, 0})));
System.out.println(json(Solution11.solve(new int[]{4, -1, 3, -1, -3, -2}, 5)));
System.out.println(json(Solution12.solve("a##ba##", "a##ba##")));
System.out.println(json(Solution13.solve(new int[]{4, -1, 3, -1, -3, -2})));
System.out.println(json(Solution14.solve(new int[]{4, -1, 3, -1, -3, -2}, 3)));
System.out.println(json(Solution15.solve(new int[]{4, -1, 3, -1, -3, -2}, 3)));
System.out.println(json(Solution16.solve(713)));
System.out.println(json(Solution17.solve(new int[]{2, 4, 3, 8, 6, 1, 3, 5, 7})));
System.out.println(json(Solution18.solve(new int[]{4, -1, 3, -1, -3, -2})));
System.out.println(json(Solution19.solve(new int[]{4, -1, 3, -1, -3, -2})));
System.out.println(json(Solution20.solve(new int[]{4, -1, 3, -1, -3, -2})));
System.out.println(json(Solution21.solve(new int[]{4, -1, 3, -1, -3, -2})));
System.out.println(json(Solution22.solve(new int[]{4, -1, 3, -1, -3, -2}, 4)));
System.out.println(json(Solution23.solve(new int[]{4, 2, 3}, 17)));
System.out.println(json(Solution24.solve("bbcbaab", 4)));
System.out.println(json(Solution25.solve(new int[]{4, -1, 3, -1, -3, -2})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("bbcbaab")));
System.out.println(json(Solution27.solve("BBCBAAB", 2)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0}, 2)));
System.out.println(json(Solution29.solve("bbcbaab", "bca")));
System.out.println(json(Solution30.solve("bbcbaab", "bca")));
System.out.println(json(Solution31.solve("bbcbaab", "bca")));
System.out.println(json(Solution32.solve("cbbbaaacbcccaa", new String[]{"ca", "cb", "bc"})));
System.out.println(json(Solution0.solve(new int[]{-4, -3, -2, -1, 0, 0, 2, 2}, 3)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, -3, -2, -1, 0, 0, 2, 2})));
System.out.println(json(Solution3.solve(new int[]{-4, -3, -2, -1, 0, 0, 2, 2})));
System.out.println(json(Solution4.solve(new int[]{-4, -3, -2, -1, 0, 0, 2, 2})));
System.out.println(json(Solution5.solve(new int[]{-4, -3, -2, -1, 0, 0, 2, 2})));
System.out.println(json(Solution6.solve(new int[]{-4, 2, 0, -3, 2, -1, -2, 0})));
System.out.println(json(Solution7.solve(new int[]{-4, 2, 0, -3, 2, -1, -2, 0}, 3)));
System.out.println(json(Solution8.solve(new int[]{-4, 2, 0, -3, 2, -1, -2, 0}, 3)));
System.out.println(json(Solution9.solve(new int[]{3, 2, 6, 3, 4, 2, 4}, 24)));
System.out.println(json(Solution10.solve(new int[]{})));
System.out.println(json(Solution11.solve(new int[]{-4, 2, 0, -3, 2, -1, -2, 0}, 3)));
System.out.println(json(Solution12.solve("a##ba", "#ba")));
System.out.println(json(Solution13.solve(new int[]{-4, 2, 0, -3, 2, -1, -2, 0})));
}
static void batch300() {
System.out.println(json(Solution14.solve(new int[]{-4, 2, 0, -3, 2, -1, -2, 0}, 3)));
System.out.println(json(Solution15.solve(new int[]{-4, 2, 0, -3, 2, -1, -2, 0}, 3)));
System.out.println(json(Solution16.solve(1551)));
System.out.println(json(Solution17.solve(new int[]{2, 4, 3, 5, 1, 3, 6})));
System.out.println(json(Solution18.solve(new int[]{-4, 2, 0, -3, 2, -1, -2, 0})));
System.out.println(json(Solution19.solve(new int[]{-4, 2, 0, -3, 2, -1, -2, 0})));
System.out.println(json(Solution20.solve(new int[]{-4, 2, 0, -3, 2, -1, -2, 0})));
System.out.println(json(Solution21.solve(new int[]{-4, 2, 1, -3, 2, -1, -2, 1})));
System.out.println(json(Solution22.solve(new int[]{-4, 2, 0, -3, 2, -1, -2, 0}, 6)));
System.out.println(json(Solution23.solve(new int[]{3, 2, 6, 3, 4, 2, 4}, 1)));
System.out.println(json(Solution24.solve("baabbb", 1)));
System.out.println(json(Solution25.solve(new int[]{-4, 2, 0, -3, 2, -1, -2, 0})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("baabbb")));
System.out.println(json(Solution27.solve("BAABBB", 3)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0}, 3)));
System.out.println(json(Solution29.solve("baabbb", "baa")));
System.out.println(json(Solution30.solve("baabbb", "baa")));
System.out.println(json(Solution31.solve("baabbb", "baa")));
System.out.println(json(Solution32.solve("babbacabbabbba", new String[]{"c", "b"})));
System.out.println(json(Solution0.solve(new int[]{0, 1}, 4)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{2})));
System.out.println(json(Solution3.solve(new int[]{2})));
System.out.println(json(Solution4.solve(new int[]{2})));
System.out.println(json(Solution5.solve(new int[]{2})));
System.out.println(json(Solution6.solve(new int[]{2, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{2, 0, 1, 2}, 4)));
System.out.println(json(Solution8.solve(new int[]{2, 0, 1, 2}, 4)));
System.out.println(json(Solution9.solve(new int[]{7}, 36)));
System.out.println(json(Solution10.solve(new int[]{2, 0, 1, 2})));
System.out.println(json(Solution11.solve(new int[]{2}, 4)));
System.out.println(json(Solution12.solve("a##", "a")));
System.out.println(json(Solution13.solve(new int[]{2})));
System.out.println(json(Solution14.solve(new int[]{2}, 0)));
System.out.println(json(Solution15.solve(new int[]{2}, 0)));
System.out.println(json(Solution16.solve(99)));
System.out.println(json(Solution17.solve(new int[]{2, 1, 1})));
System.out.println(json(Solution18.solve(new int[]{2})));
System.out.println(json(Solution19.solve(new int[]{2})));
System.out.println(json(Solution20.solve(new int[]{2})));
System.out.println(json(Solution21.solve(new int[]{2})));
System.out.println(json(Solution22.solve(new int[]{2}, 1)));
System.out.println(json(Solution23.solve(new int[]{7}, 5)));
System.out.println(json(Solution24.solve("cababa", 1)));
System.out.println(json(Solution25.solve(new int[]{2})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("cababa")));
System.out.println(json(Solution27.solve("CABABA", 3)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0}, 3)));
System.out.println(json(Solution29.solve("cababa", "acc")));
System.out.println(json(Solution30.solve("cababa", "acc")));
System.out.println(json(Solution31.solve("cababa", "acc")));
System.out.println(json(Solution32.solve("bbaacaabbaaabc", new String[]{"c", "c"})));
System.out.println(json(Solution0.solve(new int[]{0, 1}, -6)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{1})));
System.out.println(json(Solution3.solve(new int[]{1})));
System.out.println(json(Solution4.solve(new int[]{1})));
System.out.println(json(Solution5.solve(new int[]{1})));
System.out.println(json(Solution6.solve(new int[]{1, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{1, 0, 1, 2}, -6)));
System.out.println(json(Solution8.solve(new int[]{1, 0, 1, 2}, -6)));
System.out.println(json(Solution9.solve(new int[]{4}, 20)));
System.out.println(json(Solution10.solve(new int[]{0, 0, 1, 1, 1, 2, 1, 0})));
System.out.println(json(Solution11.solve(new int[]{1}, -6)));
System.out.println(json(Solution12.solve("a##ba", "#ba")));
System.out.println(json(Solution13.solve(new int[]{1})));
System.out.println(json(Solution14.solve(new int[]{1}, 0)));
System.out.println(json(Solution15.solve(new int[]{1}, 0)));
System.out.println(json(Solution16.solve(971)));
System.out.println(json(Solution17.solve(new int[]{2, 1, 1})));
System.out.println(json(Solution18.solve(new int[]{1})));
System.out.println(json(Solution19.solve(new int[]{1})));
System.out.println(json(Solution20.solve(new int[]{1})));
System.out.println(json(Solution21.solve(new int[]{1})));
System.out.println(json(Solution22.solve(new int[]{1}, 1)));
System.out.println(json(Solution23.solve(new int[]{4}, 17)));
System.out.println(json(Solution24.solve("cbcbc", 2)));
System.out.println(json(Solution25.solve(new int[]{1})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("cbcbc")));
System.out.println(json(Solution27.solve("CBCBC", 0)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0}, 0)));
System.out.println(json(Solution29.solve("cbcbc", "b")));
System.out.println(json(Solution30.solve("cbcbc", "b")));
System.out.println(json(Solution31.solve("cbcbc", "b")));
System.out.println(json(Solution32.solve("cbbcccccaaccac", new String[]{"ba"})));
System.out.println(json(Solution0.solve(new int[]{-4, -2, -1, 4}, 6)));
System.out.println(json(Solution1.solve(new int[]{0})));
System.out.println(json(Solution2.solve(new int[]{-4, -2, -1, 4})));
System.out.println(json(Solution3.solve(new int[]{-4, -2, -1, 4})));
System.out.println(json(Solution4.solve(new int[]{-4, -2, -1, 4})));
System.out.println(json(Solution5.solve(new int[]{-4, -2, -1, 4})));
System.out.println(json(Solution6.solve(new int[]{-1, -4, -2, 4})));
System.out.println(json(Solution7.solve(new int[]{-1, -4, -2, 4}, 6)));
System.out.println(json(Solution8.solve(new int[]{-1, -4, -2, 4}, 6)));
System.out.println(json(Solution9.solve(new int[]{7, 4, 6}, 40)));
System.out.println(json(Solution10.solve(new int[]{})));
System.out.println(json(Solution11.solve(new int[]{-1, -4, -2, 4}, 6)));
System.out.println(json(Solution12.solve("a##ba##", "a##ba##")));
System.out.println(json(Solution13.solve(new int[]{-1, -4, -2, 4})));
System.out.println(json(Solution14.solve(new int[]{-1, -4, -2, 4}, 3)));
}
static void batch400() {
System.out.println(json(Solution15.solve(new int[]{-1, -4, -2, 4}, 3)));
System.out.println(json(Solution16.solve(1933)));
System.out.println(json(Solution17.solve(new int[]{2, 3, 4, 3, 1})));
System.out.println(json(Solution18.solve(new int[]{-1, -4, -2, 4})));
System.out.println(json(Solution19.solve(new int[]{-1, -4, -2, 4})));
System.out.println(json(Solution20.solve(new int[]{-1, -4, -2, 4})));
System.out.println(json(Solution21.solve(new int[]{-1, -4, -2, 4})));
System.out.println(json(Solution22.solve(new int[]{-1, -4, -2, 4}, 2)));
System.out.println(json(Solution23.solve(new int[]{7, 4, 6}, 5)));
System.out.println(json(Solution24.solve("baacabbb", 3)));
System.out.println(json(Solution25.solve(new int[]{-1, -4, -2, 4})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("baacabbb")));
System.out.println(json(Solution27.solve("BAACABBB", 1)));
System.out.println(json(Solution28.solve(new int[]{0}, 1)));
System.out.println(json(Solution29.solve("baacabbb", "aaa")));
System.out.println(json(Solution30.solve("baacabbb", "aaa")));
System.out.println(json(Solution31.solve("baacabbb", "aaa")));
System.out.println(json(Solution32.solve("abcabbccaaaaba", new String[]{"c", "a"})));
System.out.println(json(Solution0.solve(new int[]{-4, -1, 1, 2, 2, 2, 4}, -2)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, -1, 1, 2, 2, 2, 4})));
System.out.println(json(Solution3.solve(new int[]{-4, -1, 1, 2, 2, 2, 4})));
System.out.println(json(Solution4.solve(new int[]{-4, -1, 1, 2, 2, 2, 4})));
System.out.println(json(Solution5.solve(new int[]{-4, -1, 1, 2, 2, 2, 4})));
System.out.println(json(Solution6.solve(new int[]{2, -4, -1, 2, 4, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{2, -4, -1, 2, 4, 1, 2}, -2)));
System.out.println(json(Solution8.solve(new int[]{2, -4, -1, 2, 4, 1, 2}, -2)));
System.out.println(json(Solution9.solve(new int[]{4}, 20)));
System.out.println(json(Solution10.solve(new int[]{0, 1, 0, 1, 0, 2})));
System.out.println(json(Solution11.solve(new int[]{2, -4, -1, 2, 4, 1, 2}, -2)));
System.out.println(json(Solution12.solve("#ba##ba", "#ba##ba")));
System.out.println(json(Solution13.solve(new int[]{2, -4, -1, 2, 4, 1, 2})));
System.out.println(json(Solution14.solve(new int[]{2, -4, -1, 2, 4, 1, 2}, 2)));
System.out.println(json(Solution15.solve(new int[]{2, -4, -1, 2, 4, 1, 2}, 2)));
System.out.println(json(Solution16.solve(1567)));
System.out.println(json(Solution17.solve(new int[]{5, 4, 5, 1, 6, 3, 2})));
System.out.println(json(Solution18.solve(new int[]{2, -4, -1, 2, 4, 1, 2})));
System.out.println(json(Solution19.solve(new int[]{2, -4, -1, 2, 4, 1, 2})));
System.out.println(json(Solution20.solve(new int[]{2, -4, -1, 2, 4, 1, 2})));
System.out.println(json(Solution21.solve(new int[]{2, -4, -1, 2, 4, 1, 2})));
System.out.println(json(Solution22.solve(new int[]{2, -4, -1, 2, 4, 1, 2}, 3)));
System.out.println(json(Solution23.solve(new int[]{4}, 17)));
System.out.println(json(Solution24.solve("cbbb", 3)));
System.out.println(json(Solution25.solve(new int[]{2, -4, -1, 2, 4, 1, 2})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("cbbb")));
System.out.println(json(Solution27.solve("CBBB", 1)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0}, 1)));
System.out.println(json(Solution29.solve("cbbb", "c")));
System.out.println(json(Solution30.solve("cbbb", "c")));
System.out.println(json(Solution31.solve("cbbb", "c")));
System.out.println(json(Solution32.solve("cbaabcaaabcbcb", new String[]{"a", "a"})));
System.out.println(json(Solution0.solve(new int[]{-4, -3, 0, 1, 1}, -5)));
System.out.println(json(Solution1.solve(new int[]{0})));
System.out.println(json(Solution2.solve(new int[]{-4, -3, 0, 1, 1})));
System.out.println(json(Solution3.solve(new int[]{-4, -3, 0, 1, 1})));
System.out.println(json(Solution4.solve(new int[]{-4, -3, 0, 1, 1})));
System.out.println(json(Solution5.solve(new int[]{-4, -3, 0, 1, 1})));
System.out.println(json(Solution6.solve(new int[]{0, 1, -4, -3, 1})));
System.out.println(json(Solution7.solve(new int[]{0, 1, -4, -3, 1}, -5)));
System.out.println(json(Solution8.solve(new int[]{0, 1, -4, -3, 1}, -5)));
System.out.println(json(Solution9.solve(new int[]{6, 4, 2}, 40)));
System.out.println(json(Solution10.solve(new int[]{0, 2, 0, 1, 1, 0, 2, 2})));
System.out.println(json(Solution11.solve(new int[]{0, 1, -4, -3, 1}, -5)));
System.out.println(json(Solution12.solve("a##ba", "#ba##")));
System.out.println(json(Solution13.solve(new int[]{0, 1, -4, -3, 1})));
System.out.println(json(Solution14.solve(new int[]{0, 1, -4, -3, 1}, 2)));
System.out.println(json(Solution15.solve(new int[]{0, 1, -4, -3, 1}, 2)));
System.out.println(json(Solution16.solve(1609)));
System.out.println(json(Solution17.solve(new int[]{7, 4, 3, 1, 2, 8, 3, 5, 6})));
System.out.println(json(Solution18.solve(new int[]{0, 1, -4, -3, 1})));
System.out.println(json(Solution19.solve(new int[]{0, 1, -4, -3, 1})));
System.out.println(json(Solution20.solve(new int[]{0, 1, -4, -3, 1})));
System.out.println(json(Solution21.solve(new int[]{1, 1, -4, -3, 1})));
System.out.println(json(Solution22.solve(new int[]{0, 1, -4, -3, 1}, 2)));
System.out.println(json(Solution23.solve(new int[]{6, 4, 2}, 1)));
System.out.println(json(Solution24.solve("cbbaacc", 4)));
System.out.println(json(Solution25.solve(new int[]{0, 1, -4, -3, 1})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("cbbaacc")));
System.out.println(json(Solution27.solve("CBBAACC", 2)));
System.out.println(json(Solution28.solve(new int[]{0}, 2)));
System.out.println(json(Solution29.solve("cbbaacc", "a")));
System.out.println(json(Solution30.solve("cbbaacc", "a")));
System.out.println(json(Solution31.solve("cbbaacc", "a")));
System.out.println(json(Solution32.solve("bcabccccabbcca", new String[]{"bc", "bb", "ba"})));
System.out.println(json(Solution0.solve(new int[]{-4, -3, 1, 3, 4, 4}, 0)));
System.out.println(json(Solution1.solve(new int[]{0})));
System.out.println(json(Solution2.solve(new int[]{-4, -3, 1, 3, 4, 4})));
System.out.println(json(Solution3.solve(new int[]{-4, -3, 1, 3, 4, 4})));
System.out.println(json(Solution4.solve(new int[]{-4, -3, 1, 3, 4, 4})));
System.out.println(json(Solution5.solve(new int[]{-4, -3, 1, 3, 4, 4})));
System.out.println(json(Solution6.solve(new int[]{4, 1, 4, 3, -3, -4})));
System.out.println(json(Solution7.solve(new int[]{4, 1, 4, 3, -3, -4}, 0)));
System.out.println(json(Solution8.solve(new int[]{4, 1, 4, 3, -3, -4}, 0)));
System.out.println(json(Solution9.solve(new int[]{6, 5, 5, 2, 2, 1, 6}, 8)));
System.out.println(json(Solution10.solve(new int[]{2, 0, 1, 1, 2, 1, 1, 1})));
System.out.println(json(Solution11.solve(new int[]{4, 1, 4, 3, -3, -4}, 0)));
System.out.println(json(Solution12.solve("a##ba##", "a##")));
System.out.println(json(Solution13.solve(new int[]{4, 1, 4, 3, -3, -4})));
System.out.println(json(Solution14.solve(new int[]{4, 1, 4, 3, -3, -4}, 4)));
System.out.println(json(Solution15.solve(new int[]{4, 1, 4, 3, -3, -4}, 4)));
}
static void batch500() {
System.out.println(json(Solution16.solve(33)));
System.out.println(json(Solution17.solve(new int[]{2, 7, 6, 1, 3, 3, 4, 5, 8})));
System.out.println(json(Solution18.solve(new int[]{4, 1, 4, 3, -3, -4})));
System.out.println(json(Solution19.solve(new int[]{4, 1, 4, 3, -3, -4})));
System.out.println(json(Solution20.solve(new int[]{4, 1, 4, 3, -3, -4})));
System.out.println(json(Solution21.solve(new int[]{4, 1, 4, 3, -3, -4})));
System.out.println(json(Solution22.solve(new int[]{4, 1, 4, 3, -3, -4}, 4)));
System.out.println(json(Solution23.solve(new int[]{6, 5, 5, 2, 2, 1, 6}, 1)));
System.out.println(json(Solution24.solve("cabc", 3)));
System.out.println(json(Solution25.solve(new int[]{4, 1, 4, 3, -3, -4})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("cabc")));
System.out.println(json(Solution27.solve("CABC", 1)));
System.out.println(json(Solution28.solve(new int[]{0}, 1)));
System.out.println(json(Solution29.solve("cabc", "c")));
System.out.println(json(Solution30.solve("cabc", "c")));
System.out.println(json(Solution31.solve("cabc", "c")));
System.out.println(json(Solution32.solve("cbaccbbcccabaa", new String[]{"a"})));
System.out.println(json(Solution0.solve(new int[]{-4, -2, -1, 3, 4, 4}, 8)));
System.out.println(json(Solution1.solve(new int[]{0})));
System.out.println(json(Solution2.solve(new int[]{-4, -2, -1, 3, 4, 4})));
System.out.println(json(Solution3.solve(new int[]{-4, -2, -1, 3, 4, 4})));
System.out.println(json(Solution4.solve(new int[]{-4, -2, -1, 3, 4, 4})));
System.out.println(json(Solution5.solve(new int[]{-4, -2, -1, 3, 4, 4})));
System.out.println(json(Solution6.solve(new int[]{-1, 4, 3, -2, 4, -4})));
System.out.println(json(Solution7.solve(new int[]{-1, 4, 3, -2, 4, -4}, 8)));
System.out.println(json(Solution8.solve(new int[]{-1, 4, 3, -2, 4, -4}, 8)));
System.out.println(json(Solution9.solve(new int[]{2, 2, 3, 6, 3, 6, 2}, 16)));
System.out.println(json(Solution10.solve(new int[]{0, 2})));
System.out.println(json(Solution11.solve(new int[]{-1, 4, 3, -2, 4, -4}, 8)));
System.out.println(json(Solution12.solve("#ba##ba##", "a##ba##")));
System.out.println(json(Solution13.solve(new int[]{-1, 4, 3, -2, 4, -4})));
System.out.println(json(Solution14.solve(new int[]{-1, 4, 3, -2, 4, -4}, 5)));
System.out.println(json(Solution15.solve(new int[]{-1, 4, 3, -2, 4, -4}, 5)));
System.out.println(json(Solution16.solve(337)));
System.out.println(json(Solution17.solve(new int[]{2, 3, 4, 1, 3, 8, 7, 5, 6})));
System.out.println(json(Solution18.solve(new int[]{-1, 4, 3, -2, 4, -4})));
System.out.println(json(Solution19.solve(new int[]{-1, 4, 3, -2, 4, -4})));
System.out.println(json(Solution20.solve(new int[]{-1, 4, 3, -2, 4, -4})));
System.out.println(json(Solution21.solve(new int[]{-1, 4, 3, -2, 4, -4})));
System.out.println(json(Solution22.solve(new int[]{-1, 4, 3, -2, 4, -4}, 4)));
System.out.println(json(Solution23.solve(new int[]{2, 2, 3, 6, 3, 6, 2}, 1)));
System.out.println(json(Solution24.solve("aab", 4)));
System.out.println(json(Solution25.solve(new int[]{-1, 4, 3, -2, 4, -4})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("aab")));
System.out.println(json(Solution27.solve("AAB", 2)));
System.out.println(json(Solution28.solve(new int[]{0}, 2)));
System.out.println(json(Solution29.solve("aab", "bca")));
System.out.println(json(Solution30.solve("aab", "bca")));
System.out.println(json(Solution31.solve("aab", "bca")));
System.out.println(json(Solution32.solve("ccacbcaaaababc", new String[]{"aa"})));
System.out.println(json(Solution0.solve(new int[]{-4, -3, -1, -1, 0, 0, 2, 3}, 3)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, -3, -1, -1, 0, 0, 2, 3})));
System.out.println(json(Solution3.solve(new int[]{-4, -3, -1, -1, 0, 0, 2, 3})));
System.out.println(json(Solution4.solve(new int[]{-4, -3, -1, -1, 0, 0, 2, 3})));
System.out.println(json(Solution5.solve(new int[]{-4, -3, -1, -1, 0, 0, 2, 3})));
System.out.println(json(Solution6.solve(new int[]{-3, 3, -1, 0, 0, -1, -4, 2})));
System.out.println(json(Solution7.solve(new int[]{-3, 3, -1, 0, 0, -1, -4, 2}, 3)));
System.out.println(json(Solution8.solve(new int[]{-3, 3, -1, 0, 0, -1, -4, 2}, 3)));
System.out.println(json(Solution9.solve(new int[]{2, 2, 5}, 24)));
System.out.println(json(Solution10.solve(new int[]{0, 0, 0, 1, 0, 0})));
System.out.println(json(Solution11.solve(new int[]{-3, 3, -1, 0, 0, -1, -4, 2}, 3)));
System.out.println(json(Solution12.solve("#ba##ba", "#ba##")));
System.out.println(json(Solution13.solve(new int[]{-3, 3, -1, 0, 0, -1, -4, 2})));
System.out.println(json(Solution14.solve(new int[]{-3, 3, -1, 0, 0, -1, -4, 2}, 6)));
System.out.println(json(Solution15.solve(new int[]{-3, 3, -1, 0, 0, -1, -4, 2}, 6)));
System.out.println(json(Solution16.solve(937)));
System.out.println(json(Solution17.solve(new int[]{3, 3, 4, 6, 7, 1, 2, 5, 8})));
System.out.println(json(Solution18.solve(new int[]{-3, 3, -1, 0, 0, -1, -4, 2})));
System.out.println(json(Solution19.solve(new int[]{-3, 3, -1, 0, 0, -1, -4, 2})));
System.out.println(json(Solution20.solve(new int[]{-3, 3, -1, 0, 0, -1, -4, 2})));
System.out.println(json(Solution21.solve(new int[]{-3, 3, -1, 1, 1, -1, -4, 2})));
System.out.println(json(Solution22.solve(new int[]{-3, 3, -1, 0, 0, -1, -4, 2}, 2)));
System.out.println(json(Solution23.solve(new int[]{2, 2, 5}, 9)));
System.out.println(json(Solution24.solve("ccbbc", 2)));
System.out.println(json(Solution25.solve(new int[]{-3, 3, -1, 0, 0, -1, -4, 2})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("ccbbc")));
System.out.println(json(Solution27.solve("CCBBC", 0)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0}, 0)));
System.out.println(json(Solution29.solve("ccbbc", "c")));
System.out.println(json(Solution30.solve("ccbbc", "c")));
System.out.println(json(Solution31.solve("ccbbc", "c")));
System.out.println(json(Solution32.solve("abcbacbacababa", new String[]{"bb", "ba"})));
System.out.println(json(Solution0.solve(new int[]{-4, -4}, 5)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, -4})));
System.out.println(json(Solution3.solve(new int[]{-4, -4})));
System.out.println(json(Solution4.solve(new int[]{-4, -4})));
System.out.println(json(Solution5.solve(new int[]{-4, -4})));
System.out.println(json(Solution6.solve(new int[]{-4, -4, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{-4, -4, 0, 1, 2}, 5)));
System.out.println(json(Solution8.solve(new int[]{-4, -4, 0, 1, 2}, 5)));
System.out.println(json(Solution9.solve(new int[]{5}, 36)));
System.out.println(json(Solution10.solve(new int[]{1, 1})));
System.out.println(json(Solution11.solve(new int[]{-4, -4}, 5)));
System.out.println(json(Solution12.solve("#", "a")));
System.out.println(json(Solution13.solve(new int[]{-4, -4})));
System.out.println(json(Solution14.solve(new int[]{-4, -4}, -1)));
System.out.println(json(Solution15.solve(new int[]{-4, -4}, -1)));
System.out.println(json(Solution16.solve(1751)));
}
static void batch600() {
System.out.println(json(Solution17.solve(new int[]{2, 3, 3, 6, 1, 5, 4})));
System.out.println(json(Solution18.solve(new int[]{-4, -4})));
System.out.println(json(Solution19.solve(new int[]{-4, -4})));
System.out.println(json(Solution20.solve(new int[]{-4, -4})));
System.out.println(json(Solution21.solve(new int[]{-4, -4})));
System.out.println(json(Solution22.solve(new int[]{-4, -4}, 2)));
System.out.println(json(Solution23.solve(new int[]{5}, 17)));
System.out.println(json(Solution24.solve("caccab", 1)));
System.out.println(json(Solution25.solve(new int[]{-4, -4})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("caccab")));
System.out.println(json(Solution27.solve("CACCAB", 3)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0}, 3)));
System.out.println(json(Solution29.solve("caccab", "c")));
System.out.println(json(Solution30.solve("caccab", "c")));
System.out.println(json(Solution31.solve("caccab", "c")));
System.out.println(json(Solution32.solve("bbacbbbcbbcbbc", new String[]{"b"})));
System.out.println(json(Solution0.solve(new int[]{-4, -2, 0, 1}, 6)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, -2, 0, 1})));
System.out.println(json(Solution3.solve(new int[]{-4, -2, 0, 1})));
System.out.println(json(Solution4.solve(new int[]{-4, -2, 0, 1})));
System.out.println(json(Solution5.solve(new int[]{-4, -2, 0, 1})));
System.out.println(json(Solution6.solve(new int[]{-4, 0, 1, -2})));
System.out.println(json(Solution7.solve(new int[]{-4, 0, 1, -2}, 6)));
System.out.println(json(Solution8.solve(new int[]{-4, 0, 1, -2}, 6)));
System.out.println(json(Solution9.solve(new int[]{7, 2, 7, 6, 3, 4, 7}, 32)));
System.out.println(json(Solution10.solve(new int[]{1, 2, 1, 0, 0, 1})));
System.out.println(json(Solution11.solve(new int[]{-4, 0, 1, -2}, 6)));
System.out.println(json(Solution12.solve("#ba##ba##", "a")));
System.out.println(json(Solution13.solve(new int[]{-4, 0, 1, -2})));
System.out.println(json(Solution14.solve(new int[]{-4, 0, 1, -2}, -1)));
System.out.println(json(Solution15.solve(new int[]{-4, 0, 1, -2}, -1)));
System.out.println(json(Solution16.solve(535)));
System.out.println(json(Solution17.solve(new int[]{2, 5, 5, 6, 4, 1, 3})));
System.out.println(json(Solution18.solve(new int[]{-4, 0, 1, -2})));
System.out.println(json(Solution19.solve(new int[]{-4, 0, 1, -2})));
System.out.println(json(Solution20.solve(new int[]{-4, 0, 1, -2})));
System.out.println(json(Solution21.solve(new int[]{-4, 1, 1, -2})));
System.out.println(json(Solution22.solve(new int[]{-4, 0, 1, -2}, 2)));
System.out.println(json(Solution23.solve(new int[]{7, 2, 7, 6, 3, 4, 7}, 9)));
System.out.println(json(Solution24.solve("", 3)));
System.out.println(json(Solution25.solve(new int[]{-4, 0, 1, -2})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("")));
System.out.println(json(Solution27.solve("", 1)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0}, 1)));
System.out.println(json(Solution29.solve("", "ca")));
System.out.println(json(Solution30.solve("", "ca")));
System.out.println(json(Solution31.solve("", "ca")));
System.out.println(json(Solution32.solve("bbaabbcbabbbac", new String[]{"aa", "ab", "cb"})));
System.out.println(json(Solution0.solve(new int[]{-2, -2, -1, 2}, -3)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-2, -2, -1, 2})));
System.out.println(json(Solution3.solve(new int[]{-2, -2, -1, 2})));
System.out.println(json(Solution4.solve(new int[]{-2, -2, -1, 2})));
System.out.println(json(Solution5.solve(new int[]{-2, -2, -1, 2})));
System.out.println(json(Solution6.solve(new int[]{-2, 2, -2, -1})));
System.out.println(json(Solution7.solve(new int[]{-2, 2, -2, -1}, -3)));
System.out.println(json(Solution8.solve(new int[]{-2, 2, -2, -1}, -3)));
System.out.println(json(Solution9.solve(new int[]{4}, 20)));
System.out.println(json(Solution10.solve(new int[]{})));
System.out.println(json(Solution11.solve(new int[]{-2, 2, -2, -1}, -3)));
System.out.println(json(Solution12.solve("a##ba", "#ba")));
System.out.println(json(Solution13.solve(new int[]{-2, 2, -2, -1})));
System.out.println(json(Solution14.solve(new int[]{-2, 2, -2, -1}, 1)));
System.out.println(json(Solution15.solve(new int[]{-2, 2, -2, -1}, 1)));
System.out.println(json(Solution16.solve(315)));
System.out.println(json(Solution17.solve(new int[]{2, 1, 1})));
System.out.println(json(Solution18.solve(new int[]{-2, 2, -2, -1})));
System.out.println(json(Solution19.solve(new int[]{-2, 2, -2, -1})));
System.out.println(json(Solution20.solve(new int[]{-2, 2, -2, -1})));
System.out.println(json(Solution21.solve(new int[]{-2, 2, -2, -1})));
System.out.println(json(Solution22.solve(new int[]{-2, 2, -2, -1}, 2)));
System.out.println(json(Solution23.solve(new int[]{4}, 17)));
System.out.println(json(Solution24.solve("bcaaccc", 4)));
System.out.println(json(Solution25.solve(new int[]{-2, 2, -2, -1})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("bcaaccc")));
System.out.println(json(Solution27.solve("BCAACCC", 2)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0}, 2)));
System.out.println(json(Solution29.solve("bcaaccc", "c")));
System.out.println(json(Solution30.solve("bcaaccc", "c")));
System.out.println(json(Solution31.solve("bcaaccc", "c")));
System.out.println(json(Solution32.solve("cacaaacbacbccb", new String[]{"ab", "cb"})));
System.out.println(json(Solution0.solve(new int[]{-3, -2, -1, 0, 0, 1, 3, 4}, -2)));
System.out.println(json(Solution1.solve(new int[]{0})));
System.out.println(json(Solution2.solve(new int[]{-3, -2, -1, 0, 0, 1, 3, 4})));
System.out.println(json(Solution3.solve(new int[]{-3, -2, -1, 0, 0, 1, 3, 4})));
System.out.println(json(Solution4.solve(new int[]{-3, -2, -1, 0, 0, 1, 3, 4})));
System.out.println(json(Solution5.solve(new int[]{-3, -2, -1, 0, 0, 1, 3, 4})));
System.out.println(json(Solution6.solve(new int[]{1, 0, 0, -2, 3, -3, -1, 4})));
System.out.println(json(Solution7.solve(new int[]{1, 0, 0, -2, 3, -3, -1, 4}, -2)));
System.out.println(json(Solution8.solve(new int[]{1, 0, 0, -2, 3, -3, -1, 4}, -2)));
System.out.println(json(Solution9.solve(new int[]{5, 3, 2}, 40)));
System.out.println(json(Solution10.solve(new int[]{1, 0, 2, 2})));
System.out.println(json(Solution11.solve(new int[]{1, 0, 0, -2, 3, -3, -1, 4}, -2)));
System.out.println(json(Solution12.solve("a", "#ba")));
System.out.println(json(Solution13.solve(new int[]{1, 0, 0, -2, 3, -3, -1, 4})));
System.out.println(json(Solution14.solve(new int[]{1, 0, 0, -2, 3, -3, -1, 4}, 4)));
System.out.println(json(Solution15.solve(new int[]{1, 0, 0, -2, 3, -3, -1, 4}, 4)));
System.out.println(json(Solution16.solve(943)));
System.out.println(json(Solution17.solve(new int[]{5, 3, 4, 1, 2, 5, 6})));
}
static void batch700() {
System.out.println(json(Solution18.solve(new int[]{1, 0, 0, -2, 3, -3, -1, 4})));
System.out.println(json(Solution19.solve(new int[]{1, 0, 0, -2, 3, -3, -1, 4})));
System.out.println(json(Solution20.solve(new int[]{1, 0, 0, -2, 3, -3, -1, 4})));
System.out.println(json(Solution21.solve(new int[]{1, 1, 1, -2, 3, -3, -1, 4})));
System.out.println(json(Solution22.solve(new int[]{1, 0, 0, -2, 3, -3, -1, 4}, 6)));
System.out.println(json(Solution23.solve(new int[]{5, 3, 2}, 1)));
System.out.println(json(Solution24.solve("ccb", 4)));
System.out.println(json(Solution25.solve(new int[]{1, 0, 0, -2, 3, -3, -1, 4})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("ccb")));
System.out.println(json(Solution27.solve("CCB", 2)));
System.out.println(json(Solution28.solve(new int[]{0}, 2)));
System.out.println(json(Solution29.solve("ccb", "bb")));
System.out.println(json(Solution30.solve("ccb", "bb")));
System.out.println(json(Solution31.solve("ccb", "bb")));
System.out.println(json(Solution32.solve("cccbcbcacbbbac", new String[]{"b", "b"})));
System.out.println(json(Solution0.solve(new int[]{-2, -1, -1, 2, 3}, 8)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-2, -1, -1, 2, 3})));
System.out.println(json(Solution3.solve(new int[]{-2, -1, -1, 2, 3})));
System.out.println(json(Solution4.solve(new int[]{-2, -1, -1, 2, 3})));
System.out.println(json(Solution5.solve(new int[]{-2, -1, -1, 2, 3})));
System.out.println(json(Solution6.solve(new int[]{-1, -1, 3, -2, 2})));
System.out.println(json(Solution7.solve(new int[]{-1, -1, 3, -2, 2}, 8)));
System.out.println(json(Solution8.solve(new int[]{-1, -1, 3, -2, 2}, 8)));
System.out.println(json(Solution9.solve(new int[]{6, 3, 1, 4, 5, 3, 4}, 40)));
System.out.println(json(Solution10.solve(new int[]{2, 2, 2, 0, 1, 2})));
System.out.println(json(Solution11.solve(new int[]{-1, -1, 3, -2, 2}, 8)));
System.out.println(json(Solution12.solve("#ba", "#")));
System.out.println(json(Solution13.solve(new int[]{-1, -1, 3, -2, 2})));
System.out.println(json(Solution14.solve(new int[]{-1, -1, 3, -2, 2}, 0)));
System.out.println(json(Solution15.solve(new int[]{-1, -1, 3, -2, 2}, 0)));
System.out.println(json(Solution16.solve(857)));
System.out.println(json(Solution17.solve(new int[]{2, 8, 4, 1, 6, 3, 7, 3, 5})));
System.out.println(json(Solution18.solve(new int[]{-1, -1, 3, -2, 2})));
System.out.println(json(Solution19.solve(new int[]{-1, -1, 3, -2, 2})));
System.out.println(json(Solution20.solve(new int[]{-1, -1, 3, -2, 2})));
System.out.println(json(Solution21.solve(new int[]{-1, -1, 3, -2, 2})));
System.out.println(json(Solution22.solve(new int[]{-1, -1, 3, -2, 2}, 1)));
System.out.println(json(Solution23.solve(new int[]{6, 3, 1, 4, 5, 3, 4}, 5)));
System.out.println(json(Solution24.solve("c", 2)));
System.out.println(json(Solution25.solve(new int[]{-1, -1, 3, -2, 2})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("c")));
System.out.println(json(Solution27.solve("C", 0)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0}, 0)));
System.out.println(json(Solution29.solve("c", "bc")));
System.out.println(json(Solution30.solve("c", "bc")));
System.out.println(json(Solution31.solve("c", "bc")));
System.out.println(json(Solution32.solve("cbccababacacab", new String[]{"c", "c", "c"})));
System.out.println(json(Solution0.solve(new int[]{-3, 1, 2, 2, 3, 4}, -1)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-3, 1, 2, 2, 3, 4})));
System.out.println(json(Solution3.solve(new int[]{-3, 1, 2, 2, 3, 4})));
System.out.println(json(Solution4.solve(new int[]{-3, 1, 2, 2, 3, 4})));
System.out.println(json(Solution5.solve(new int[]{-3, 1, 2, 2, 3, 4})));
System.out.println(json(Solution6.solve(new int[]{-3, 4, 2, 2, 3, 1})));
System.out.println(json(Solution7.solve(new int[]{-3, 4, 2, 2, 3, 1}, -1)));
System.out.println(json(Solution8.solve(new int[]{-3, 4, 2, 2, 3, 1}, -1)));
System.out.println(json(Solution9.solve(new int[]{6}, 36)));
System.out.println(json(Solution10.solve(new int[]{2, 1})));
System.out.println(json(Solution11.solve(new int[]{-3, 4, 2, 2, 3, 1}, -1)));
System.out.println(json(Solution12.solve("#", "a##ba##ba")));
System.out.println(json(Solution13.solve(new int[]{-3, 4, 2, 2, 3, 1})));
System.out.println(json(Solution14.solve(new int[]{-3, 4, 2, 2, 3, 1}, 5)));
System.out.println(json(Solution15.solve(new int[]{-3, 4, 2, 2, 3, 1}, 5)));
System.out.println(json(Solution16.solve(15)));
System.out.println(json(Solution17.solve(new int[]{2, 3, 1, 6, 1, 5, 4})));
System.out.println(json(Solution18.solve(new int[]{-3, 4, 2, 2, 3, 1})));
System.out.println(json(Solution19.solve(new int[]{-3, 4, 2, 2, 3, 1})));
System.out.println(json(Solution20.solve(new int[]{-3, 4, 2, 2, 3, 1})));
System.out.println(json(Solution21.solve(new int[]{-3, 4, 2, 2, 3, 1})));
System.out.println(json(Solution22.solve(new int[]{-3, 4, 2, 2, 3, 1}, 6)));
System.out.println(json(Solution23.solve(new int[]{6}, 5)));
System.out.println(json(Solution24.solve("", 3)));
System.out.println(json(Solution25.solve(new int[]{-3, 4, 2, 2, 3, 1})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("")));
System.out.println(json(Solution27.solve("", 1)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0}, 1)));
System.out.println(json(Solution29.solve("", "aaa")));
System.out.println(json(Solution30.solve("", "aaa")));
System.out.println(json(Solution31.solve("", "aaa")));
System.out.println(json(Solution32.solve("cbbbbaaacabbbc", new String[]{"a", "c", "a"})));
System.out.println(json(Solution0.solve(new int[]{0, 1}, -1)));
System.out.println(json(Solution1.solve(new int[]{0})));
System.out.println(json(Solution2.solve(new int[]{0, 1})));
System.out.println(json(Solution3.solve(new int[]{0, 1})));
System.out.println(json(Solution4.solve(new int[]{0, 1})));
System.out.println(json(Solution5.solve(new int[]{0, 1})));
System.out.println(json(Solution6.solve(new int[]{1, 0, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{1, 0, 0, 1, 2}, -1)));
System.out.println(json(Solution8.solve(new int[]{1, 0, 0, 1, 2}, -1)));
System.out.println(json(Solution9.solve(new int[]{2, 4, 4, 5, 7, 7, 2}, 40)));
System.out.println(json(Solution10.solve(new int[]{2, 2, 2, 1})));
System.out.println(json(Solution11.solve(new int[]{1, 0}, -1)));
System.out.println(json(Solution12.solve("a##ba", "#")));
System.out.println(json(Solution13.solve(new int[]{1, 0})));
System.out.println(json(Solution14.solve(new int[]{1, 0}, -1)));
System.out.println(json(Solution15.solve(new int[]{1, 0}, -1)));
System.out.println(json(Solution16.solve(513)));
System.out.println(json(Solution17.solve(new int[]{2, 7, 4, 3, 1, 8, 3, 5, 6})));
System.out.println(json(Solution18.solve(new int[]{1, 0})));
}
static void batch800() {
System.out.println(json(Solution19.solve(new int[]{1, 0})));
System.out.println(json(Solution20.solve(new int[]{1, 0})));
System.out.println(json(Solution21.solve(new int[]{1, 1})));
System.out.println(json(Solution22.solve(new int[]{1, 0}, 2)));
System.out.println(json(Solution23.solve(new int[]{2, 4, 4, 5, 7, 7, 2}, 17)));
System.out.println(json(Solution24.solve("bac", 4)));
System.out.println(json(Solution25.solve(new int[]{1, 0})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("bac")));
System.out.println(json(Solution27.solve("BAC", 2)));
System.out.println(json(Solution28.solve(new int[]{0}, 2)));
System.out.println(json(Solution29.solve("bac", "cb")));
System.out.println(json(Solution30.solve("bac", "cb")));
System.out.println(json(Solution31.solve("bac", "cb")));
System.out.println(json(Solution32.solve("bbbcacabbaaaab", new String[]{"b"})));
System.out.println(json(Solution0.solve(new int[]{-3, -3, -2, -1, 3, 4}, -3)));
System.out.println(json(Solution1.solve(new int[]{0})));
System.out.println(json(Solution2.solve(new int[]{-3, -3, -2, -1, 3, 4})));
System.out.println(json(Solution3.solve(new int[]{-3, -3, -2, -1, 3, 4})));
System.out.println(json(Solution4.solve(new int[]{-3, -3, -2, -1, 3, 4})));
System.out.println(json(Solution5.solve(new int[]{-3, -3, -2, -1, 3, 4})));
System.out.println(json(Solution6.solve(new int[]{4, 3, -1, -3, -3, -2})));
System.out.println(json(Solution7.solve(new int[]{4, 3, -1, -3, -3, -2}, -3)));
System.out.println(json(Solution8.solve(new int[]{4, 3, -1, -3, -3, -2}, -3)));
System.out.println(json(Solution9.solve(new int[]{7, 6, 5, 7, 5, 1, 2}, 40)));
System.out.println(json(Solution10.solve(new int[]{1, 2})));
System.out.println(json(Solution11.solve(new int[]{4, 3, -1, -3, -3, -2}, -3)));
System.out.println(json(Solution12.solve("#ba", "#ba##ba##")));
System.out.println(json(Solution13.solve(new int[]{4, 3, -1, -3, -3, -2})));
System.out.println(json(Solution14.solve(new int[]{4, 3, -1, -3, -3, -2}, 1)));
System.out.println(json(Solution15.solve(new int[]{4, 3, -1, -3, -3, -2}, 1)));
System.out.println(json(Solution16.solve(621)));
System.out.println(json(Solution17.solve(new int[]{3, 4, 2, 1, 3})));
System.out.println(json(Solution18.solve(new int[]{4, 3, -1, -3, -3, -2})));
System.out.println(json(Solution19.solve(new int[]{4, 3, -1, -3, -3, -2})));
System.out.println(json(Solution20.solve(new int[]{4, 3, -1, -3, -3, -2})));
System.out.println(json(Solution21.solve(new int[]{4, 3, -1, -3, -3, -2})));
System.out.println(json(Solution22.solve(new int[]{4, 3, -1, -3, -3, -2}, 6)));
System.out.println(json(Solution23.solve(new int[]{7, 6, 5, 7, 5, 1, 2}, 5)));
System.out.println(json(Solution24.solve("ccacabac", 3)));
System.out.println(json(Solution25.solve(new int[]{4, 3, -1, -3, -3, -2})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("ccacabac")));
System.out.println(json(Solution27.solve("CCACABAC", 1)));
System.out.println(json(Solution28.solve(new int[]{0}, 1)));
System.out.println(json(Solution29.solve("ccacabac", "ac")));
System.out.println(json(Solution30.solve("ccacabac", "ac")));
System.out.println(json(Solution31.solve("ccacabac", "ac")));
System.out.println(json(Solution32.solve("aacbbaccabbbcc", new String[]{"bb", "ab", "ca"})));
System.out.println(json(Solution0.solve(new int[]{-4, -3, 0, 0, 1, 3, 4, 4}, 6)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, -3, 0, 0, 1, 3, 4, 4})));
System.out.println(json(Solution3.solve(new int[]{-4, -3, 0, 0, 1, 3, 4, 4})));
System.out.println(json(Solution4.solve(new int[]{-4, -3, 0, 0, 1, 3, 4, 4})));
System.out.println(json(Solution5.solve(new int[]{-4, -3, 0, 0, 1, 3, 4, 4})));
System.out.println(json(Solution6.solve(new int[]{3, 4, 1, -3, -4, 0, 0, 4})));
System.out.println(json(Solution7.solve(new int[]{3, 4, 1, -3, -4, 0, 0, 4}, 6)));
System.out.println(json(Solution8.solve(new int[]{3, 4, 1, -3, -4, 0, 0, 4}, 6)));
System.out.println(json(Solution9.solve(new int[]{2, 1, 4}, 32)));
System.out.println(json(Solution10.solve(new int[]{0, 1})));
System.out.println(json(Solution11.solve(new int[]{3, 4, 1, -3, -4, 0, 0, 4}, 6)));
System.out.println(json(Solution12.solve("#", "a")));
System.out.println(json(Solution13.solve(new int[]{3, 4, 1, -3, -4, 0, 0, 4})));
System.out.println(json(Solution14.solve(new int[]{3, 4, 1, -3, -4, 0, 0, 4}, 4)));
System.out.println(json(Solution15.solve(new int[]{3, 4, 1, -3, -4, 0, 0, 4}, 4)));
System.out.println(json(Solution16.solve(859)));
System.out.println(json(Solution17.solve(new int[]{2, 1, 1})));
System.out.println(json(Solution18.solve(new int[]{3, 4, 1, -3, -4, 0, 0, 4})));
System.out.println(json(Solution19.solve(new int[]{3, 4, 1, -3, -4, 0, 0, 4})));
System.out.println(json(Solution20.solve(new int[]{3, 4, 1, -3, -4, 0, 0, 4})));
System.out.println(json(Solution21.solve(new int[]{3, 4, 1, -3, -4, 1, 1, 4})));
System.out.println(json(Solution22.solve(new int[]{3, 4, 1, -3, -4, 0, 0, 4}, 6)));
System.out.println(json(Solution23.solve(new int[]{2, 1, 4}, 17)));
System.out.println(json(Solution24.solve("aaacc", 2)));
System.out.println(json(Solution25.solve(new int[]{3, 4, 1, -3, -4, 0, 0, 4})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("aaacc")));
System.out.println(json(Solution27.solve("AAACC", 0)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0}, 0)));
System.out.println(json(Solution29.solve("aaacc", "bc")));
System.out.println(json(Solution30.solve("aaacc", "bc")));
System.out.println(json(Solution31.solve("aaacc", "bc")));
System.out.println(json(Solution32.solve("accccbbbaccbbb", new String[]{"c"})));
System.out.println(json(Solution0.solve(new int[]{-3, -1, 0, 1}, 6)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-3, -1, 0, 1})));
System.out.println(json(Solution3.solve(new int[]{-3, -1, 0, 1})));
System.out.println(json(Solution4.solve(new int[]{-3, -1, 0, 1})));
System.out.println(json(Solution5.solve(new int[]{-3, -1, 0, 1})));
System.out.println(json(Solution6.solve(new int[]{0, -1, 1, -3})));
System.out.println(json(Solution7.solve(new int[]{0, -1, 1, -3}, 6)));
System.out.println(json(Solution8.solve(new int[]{0, -1, 1, -3}, 6)));
System.out.println(json(Solution9.solve(new int[]{6, 3, 7}, 40)));
System.out.println(json(Solution10.solve(new int[]{0, 0})));
System.out.println(json(Solution11.solve(new int[]{0, -1, 1, -3}, 6)));
System.out.println(json(Solution12.solve("#ba##ba##", "a##ba##")));
System.out.println(json(Solution13.solve(new int[]{0, -1, 1, -3})));
System.out.println(json(Solution14.solve(new int[]{0, -1, 1, -3}, -1)));
System.out.println(json(Solution15.solve(new int[]{0, -1, 1, -3}, -1)));
System.out.println(json(Solution16.solve(89)));
System.out.println(json(Solution17.solve(new int[]{7, 3, 4, 6, 2, 1, 8, 3, 5})));
System.out.println(json(Solution18.solve(new int[]{0, -1, 1, -3})));
System.out.println(json(Solution19.solve(new int[]{0, -1, 1, -3})));
}
static void batch900() {
System.out.println(json(Solution20.solve(new int[]{0, -1, 1, -3})));
System.out.println(json(Solution21.solve(new int[]{1, -1, 1, -3})));
System.out.println(json(Solution22.solve(new int[]{0, -1, 1, -3}, 2)));
System.out.println(json(Solution23.solve(new int[]{6, 3, 7}, 5)));
System.out.println(json(Solution24.solve("acabc", 2)));
System.out.println(json(Solution25.solve(new int[]{0, -1, 1, -3})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("acabc")));
System.out.println(json(Solution27.solve("ACABC", 0)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0}, 0)));
System.out.println(json(Solution29.solve("acabc", "bcb")));
System.out.println(json(Solution30.solve("acabc", "bcb")));
System.out.println(json(Solution31.solve("acabc", "bcb")));
System.out.println(json(Solution32.solve("acacacbababaaa", new String[]{"bc"})));
System.out.println(json(Solution0.solve(new int[]{-3, -1}, 5)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-3, -1})));
System.out.println(json(Solution3.solve(new int[]{-3, -1})));
System.out.println(json(Solution4.solve(new int[]{-3, -1})));
System.out.println(json(Solution5.solve(new int[]{-3, -1})));
System.out.println(json(Solution6.solve(new int[]{-3, -1, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{-3, -1, 0, 1, 2}, 5)));
System.out.println(json(Solution8.solve(new int[]{-3, -1, 0, 1, 2}, 5)));
System.out.println(json(Solution9.solve(new int[]{5, 3, 2}, 40)));
System.out.println(json(Solution10.solve(new int[]{0, 1, 1, 2, 0, 2})));
System.out.println(json(Solution11.solve(new int[]{-3, -1}, 5)));
System.out.println(json(Solution12.solve("#ba##", "a##ba")));
System.out.println(json(Solution13.solve(new int[]{-3, -1})));
System.out.println(json(Solution14.solve(new int[]{-3, -1}, -1)));
System.out.println(json(Solution15.solve(new int[]{-3, -1}, -1)));
System.out.println(json(Solution16.solve(279)));
System.out.println(json(Solution17.solve(new int[]{2, 4, 3, 1, 5, 5, 6})));
System.out.println(json(Solution18.solve(new int[]{-3, -1})));
System.out.println(json(Solution19.solve(new int[]{-3, -1})));
System.out.println(json(Solution20.solve(new int[]{-3, -1})));
System.out.println(json(Solution21.solve(new int[]{-3, -1})));
System.out.println(json(Solution22.solve(new int[]{-3, -1}, 2)));
System.out.println(json(Solution23.solve(new int[]{5, 3, 2}, 9)));
System.out.println(json(Solution24.solve("ccbcbccc", 3)));
System.out.println(json(Solution25.solve(new int[]{-3, -1})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("ccbcbccc")));
System.out.println(json(Solution27.solve("CCBCBCCC", 1)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0}, 1)));
System.out.println(json(Solution29.solve("ccbcbccc", "bca")));
System.out.println(json(Solution30.solve("ccbcbccc", "bca")));
System.out.println(json(Solution31.solve("ccbcbccc", "bca")));
System.out.println(json(Solution32.solve("baababcbacacac", new String[]{"a", "a"})));
System.out.println(json(Solution0.solve(new int[]{-2, 1, 2}, 1)));
System.out.println(json(Solution1.solve(new int[]{0})));
System.out.println(json(Solution2.solve(new int[]{-2, 1, 2})));
System.out.println(json(Solution3.solve(new int[]{-2, 1, 2})));
System.out.println(json(Solution4.solve(new int[]{-2, 1, 2})));
System.out.println(json(Solution5.solve(new int[]{-2, 1, 2})));
System.out.println(json(Solution6.solve(new int[]{-2, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{-2, 1, 2}, 1)));
System.out.println(json(Solution8.solve(new int[]{-2, 1, 2}, 1)));
System.out.println(json(Solution9.solve(new int[]{4, 4, 5, 5, 7, 6, 4}, 16)));
System.out.println(json(Solution10.solve(new int[]{2, 0, 0, 2, 2, 1})));
System.out.println(json(Solution11.solve(new int[]{-2, 1, 2}, 1)));
System.out.println(json(Solution12.solve("#ba##", "a##ba##ba")));
System.out.println(json(Solution13.solve(new int[]{-2, 1, 2})));
System.out.println(json(Solution14.solve(new int[]{-2, 1, 2}, 2)));
System.out.println(json(Solution15.solve(new int[]{-2, 1, 2}, 2)));
System.out.println(json(Solution16.solve(715)));
System.out.println(json(Solution17.solve(new int[]{2, 1, 1})));
System.out.println(json(Solution18.solve(new int[]{-2, 1, 2})));
System.out.println(json(Solution19.solve(new int[]{-2, 1, 2})));
System.out.println(json(Solution20.solve(new int[]{-2, 1, 2})));
System.out.println(json(Solution21.solve(new int[]{-2, 1, 2})));
System.out.println(json(Solution22.solve(new int[]{-2, 1, 2}, 2)));
System.out.println(json(Solution23.solve(new int[]{4, 4, 5, 5, 7, 6, 4}, 17)));
System.out.println(json(Solution24.solve("cccaaaa", 4)));
System.out.println(json(Solution25.solve(new int[]{-2, 1, 2})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("cccaaaa")));
System.out.println(json(Solution27.solve("CCCAAAA", 2)));
System.out.println(json(Solution28.solve(new int[]{0}, 2)));
System.out.println(json(Solution29.solve("cccaaaa", "a")));
System.out.println(json(Solution30.solve("cccaaaa", "a")));
System.out.println(json(Solution31.solve("cccaaaa", "a")));
System.out.println(json(Solution32.solve("cbaaccacacbacb", new String[]{"ba", "ac"})));
System.out.println(json(Solution0.solve(new int[]{-4, -4, -2, -2, 0, 2, 3, 4}, 6)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, -4, -2, -2, 0, 2, 3, 4})));
System.out.println(json(Solution3.solve(new int[]{-4, -4, -2, -2, 0, 2, 3, 4})));
System.out.println(json(Solution4.solve(new int[]{-4, -4, -2, -2, 0, 2, 3, 4})));
System.out.println(json(Solution5.solve(new int[]{-4, -4, -2, -2, 0, 2, 3, 4})));
System.out.println(json(Solution6.solve(new int[]{-4, 3, -4, -2, 2, 4, 0, -2})));
System.out.println(json(Solution7.solve(new int[]{-4, 3, -4, -2, 2, 4, 0, -2}, 6)));
System.out.println(json(Solution8.solve(new int[]{-4, 3, -4, -2, 2, 4, 0, -2}, 6)));
System.out.println(json(Solution9.solve(new int[]{6, 1, 1}, 8)));
System.out.println(json(Solution10.solve(new int[]{0, 0, 1, 2, 0, 0, 0, 0})));
System.out.println(json(Solution11.solve(new int[]{-4, 3, -4, -2, 2, 4, 0, -2}, 6)));
System.out.println(json(Solution12.solve("a", "#")));
System.out.println(json(Solution13.solve(new int[]{-4, 3, -4, -2, 2, 4, 0, -2})));
System.out.println(json(Solution14.solve(new int[]{-4, 3, -4, -2, 2, 4, 0, -2}, 7)));
System.out.println(json(Solution15.solve(new int[]{-4, 3, -4, -2, 2, 4, 0, -2}, 7)));
System.out.println(json(Solution16.solve(1129)));
System.out.println(json(Solution17.solve(new int[]{2, 7, 8, 1, 4, 3, 6, 5, 3})));
System.out.println(json(Solution18.solve(new int[]{-4, 3, -4, -2, 2, 4, 0, -2})));
System.out.println(json(Solution19.solve(new int[]{-4, 3, -4, -2, 2, 4, 0, -2})));
System.out.println(json(Solution20.solve(new int[]{-4, 3, -4, -2, 2, 4, 0, -2})));
}
static void batch1000() {
System.out.println(json(Solution21.solve(new int[]{-4, 3, -4, -2, 2, 4, 1, -2})));
System.out.println(json(Solution22.solve(new int[]{-4, 3, -4, -2, 2, 4, 0, -2}, 2)));
System.out.println(json(Solution23.solve(new int[]{6, 1, 1}, 5)));
System.out.println(json(Solution24.solve("cbbbba", 1)));
System.out.println(json(Solution25.solve(new int[]{-4, 3, -4, -2, 2, 4, 0, -2})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("cbbbba")));
System.out.println(json(Solution27.solve("CBBBBA", 3)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0}, 3)));
System.out.println(json(Solution29.solve("cbbbba", "b")));
System.out.println(json(Solution30.solve("cbbbba", "b")));
System.out.println(json(Solution31.solve("cbbbba", "b")));
System.out.println(json(Solution32.solve("cbbbcaaaabaaaa", new String[]{"c", "a", "a"})));
System.out.println(json(Solution0.solve(new int[]{-4, 1}, 7)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, 1})));
System.out.println(json(Solution3.solve(new int[]{-4, 1})));
System.out.println(json(Solution4.solve(new int[]{-4, 1})));
System.out.println(json(Solution5.solve(new int[]{-4, 1})));
System.out.println(json(Solution6.solve(new int[]{1, -4, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{1, -4, 0, 1, 2}, 7)));
System.out.println(json(Solution8.solve(new int[]{1, -4, 0, 1, 2}, 7)));
System.out.println(json(Solution9.solve(new int[]{7}, 20)));
System.out.println(json(Solution10.solve(new int[]{1, 0})));
System.out.println(json(Solution11.solve(new int[]{1, -4}, 7)));
System.out.println(json(Solution12.solve("#ba##ba##", "a##ba")));
System.out.println(json(Solution13.solve(new int[]{1, -4})));
System.out.println(json(Solution14.solve(new int[]{1, -4}, 0)));
System.out.println(json(Solution15.solve(new int[]{1, -4}, 0)));
System.out.println(json(Solution16.solve(1491)));
System.out.println(json(Solution17.solve(new int[]{2, 1, 1})));
System.out.println(json(Solution18.solve(new int[]{1, -4})));
System.out.println(json(Solution19.solve(new int[]{1, -4})));
System.out.println(json(Solution20.solve(new int[]{1, -4})));
System.out.println(json(Solution21.solve(new int[]{1, -4})));
System.out.println(json(Solution22.solve(new int[]{1, -4}, 2)));
System.out.println(json(Solution23.solve(new int[]{7}, 17)));
System.out.println(json(Solution24.solve("cbabc", 2)));
System.out.println(json(Solution25.solve(new int[]{1, -4})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("cbabc")));
System.out.println(json(Solution27.solve("CBABC", 0)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0}, 0)));
System.out.println(json(Solution29.solve("cbabc", "ba")));
System.out.println(json(Solution30.solve("cbabc", "ba")));
System.out.println(json(Solution31.solve("cbabc", "ba")));
System.out.println(json(Solution32.solve("aaccabaabacbcc", new String[]{"b"})));
System.out.println(json(Solution0.solve(new int[]{-3, 1, 1, 3}, -8)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-3, 1, 1, 3})));
System.out.println(json(Solution3.solve(new int[]{-3, 1, 1, 3})));
System.out.println(json(Solution4.solve(new int[]{-3, 1, 1, 3})));
System.out.println(json(Solution5.solve(new int[]{-3, 1, 1, 3})));
System.out.println(json(Solution6.solve(new int[]{3, 1, 1, -3})));
System.out.println(json(Solution7.solve(new int[]{3, 1, 1, -3}, -8)));
System.out.println(json(Solution8.solve(new int[]{3, 1, 1, -3}, -8)));
System.out.println(json(Solution9.solve(new int[]{1, 1, 1}, 24)));
System.out.println(json(Solution10.solve(new int[]{})));
System.out.println(json(Solution11.solve(new int[]{3, 1, 1, -3}, -8)));
System.out.println(json(Solution12.solve("a##", "a##ba##ba")));
System.out.println(json(Solution13.solve(new int[]{3, 1, 1, -3})));
System.out.println(json(Solution14.solve(new int[]{3, 1, 1, -3}, 1)));
System.out.println(json(Solution15.solve(new int[]{3, 1, 1, -3}, 1)));
System.out.println(json(Solution16.solve(1627)));
System.out.println(json(Solution17.solve(new int[]{2, 1, 1})));
System.out.println(json(Solution18.solve(new int[]{3, 1, 1, -3})));
System.out.println(json(Solution19.solve(new int[]{3, 1, 1, -3})));
System.out.println(json(Solution20.solve(new int[]{3, 1, 1, -3})));
System.out.println(json(Solution21.solve(new int[]{3, 1, 1, -3})));
System.out.println(json(Solution22.solve(new int[]{3, 1, 1, -3}, 2)));
System.out.println(json(Solution23.solve(new int[]{1, 1, 1}, 17)));
System.out.println(json(Solution24.solve("ac", 1)));
System.out.println(json(Solution25.solve(new int[]{3, 1, 1, -3})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("ac")));
System.out.println(json(Solution27.solve("AC", 3)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0}, 3)));
System.out.println(json(Solution29.solve("ac", "bb")));
System.out.println(json(Solution30.solve("ac", "bb")));
System.out.println(json(Solution31.solve("ac", "bb")));
System.out.println(json(Solution32.solve("cccabababbbabc", new String[]{"cb", "aa", "bb"})));
System.out.println(json(Solution0.solve(new int[]{-3, 0, 1, 1, 3, 3}, 3)));
System.out.println(json(Solution1.solve(new int[]{0})));
System.out.println(json(Solution2.solve(new int[]{-3, 0, 1, 1, 3, 3})));
System.out.println(json(Solution3.solve(new int[]{-3, 0, 1, 1, 3, 3})));
System.out.println(json(Solution4.solve(new int[]{-3, 0, 1, 1, 3, 3})));
System.out.println(json(Solution5.solve(new int[]{-3, 0, 1, 1, 3, 3})));
System.out.println(json(Solution6.solve(new int[]{3, -3, 1, 3, 0, 1})));
System.out.println(json(Solution7.solve(new int[]{3, -3, 1, 3, 0, 1}, 3)));
System.out.println(json(Solution8.solve(new int[]{3, -3, 1, 3, 0, 1}, 3)));
System.out.println(json(Solution9.solve(new int[]{4, 5, 7, 7, 1, 5, 3}, 24)));
System.out.println(json(Solution10.solve(new int[]{2, 1, 1, 0})));
System.out.println(json(Solution11.solve(new int[]{3, -3, 1, 3, 0, 1}, 3)));
System.out.println(json(Solution12.solve("a##ba##ba", "#ba##ba##")));
System.out.println(json(Solution13.solve(new int[]{3, -3, 1, 3, 0, 1})));
System.out.println(json(Solution14.solve(new int[]{3, -3, 1, 3, 0, 1}, -1)));
System.out.println(json(Solution15.solve(new int[]{3, -3, 1, 3, 0, 1}, -1)));
System.out.println(json(Solution16.solve(925)));
System.out.println(json(Solution17.solve(new int[]{2, 3, 3, 1, 4})));
System.out.println(json(Solution18.solve(new int[]{3, -3, 1, 3, 0, 1})));
System.out.println(json(Solution19.solve(new int[]{3, -3, 1, 3, 0, 1})));
System.out.println(json(Solution20.solve(new int[]{3, -3, 1, 3, 0, 1})));
System.out.println(json(Solution21.solve(new int[]{3, -3, 1, 3, 1, 1})));
}
static void batch1100() {
System.out.println(json(Solution22.solve(new int[]{3, -3, 1, 3, 0, 1}, 6)));
System.out.println(json(Solution23.solve(new int[]{4, 5, 7, 7, 1, 5, 3}, 17)));
System.out.println(json(Solution24.solve("abcab", 2)));
System.out.println(json(Solution25.solve(new int[]{3, -3, 1, 3, 0, 1})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("abcab")));
System.out.println(json(Solution27.solve("ABCAB", 0)));
System.out.println(json(Solution28.solve(new int[]{0}, 0)));
System.out.println(json(Solution29.solve("abcab", "aa")));
System.out.println(json(Solution30.solve("abcab", "aa")));
System.out.println(json(Solution31.solve("abcab", "aa")));
System.out.println(json(Solution32.solve("bacabccccaabab", new String[]{"b", "b"})));
System.out.println(json(Solution0.solve(new int[]{-3, -3, 0}, 3)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-3, -3, 0})));
System.out.println(json(Solution3.solve(new int[]{-3, -3, 0})));
System.out.println(json(Solution4.solve(new int[]{-3, -3, 0})));
System.out.println(json(Solution5.solve(new int[]{-3, -3, 0})));
System.out.println(json(Solution6.solve(new int[]{-3, 0, -3})));
System.out.println(json(Solution7.solve(new int[]{-3, 0, -3}, 3)));
System.out.println(json(Solution8.solve(new int[]{-3, 0, -3}, 3)));
System.out.println(json(Solution9.solve(new int[]{6}, 12)));
System.out.println(json(Solution10.solve(new int[]{1, 1, 2, 1, 0, 1})));
System.out.println(json(Solution11.solve(new int[]{-3, 0, -3}, 3)));
System.out.println(json(Solution12.solve("#ba##ba", "#ba##ba")));
System.out.println(json(Solution13.solve(new int[]{-3, 0, -3})));
System.out.println(json(Solution14.solve(new int[]{-3, 0, -3}, 2)));
System.out.println(json(Solution15.solve(new int[]{-3, 0, -3}, 2)));
System.out.println(json(Solution16.solve(1735)));
System.out.println(json(Solution17.solve(new int[]{6, 4, 2, 1, 3, 5, 5})));
System.out.println(json(Solution18.solve(new int[]{-3, 0, -3})));
System.out.println(json(Solution19.solve(new int[]{-3, 0, -3})));
System.out.println(json(Solution20.solve(new int[]{-3, 0, -3})));
System.out.println(json(Solution21.solve(new int[]{-3, 1, -3})));
System.out.println(json(Solution22.solve(new int[]{-3, 0, -3}, 1)));
System.out.println(json(Solution23.solve(new int[]{6}, 1)));
System.out.println(json(Solution24.solve("bcbac", 2)));
System.out.println(json(Solution25.solve(new int[]{-3, 0, -3})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("bcbac")));
System.out.println(json(Solution27.solve("BCBAC", 0)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0}, 0)));
System.out.println(json(Solution29.solve("bcbac", "ca")));
System.out.println(json(Solution30.solve("bcbac", "ca")));
System.out.println(json(Solution31.solve("bcbac", "ca")));
System.out.println(json(Solution32.solve("acbcababcbcbca", new String[]{"c", "c", "c"})));
System.out.println(json(Solution0.solve(new int[]{-4, -4, -3, -2, 0, 0}, -6)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, -4, -3, -2, 0, 0})));
System.out.println(json(Solution3.solve(new int[]{-4, -4, -3, -2, 0, 0})));
System.out.println(json(Solution4.solve(new int[]{-4, -4, -3, -2, 0, 0})));
System.out.println(json(Solution5.solve(new int[]{-4, -4, -3, -2, 0, 0})));
System.out.println(json(Solution6.solve(new int[]{0, -4, -2, 0, -4, -3})));
System.out.println(json(Solution7.solve(new int[]{0, -4, -2, 0, -4, -3}, -6)));
System.out.println(json(Solution8.solve(new int[]{0, -4, -2, 0, -4, -3}, -6)));
System.out.println(json(Solution9.solve(new int[]{5, 1, 4, 2, 3}, 28)));
System.out.println(json(Solution10.solve(new int[]{2, 1, 1, 2, 2, 2})));
System.out.println(json(Solution11.solve(new int[]{0, -4, -2, 0, -4, -3}, -6)));
System.out.println(json(Solution12.solve("#ba", "#")));
System.out.println(json(Solution13.solve(new int[]{0, -4, -2, 0, -4, -3})));
System.out.println(json(Solution14.solve(new int[]{0, -4, -2, 0, -4, -3}, 1)));
System.out.println(json(Solution15.solve(new int[]{0, -4, -2, 0, -4, -3}, 1)));
System.out.println(json(Solution16.solve(981)));
System.out.println(json(Solution17.solve(new int[]{3, 3, 4, 1, 2})));
System.out.println(json(Solution18.solve(new int[]{0, -4, -2, 0, -4, -3})));
System.out.println(json(Solution19.solve(new int[]{0, -4, -2, 0, -4, -3})));
System.out.println(json(Solution20.solve(new int[]{0, -4, -2, 0, -4, -3})));
System.out.println(json(Solution21.solve(new int[]{1, -4, -2, 1, -4, -3})));
System.out.println(json(Solution22.solve(new int[]{0, -4, -2, 0, -4, -3}, 4)));
System.out.println(json(Solution23.solve(new int[]{5, 1, 4, 2, 3}, 17)));
System.out.println(json(Solution24.solve("ccb", 4)));
System.out.println(json(Solution25.solve(new int[]{0, -4, -2, 0, -4, -3})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("ccb")));
System.out.println(json(Solution27.solve("CCB", 2)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0}, 2)));
System.out.println(json(Solution29.solve("ccb", "b")));
System.out.println(json(Solution30.solve("ccb", "b")));
System.out.println(json(Solution31.solve("ccb", "b")));
System.out.println(json(Solution32.solve("cabccccabccbbc", new String[]{"ba", "cc", "cc"})));
System.out.println(json(Solution0.solve(new int[]{-4, 2}, -1)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, 2})));
System.out.println(json(Solution3.solve(new int[]{-4, 2})));
System.out.println(json(Solution4.solve(new int[]{-4, 2})));
System.out.println(json(Solution5.solve(new int[]{-4, 2})));
System.out.println(json(Solution6.solve(new int[]{2, -4, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{2, -4, 0, 1, 2}, -1)));
System.out.println(json(Solution8.solve(new int[]{2, -4, 0, 1, 2}, -1)));
System.out.println(json(Solution9.solve(new int[]{5, 3, 5, 4, 6}, 36)));
System.out.println(json(Solution10.solve(new int[]{2, 0, 0, 2, 0, 0})));
System.out.println(json(Solution11.solve(new int[]{2, -4}, -1)));
System.out.println(json(Solution12.solve("#ba##ba##", "a##")));
System.out.println(json(Solution13.solve(new int[]{2, -4})));
System.out.println(json(Solution14.solve(new int[]{2, -4}, 0)));
System.out.println(json(Solution15.solve(new int[]{2, -4}, 0)));
System.out.println(json(Solution16.solve(1205)));
System.out.println(json(Solution17.solve(new int[]{3, 3, 2, 1, 4})));
System.out.println(json(Solution18.solve(new int[]{2, -4})));
System.out.println(json(Solution19.solve(new int[]{2, -4})));
System.out.println(json(Solution20.solve(new int[]{2, -4})));
System.out.println(json(Solution21.solve(new int[]{2, -4})));
System.out.println(json(Solution22.solve(new int[]{2, -4}, 2)));
}
static void batch1200() {
System.out.println(json(Solution23.solve(new int[]{5, 3, 5, 4, 6}, 1)));
System.out.println(json(Solution24.solve("bbccac", 1)));
System.out.println(json(Solution25.solve(new int[]{2, -4})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("bbccac")));
System.out.println(json(Solution27.solve("BBCCAC", 3)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0}, 3)));
System.out.println(json(Solution29.solve("bbccac", "b")));
System.out.println(json(Solution30.solve("bbccac", "b")));
System.out.println(json(Solution31.solve("bbccac", "b")));
System.out.println(json(Solution32.solve("baacbbcbacbaba", new String[]{"a", "b", "c"})));
System.out.println(json(Solution0.solve(new int[]{-1, 1}, -2)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-1, 1})));
System.out.println(json(Solution3.solve(new int[]{-1, 1})));
System.out.println(json(Solution4.solve(new int[]{-1, 1})));
System.out.println(json(Solution5.solve(new int[]{-1, 1})));
System.out.println(json(Solution6.solve(new int[]{1, -1, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{1, -1, 0, 1, 2}, -2)));
System.out.println(json(Solution8.solve(new int[]{1, -1, 0, 1, 2}, -2)));
System.out.println(json(Solution9.solve(new int[]{4, 7, 1}, 40)));
System.out.println(json(Solution10.solve(new int[]{1, 1, 1, 0, 0, 2, 1, 1})));
System.out.println(json(Solution11.solve(new int[]{1, -1}, -2)));
System.out.println(json(Solution12.solve("a##ba##ba", "#ba")));
System.out.println(json(Solution13.solve(new int[]{1, -1})));
System.out.println(json(Solution14.solve(new int[]{1, -1}, 0)));
System.out.println(json(Solution15.solve(new int[]{1, -1}, 0)));
System.out.println(json(Solution16.solve(723)));
System.out.println(json(Solution17.solve(new int[]{1, 1, 2})));
System.out.println(json(Solution18.solve(new int[]{1, -1})));
System.out.println(json(Solution19.solve(new int[]{1, -1})));
System.out.println(json(Solution20.solve(new int[]{1, -1})));
System.out.println(json(Solution21.solve(new int[]{1, -1})));
System.out.println(json(Solution22.solve(new int[]{1, -1}, 2)));
System.out.println(json(Solution23.solve(new int[]{4, 7, 1}, 13)));
System.out.println(json(Solution24.solve("ccb", 4)));
System.out.println(json(Solution25.solve(new int[]{1, -1})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("ccb")));
System.out.println(json(Solution27.solve("CCB", 2)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0}, 2)));
System.out.println(json(Solution29.solve("ccb", "a")));
System.out.println(json(Solution30.solve("ccb", "a")));
System.out.println(json(Solution31.solve("ccb", "a")));
System.out.println(json(Solution32.solve("cacbabaaababaa", new String[]{"cc"})));
System.out.println(json(Solution0.solve(new int[]{-3, 0}, 0)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-3, 0})));
System.out.println(json(Solution3.solve(new int[]{-3, 0})));
System.out.println(json(Solution4.solve(new int[]{-3, 0})));
System.out.println(json(Solution5.solve(new int[]{-3, 0})));
System.out.println(json(Solution6.solve(new int[]{0, -3, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{0, -3, 0, 1, 2}, 0)));
System.out.println(json(Solution8.solve(new int[]{0, -3, 0, 1, 2}, 0)));
System.out.println(json(Solution9.solve(new int[]{5, 1, 7, 5, 1, 6, 7}, 40)));
System.out.println(json(Solution10.solve(new int[]{1, 1, 2, 2, 1, 1})));
System.out.println(json(Solution11.solve(new int[]{0, -3}, 0)));
System.out.println(json(Solution12.solve("#ba##ba##", "a##ba##ba")));
System.out.println(json(Solution13.solve(new int[]{0, -3})));
System.out.println(json(Solution14.solve(new int[]{0, -3}, -1)));
System.out.println(json(Solution15.solve(new int[]{0, -3}, -1)));
System.out.println(json(Solution16.solve(1959)));
System.out.println(json(Solution17.solve(new int[]{5, 6, 1, 1, 2, 3, 4})));
System.out.println(json(Solution18.solve(new int[]{0, -3})));
System.out.println(json(Solution19.solve(new int[]{0, -3})));
System.out.println(json(Solution20.solve(new int[]{0, -3})));
System.out.println(json(Solution21.solve(new int[]{1, -3})));
System.out.println(json(Solution22.solve(new int[]{0, -3}, 2)));
System.out.println(json(Solution23.solve(new int[]{5, 1, 7, 5, 1, 6, 7}, 13)));
System.out.println(json(Solution24.solve("ccacb", 2)));
System.out.println(json(Solution25.solve(new int[]{0, -3})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("ccacb")));
System.out.println(json(Solution27.solve("CCACB", 0)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0}, 0)));
System.out.println(json(Solution29.solve("ccacb", "a")));
System.out.println(json(Solution30.solve("ccacb", "a")));
System.out.println(json(Solution31.solve("ccacb", "a")));
System.out.println(json(Solution32.solve("aabcabccccbcbb", new String[]{"ba"})));
System.out.println(json(Solution0.solve(new int[]{-2, -1, 1, 4}, 7)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-2, -1, 1, 4})));
System.out.println(json(Solution3.solve(new int[]{-2, -1, 1, 4})));
System.out.println(json(Solution4.solve(new int[]{-2, -1, 1, 4})));
System.out.println(json(Solution5.solve(new int[]{-2, -1, 1, 4})));
System.out.println(json(Solution6.solve(new int[]{4, -2, 1, -1})));
System.out.println(json(Solution7.solve(new int[]{4, -2, 1, -1}, 7)));
System.out.println(json(Solution8.solve(new int[]{4, -2, 1, -1}, 7)));
System.out.println(json(Solution9.solve(new int[]{7, 6, 7}, 8)));
System.out.println(json(Solution10.solve(new int[]{2, 1, 1, 0})));
System.out.println(json(Solution11.solve(new int[]{4, -2, 1, -1}, 7)));
System.out.println(json(Solution12.solve("a##", "a##ba##")));
System.out.println(json(Solution13.solve(new int[]{4, -2, 1, -1})));
System.out.println(json(Solution14.solve(new int[]{4, -2, 1, -1}, -1)));
System.out.println(json(Solution15.solve(new int[]{4, -2, 1, -1}, -1)));
System.out.println(json(Solution16.solve(1349)));
System.out.println(json(Solution17.solve(new int[]{2, 3, 4, 3, 1})));
System.out.println(json(Solution18.solve(new int[]{4, -2, 1, -1})));
System.out.println(json(Solution19.solve(new int[]{4, -2, 1, -1})));
System.out.println(json(Solution20.solve(new int[]{4, -2, 1, -1})));
System.out.println(json(Solution21.solve(new int[]{4, -2, 1, -1})));
System.out.println(json(Solution22.solve(new int[]{4, -2, 1, -1}, 2)));
System.out.println(json(Solution23.solve(new int[]{7, 6, 7}, 9)));
}
static void batch1300() {
System.out.println(json(Solution24.solve("", 3)));
System.out.println(json(Solution25.solve(new int[]{4, -2, 1, -1})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("")));
System.out.println(json(Solution27.solve("", 1)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0}, 1)));
System.out.println(json(Solution29.solve("", "acb")));
System.out.println(json(Solution30.solve("", "acb")));
System.out.println(json(Solution31.solve("", "acb")));
System.out.println(json(Solution32.solve("abcbaaabcbbbba", new String[]{"b"})));
System.out.println(json(Solution0.solve(new int[]{-3, -1, 1, 2}, 7)));
System.out.println(json(Solution1.solve(new int[]{0})));
System.out.println(json(Solution2.solve(new int[]{-3, -1, 1, 2})));
System.out.println(json(Solution3.solve(new int[]{-3, -1, 1, 2})));
System.out.println(json(Solution4.solve(new int[]{-3, -1, 1, 2})));
System.out.println(json(Solution5.solve(new int[]{-3, -1, 1, 2})));
System.out.println(json(Solution6.solve(new int[]{1, 2, -3, -1})));
System.out.println(json(Solution7.solve(new int[]{1, 2, -3, -1}, 7)));
System.out.println(json(Solution8.solve(new int[]{1, 2, -3, -1}, 7)));
System.out.println(json(Solution9.solve(new int[]{3, 1, 6}, 24)));
System.out.println(json(Solution10.solve(new int[]{2, 2, 0, 1})));
System.out.println(json(Solution11.solve(new int[]{1, 2, -3, -1}, 7)));
System.out.println(json(Solution12.solve("a##ba", "#")));
System.out.println(json(Solution13.solve(new int[]{1, 2, -3, -1})));
System.out.println(json(Solution14.solve(new int[]{1, 2, -3, -1}, 2)));
System.out.println(json(Solution15.solve(new int[]{1, 2, -3, -1}, 2)));
System.out.println(json(Solution16.solve(961)));
System.out.println(json(Solution17.solve(new int[]{3, 4, 8, 7, 2, 3, 1, 5, 6})));
System.out.println(json(Solution18.solve(new int[]{1, 2, -3, -1})));
System.out.println(json(Solution19.solve(new int[]{1, 2, -3, -1})));
System.out.println(json(Solution20.solve(new int[]{1, 2, -3, -1})));
System.out.println(json(Solution21.solve(new int[]{1, 2, -3, -1})));
System.out.println(json(Solution22.solve(new int[]{1, 2, -3, -1}, 2)));
System.out.println(json(Solution23.solve(new int[]{3, 1, 6}, 1)));
System.out.println(json(Solution24.solve("ccbccab", 4)));
System.out.println(json(Solution25.solve(new int[]{1, 2, -3, -1})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("ccbccab")));
System.out.println(json(Solution27.solve("CCBCCAB", 2)));
System.out.println(json(Solution28.solve(new int[]{0}, 2)));
System.out.println(json(Solution29.solve("ccbccab", "cab")));
System.out.println(json(Solution30.solve("ccbccab", "cab")));
System.out.println(json(Solution31.solve("ccbccab", "cab")));
System.out.println(json(Solution32.solve("bcbccabacccaba", new String[]{"aa"})));
System.out.println(json(Solution0.solve(new int[]{-2, 2, 2, 4}, 2)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-2, 2, 2, 4})));
System.out.println(json(Solution3.solve(new int[]{-2, 2, 2, 4})));
System.out.println(json(Solution4.solve(new int[]{-2, 2, 2, 4})));
System.out.println(json(Solution5.solve(new int[]{-2, 2, 2, 4})));
System.out.println(json(Solution6.solve(new int[]{2, 2, 4, -2})));
System.out.println(json(Solution7.solve(new int[]{2, 2, 4, -2}, 2)));
System.out.println(json(Solution8.solve(new int[]{2, 2, 4, -2}, 2)));
System.out.println(json(Solution9.solve(new int[]{1}, 4)));
System.out.println(json(Solution10.solve(new int[]{1, 0, 0, 2, 0, 2, 2, 0})));
System.out.println(json(Solution11.solve(new int[]{2, 2, 4, -2}, 2)));
System.out.println(json(Solution12.solve("a##ba##", "a")));
System.out.println(json(Solution13.solve(new int[]{2, 2, 4, -2})));
System.out.println(json(Solution14.solve(new int[]{2, 2, 4, -2}, 3)));
System.out.println(json(Solution15.solve(new int[]{2, 2, 4, -2}, 3)));
System.out.println(json(Solution16.solve(1107)));
System.out.println(json(Solution17.solve(new int[]{2, 1, 1})));
System.out.println(json(Solution18.solve(new int[]{2, 2, 4, -2})));
System.out.println(json(Solution19.solve(new int[]{2, 2, 4, -2})));
System.out.println(json(Solution20.solve(new int[]{2, 2, 4, -2})));
System.out.println(json(Solution21.solve(new int[]{2, 2, 4, -2})));
System.out.println(json(Solution22.solve(new int[]{2, 2, 4, -2}, 2)));
System.out.println(json(Solution23.solve(new int[]{1}, 5)));
System.out.println(json(Solution24.solve("", 3)));
System.out.println(json(Solution25.solve(new int[]{2, 2, 4, -2})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("")));
System.out.println(json(Solution27.solve("", 1)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0}, 1)));
System.out.println(json(Solution29.solve("", "ca")));
System.out.println(json(Solution30.solve("", "ca")));
System.out.println(json(Solution31.solve("", "ca")));
System.out.println(json(Solution32.solve("abbccabacbaabb", new String[]{"cb", "ab"})));
System.out.println(json(Solution0.solve(new int[]{-3, 0}, 1)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-3, 0})));
System.out.println(json(Solution3.solve(new int[]{-3, 0})));
System.out.println(json(Solution4.solve(new int[]{-3, 0})));
System.out.println(json(Solution5.solve(new int[]{-3, 0})));
System.out.println(json(Solution6.solve(new int[]{-3, 0, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{-3, 0, 0, 1, 2}, 1)));
System.out.println(json(Solution8.solve(new int[]{-3, 0, 0, 1, 2}, 1)));
System.out.println(json(Solution9.solve(new int[]{6, 5, 2, 4, 6, 1, 3}, 16)));
System.out.println(json(Solution10.solve(new int[]{0, 1})));
System.out.println(json(Solution11.solve(new int[]{-3, 0}, 1)));
System.out.println(json(Solution12.solve("#ba", "#ba##ba")));
System.out.println(json(Solution13.solve(new int[]{-3, 0})));
System.out.println(json(Solution14.solve(new int[]{-3, 0}, -1)));
System.out.println(json(Solution15.solve(new int[]{-3, 0}, -1)));
System.out.println(json(Solution16.solve(123)));
System.out.println(json(Solution17.solve(new int[]{2, 1, 1})));
System.out.println(json(Solution18.solve(new int[]{-3, 0})));
System.out.println(json(Solution19.solve(new int[]{-3, 0})));
System.out.println(json(Solution20.solve(new int[]{-3, 0})));
System.out.println(json(Solution21.solve(new int[]{-3, 1})));
System.out.println(json(Solution22.solve(new int[]{-3, 0}, 2)));
System.out.println(json(Solution23.solve(new int[]{6, 5, 2, 4, 6, 1, 3}, 9)));
System.out.println(json(Solution24.solve("bbcbcabb", 3)));
}
static void batch1400() {
System.out.println(json(Solution25.solve(new int[]{-3, 0})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("bbcbcabb")));
System.out.println(json(Solution27.solve("BBCBCABB", 1)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0}, 1)));
System.out.println(json(Solution29.solve("bbcbcabb", "b")));
System.out.println(json(Solution30.solve("bbcbcabb", "b")));
System.out.println(json(Solution31.solve("bbcbcabb", "b")));
System.out.println(json(Solution32.solve("bbcbccbcaaaccc", new String[]{"c", "b"})));
System.out.println(json(Solution0.solve(new int[]{0, 1}, 5)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{0})));
System.out.println(json(Solution3.solve(new int[]{0})));
System.out.println(json(Solution4.solve(new int[]{0})));
System.out.println(json(Solution5.solve(new int[]{0})));
System.out.println(json(Solution6.solve(new int[]{0, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{0, 0, 1, 2}, 5)));
System.out.println(json(Solution8.solve(new int[]{0, 0, 1, 2}, 5)));
System.out.println(json(Solution9.solve(new int[]{6, 1, 4}, 8)));
System.out.println(json(Solution10.solve(new int[]{2, 2})));
System.out.println(json(Solution11.solve(new int[]{0}, 5)));
System.out.println(json(Solution12.solve("#ba##", "a##")));
System.out.println(json(Solution13.solve(new int[]{0})));
System.out.println(json(Solution14.solve(new int[]{0}, 0)));
System.out.println(json(Solution15.solve(new int[]{0}, 0)));
System.out.println(json(Solution16.solve(1073)));
System.out.println(json(Solution17.solve(new int[]{2, 4, 3, 6, 1, 8, 7, 5, 3})));
System.out.println(json(Solution18.solve(new int[]{0})));
System.out.println(json(Solution19.solve(new int[]{0})));
System.out.println(json(Solution20.solve(new int[]{0})));
System.out.println(json(Solution21.solve(new int[]{1})));
System.out.println(json(Solution22.solve(new int[]{0}, 1)));
System.out.println(json(Solution23.solve(new int[]{6, 1, 4}, 1)));
System.out.println(json(Solution24.solve("abbabcc", 4)));
System.out.println(json(Solution25.solve(new int[]{0})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("abbabcc")));
System.out.println(json(Solution27.solve("ABBABCC", 2)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0}, 2)));
System.out.println(json(Solution29.solve("abbabcc", "ca")));
System.out.println(json(Solution30.solve("abbabcc", "ca")));
System.out.println(json(Solution31.solve("abbabcc", "ca")));
System.out.println(json(Solution32.solve("aaaccabbbcaaaa", new String[]{"b", "c"})));
System.out.println(json(Solution0.solve(new int[]{-4, -3, -1, 0, 4}, -8)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, -3, -1, 0, 4})));
System.out.println(json(Solution3.solve(new int[]{-4, -3, -1, 0, 4})));
System.out.println(json(Solution4.solve(new int[]{-4, -3, -1, 0, 4})));
System.out.println(json(Solution5.solve(new int[]{-4, -3, -1, 0, 4})));
System.out.println(json(Solution6.solve(new int[]{0, -3, -4, -1, 4})));
System.out.println(json(Solution7.solve(new int[]{0, -3, -4, -1, 4}, -8)));
System.out.println(json(Solution8.solve(new int[]{0, -3, -4, -1, 4}, -8)));
System.out.println(json(Solution9.solve(new int[]{6, 4, 5}, 16)));
System.out.println(json(Solution10.solve(new int[]{2, 2})));
System.out.println(json(Solution11.solve(new int[]{0, -3, -4, -1, 4}, -8)));
System.out.println(json(Solution12.solve("#ba", "#ba##ba##")));
System.out.println(json(Solution13.solve(new int[]{0, -3, -4, -1, 4})));
System.out.println(json(Solution14.solve(new int[]{0, -3, -4, -1, 4}, 0)));
System.out.println(json(Solution15.solve(new int[]{0, -3, -4, -1, 4}, 0)));
System.out.println(json(Solution16.solve(965)));
System.out.println(json(Solution17.solve(new int[]{2, 4, 3, 1, 3})));
System.out.println(json(Solution18.solve(new int[]{0, -3, -4, -1, 4})));
System.out.println(json(Solution19.solve(new int[]{0, -3, -4, -1, 4})));
System.out.println(json(Solution20.solve(new int[]{0, -3, -4, -1, 4})));
System.out.println(json(Solution21.solve(new int[]{1, -3, -4, -1, 4})));
System.out.println(json(Solution22.solve(new int[]{0, -3, -4, -1, 4}, 1)));
System.out.println(json(Solution23.solve(new int[]{6, 4, 5}, 13)));
System.out.println(json(Solution24.solve("acbb", 3)));
System.out.println(json(Solution25.solve(new int[]{0, -3, -4, -1, 4})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("acbb")));
System.out.println(json(Solution27.solve("ACBB", 1)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0}, 1)));
System.out.println(json(Solution29.solve("acbb", "a")));
System.out.println(json(Solution30.solve("acbb", "a")));
System.out.println(json(Solution31.solve("acbb", "a")));
System.out.println(json(Solution32.solve("cabcbcaaabbbca", new String[]{"b"})));
System.out.println(json(Solution0.solve(new int[]{-2, -2, -2, -1, 1, 4}, 7)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-2, -2, -2, -1, 1, 4})));
System.out.println(json(Solution3.solve(new int[]{-2, -2, -2, -1, 1, 4})));
System.out.println(json(Solution4.solve(new int[]{-2, -2, -2, -1, 1, 4})));
System.out.println(json(Solution5.solve(new int[]{-2, -2, -2, -1, 1, 4})));
System.out.println(json(Solution6.solve(new int[]{-2, 1, -2, 4, -2, -1})));
System.out.println(json(Solution7.solve(new int[]{-2, 1, -2, 4, -2, -1}, 7)));
System.out.println(json(Solution8.solve(new int[]{-2, 1, -2, 4, -2, -1}, 7)));
System.out.println(json(Solution9.solve(new int[]{4}, 12)));
System.out.println(json(Solution10.solve(new int[]{2, 0, 1, 1, 2, 1, 1, 1})));
System.out.println(json(Solution11.solve(new int[]{-2, 1, -2, 4, -2, -1}, 7)));
System.out.println(json(Solution12.solve("a##", "a")));
System.out.println(json(Solution13.solve(new int[]{-2, 1, -2, 4, -2, -1})));
System.out.println(json(Solution14.solve(new int[]{-2, 1, -2, 4, -2, -1}, 5)));
System.out.println(json(Solution15.solve(new int[]{-2, 1, -2, 4, -2, -1}, 5)));
System.out.println(json(Solution16.solve(695)));
System.out.println(json(Solution17.solve(new int[]{3, 4, 5, 6, 2, 1, 3})));
System.out.println(json(Solution18.solve(new int[]{-2, 1, -2, 4, -2, -1})));
System.out.println(json(Solution19.solve(new int[]{-2, 1, -2, 4, -2, -1})));
System.out.println(json(Solution20.solve(new int[]{-2, 1, -2, 4, -2, -1})));
System.out.println(json(Solution21.solve(new int[]{-2, 1, -2, 4, -2, -1})));
System.out.println(json(Solution22.solve(new int[]{-2, 1, -2, 4, -2, -1}, 6)));
System.out.println(json(Solution23.solve(new int[]{4}, 13)));
System.out.println(json(Solution24.solve("bcacacb", 4)));
System.out.println(json(Solution25.solve(new int[]{-2, 1, -2, 4, -2, -1})));
}
static void batch1500() {
System.out.println(json(new Solution26().lengthOfLongestSubstring("bcacacb")));
System.out.println(json(Solution27.solve("BCACACB", 2)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0}, 2)));
System.out.println(json(Solution29.solve("bcacacb", "aa")));
System.out.println(json(Solution30.solve("bcacacb", "aa")));
System.out.println(json(Solution31.solve("bcacacb", "aa")));
System.out.println(json(Solution32.solve("cbbccbbaabacaa", new String[]{"a", "a", "a"})));
System.out.println(json(Solution0.solve(new int[]{-4, -1, 2, 2, 3, 3, 4, 4}, -7)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, -1, 2, 2, 3, 3, 4, 4})));
System.out.println(json(Solution3.solve(new int[]{-4, -1, 2, 2, 3, 3, 4, 4})));
System.out.println(json(Solution4.solve(new int[]{-4, -1, 2, 2, 3, 3, 4, 4})));
System.out.println(json(Solution5.solve(new int[]{-4, -1, 2, 2, 3, 3, 4, 4})));
System.out.println(json(Solution6.solve(new int[]{-4, 4, 2, 3, 4, 3, -1, 2})));
System.out.println(json(Solution7.solve(new int[]{-4, 4, 2, 3, 4, 3, -1, 2}, -7)));
System.out.println(json(Solution8.solve(new int[]{-4, 4, 2, 3, 4, 3, -1, 2}, -7)));
System.out.println(json(Solution9.solve(new int[]{7}, 4)));
System.out.println(json(Solution10.solve(new int[]{1, 0, 2, 1, 2, 0, 0, 1})));
System.out.println(json(Solution11.solve(new int[]{-4, 4, 2, 3, 4, 3, -1, 2}, -7)));
System.out.println(json(Solution12.solve("a##ba", "#ba##ba##")));
System.out.println(json(Solution13.solve(new int[]{-4, 4, 2, 3, 4, 3, -1, 2})));
System.out.println(json(Solution14.solve(new int[]{-4, 4, 2, 3, 4, 3, -1, 2}, -1)));
System.out.println(json(Solution15.solve(new int[]{-4, 4, 2, 3, 4, 3, -1, 2}, -1)));
System.out.println(json(Solution16.solve(1169)));
System.out.println(json(Solution17.solve(new int[]{7, 6, 4, 1, 3, 8, 2, 5, 3})));
System.out.println(json(Solution18.solve(new int[]{-4, 4, 2, 3, 4, 3, -1, 2})));
System.out.println(json(Solution19.solve(new int[]{-4, 4, 2, 3, 4, 3, -1, 2})));
System.out.println(json(Solution20.solve(new int[]{-4, 4, 2, 3, 4, 3, -1, 2})));
System.out.println(json(Solution21.solve(new int[]{-4, 4, 2, 3, 4, 3, -1, 2})));
System.out.println(json(Solution22.solve(new int[]{-4, 4, 2, 3, 4, 3, -1, 2}, 2)));
System.out.println(json(Solution23.solve(new int[]{7}, 9)));
System.out.println(json(Solution24.solve("abaccbab", 3)));
System.out.println(json(Solution25.solve(new int[]{-4, 4, 2, 3, 4, 3, -1, 2})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("abaccbab")));
System.out.println(json(Solution27.solve("ABACCBAB", 1)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0}, 1)));
System.out.println(json(Solution29.solve("abaccbab", "cbb")));
System.out.println(json(Solution30.solve("abaccbab", "cbb")));
System.out.println(json(Solution31.solve("abaccbab", "cbb")));
System.out.println(json(Solution32.solve("babacbbabaaccb", new String[]{"b", "c", "b"})));
System.out.println(json(Solution0.solve(new int[]{-3, -2, -2, 0, 1, 4}, -2)));
System.out.println(json(Solution1.solve(new int[]{0})));
System.out.println(json(Solution2.solve(new int[]{-3, -2, -2, 0, 1, 4})));
System.out.println(json(Solution3.solve(new int[]{-3, -2, -2, 0, 1, 4})));
System.out.println(json(Solution4.solve(new int[]{-3, -2, -2, 0, 1, 4})));
System.out.println(json(Solution5.solve(new int[]{-3, -2, -2, 0, 1, 4})));
System.out.println(json(Solution6.solve(new int[]{-2, 0, -2, 4, -3, 1})));
System.out.println(json(Solution7.solve(new int[]{-2, 0, -2, 4, -3, 1}, -2)));
System.out.println(json(Solution8.solve(new int[]{-2, 0, -2, 4, -3, 1}, -2)));
System.out.println(json(Solution9.solve(new int[]{3, 7, 3, 3, 6, 3, 3}, 24)));
System.out.println(json(Solution10.solve(new int[]{0, 1})));
System.out.println(json(Solution11.solve(new int[]{-2, 0, -2, 4, -3, 1}, -2)));
System.out.println(json(Solution12.solve("#ba", "#ba##ba")));
System.out.println(json(Solution13.solve(new int[]{-2, 0, -2, 4, -3, 1})));
System.out.println(json(Solution14.solve(new int[]{-2, 0, -2, 4, -3, 1}, 4)));
System.out.println(json(Solution15.solve(new int[]{-2, 0, -2, 4, -3, 1}, 4)));
System.out.println(json(Solution16.solve(403)));
System.out.println(json(Solution17.solve(new int[]{2, 1, 1})));
System.out.println(json(Solution18.solve(new int[]{-2, 0, -2, 4, -3, 1})));
System.out.println(json(Solution19.solve(new int[]{-2, 0, -2, 4, -3, 1})));
System.out.println(json(Solution20.solve(new int[]{-2, 0, -2, 4, -3, 1})));
System.out.println(json(Solution21.solve(new int[]{-2, 1, -2, 4, -3, 1})));
System.out.println(json(Solution22.solve(new int[]{-2, 0, -2, 4, -3, 1}, 6)));
System.out.println(json(Solution23.solve(new int[]{3, 7, 3, 3, 6, 3, 3}, 13)));
System.out.println(json(Solution24.solve("ac", 1)));
System.out.println(json(Solution25.solve(new int[]{-2, 0, -2, 4, -3, 1})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("ac")));
System.out.println(json(Solution27.solve("AC", 3)));
System.out.println(json(Solution28.solve(new int[]{0}, 3)));
System.out.println(json(Solution29.solve("ac", "a")));
System.out.println(json(Solution30.solve("ac", "a")));
System.out.println(json(Solution31.solve("ac", "a")));
System.out.println(json(Solution32.solve("bbbbacbacaabac", new String[]{"b", "c", "b"})));
System.out.println(json(Solution0.solve(new int[]{-2, -1}, 5)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-2, -1})));
System.out.println(json(Solution3.solve(new int[]{-2, -1})));
System.out.println(json(Solution4.solve(new int[]{-2, -1})));
System.out.println(json(Solution5.solve(new int[]{-2, -1})));
System.out.println(json(Solution6.solve(new int[]{-2, -1, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{-2, -1, 0, 1, 2}, 5)));
System.out.println(json(Solution8.solve(new int[]{-2, -1, 0, 1, 2}, 5)));
System.out.println(json(Solution9.solve(new int[]{1}, 4)));
System.out.println(json(Solution10.solve(new int[]{0, 0, 2, 2, 2, 2})));
System.out.println(json(Solution11.solve(new int[]{-2, -1}, 5)));
System.out.println(json(Solution12.solve("#ba##ba", "#ba##ba##")));
System.out.println(json(Solution13.solve(new int[]{-2, -1})));
System.out.println(json(Solution14.solve(new int[]{-2, -1}, -1)));
System.out.println(json(Solution15.solve(new int[]{-2, -1}, -1)));
System.out.println(json(Solution16.solve(1721)));
System.out.println(json(Solution17.solve(new int[]{3, 6, 2, 3, 4, 7, 8, 5, 1})));
System.out.println(json(Solution18.solve(new int[]{-2, -1})));
System.out.println(json(Solution19.solve(new int[]{-2, -1})));
System.out.println(json(Solution20.solve(new int[]{-2, -1})));
System.out.println(json(Solution21.solve(new int[]{-2, -1})));
System.out.println(json(Solution22.solve(new int[]{-2, -1}, 2)));
System.out.println(json(Solution23.solve(new int[]{1}, 17)));
System.out.println(json(Solution24.solve("caccacca", 3)));
System.out.println(json(Solution25.solve(new int[]{-2, -1})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("caccacca")));
}
static void batch1600() {
System.out.println(json(Solution27.solve("CACCACCA", 1)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0}, 1)));
System.out.println(json(Solution29.solve("caccacca", "aac")));
System.out.println(json(Solution30.solve("caccacca", "aac")));
System.out.println(json(Solution31.solve("caccacca", "aac")));
System.out.println(json(Solution32.solve("cabcbcbbcbaccb", new String[]{"c"})));
System.out.println(json(Solution0.solve(new int[]{-4, -3, -1, 4}, -7)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, -3, -1, 4})));
System.out.println(json(Solution3.solve(new int[]{-4, -3, -1, 4})));
System.out.println(json(Solution4.solve(new int[]{-4, -3, -1, 4})));
System.out.println(json(Solution5.solve(new int[]{-4, -3, -1, 4})));
System.out.println(json(Solution6.solve(new int[]{-1, -4, 4, -3})));
System.out.println(json(Solution7.solve(new int[]{-1, -4, 4, -3}, -7)));
System.out.println(json(Solution8.solve(new int[]{-1, -4, 4, -3}, -7)));
System.out.println(json(Solution9.solve(new int[]{5, 7, 4}, 16)));
System.out.println(json(Solution10.solve(new int[]{})));
System.out.println(json(Solution11.solve(new int[]{-1, -4, 4, -3}, -7)));
System.out.println(json(Solution12.solve("a##ba##ba", "#ba##")));
System.out.println(json(Solution13.solve(new int[]{-1, -4, 4, -3})));
System.out.println(json(Solution14.solve(new int[]{-1, -4, 4, -3}, 1)));
System.out.println(json(Solution15.solve(new int[]{-1, -4, 4, -3}, 1)));
System.out.println(json(Solution16.solve(1149)));
System.out.println(json(Solution17.solve(new int[]{3, 4, 3, 1, 2})));
System.out.println(json(Solution18.solve(new int[]{-1, -4, 4, -3})));
System.out.println(json(Solution19.solve(new int[]{-1, -4, 4, -3})));
System.out.println(json(Solution20.solve(new int[]{-1, -4, 4, -3})));
System.out.println(json(Solution21.solve(new int[]{-1, -4, 4, -3})));
System.out.println(json(Solution22.solve(new int[]{-1, -4, 4, -3}, 2)));
System.out.println(json(Solution23.solve(new int[]{5, 7, 4}, 9)));
System.out.println(json(Solution24.solve("aabcca", 1)));
System.out.println(json(Solution25.solve(new int[]{-1, -4, 4, -3})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("aabcca")));
System.out.println(json(Solution27.solve("AABCCA", 3)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0}, 3)));
System.out.println(json(Solution29.solve("aabcca", "b")));
System.out.println(json(Solution30.solve("aabcca", "b")));
System.out.println(json(Solution31.solve("aabcca", "b")));
System.out.println(json(Solution32.solve("acbbbccabbbbba", new String[]{"b", "a", "a"})));
System.out.println(json(Solution0.solve(new int[]{-3, 2}, 2)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-3, 2})));
System.out.println(json(Solution3.solve(new int[]{-3, 2})));
System.out.println(json(Solution4.solve(new int[]{-3, 2})));
System.out.println(json(Solution5.solve(new int[]{-3, 2})));
System.out.println(json(Solution6.solve(new int[]{-3, 2, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{-3, 2, 0, 1, 2}, 2)));
System.out.println(json(Solution8.solve(new int[]{-3, 2, 0, 1, 2}, 2)));
System.out.println(json(Solution9.solve(new int[]{6, 3, 2}, 16)));
System.out.println(json(Solution10.solve(new int[]{2, 0, 1, 0, 1, 1, 1, 0})));
System.out.println(json(Solution11.solve(new int[]{-3, 2}, 2)));
System.out.println(json(Solution12.solve("a##ba##", "a##")));
System.out.println(json(Solution13.solve(new int[]{-3, 2})));
System.out.println(json(Solution14.solve(new int[]{-3, 2}, 0)));
System.out.println(json(Solution15.solve(new int[]{-3, 2}, 0)));
System.out.println(json(Solution16.solve(1081)));
System.out.println(json(Solution17.solve(new int[]{7, 3, 4, 6, 1, 8, 3, 5, 2})));
System.out.println(json(Solution18.solve(new int[]{-3, 2})));
System.out.println(json(Solution19.solve(new int[]{-3, 2})));
System.out.println(json(Solution20.solve(new int[]{-3, 2})));
System.out.println(json(Solution21.solve(new int[]{-3, 2})));
System.out.println(json(Solution22.solve(new int[]{-3, 2}, 2)));
System.out.println(json(Solution23.solve(new int[]{6, 3, 2}, 9)));
System.out.println(json(Solution24.solve("caacaccb", 3)));
System.out.println(json(Solution25.solve(new int[]{-3, 2})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("caacaccb")));
System.out.println(json(Solution27.solve("CAACACCB", 1)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0}, 1)));
System.out.println(json(Solution29.solve("caacaccb", "c")));
System.out.println(json(Solution30.solve("caacaccb", "c")));
System.out.println(json(Solution31.solve("caacaccb", "c")));
System.out.println(json(Solution32.solve("ccccaaaaaaccbc", new String[]{"c", "b", "c"})));
System.out.println(json(Solution0.solve(new int[]{-3, 0, 3, 3}, -2)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-3, 0, 3, 3})));
System.out.println(json(Solution3.solve(new int[]{-3, 0, 3, 3})));
System.out.println(json(Solution4.solve(new int[]{-3, 0, 3, 3})));
System.out.println(json(Solution5.solve(new int[]{-3, 0, 3, 3})));
System.out.println(json(Solution6.solve(new int[]{-3, 0, 3, 3})));
System.out.println(json(Solution7.solve(new int[]{-3, 0, 3, 3}, -2)));
System.out.println(json(Solution8.solve(new int[]{-3, 0, 3, 3}, -2)));
System.out.println(json(Solution9.solve(new int[]{6}, 36)));
System.out.println(json(Solution10.solve(new int[]{2, 2, 2, 1, 1, 0, 2, 1})));
System.out.println(json(Solution11.solve(new int[]{-3, 0, 3, 3}, -2)));
System.out.println(json(Solution12.solve("a##ba##ba", "#ba##ba##")));
System.out.println(json(Solution13.solve(new int[]{-3, 0, 3, 3})));
System.out.println(json(Solution14.solve(new int[]{-3, 0, 3, 3}, -1)));
System.out.println(json(Solution15.solve(new int[]{-3, 0, 3, 3}, -1)));
System.out.println(json(Solution16.solve(1701)));
System.out.println(json(Solution17.solve(new int[]{3, 4, 2, 1, 3})));
System.out.println(json(Solution18.solve(new int[]{-3, 0, 3, 3})));
System.out.println(json(Solution19.solve(new int[]{-3, 0, 3, 3})));
System.out.println(json(Solution20.solve(new int[]{-3, 0, 3, 3})));
System.out.println(json(Solution21.solve(new int[]{-3, 1, 3, 3})));
System.out.println(json(Solution22.solve(new int[]{-3, 0, 3, 3}, 2)));
System.out.println(json(Solution23.solve(new int[]{6}, 9)));
System.out.println(json(Solution24.solve("bbab", 3)));
System.out.println(json(Solution25.solve(new int[]{-3, 0, 3, 3})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("bbab")));
System.out.println(json(Solution27.solve("BBAB", 1)));
}
static void batch1700() {
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0}, 1)));
System.out.println(json(Solution29.solve("bbab", "bca")));
System.out.println(json(Solution30.solve("bbab", "bca")));
System.out.println(json(Solution31.solve("bbab", "bca")));
System.out.println(json(Solution32.solve("bbaacababcaaba", new String[]{"c", "c", "b"})));
System.out.println(json(Solution0.solve(new int[]{1, 3}, 3)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{1, 3})));
System.out.println(json(Solution3.solve(new int[]{1, 3})));
System.out.println(json(Solution4.solve(new int[]{1, 3})));
System.out.println(json(Solution5.solve(new int[]{1, 3})));
System.out.println(json(Solution6.solve(new int[]{1, 3, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{1, 3, 0, 1, 2}, 3)));
System.out.println(json(Solution8.solve(new int[]{1, 3, 0, 1, 2}, 3)));
System.out.println(json(Solution9.solve(new int[]{7, 6, 7, 4, 7, 1, 7}, 8)));
System.out.println(json(Solution10.solve(new int[]{})));
System.out.println(json(Solution11.solve(new int[]{1, 3}, 3)));
System.out.println(json(Solution12.solve("a##ba", "#")));
System.out.println(json(Solution13.solve(new int[]{1, 3})));
System.out.println(json(Solution14.solve(new int[]{1, 3}, 0)));
System.out.println(json(Solution15.solve(new int[]{1, 3}, 0)));
System.out.println(json(Solution16.solve(1341)));
System.out.println(json(Solution17.solve(new int[]{3, 3, 4, 1, 2})));
System.out.println(json(Solution18.solve(new int[]{1, 3})));
System.out.println(json(Solution19.solve(new int[]{1, 3})));
System.out.println(json(Solution20.solve(new int[]{1, 3})));
System.out.println(json(Solution21.solve(new int[]{1, 3})));
System.out.println(json(Solution22.solve(new int[]{1, 3}, 2)));
System.out.println(json(Solution23.solve(new int[]{7, 6, 7, 4, 7, 1, 7}, 1)));
System.out.println(json(Solution24.solve("cabacca", 4)));
System.out.println(json(Solution25.solve(new int[]{1, 3})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("cabacca")));
System.out.println(json(Solution27.solve("CABACCA", 2)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0}, 2)));
System.out.println(json(Solution29.solve("cabacca", "bb")));
System.out.println(json(Solution30.solve("cabacca", "bb")));
System.out.println(json(Solution31.solve("cabacca", "bb")));
System.out.println(json(Solution32.solve("bcccacbcabacca", new String[]{"b"})));
System.out.println(json(Solution0.solve(new int[]{1, 2}, 6)));
System.out.println(json(Solution1.solve(new int[]{0})));
System.out.println(json(Solution2.solve(new int[]{1, 2})));
System.out.println(json(Solution3.solve(new int[]{1, 2})));
System.out.println(json(Solution4.solve(new int[]{1, 2})));
System.out.println(json(Solution5.solve(new int[]{1, 2})));
System.out.println(json(Solution6.solve(new int[]{2, 1, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{2, 1, 0, 1, 2}, 6)));
System.out.println(json(Solution8.solve(new int[]{2, 1, 0, 1, 2}, 6)));
System.out.println(json(Solution9.solve(new int[]{6, 2, 5, 2, 2, 2, 2}, 24)));
System.out.println(json(Solution10.solve(new int[]{2, 2, 0, 0, 2, 0})));
System.out.println(json(Solution11.solve(new int[]{2, 1}, 6)));
System.out.println(json(Solution12.solve("#ba", "#ba##ba##")));
System.out.println(json(Solution13.solve(new int[]{2, 1})));
System.out.println(json(Solution14.solve(new int[]{2, 1}, -1)));
System.out.println(json(Solution15.solve(new int[]{2, 1}, -1)));
System.out.println(json(Solution16.solve(441)));
System.out.println(json(Solution17.solve(new int[]{6, 3, 4, 1, 2, 3, 8, 5, 7})));
System.out.println(json(Solution18.solve(new int[]{2, 1})));
System.out.println(json(Solution19.solve(new int[]{2, 1})));
System.out.println(json(Solution20.solve(new int[]{2, 1})));
System.out.println(json(Solution21.solve(new int[]{2, 1})));
System.out.println(json(Solution22.solve(new int[]{2, 1}, 2)));
System.out.println(json(Solution23.solve(new int[]{6, 2, 5, 2, 2, 2, 2}, 13)));
System.out.println(json(Solution24.solve("aaacccbb", 3)));
System.out.println(json(Solution25.solve(new int[]{2, 1})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("aaacccbb")));
System.out.println(json(Solution27.solve("AAACCCBB", 1)));
System.out.println(json(Solution28.solve(new int[]{0}, 1)));
System.out.println(json(Solution29.solve("aaacccbb", "c")));
System.out.println(json(Solution30.solve("aaacccbb", "c")));
System.out.println(json(Solution31.solve("aaacccbb", "c")));
System.out.println(json(Solution32.solve("aaccbaccacaaaa", new String[]{"c"})));
System.out.println(json(Solution0.solve(new int[]{-2, -1}, -2)));
System.out.println(json(Solution1.solve(new int[]{0})));
System.out.println(json(Solution2.solve(new int[]{-2, -1})));
System.out.println(json(Solution3.solve(new int[]{-2, -1})));
System.out.println(json(Solution4.solve(new int[]{-2, -1})));
System.out.println(json(Solution5.solve(new int[]{-2, -1})));
System.out.println(json(Solution6.solve(new int[]{-1, -2, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{-1, -2, 0, 1, 2}, -2)));
System.out.println(json(Solution8.solve(new int[]{-1, -2, 0, 1, 2}, -2)));
System.out.println(json(Solution9.solve(new int[]{2, 6, 1, 4, 1, 1, 6}, 8)));
System.out.println(json(Solution10.solve(new int[]{1, 1, 0, 2, 2, 1})));
System.out.println(json(Solution11.solve(new int[]{-1, -2}, -2)));
System.out.println(json(Solution12.solve("#ba", "#")));
System.out.println(json(Solution13.solve(new int[]{-1, -2})));
System.out.println(json(Solution14.solve(new int[]{-1, -2}, 0)));
System.out.println(json(Solution15.solve(new int[]{-1, -2}, 0)));
System.out.println(json(Solution16.solve(1313)));
System.out.println(json(Solution17.solve(new int[]{2, 8, 3, 1, 4, 3, 6, 5, 7})));
System.out.println(json(Solution18.solve(new int[]{-1, -2})));
System.out.println(json(Solution19.solve(new int[]{-1, -2})));
System.out.println(json(Solution20.solve(new int[]{-1, -2})));
System.out.println(json(Solution21.solve(new int[]{-1, -2})));
System.out.println(json(Solution22.solve(new int[]{-1, -2}, 2)));
System.out.println(json(Solution23.solve(new int[]{2, 6, 1, 4, 1, 1, 6}, 17)));
System.out.println(json(Solution24.solve("b", 2)));
System.out.println(json(Solution25.solve(new int[]{-1, -2})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("b")));
System.out.println(json(Solution27.solve("B", 0)));
System.out.println(json(Solution28.solve(new int[]{0}, 0)));
}
static void batch1800() {
System.out.println(json(Solution29.solve("b", "aa")));
System.out.println(json(Solution30.solve("b", "aa")));
System.out.println(json(Solution31.solve("b", "aa")));
System.out.println(json(Solution32.solve("cbaccbbbccaaba", new String[]{"c", "b", "a"})));
System.out.println(json(Solution0.solve(new int[]{-3, -1, -1, 0, 4, 4}, 7)));
System.out.println(json(Solution1.solve(new int[]{0})));
System.out.println(json(Solution2.solve(new int[]{-3, -1, -1, 0, 4, 4})));
System.out.println(json(Solution3.solve(new int[]{-3, -1, -1, 0, 4, 4})));
System.out.println(json(Solution4.solve(new int[]{-3, -1, -1, 0, 4, 4})));
System.out.println(json(Solution5.solve(new int[]{-3, -1, -1, 0, 4, 4})));
System.out.println(json(Solution6.solve(new int[]{-1, 4, -1, -3, 0, 4})));
System.out.println(json(Solution7.solve(new int[]{-1, 4, -1, -3, 0, 4}, 7)));
System.out.println(json(Solution8.solve(new int[]{-1, 4, -1, -3, 0, 4}, 7)));
System.out.println(json(Solution9.solve(new int[]{3, 2, 2, 1, 4, 7, 4}, 16)));
System.out.println(json(Solution10.solve(new int[]{1, 2, 1, 0, 1, 2, 0, 1})));
System.out.println(json(Solution11.solve(new int[]{-1, 4, -1, -3, 0, 4}, 7)));
System.out.println(json(Solution12.solve("a##ba", "#ba##ba")));
System.out.println(json(Solution13.solve(new int[]{-1, 4, -1, -3, 0, 4})));
System.out.println(json(Solution14.solve(new int[]{-1, 4, -1, -3, 0, 4}, 3)));
System.out.println(json(Solution15.solve(new int[]{-1, 4, -1, -3, 0, 4}, 3)));
System.out.println(json(Solution16.solve(1427)));
System.out.println(json(Solution17.solve(new int[]{2, 1, 1})));
System.out.println(json(Solution18.solve(new int[]{-1, 4, -1, -3, 0, 4})));
System.out.println(json(Solution19.solve(new int[]{-1, 4, -1, -3, 0, 4})));
System.out.println(json(Solution20.solve(new int[]{-1, 4, -1, -3, 0, 4})));
System.out.println(json(Solution21.solve(new int[]{-1, 4, -1, -3, 1, 4})));
System.out.println(json(Solution22.solve(new int[]{-1, 4, -1, -3, 0, 4}, 6)));
System.out.println(json(Solution23.solve(new int[]{3, 2, 2, 1, 4, 7, 4}, 1)));
System.out.println(json(Solution24.solve("bcbcaabb", 3)));
System.out.println(json(Solution25.solve(new int[]{-1, 4, -1, -3, 0, 4})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("bcbcaabb")));
System.out.println(json(Solution27.solve("BCBCAABB", 1)));
System.out.println(json(Solution28.solve(new int[]{0}, 1)));
System.out.println(json(Solution29.solve("bcbcaabb", "acc")));
System.out.println(json(Solution30.solve("bcbcaabb", "acc")));
System.out.println(json(Solution31.solve("bcbcaabb", "acc")));
System.out.println(json(Solution32.solve("bbbcacbbcbabbb", new String[]{"c", "c", "b"})));
System.out.println(json(Solution0.solve(new int[]{-2, 4}, 2)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-2, 4})));
System.out.println(json(Solution3.solve(new int[]{-2, 4})));
System.out.println(json(Solution4.solve(new int[]{-2, 4})));
System.out.println(json(Solution5.solve(new int[]{-2, 4})));
System.out.println(json(Solution6.solve(new int[]{4, -2, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{4, -2, 0, 1, 2}, 2)));
System.out.println(json(Solution8.solve(new int[]{4, -2, 0, 1, 2}, 2)));
System.out.println(json(Solution9.solve(new int[]{6, 3, 2}, 8)));
System.out.println(json(Solution10.solve(new int[]{1, 1, 0, 2})));
System.out.println(json(Solution11.solve(new int[]{4, -2}, 2)));
System.out.println(json(Solution12.solve("a##ba", "#ba")));
System.out.println(json(Solution13.solve(new int[]{4, -2})));
System.out.println(json(Solution14.solve(new int[]{4, -2}, 0)));
System.out.println(json(Solution15.solve(new int[]{4, -2}, 0)));
System.out.println(json(Solution16.solve(987)));
System.out.println(json(Solution17.solve(new int[]{2, 1, 1})));
System.out.println(json(Solution18.solve(new int[]{4, -2})));
System.out.println(json(Solution19.solve(new int[]{4, -2})));
System.out.println(json(Solution20.solve(new int[]{4, -2})));
System.out.println(json(Solution21.solve(new int[]{4, -2})));
System.out.println(json(Solution22.solve(new int[]{4, -2}, 2)));
System.out.println(json(Solution23.solve(new int[]{6, 3, 2}, 9)));
System.out.println(json(Solution24.solve("bc", 1)));
System.out.println(json(Solution25.solve(new int[]{4, -2})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("bc")));
System.out.println(json(Solution27.solve("BC", 3)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0}, 3)));
System.out.println(json(Solution29.solve("bc", "b")));
System.out.println(json(Solution30.solve("bc", "b")));
System.out.println(json(Solution31.solve("bc", "b")));
System.out.println(json(Solution32.solve("baabccbabcabab", new String[]{"b", "c"})));
System.out.println(json(Solution0.solve(new int[]{-2, -1, 3}, -3)));
System.out.println(json(Solution1.solve(new int[]{0})));
System.out.println(json(Solution2.solve(new int[]{-2, -1, 3})));
System.out.println(json(Solution3.solve(new int[]{-2, -1, 3})));
System.out.println(json(Solution4.solve(new int[]{-2, -1, 3})));
System.out.println(json(Solution5.solve(new int[]{-2, -1, 3})));
System.out.println(json(Solution6.solve(new int[]{-2, -1, 3})));
System.out.println(json(Solution7.solve(new int[]{-2, -1, 3}, -3)));
System.out.println(json(Solution8.solve(new int[]{-2, -1, 3}, -3)));
System.out.println(json(Solution9.solve(new int[]{3, 2, 7, 2, 5, 3, 1}, 16)));
System.out.println(json(Solution10.solve(new int[]{})));
System.out.println(json(Solution11.solve(new int[]{-2, -1, 3}, -3)));
System.out.println(json(Solution12.solve("a##", "a##")));
System.out.println(json(Solution13.solve(new int[]{-2, -1, 3})));
System.out.println(json(Solution14.solve(new int[]{-2, -1, 3}, 0)));
System.out.println(json(Solution15.solve(new int[]{-2, -1, 3}, 0)));
System.out.println(json(Solution16.solve(133)));
System.out.println(json(Solution17.solve(new int[]{2, 3, 4, 1, 3})));
System.out.println(json(Solution18.solve(new int[]{-2, -1, 3})));
System.out.println(json(Solution19.solve(new int[]{-2, -1, 3})));
System.out.println(json(Solution20.solve(new int[]{-2, -1, 3})));
System.out.println(json(Solution21.solve(new int[]{-2, -1, 3})));
System.out.println(json(Solution22.solve(new int[]{-2, -1, 3}, 3)));
System.out.println(json(Solution23.solve(new int[]{3, 2, 7, 2, 5, 3, 1}, 1)));
System.out.println(json(Solution24.solve("", 3)));
System.out.println(json(Solution25.solve(new int[]{-2, -1, 3})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("")));
System.out.println(json(Solution27.solve("", 1)));
System.out.println(json(Solution28.solve(new int[]{0}, 1)));
System.out.println(json(Solution29.solve("", "c")));
}
static void batch1900() {
System.out.println(json(Solution30.solve("", "c")));
System.out.println(json(Solution31.solve("", "c")));
System.out.println(json(Solution32.solve("baabbabbcabacb", new String[]{"a"})));
System.out.println(json(Solution0.solve(new int[]{-4, 4}, 7)));
System.out.println(json(Solution1.solve(new int[]{0})));
System.out.println(json(Solution2.solve(new int[]{-4, 4})));
System.out.println(json(Solution3.solve(new int[]{-4, 4})));
System.out.println(json(Solution4.solve(new int[]{-4, 4})));
System.out.println(json(Solution5.solve(new int[]{-4, 4})));
System.out.println(json(Solution6.solve(new int[]{-4, 4, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{-4, 4, 0, 1, 2}, 7)));
System.out.println(json(Solution8.solve(new int[]{-4, 4, 0, 1, 2}, 7)));
System.out.println(json(Solution9.solve(new int[]{3, 1, 5, 5, 3, 7, 4}, 32)));
System.out.println(json(Solution10.solve(new int[]{2, 2})));
System.out.println(json(Solution11.solve(new int[]{-4, 4}, 7)));
System.out.println(json(Solution12.solve("#", "a##")));
System.out.println(json(Solution13.solve(new int[]{-4, 4})));
System.out.println(json(Solution14.solve(new int[]{-4, 4}, 0)));
System.out.println(json(Solution15.solve(new int[]{-4, 4}, 0)));
System.out.println(json(Solution16.solve(621)));
System.out.println(json(Solution17.solve(new int[]{3, 3, 2, 1, 4})));
System.out.println(json(Solution18.solve(new int[]{-4, 4})));
System.out.println(json(Solution19.solve(new int[]{-4, 4})));
System.out.println(json(Solution20.solve(new int[]{-4, 4})));
System.out.println(json(Solution21.solve(new int[]{-4, 4})));
System.out.println(json(Solution22.solve(new int[]{-4, 4}, 2)));
System.out.println(json(Solution23.solve(new int[]{3, 1, 5, 5, 3, 7, 4}, 1)));
System.out.println(json(Solution24.solve("babaa", 2)));
System.out.println(json(Solution25.solve(new int[]{-4, 4})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("babaa")));
System.out.println(json(Solution27.solve("BABAA", 0)));
System.out.println(json(Solution28.solve(new int[]{0}, 0)));
System.out.println(json(Solution29.solve("babaa", "bba")));
System.out.println(json(Solution30.solve("babaa", "bba")));
System.out.println(json(Solution31.solve("babaa", "bba")));
System.out.println(json(Solution32.solve("cacbaaccbbbabc", new String[]{"ba"})));
System.out.println(json(Solution0.solve(new int[]{-4, -1}, 8)));
System.out.println(json(Solution1.solve(new int[]{0})));
System.out.println(json(Solution2.solve(new int[]{-4, -1})));
System.out.println(json(Solution3.solve(new int[]{-4, -1})));
System.out.println(json(Solution4.solve(new int[]{-4, -1})));
System.out.println(json(Solution5.solve(new int[]{-4, -1})));
System.out.println(json(Solution6.solve(new int[]{-1, -4, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{-1, -4, 0, 1, 2}, 8)));
System.out.println(json(Solution8.solve(new int[]{-1, -4, 0, 1, 2}, 8)));
System.out.println(json(Solution9.solve(new int[]{4, 2, 7, 3, 6, 7, 2}, 40)));
System.out.println(json(Solution10.solve(new int[]{0, 1, 0, 2, 0, 2})));
System.out.println(json(Solution11.solve(new int[]{-1, -4}, 8)));
System.out.println(json(Solution12.solve("#ba##", "a##ba")));
System.out.println(json(Solution13.solve(new int[]{-1, -4})));
System.out.println(json(Solution14.solve(new int[]{-1, -4}, 0)));
System.out.println(json(Solution15.solve(new int[]{-1, -4}, 0)));
System.out.println(json(Solution16.solve(1735)));
System.out.println(json(Solution17.solve(new int[]{5, 4, 6, 5, 1, 3, 2})));
System.out.println(json(Solution18.solve(new int[]{-1, -4})));
System.out.println(json(Solution19.solve(new int[]{-1, -4})));
System.out.println(json(Solution20.solve(new int[]{-1, -4})));
System.out.println(json(Solution21.solve(new int[]{-1, -4})));
System.out.println(json(Solution22.solve(new int[]{-1, -4}, 2)));
System.out.println(json(Solution23.solve(new int[]{4, 2, 7, 3, 6, 7, 2}, 17)));
System.out.println(json(Solution24.solve("bbbca", 2)));
System.out.println(json(Solution25.solve(new int[]{-1, -4})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("bbbca")));
System.out.println(json(Solution27.solve("BBBCA", 0)));
System.out.println(json(Solution28.solve(new int[]{0}, 0)));
System.out.println(json(Solution29.solve("bbbca", "aca")));
System.out.println(json(Solution30.solve("bbbca", "aca")));
System.out.println(json(Solution31.solve("bbbca", "aca")));
System.out.println(json(Solution32.solve("cacbaacacabbaa", new String[]{"ba", "ab"})));
System.out.println(json(Solution0.solve(new int[]{-2, -2, 0, 0, 0, 2, 3, 4}, 5)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-2, -2, 0, 0, 0, 2, 3, 4})));
System.out.println(json(Solution3.solve(new int[]{-2, -2, 0, 0, 0, 2, 3, 4})));
System.out.println(json(Solution4.solve(new int[]{-2, -2, 0, 0, 0, 2, 3, 4})));
System.out.println(json(Solution5.solve(new int[]{-2, -2, 0, 0, 0, 2, 3, 4})));
System.out.println(json(Solution6.solve(new int[]{-2, 4, 2, 0, 0, 3, 0, -2})));
System.out.println(json(Solution7.solve(new int[]{-2, 4, 2, 0, 0, 3, 0, -2}, 5)));
System.out.println(json(Solution8.solve(new int[]{-2, 4, 2, 0, 0, 3, 0, -2}, 5)));
System.out.println(json(Solution9.solve(new int[]{3, 6, 3, 2, 1, 7, 4}, 8)));
System.out.println(json(Solution10.solve(new int[]{1, 2, 0, 1})));
System.out.println(json(Solution11.solve(new int[]{-2, 4, 2, 0, 0, 3, 0, -2}, 5)));
System.out.println(json(Solution12.solve("a##ba##", "a##ba")));
System.out.println(json(Solution13.solve(new int[]{-2, 4, 2, 0, 0, 3, 0, -2})));
System.out.println(json(Solution14.solve(new int[]{-2, 4, 2, 0, 0, 3, 0, -2}, 4)));
System.out.println(json(Solution15.solve(new int[]{-2, 4, 2, 0, 0, 3, 0, -2}, 4)));
System.out.println(json(Solution16.solve(1871)));
System.out.println(json(Solution17.solve(new int[]{3, 5, 6, 1, 4, 3, 2})));
System.out.println(json(Solution18.solve(new int[]{-2, 4, 2, 0, 0, 3, 0, -2})));
System.out.println(json(Solution19.solve(new int[]{-2, 4, 2, 0, 0, 3, 0, -2})));
System.out.println(json(Solution20.solve(new int[]{-2, 4, 2, 0, 0, 3, 0, -2})));
System.out.println(json(Solution21.solve(new int[]{-2, 4, 2, 1, 1, 3, 1, -2})));
System.out.println(json(Solution22.solve(new int[]{-2, 4, 2, 0, 0, 3, 0, -2}, 6)));
System.out.println(json(Solution23.solve(new int[]{3, 6, 3, 2, 1, 7, 4}, 13)));
System.out.println(json(Solution24.solve("bcbcaccb", 3)));
System.out.println(json(Solution25.solve(new int[]{-2, 4, 2, 0, 0, 3, 0, -2})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("bcbcaccb")));
System.out.println(json(Solution27.solve("BCBCACCB", 1)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0}, 1)));
System.out.println(json(Solution29.solve("bcbcaccb", "cb")));
System.out.println(json(Solution30.solve("bcbcaccb", "cb")));
}
static void batch2000() {
System.out.println(json(Solution31.solve("bcbcaccb", "cb")));
System.out.println(json(Solution32.solve("aabacaacaabbbb", new String[]{"ba", "bc", "ac"})));
System.out.println(json(Solution0.solve(new int[]{-4, -2, 1, 3}, -6)));
System.out.println(json(Solution1.solve(new int[]{0})));
System.out.println(json(Solution2.solve(new int[]{-4, -2, 1, 3})));
System.out.println(json(Solution3.solve(new int[]{-4, -2, 1, 3})));
System.out.println(json(Solution4.solve(new int[]{-4, -2, 1, 3})));
System.out.println(json(Solution5.solve(new int[]{-4, -2, 1, 3})));
System.out.println(json(Solution6.solve(new int[]{3, 1, -2, -4})));
System.out.println(json(Solution7.solve(new int[]{3, 1, -2, -4}, -6)));
System.out.println(json(Solution8.solve(new int[]{3, 1, -2, -4}, -6)));
System.out.println(json(Solution9.solve(new int[]{1, 2, 4}, 16)));
System.out.println(json(Solution10.solve(new int[]{1, 2, 0, 0, 0, 0})));
System.out.println(json(Solution11.solve(new int[]{3, 1, -2, -4}, -6)));
System.out.println(json(Solution12.solve("#ba##", "a##ba")));
System.out.println(json(Solution13.solve(new int[]{3, 1, -2, -4})));
System.out.println(json(Solution14.solve(new int[]{3, 1, -2, -4}, 1)));
System.out.println(json(Solution15.solve(new int[]{3, 1, -2, -4}, 1)));
System.out.println(json(Solution16.solve(1855)));
System.out.println(json(Solution17.solve(new int[]{6, 4, 3, 1, 2, 5, 3})));
System.out.println(json(Solution18.solve(new int[]{3, 1, -2, -4})));
System.out.println(json(Solution19.solve(new int[]{3, 1, -2, -4})));
System.out.println(json(Solution20.solve(new int[]{3, 1, -2, -4})));
System.out.println(json(Solution21.solve(new int[]{3, 1, -2, -4})));
System.out.println(json(Solution22.solve(new int[]{3, 1, -2, -4}, 2)));
System.out.println(json(Solution23.solve(new int[]{1, 2, 4}, 1)));
System.out.println(json(Solution24.solve("c", 2)));
System.out.println(json(Solution25.solve(new int[]{3, 1, -2, -4})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("c")));
System.out.println(json(Solution27.solve("C", 0)));
System.out.println(json(Solution28.solve(new int[]{0}, 0)));
System.out.println(json(Solution29.solve("c", "bbb")));
System.out.println(json(Solution30.solve("c", "bbb")));
System.out.println(json(Solution31.solve("c", "bbb")));
System.out.println(json(Solution32.solve("baabbacbbcbaac", new String[]{"cb"})));
System.out.println(json(Solution0.solve(new int[]{2, 2}, 2)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{2, 2})));
System.out.println(json(Solution3.solve(new int[]{2, 2})));
System.out.println(json(Solution4.solve(new int[]{2, 2})));
System.out.println(json(Solution5.solve(new int[]{2, 2})));
System.out.println(json(Solution6.solve(new int[]{2, 2, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{2, 2, 0, 1, 2}, 2)));
System.out.println(json(Solution8.solve(new int[]{2, 2, 0, 1, 2}, 2)));
System.out.println(json(Solution9.solve(new int[]{6, 4, 2, 5, 1, 4, 7}, 16)));
System.out.println(json(Solution10.solve(new int[]{1, 2, 1, 0, 1, 0, 0, 1})));
System.out.println(json(Solution11.solve(new int[]{2, 2}, 2)));
System.out.println(json(Solution12.solve("a##ba##ba", "#ba##ba")));
System.out.println(json(Solution13.solve(new int[]{2, 2})));
System.out.println(json(Solution14.solve(new int[]{2, 2}, -1)));
System.out.println(json(Solution15.solve(new int[]{2, 2}, -1)));
System.out.println(json(Solution16.solve(743)));
System.out.println(json(Solution17.solve(new int[]{3, 4, 5, 6, 2, 1, 5})));
System.out.println(json(Solution18.solve(new int[]{2, 2})));
System.out.println(json(Solution19.solve(new int[]{2, 2})));
System.out.println(json(Solution20.solve(new int[]{2, 2})));
System.out.println(json(Solution21.solve(new int[]{2, 2})));
System.out.println(json(Solution22.solve(new int[]{2, 2}, 2)));
System.out.println(json(Solution23.solve(new int[]{6, 4, 2, 5, 1, 4, 7}, 13)));
System.out.println(json(Solution24.solve("cbacbb", 1)));
System.out.println(json(Solution25.solve(new int[]{2, 2})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("cbacbb")));
System.out.println(json(Solution27.solve("CBACBB", 3)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0}, 3)));
System.out.println(json(Solution29.solve("cbacbb", "c")));
System.out.println(json(Solution30.solve("cbacbb", "c")));
System.out.println(json(Solution31.solve("cbacbb", "c")));
System.out.println(json(Solution32.solve("cabcaababccaba", new String[]{"b"})));
System.out.println(json(Solution0.solve(new int[]{0, 0, 1, 2}, -8)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{0, 0, 1, 2})));
System.out.println(json(Solution3.solve(new int[]{0, 0, 1, 2})));
System.out.println(json(Solution4.solve(new int[]{0, 0, 1, 2})));
System.out.println(json(Solution5.solve(new int[]{0, 0, 1, 2})));
System.out.println(json(Solution6.solve(new int[]{1, 0, 0, 2})));
System.out.println(json(Solution7.solve(new int[]{1, 0, 0, 2}, -8)));
System.out.println(json(Solution8.solve(new int[]{1, 0, 0, 2}, -8)));
System.out.println(json(Solution9.solve(new int[]{4, 3, 7, 1, 6, 4, 5}, 40)));
System.out.println(json(Solution10.solve(new int[]{1, 0, 0, 2, 2, 1, 0, 0})));
System.out.println(json(Solution11.solve(new int[]{1, 0, 0, 2}, -8)));
System.out.println(json(Solution12.solve("a##ba", "#ba##")));
System.out.println(json(Solution13.solve(new int[]{1, 0, 0, 2})));
System.out.println(json(Solution14.solve(new int[]{1, 0, 0, 2}, 3)));
System.out.println(json(Solution15.solve(new int[]{1, 0, 0, 2}, 3)));
System.out.println(json(Solution16.solve(1993)));
System.out.println(json(Solution17.solve(new int[]{2, 6, 3, 1, 4, 8, 7, 3, 5})));
System.out.println(json(Solution18.solve(new int[]{1, 0, 0, 2})));
System.out.println(json(Solution19.solve(new int[]{1, 0, 0, 2})));
System.out.println(json(Solution20.solve(new int[]{1, 0, 0, 2})));
System.out.println(json(Solution21.solve(new int[]{1, 1, 1, 2})));
System.out.println(json(Solution22.solve(new int[]{1, 0, 0, 2}, 2)));
System.out.println(json(Solution23.solve(new int[]{4, 3, 7, 1, 6, 4, 5}, 5)));
System.out.println(json(Solution24.solve("bbbac", 2)));
System.out.println(json(Solution25.solve(new int[]{1, 0, 0, 2})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("bbbac")));
System.out.println(json(Solution27.solve("BBBAC", 0)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0}, 0)));
System.out.println(json(Solution29.solve("bbbac", "ccb")));
System.out.println(json(Solution30.solve("bbbac", "ccb")));
System.out.println(json(Solution31.solve("bbbac", "ccb")));
}
static void batch2100() {
System.out.println(json(Solution32.solve("bbabacccabbbcb", new String[]{"bc", "cc"})));
System.out.println(json(Solution0.solve(new int[]{-4, -3, -3, 4}, 0)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, -3, -3, 4})));
System.out.println(json(Solution3.solve(new int[]{-4, -3, -3, 4})));
System.out.println(json(Solution4.solve(new int[]{-4, -3, -3, 4})));
System.out.println(json(Solution5.solve(new int[]{-4, -3, -3, 4})));
System.out.println(json(Solution6.solve(new int[]{-3, -3, -4, 4})));
System.out.println(json(Solution7.solve(new int[]{-3, -3, -4, 4}, 0)));
System.out.println(json(Solution8.solve(new int[]{-3, -3, -4, 4}, 0)));
System.out.println(json(Solution9.solve(new int[]{6, 4, 2, 4, 5, 7, 4}, 32)));
System.out.println(json(Solution10.solve(new int[]{2, 2, 0, 2, 1, 2})));
System.out.println(json(Solution11.solve(new int[]{-3, -3, -4, 4}, 0)));
System.out.println(json(Solution12.solve("#ba", "#ba##ba##")));
System.out.println(json(Solution13.solve(new int[]{-3, -3, -4, 4})));
System.out.println(json(Solution14.solve(new int[]{-3, -3, -4, 4}, 1)));
System.out.println(json(Solution15.solve(new int[]{-3, -3, -4, 4}, 1)));
System.out.println(json(Solution16.solve(1585)));
System.out.println(json(Solution17.solve(new int[]{2, 3, 4, 6, 7, 8, 1, 3, 5})));
System.out.println(json(Solution18.solve(new int[]{-3, -3, -4, 4})));
System.out.println(json(Solution19.solve(new int[]{-3, -3, -4, 4})));
System.out.println(json(Solution20.solve(new int[]{-3, -3, -4, 4})));
System.out.println(json(Solution21.solve(new int[]{-3, -3, -4, 4})));
System.out.println(json(Solution22.solve(new int[]{-3, -3, -4, 4}, 2)));
System.out.println(json(Solution23.solve(new int[]{6, 4, 2, 4, 5, 7, 4}, 13)));
System.out.println(json(Solution24.solve("a", 2)));
System.out.println(json(Solution25.solve(new int[]{-3, -3, -4, 4})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("a")));
System.out.println(json(Solution27.solve("A", 0)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0}, 0)));
System.out.println(json(Solution29.solve("a", "cb")));
System.out.println(json(Solution30.solve("a", "cb")));
System.out.println(json(Solution31.solve("a", "cb")));
System.out.println(json(Solution32.solve("baabbbcbcaccca", new String[]{"a", "c", "c"})));
System.out.println(json(Solution0.solve(new int[]{-3, -2, -2, 1, 2, 4}, -6)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-3, -2, -2, 1, 2, 4})));
System.out.println(json(Solution3.solve(new int[]{-3, -2, -2, 1, 2, 4})));
System.out.println(json(Solution4.solve(new int[]{-3, -2, -2, 1, 2, 4})));
System.out.println(json(Solution5.solve(new int[]{-3, -2, -2, 1, 2, 4})));
System.out.println(json(Solution6.solve(new int[]{-2, 2, 1, -2, 4, -3})));
System.out.println(json(Solution7.solve(new int[]{-2, 2, 1, -2, 4, -3}, -6)));
System.out.println(json(Solution8.solve(new int[]{-2, 2, 1, -2, 4, -3}, -6)));
System.out.println(json(Solution9.solve(new int[]{2, 3, 2, 5, 1}, 36)));
System.out.println(json(Solution10.solve(new int[]{2, 0, 2, 1})));
System.out.println(json(Solution11.solve(new int[]{-2, 2, 1, -2, 4, -3}, -6)));
System.out.println(json(Solution12.solve("a", "#ba##ba")));
System.out.println(json(Solution13.solve(new int[]{-2, 2, 1, -2, 4, -3})));
System.out.println(json(Solution14.solve(new int[]{-2, 2, 1, -2, 4, -3}, 3)));
System.out.println(json(Solution15.solve(new int[]{-2, 2, 1, -2, 4, -3}, 3)));
System.out.println(json(Solution16.solve(1775)));
System.out.println(json(Solution17.solve(new int[]{2, 3, 4, 5, 6, 3, 1})));
System.out.println(json(Solution18.solve(new int[]{-2, 2, 1, -2, 4, -3})));
System.out.println(json(Solution19.solve(new int[]{-2, 2, 1, -2, 4, -3})));
System.out.println(json(Solution20.solve(new int[]{-2, 2, 1, -2, 4, -3})));
System.out.println(json(Solution21.solve(new int[]{-2, 2, 1, -2, 4, -3})));
System.out.println(json(Solution22.solve(new int[]{-2, 2, 1, -2, 4, -3}, 6)));
System.out.println(json(Solution23.solve(new int[]{2, 3, 2, 5, 1}, 9)));
System.out.println(json(Solution24.solve("aabcac", 1)));
System.out.println(json(Solution25.solve(new int[]{-2, 2, 1, -2, 4, -3})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("aabcac")));
System.out.println(json(Solution27.solve("AABCAC", 3)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0}, 3)));
System.out.println(json(Solution29.solve("aabcac", "abc")));
System.out.println(json(Solution30.solve("aabcac", "abc")));
System.out.println(json(Solution31.solve("aabcac", "abc")));
System.out.println(json(Solution32.solve("acbabbacabbbba", new String[]{"a", "a", "b"})));
System.out.println(json(Solution0.solve(new int[]{-2, -2, -2, -2, -2, 0, 2, 2}, 2)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-2, -2, -2, -2, -2, 0, 2, 2})));
System.out.println(json(Solution3.solve(new int[]{-2, -2, -2, -2, -2, 0, 2, 2})));
System.out.println(json(Solution4.solve(new int[]{-2, -2, -2, -2, -2, 0, 2, 2})));
System.out.println(json(Solution5.solve(new int[]{-2, -2, -2, -2, -2, 0, 2, 2})));
System.out.println(json(Solution6.solve(new int[]{2, -2, -2, 0, -2, -2, -2, 2})));
System.out.println(json(Solution7.solve(new int[]{2, -2, -2, 0, -2, -2, -2, 2}, 2)));
System.out.println(json(Solution8.solve(new int[]{2, -2, -2, 0, -2, -2, -2, 2}, 2)));
System.out.println(json(Solution9.solve(new int[]{2, 7, 3, 1, 3}, 12)));
System.out.println(json(Solution10.solve(new int[]{1, 2, 2, 0, 2, 0})));
System.out.println(json(Solution11.solve(new int[]{2, -2, -2, 0, -2, -2, -2, 2}, 2)));
System.out.println(json(Solution12.solve("#ba##ba##", "a##ba##ba")));
System.out.println(json(Solution13.solve(new int[]{2, -2, -2, 0, -2, -2, -2, 2})));
System.out.println(json(Solution14.solve(new int[]{2, -2, -2, 0, -2, -2, -2, 2}, 7)));
System.out.println(json(Solution15.solve(new int[]{2, -2, -2, 0, -2, -2, -2, 2}, 7)));
System.out.println(json(Solution16.solve(971)));
System.out.println(json(Solution17.solve(new int[]{2, 1, 1})));
System.out.println(json(Solution18.solve(new int[]{2, -2, -2, 0, -2, -2, -2, 2})));
System.out.println(json(Solution19.solve(new int[]{2, -2, -2, 0, -2, -2, -2, 2})));
System.out.println(json(Solution20.solve(new int[]{2, -2, -2, 0, -2, -2, -2, 2})));
System.out.println(json(Solution21.solve(new int[]{2, -2, -2, 1, -2, -2, -2, 2})));
System.out.println(json(Solution22.solve(new int[]{2, -2, -2, 0, -2, -2, -2, 2}, 6)));
System.out.println(json(Solution23.solve(new int[]{2, 7, 3, 1, 3}, 9)));
System.out.println(json(Solution24.solve("cc", 1)));
System.out.println(json(Solution25.solve(new int[]{2, -2, -2, 0, -2, -2, -2, 2})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("cc")));
System.out.println(json(Solution27.solve("CC", 3)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0}, 3)));
System.out.println(json(Solution29.solve("cc", "b")));
System.out.println(json(Solution30.solve("cc", "b")));
System.out.println(json(Solution31.solve("cc", "b")));
System.out.println(json(Solution32.solve("bccbbaacbcbcca", new String[]{"b"})));
}
static void batch2200() {
System.out.println(json(Solution0.solve(new int[]{-4, -3, -3, -1, -1, 0, 0, 3}, -6)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, -3, -3, -1, -1, 0, 0, 3})));
System.out.println(json(Solution3.solve(new int[]{-4, -3, -3, -1, -1, 0, 0, 3})));
System.out.println(json(Solution4.solve(new int[]{-4, -3, -3, -1, -1, 0, 0, 3})));
System.out.println(json(Solution5.solve(new int[]{-4, -3, -3, -1, -1, 0, 0, 3})));
System.out.println(json(Solution6.solve(new int[]{3, -1, -3, -3, 0, -4, -1, 0})));
System.out.println(json(Solution7.solve(new int[]{3, -1, -3, -3, 0, -4, -1, 0}, -6)));
System.out.println(json(Solution8.solve(new int[]{3, -1, -3, -3, 0, -4, -1, 0}, -6)));
System.out.println(json(Solution9.solve(new int[]{5, 1, 6}, 40)));
System.out.println(json(Solution10.solve(new int[]{2, 0, 0, 1, 1, 1})));
System.out.println(json(Solution11.solve(new int[]{3, -1, -3, -3, 0, -4, -1, 0}, -6)));
System.out.println(json(Solution12.solve("#ba", "#ba")));
System.out.println(json(Solution13.solve(new int[]{3, -1, -3, -3, 0, -4, -1, 0})));
System.out.println(json(Solution14.solve(new int[]{3, -1, -3, -3, 0, -4, -1, 0}, 3)));
System.out.println(json(Solution15.solve(new int[]{3, -1, -3, -3, 0, -4, -1, 0}, 3)));
System.out.println(json(Solution16.solve(1251)));
System.out.println(json(Solution17.solve(new int[]{2, 1, 1})));
System.out.println(json(Solution18.solve(new int[]{3, -1, -3, -3, 0, -4, -1, 0})));
System.out.println(json(Solution19.solve(new int[]{3, -1, -3, -3, 0, -4, -1, 0})));
System.out.println(json(Solution20.solve(new int[]{3, -1, -3, -3, 0, -4, -1, 0})));
System.out.println(json(Solution21.solve(new int[]{3, -1, -3, -3, 1, -4, -1, 1})));
System.out.println(json(Solution22.solve(new int[]{3, -1, -3, -3, 0, -4, -1, 0}, 6)));
System.out.println(json(Solution23.solve(new int[]{5, 1, 6}, 9)));
System.out.println(json(Solution24.solve("a", 2)));
System.out.println(json(Solution25.solve(new int[]{3, -1, -3, -3, 0, -4, -1, 0})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("a")));
System.out.println(json(Solution27.solve("A", 0)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0}, 0)));
System.out.println(json(Solution29.solve("a", "bac")));
System.out.println(json(Solution30.solve("a", "bac")));
System.out.println(json(Solution31.solve("a", "bac")));
System.out.println(json(Solution32.solve("acaaabbcccbcca", new String[]{"ba"})));
System.out.println(json(Solution0.solve(new int[]{3, 4}, 7)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{3, 4})));
System.out.println(json(Solution3.solve(new int[]{3, 4})));
System.out.println(json(Solution4.solve(new int[]{3, 4})));
System.out.println(json(Solution5.solve(new int[]{3, 4})));
System.out.println(json(Solution6.solve(new int[]{4, 3, 0, 1, 2})));
System.out.println(json(Solution7.solve(new int[]{4, 3, 0, 1, 2}, 7)));
System.out.println(json(Solution8.solve(new int[]{4, 3, 0, 1, 2}, 7)));
System.out.println(json(Solution9.solve(new int[]{5, 1, 1, 1, 4, 3, 6}, 40)));
System.out.println(json(Solution10.solve(new int[]{0, 0, 2, 2, 2, 1})));
System.out.println(json(Solution11.solve(new int[]{4, 3}, 7)));
System.out.println(json(Solution12.solve("#ba##", "a")));
System.out.println(json(Solution13.solve(new int[]{4, 3})));
System.out.println(json(Solution14.solve(new int[]{4, 3}, -1)));
System.out.println(json(Solution15.solve(new int[]{4, 3}, -1)));
System.out.println(json(Solution16.solve(1083)));
System.out.println(json(Solution17.solve(new int[]{2, 1, 1})));
System.out.println(json(Solution18.solve(new int[]{4, 3})));
System.out.println(json(Solution19.solve(new int[]{4, 3})));
System.out.println(json(Solution20.solve(new int[]{4, 3})));
System.out.println(json(Solution21.solve(new int[]{4, 3})));
System.out.println(json(Solution22.solve(new int[]{4, 3}, 2)));
System.out.println(json(Solution23.solve(new int[]{5, 1, 1, 1, 4, 3, 6}, 13)));
System.out.println(json(Solution24.solve("aaacca", 1)));
System.out.println(json(Solution25.solve(new int[]{4, 3})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("aaacca")));
System.out.println(json(Solution27.solve("AAACCA", 3)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0}, 3)));
System.out.println(json(Solution29.solve("aaacca", "a")));
System.out.println(json(Solution30.solve("aaacca", "a")));
System.out.println(json(Solution31.solve("aaacca", "a")));
System.out.println(json(Solution32.solve("aabbbcaacbaabb", new String[]{"a"})));
System.out.println(json(Solution0.solve(new int[]{-3, -1, 0, 3}, 8)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-3, -1, 0, 3})));
System.out.println(json(Solution3.solve(new int[]{-3, -1, 0, 3})));
System.out.println(json(Solution4.solve(new int[]{-3, -1, 0, 3})));
System.out.println(json(Solution5.solve(new int[]{-3, -1, 0, 3})));
System.out.println(json(Solution6.solve(new int[]{-3, -1, 0, 3})));
System.out.println(json(Solution7.solve(new int[]{-3, -1, 0, 3}, 8)));
System.out.println(json(Solution8.solve(new int[]{-3, -1, 0, 3}, 8)));
System.out.println(json(Solution9.solve(new int[]{3, 7, 3, 7, 6, 1, 2}, 8)));
System.out.println(json(Solution10.solve(new int[]{})));
System.out.println(json(Solution11.solve(new int[]{-3, -1, 0, 3}, 8)));
System.out.println(json(Solution12.solve("a##", "a##ba##ba")));
System.out.println(json(Solution13.solve(new int[]{-3, -1, 0, 3})));
System.out.println(json(Solution14.solve(new int[]{-3, -1, 0, 3}, -1)));
System.out.println(json(Solution15.solve(new int[]{-3, -1, 0, 3}, -1)));
System.out.println(json(Solution16.solve(1619)));
System.out.println(json(Solution17.solve(new int[]{1, 1, 2})));
System.out.println(json(Solution18.solve(new int[]{-3, -1, 0, 3})));
System.out.println(json(Solution19.solve(new int[]{-3, -1, 0, 3})));
System.out.println(json(Solution20.solve(new int[]{-3, -1, 0, 3})));
System.out.println(json(Solution21.solve(new int[]{-3, -1, 1, 3})));
System.out.println(json(Solution22.solve(new int[]{-3, -1, 0, 3}, 2)));
System.out.println(json(Solution23.solve(new int[]{3, 7, 3, 7, 6, 1, 2}, 17)));
System.out.println(json(Solution24.solve("b", 2)));
System.out.println(json(Solution25.solve(new int[]{-3, -1, 0, 3})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("b")));
System.out.println(json(Solution27.solve("B", 0)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0}, 0)));
System.out.println(json(Solution29.solve("b", "c")));
System.out.println(json(Solution30.solve("b", "c")));
System.out.println(json(Solution31.solve("b", "c")));
System.out.println(json(Solution32.solve("cbbcabcbaaaacb", new String[]{"cc"})));
System.out.println(json(Solution0.solve(new int[]{-2, -2, -1, -1, 0, 3, 4, 4}, -8)));
}
static void batch2300() {
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-2, -2, -1, -1, 0, 3, 4, 4})));
System.out.println(json(Solution3.solve(new int[]{-2, -2, -1, -1, 0, 3, 4, 4})));
System.out.println(json(Solution4.solve(new int[]{-2, -2, -1, -1, 0, 3, 4, 4})));
System.out.println(json(Solution5.solve(new int[]{-2, -2, -1, -1, 0, 3, 4, 4})));
System.out.println(json(Solution6.solve(new int[]{3, -2, 4, -1, 0, -2, 4, -1})));
System.out.println(json(Solution7.solve(new int[]{3, -2, 4, -1, 0, -2, 4, -1}, -8)));
System.out.println(json(Solution8.solve(new int[]{3, -2, 4, -1, 0, -2, 4, -1}, -8)));
System.out.println(json(Solution9.solve(new int[]{4}, 28)));
System.out.println(json(Solution10.solve(new int[]{1, 1, 0, 2, 0, 0, 0, 2})));
System.out.println(json(Solution11.solve(new int[]{3, -2, 4, -1, 0, -2, 4, -1}, -8)));
System.out.println(json(Solution12.solve("a##ba##ba", "#ba")));
System.out.println(json(Solution13.solve(new int[]{3, -2, 4, -1, 0, -2, 4, -1})));
System.out.println(json(Solution14.solve(new int[]{3, -2, 4, -1, 0, -2, 4, -1}, 7)));
System.out.println(json(Solution15.solve(new int[]{3, -2, 4, -1, 0, -2, 4, -1}, 7)));
System.out.println(json(Solution16.solve(143)));
System.out.println(json(Solution17.solve(new int[]{3, 6, 3, 1, 4, 5, 2})));
System.out.println(json(Solution18.solve(new int[]{3, -2, 4, -1, 0, -2, 4, -1})));
System.out.println(json(Solution19.solve(new int[]{3, -2, 4, -1, 0, -2, 4, -1})));
System.out.println(json(Solution20.solve(new int[]{3, -2, 4, -1, 0, -2, 4, -1})));
System.out.println(json(Solution21.solve(new int[]{3, -2, 4, -1, 1, -2, 4, -1})));
System.out.println(json(Solution22.solve(new int[]{3, -2, 4, -1, 0, -2, 4, -1}, 6)));
System.out.println(json(Solution23.solve(new int[]{4}, 17)));
System.out.println(json(Solution24.solve("acbba", 2)));
System.out.println(json(Solution25.solve(new int[]{3, -2, 4, -1, 0, -2, 4, -1})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("acbba")));
System.out.println(json(Solution27.solve("ACBBA", 0)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0}, 0)));
System.out.println(json(Solution29.solve("acbba", "bcb")));
System.out.println(json(Solution30.solve("acbba", "bcb")));
System.out.println(json(Solution31.solve("acbba", "bcb")));
System.out.println(json(Solution32.solve("baacabcaaaccca", new String[]{"ba"})));
System.out.println(json(Solution0.solve(new int[]{-3, 0, 3, 3, 3, 3}, -8)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-3, 0, 3, 3, 3, 3})));
System.out.println(json(Solution3.solve(new int[]{-3, 0, 3, 3, 3, 3})));
System.out.println(json(Solution4.solve(new int[]{-3, 0, 3, 3, 3, 3})));
System.out.println(json(Solution5.solve(new int[]{-3, 0, 3, 3, 3, 3})));
System.out.println(json(Solution6.solve(new int[]{3, 3, 3, 0, -3, 3})));
System.out.println(json(Solution7.solve(new int[]{3, 3, 3, 0, -3, 3}, -8)));
System.out.println(json(Solution8.solve(new int[]{3, 3, 3, 0, -3, 3}, -8)));
System.out.println(json(Solution9.solve(new int[]{5, 3, 1, 4, 2}, 28)));
System.out.println(json(Solution10.solve(new int[]{1, 2, 0, 1, 0, 2, 1, 0})));
System.out.println(json(Solution11.solve(new int[]{3, 3, 3, 0, -3, 3}, -8)));
System.out.println(json(Solution12.solve("a##ba##ba", "#ba##ba##")));
System.out.println(json(Solution13.solve(new int[]{3, 3, 3, 0, -3, 3})));
System.out.println(json(Solution14.solve(new int[]{3, 3, 3, 0, -3, 3}, 1)));
System.out.println(json(Solution15.solve(new int[]{3, 3, 3, 0, -3, 3}, 1)));
System.out.println(json(Solution16.solve(1069)));
System.out.println(json(Solution17.solve(new int[]{3, 4, 2, 1, 3})));
System.out.println(json(Solution18.solve(new int[]{3, 3, 3, 0, -3, 3})));
System.out.println(json(Solution19.solve(new int[]{3, 3, 3, 0, -3, 3})));
System.out.println(json(Solution20.solve(new int[]{3, 3, 3, 0, -3, 3})));
System.out.println(json(Solution21.solve(new int[]{3, 3, 3, 1, -3, 3})));
System.out.println(json(Solution22.solve(new int[]{3, 3, 3, 0, -3, 3}, 4)));
System.out.println(json(Solution23.solve(new int[]{5, 3, 1, 4, 2}, 5)));
System.out.println(json(Solution24.solve("bcb", 4)));
System.out.println(json(Solution25.solve(new int[]{3, 3, 3, 0, -3, 3})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("bcb")));
System.out.println(json(Solution27.solve("BCB", 2)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0}, 2)));
System.out.println(json(Solution29.solve("bcb", "ca")));
System.out.println(json(Solution30.solve("bcb", "ca")));
System.out.println(json(Solution31.solve("bcb", "ca")));
System.out.println(json(Solution32.solve("ccbbbbaabbbcba", new String[]{"a", "b", "b"})));
System.out.println(json(Solution0.solve(new int[]{-4, -3, -3, -2, -1, 0, 1, 4}, -7)));
System.out.println(json(Solution1.solve(new int[]{0, 1, 0, 1, 0, 1, 0})));
System.out.println(json(Solution2.solve(new int[]{-4, -3, -3, -2, -1, 0, 1, 4})));
System.out.println(json(Solution3.solve(new int[]{-4, -3, -3, -2, -1, 0, 1, 4})));
System.out.println(json(Solution4.solve(new int[]{-4, -3, -3, -2, -1, 0, 1, 4})));
System.out.println(json(Solution5.solve(new int[]{-4, -3, -3, -2, -1, 0, 1, 4})));
System.out.println(json(Solution6.solve(new int[]{-2, -3, -4, 0, -3, 4, -1, 1})));
System.out.println(json(Solution7.solve(new int[]{-2, -3, -4, 0, -3, 4, -1, 1}, -7)));
System.out.println(json(Solution8.solve(new int[]{-2, -3, -4, 0, -3, 4, -1, 1}, -7)));
System.out.println(json(Solution9.solve(new int[]{5}, 12)));
System.out.println(json(Solution10.solve(new int[]{1, 1, 0, 0})));
System.out.println(json(Solution11.solve(new int[]{-2, -3, -4, 0, -3, 4, -1, 1}, -7)));
System.out.println(json(Solution12.solve("a##ba##ba", "#ba##ba")));
System.out.println(json(Solution13.solve(new int[]{-2, -3, -4, 0, -3, 4, -1, 1})));
System.out.println(json(Solution14.solve(new int[]{-2, -3, -4, 0, -3, 4, -1, 1}, 2)));
System.out.println(json(Solution15.solve(new int[]{-2, -3, -4, 0, -3, 4, -1, 1}, 2)));
System.out.println(json(Solution16.solve(1919)));
System.out.println(json(Solution17.solve(new int[]{3, 4, 1, 6, 2, 1, 5})));
System.out.println(json(Solution18.solve(new int[]{-2, -3, -4, 0, -3, 4, -1, 1})));
System.out.println(json(Solution19.solve(new int[]{-2, -3, -4, 0, -3, 4, -1, 1})));
System.out.println(json(Solution20.solve(new int[]{-2, -3, -4, 0, -3, 4, -1, 1})));
System.out.println(json(Solution21.solve(new int[]{-2, -3, -4, 1, -3, 4, -1, 1})));
System.out.println(json(Solution22.solve(new int[]{-2, -3, -4, 0, -3, 4, -1, 1}, 6)));
System.out.println(json(Solution23.solve(new int[]{5}, 17)));
System.out.println(json(Solution24.solve("bbcc", 3)));
System.out.println(json(Solution25.solve(new int[]{-2, -3, -4, 0, -3, 4, -1, 1})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("bbcc")));
System.out.println(json(Solution27.solve("BBCC", 1)));
System.out.println(json(Solution28.solve(new int[]{0, 1, 0, 1, 0, 1, 0}, 1)));
System.out.println(json(Solution29.solve("bbcc", "bc")));
System.out.println(json(Solution30.solve("bbcc", "bc")));
System.out.println(json(Solution31.solve("bbcc", "bc")));
System.out.println(json(Solution32.solve("cbbaabaabbcbbb", new String[]{"aa", "ac"})));
System.out.println(json(Solution0.solve(new int[]{-4, -4, -2, -1, 3, 3}, 4)));
System.out.println(json(Solution1.solve(new int[]{0})));
}
static void batch2400() {
System.out.println(json(Solution2.solve(new int[]{-4, -4, -2, -1, 3, 3})));
System.out.println(json(Solution3.solve(new int[]{-4, -4, -2, -1, 3, 3})));
System.out.println(json(Solution4.solve(new int[]{-4, -4, -2, -1, 3, 3})));
System.out.println(json(Solution5.solve(new int[]{-4, -4, -2, -1, 3, 3})));
System.out.println(json(Solution6.solve(new int[]{-1, 3, 3, -2, -4, -4})));
System.out.println(json(Solution7.solve(new int[]{-1, 3, 3, -2, -4, -4}, 4)));
System.out.println(json(Solution8.solve(new int[]{-1, 3, 3, -2, -4, -4}, 4)));
System.out.println(json(Solution9.solve(new int[]{2, 7, 3, 4, 7, 7, 4}, 40)));
System.out.println(json(Solution10.solve(new int[]{})));
System.out.println(json(Solution11.solve(new int[]{-1, 3, 3, -2, -4, -4}, 4)));
System.out.println(json(Solution12.solve("a##ba##ba", "#ba##ba##")));
System.out.println(json(Solution13.solve(new int[]{-1, 3, 3, -2, -4, -4})));
System.out.println(json(Solution14.solve(new int[]{-1, 3, 3, -2, -4, -4}, -1)));
System.out.println(json(Solution15.solve(new int[]{-1, 3, 3, -2, -4, -4}, -1)));
System.out.println(json(Solution16.solve(1697)));
System.out.println(json(Solution17.solve(new int[]{6, 4, 3, 1, 2, 3, 8, 5, 7})));
System.out.println(json(Solution18.solve(new int[]{-1, 3, 3, -2, -4, -4})));
System.out.println(json(Solution19.solve(new int[]{-1, 3, 3, -2, -4, -4})));
System.out.println(json(Solution20.solve(new int[]{-1, 3, 3, -2, -4, -4})));
System.out.println(json(Solution21.solve(new int[]{-1, 3, 3, -2, -4, -4})));
System.out.println(json(Solution22.solve(new int[]{-1, 3, 3, -2, -4, -4}, 6)));
System.out.println(json(Solution23.solve(new int[]{2, 7, 3, 4, 7, 7, 4}, 17)));
System.out.println(json(Solution24.solve("aaacbabc", 3)));
System.out.println(json(Solution25.solve(new int[]{-1, 3, 3, -2, -4, -4})));
System.out.println(json(new Solution26().lengthOfLongestSubstring("aaacbabc")));
System.out.println(json(Solution27.solve("AAACBABC", 1)));
System.out.println(json(Solution28.solve(new int[]{0}, 1)));
System.out.println(json(Solution29.solve("aaacbabc", "bc")));
System.out.println(json(Solution30.solve("aaacbabc", "bc")));
System.out.println(json(Solution31.solve("aaacbabc", "bc")));
System.out.println(json(Solution32.solve("bbcbccbcabbaaa", new String[]{"bc"})));
System.out.println(json(Solution14.solve(new int[]{}, -1)));
System.out.println(json(Solution15.solve(new int[]{1}, 0)));
System.out.println(json(Solution18.solve(new int[]{})));
System.out.println(json(Solution19.solve(new int[]{})));
System.out.println(json(Solution19.solve(new int[]{1, 2, 2, 1})));
System.out.println(json(Solution20.solve(new int[]{})));
System.out.println(json(Solution9.solve(new int[]{1, 1, 1}, 1)));
System.out.println(json(Solution29.solve("a", "aa")));
System.out.println(json(Solution29.solve("", "")));
System.out.println(json(Solution30.solve("", "")));
System.out.println(json(Solution31.solve("aaaa", "aa")));
System.out.println(json(Solution32.solve("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"})));
System.out.println(json(Solution21.solve(new int[]{-1, -2, -3, -4, -5, 6})));
System.out.println(json(Solution21.solve(new int[]{1, -1, 5, 1, 4})));
System.out.println(json(Solution13.solve(new int[]{})));
System.out.println(json(Solution33.solve(new int[]{2, 0, 0, 3, -4, -3, 2, 4})));
System.out.println(json(Solution34.solve(new int[]{2, 0, 0, 3, -4, -3, 2, 4})));
System.out.println(json(Solution35.solve(new int[]{2, 0, 0, 3, -4, -3, 2, 4})));
System.out.println(json(Solution36.solve(new int[]{2, 0, 0, 3, -4, -3, 2, 4})));
System.out.println(json(Solution37.solve(new int[]{2, 0, 0, 3, -4, -3, 2, 4})));
System.out.println(json(Solution38.solve(new int[]{2, 0, 0, 3, -4, -3, 2, 4})));
System.out.println(json(Solution39.solve(new int[]{2, 0, 0, 3, -4, -3, 2, 4}, 7)));
System.out.println(json(Solution40.solve(new int[]{2, 0, 0, 3, -4, -3, 2, 4})));
System.out.println(json(Solution41.solve(new int[]{2, 0, 0, 3, -4, -3, 2, 4}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 0, 1, 0, 1, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{2, 0, 0, 3, -4, -3, 2, 4}, 7)));
System.out.println(json(Solution44.solve(new int[]{2, 0, 0, 3, -4, -3, 2, 4}, -5, -2)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{5, 6}, new int[]{2, 6}, new int[]{-2, -1}, new int[]{-1, 3}, new int[]{-4, -3}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{5, 6}, new int[]{2, 6}, new int[]{-2, -1}, new int[]{-1, 3}, new int[]{-4, -3}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{5, 6}, new int[]{2, 6}, new int[]{-2, -1}, new int[]{-1, 3}, new int[]{-4, -3}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-4, -3}, new int[]{-2, 6}}, new int[]{2, 4})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-4, -3}, new int[]{-2, 6}}, new int[][]{new int[]{-1, -1}, new int[]{0, 1}, new int[]{3, 3}, new int[]{7, 13}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{5, 6}, new int[]{2, 6}, new int[]{-2, -1}, new int[]{-1, 3}, new int[]{-4, -3}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{5, 6, 6}, new int[]{2, 6, 5}, new int[]{-2, -1, 0}, new int[]{-1, 3, 7}, new int[]{-4, -3, 2}})));
System.out.println(json(Solution33.solve(new int[]{1, 3})));
System.out.println(json(Solution34.solve(new int[]{1, 3})));
System.out.println(json(Solution35.solve(new int[]{1, 3})));
System.out.println(json(Solution36.solve(new int[]{1, 3})));
System.out.println(json(Solution37.solve(new int[]{1, 3})));
System.out.println(json(Solution38.solve(new int[]{1, 3})));
System.out.println(json(Solution39.solve(new int[]{1, 3}, 3)));
System.out.println(json(Solution40.solve(new int[]{1, 3})));
System.out.println(json(Solution41.solve(new int[]{1, 3}, 4)));
System.out.println(json(Solution42.solve(new int[]{1, 1})));
System.out.println(json(Solution43.solve(new int[]{1, 3}, 3)));
System.out.println(json(Solution44.solve(new int[]{1, 3}, -4, 3)));
System.out.println(json(Solution45.solve(new int[][]{})));
System.out.println(json(Solution46.solve(new int[][]{})));
System.out.println(json(Solution49.solve(new int[][]{})));
System.out.println(json(Solution47.solve(new int[][]{}, new int[]{4, 6})));
System.out.println(json(Solution48.solve(new int[][]{}, new int[][]{new int[]{-5, -1}})));
System.out.println(json(Solution50.solve(new int[][]{})));
System.out.println(json(Solution51.solve(new int[][]{})));
System.out.println(json(Solution33.solve(new int[]{1})));
System.out.println(json(Solution34.solve(new int[]{1})));
System.out.println(json(Solution35.solve(new int[]{1})));
System.out.println(json(Solution36.solve(new int[]{1})));
System.out.println(json(Solution37.solve(new int[]{1})));
System.out.println(json(Solution38.solve(new int[]{1})));
System.out.println(json(Solution39.solve(new int[]{1}, -1)));
System.out.println(json(Solution40.solve(new int[]{1})));
System.out.println(json(Solution41.solve(new int[]{1}, 2)));
System.out.println(json(Solution42.solve(new int[]{1})));
System.out.println(json(Solution43.solve(new int[]{1}, -1)));
System.out.println(json(Solution44.solve(new int[]{1}, 1, 6)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-4, -3}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-4, -3}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-4, -3}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-4, -3}}, new int[]{6, 6})));
}
static void batch2500() {
System.out.println(json(Solution48.solve(new int[][]{new int[]{-4, -3}}, new int[][]{new int[]{1, 3}, new int[]{4, 6}, new int[]{9, 10}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-4, -3}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-4, -3, 6}})));
System.out.println(json(Solution33.solve(new int[]{4, 1, 3, 1, -1, -1})));
System.out.println(json(Solution34.solve(new int[]{4, 1, 3, 1, -1, -1})));
System.out.println(json(Solution35.solve(new int[]{4, 1, 3, 1, -1, -1})));
System.out.println(json(Solution36.solve(new int[]{4, 1, 3, 1, -1, -1})));
System.out.println(json(Solution37.solve(new int[]{4, 1, 3, 1, -1, -1})));
System.out.println(json(Solution38.solve(new int[]{4, 1, 3, 1, -1, -1})));
System.out.println(json(Solution39.solve(new int[]{4, 1, 3, 1, -1, -1}, 7)));
System.out.println(json(Solution40.solve(new int[]{4, 1, 3, 1, -1, -1})));
System.out.println(json(Solution41.solve(new int[]{4, 1, 3, 1, -1, -1}, 6)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 1, 1, 1, 1})));
System.out.println(json(Solution43.solve(new int[]{4, 1, 3, 1, -1, -1}, 7)));
System.out.println(json(Solution44.solve(new int[]{4, 1, 3, 1, -1, -1}, -5, -4)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{9, 11}, new int[]{5, 6}, new int[]{6, 8}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{9, 11}, new int[]{5, 6}, new int[]{6, 8}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{9, 11}, new int[]{5, 6}, new int[]{6, 8}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{5, 8}, new int[]{9, 11}}, new int[]{-2, 1})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{5, 8}, new int[]{9, 11}}, new int[][]{new int[]{-4, -2}, new int[]{1, 5}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{9, 11}, new int[]{5, 6}, new int[]{6, 8}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{9, 11, 6}, new int[]{5, 6, 5}, new int[]{6, 8, 0}})));
System.out.println(json(Solution33.solve(new int[]{0, -1, 2, 0, 3, -3, 0, 3})));
System.out.println(json(Solution34.solve(new int[]{0, -1, 2, 0, 3, -3, 0, 3})));
System.out.println(json(Solution35.solve(new int[]{0, -1, 2, 0, 3, -3, 0, 3})));
System.out.println(json(Solution36.solve(new int[]{0, -1, 2, 0, 3, -3, 0, 3})));
System.out.println(json(Solution37.solve(new int[]{0, -1, 2, 0, 3, -3, 0, 3})));
System.out.println(json(Solution38.solve(new int[]{0, -1, 2, 0, 3, -3, 0, 3})));
System.out.println(json(Solution39.solve(new int[]{0, -1, 2, 0, 3, -3, 0, 3}, 3)));
System.out.println(json(Solution40.solve(new int[]{0, -1, 2, 0, 3, -3, 0, 3})));
System.out.println(json(Solution41.solve(new int[]{0, -1, 2, 0, 3, -3, 0, 3}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 0, 0, 1, 1, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{0, -1, 2, 0, 3, -3, 0, 3}, 3)));
System.out.println(json(Solution44.solve(new int[]{0, -1, 2, 0, 3, -3, 0, 3}, -5, 0)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-5, -1}, new int[]{-1, 2}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-5, -1}, new int[]{-1, 2}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-5, -1}, new int[]{-1, 2}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, 2}}, new int[]{-2, -1})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, 2}}, new int[][]{new int[]{-1, 0}, new int[]{3, 3}, new int[]{8, 12}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-5, -1}, new int[]{-1, 2}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-5, -1, 0}, new int[]{-1, 2, 7}})));
System.out.println(json(Solution33.solve(new int[]{2, 4, -4})));
System.out.println(json(Solution34.solve(new int[]{2, 4, -4})));
System.out.println(json(Solution35.solve(new int[]{2, 4, -4})));
System.out.println(json(Solution36.solve(new int[]{2, 4, -4})));
System.out.println(json(Solution37.solve(new int[]{2, 4, -4})));
System.out.println(json(Solution38.solve(new int[]{2, 4, -4})));
System.out.println(json(Solution39.solve(new int[]{2, 4, -4}, 7)));
System.out.println(json(Solution40.solve(new int[]{2, 4, -4})));
System.out.println(json(Solution41.solve(new int[]{2, 4, -4}, 6)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{2, 4, -4}, 7)));
System.out.println(json(Solution44.solve(new int[]{2, 4, -4}, 0, 1)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-1, 2}, new int[]{-2, 1}, new int[]{-4, -1}, new int[]{7, 11}, new int[]{4, 9}, new int[]{2, 5}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-1, 2}, new int[]{-2, 1}, new int[]{-4, -1}, new int[]{7, 11}, new int[]{4, 9}, new int[]{2, 5}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-1, 2}, new int[]{-2, 1}, new int[]{-4, -1}, new int[]{7, 11}, new int[]{4, 9}, new int[]{2, 5}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-4, 11}}, new int[]{6, 7})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-4, 11}}, new int[][]{new int[]{-4, -1}, new int[]{0, 1}, new int[]{6, 8}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-1, 2}, new int[]{-2, 1}, new int[]{-4, -1}, new int[]{7, 11}, new int[]{4, 9}, new int[]{2, 5}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-1, 2, 4}, new int[]{-2, 1, 3}, new int[]{-4, -1, 6}, new int[]{7, 11, 5}, new int[]{4, 9, 0}, new int[]{2, 5, 7}})));
System.out.println(json(Solution33.solve(new int[]{2, -4, 4})));
System.out.println(json(Solution34.solve(new int[]{2, -4, 4})));
System.out.println(json(Solution35.solve(new int[]{2, -4, 4})));
System.out.println(json(Solution36.solve(new int[]{2, -4, 4})));
System.out.println(json(Solution37.solve(new int[]{2, -4, 4})));
System.out.println(json(Solution38.solve(new int[]{2, -4, 4})));
System.out.println(json(Solution39.solve(new int[]{2, -4, 4}, 7)));
System.out.println(json(Solution40.solve(new int[]{2, -4, 4})));
System.out.println(json(Solution41.solve(new int[]{2, -4, 4}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{2, -4, 4}, 7)));
System.out.println(json(Solution44.solve(new int[]{2, -4, 4}, -2, 7)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{5, 8}, new int[]{-1, 1}, new int[]{-1, 4}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{5, 8}, new int[]{-1, 1}, new int[]{-1, 4}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{5, 8}, new int[]{-1, 1}, new int[]{-1, 4}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-1, 4}, new int[]{5, 8}}, new int[]{4, 6})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-1, 4}, new int[]{5, 8}}, new int[][]{new int[]{-1, 2}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{5, 8}, new int[]{-1, 1}, new int[]{-1, 4}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{5, 8, 6}, new int[]{-1, 1, 5}, new int[]{-1, 4, 0}})));
System.out.println(json(Solution33.solve(new int[]{-1, 0, 4, -3, 2, -3, 3, 1})));
System.out.println(json(Solution34.solve(new int[]{-1, 0, 4, -3, 2, -3, 3, 1})));
System.out.println(json(Solution35.solve(new int[]{-1, 0, 4, -3, 2, -3, 3, 1})));
System.out.println(json(Solution36.solve(new int[]{-1, 0, 4, -3, 2, -3, 3, 1})));
System.out.println(json(Solution37.solve(new int[]{-1, 0, 4, -3, 2, -3, 3, 1})));
System.out.println(json(Solution38.solve(new int[]{-1, 0, 4, -3, 2, -3, 3, 1})));
System.out.println(json(Solution39.solve(new int[]{-1, 0, 4, -3, 2, -3, 3, 1}, -1)));
System.out.println(json(Solution40.solve(new int[]{-1, 0, 4, -3, 2, -3, 3, 1})));
System.out.println(json(Solution41.solve(new int[]{-1, 0, 4, -3, 2, -3, 3, 1}, 6)));
System.out.println(json(Solution42.solve(new int[]{1, 0, 0, 1, 0, 1, 1, 1})));
System.out.println(json(Solution43.solve(new int[]{-1, 0, 4, -3, 2, -3, 3, 1}, -1)));
System.out.println(json(Solution44.solve(new int[]{-1, 0, 4, -3, 2, -3, 3, 1}, 0, 3)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{7, 11}, new int[]{9, 13}, new int[]{9, 13}, new int[]{8, 12}, new int[]{3, 7}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{7, 11}, new int[]{9, 13}, new int[]{9, 13}, new int[]{8, 12}, new int[]{3, 7}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{7, 11}, new int[]{9, 13}, new int[]{9, 13}, new int[]{8, 12}, new int[]{3, 7}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{3, 13}}, new int[]{2, 3})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{3, 13}}, new int[][]{new int[]{-3, -1}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{7, 11}, new int[]{9, 13}, new int[]{9, 13}, new int[]{8, 12}, new int[]{3, 7}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{7, 11, 6}, new int[]{9, 13, 5}, new int[]{9, 13, 0}, new int[]{8, 12, 7}, new int[]{3, 7, 2}})));
System.out.println(json(Solution33.solve(new int[]{2, -4})));
System.out.println(json(Solution34.solve(new int[]{2, -4})));
}
static void batch2600() {
System.out.println(json(Solution35.solve(new int[]{2, -4})));
System.out.println(json(Solution36.solve(new int[]{2, -4})));
System.out.println(json(Solution37.solve(new int[]{2, -4})));
System.out.println(json(Solution38.solve(new int[]{2, -4})));
System.out.println(json(Solution39.solve(new int[]{2, -4}, 7)));
System.out.println(json(Solution40.solve(new int[]{2, -4})));
System.out.println(json(Solution41.solve(new int[]{2, -4}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 0})));
System.out.println(json(Solution43.solve(new int[]{2, -4}, 7)));
System.out.println(json(Solution44.solve(new int[]{2, -4}, 2, 11)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{9, 13}, new int[]{6, 8}, new int[]{9, 10}, new int[]{-2, 1}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{9, 13}, new int[]{6, 8}, new int[]{9, 10}, new int[]{-2, 1}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{9, 13}, new int[]{6, 8}, new int[]{9, 10}, new int[]{-2, 1}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-2, 1}, new int[]{6, 8}, new int[]{9, 13}}, new int[]{0, 0})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-2, 1}, new int[]{6, 8}, new int[]{9, 13}}, new int[][]{new int[]{-4, -2}, new int[]{0, 5}, new int[]{9, 9}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{9, 13}, new int[]{6, 8}, new int[]{9, 10}, new int[]{-2, 1}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{9, 13, 0}, new int[]{6, 8, 7}, new int[]{9, 10, 2}, new int[]{-2, 1, 1}})));
System.out.println(json(Solution33.solve(new int[]{-2, 4, -3, -2, -4})));
System.out.println(json(Solution34.solve(new int[]{-2, 4, -3, -2, -4})));
System.out.println(json(Solution35.solve(new int[]{-2, 4, -3, -2, -4})));
System.out.println(json(Solution36.solve(new int[]{-2, 4, -3, -2, -4})));
System.out.println(json(Solution37.solve(new int[]{-2, 4, -3, -2, -4})));
System.out.println(json(Solution38.solve(new int[]{-2, 4, -3, -2, -4})));
System.out.println(json(Solution39.solve(new int[]{-2, 4, -3, -2, -4}, 7)));
System.out.println(json(Solution40.solve(new int[]{-2, 4, -3, -2, -4})));
System.out.println(json(Solution41.solve(new int[]{-2, 4, -3, -2, -4}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 1, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{-2, 4, -3, -2, -4}, 7)));
System.out.println(json(Solution44.solve(new int[]{-2, 4, -3, -2, -4}, 1, 8)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-5, 0}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-5, 0}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-5, 0}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, 0}}, new int[]{0, 1})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, 0}}, new int[][]{new int[]{6, 7}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-5, 0}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-5, 0, 6}})));
System.out.println(json(Solution33.solve(new int[]{3, -4, 1, 4, -4, 4})));
System.out.println(json(Solution34.solve(new int[]{3, -4, 1, 4, -4, 4})));
System.out.println(json(Solution35.solve(new int[]{3, -4, 1, 4, -4, 4})));
System.out.println(json(Solution36.solve(new int[]{3, -4, 1, 4, -4, 4})));
System.out.println(json(Solution37.solve(new int[]{3, -4, 1, 4, -4, 4})));
System.out.println(json(Solution38.solve(new int[]{3, -4, 1, 4, -4, 4})));
System.out.println(json(Solution39.solve(new int[]{3, -4, 1, 4, -4, 4}, 7)));
System.out.println(json(Solution40.solve(new int[]{3, -4, 1, 4, -4, 4})));
System.out.println(json(Solution41.solve(new int[]{3, -4, 1, 4, -4, 4}, 6)));
System.out.println(json(Solution42.solve(new int[]{1, 0, 1, 0, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{3, -4, 1, 4, -4, 4}, 7)));
System.out.println(json(Solution44.solve(new int[]{3, -4, 1, 4, -4, 4}, -4, -3)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{6, 9}, new int[]{-4, -3}, new int[]{-2, 0}, new int[]{8, 9}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{6, 9}, new int[]{-4, -3}, new int[]{-2, 0}, new int[]{8, 9}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{6, 9}, new int[]{-4, -3}, new int[]{-2, 0}, new int[]{8, 9}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-4, -3}, new int[]{-2, 0}, new int[]{6, 9}}, new int[]{0, 1})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-4, -3}, new int[]{-2, 0}, new int[]{6, 9}}, new int[][]{new int[]{-5, -2}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{6, 9}, new int[]{-4, -3}, new int[]{-2, 0}, new int[]{8, 9}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{6, 9, 0}, new int[]{-4, -3, 7}, new int[]{-2, 0, 2}, new int[]{8, 9, 1}})));
System.out.println(json(Solution33.solve(new int[]{-2, 4, 4, -3, 0})));
System.out.println(json(Solution34.solve(new int[]{-2, 4, 4, -3, 0})));
System.out.println(json(Solution35.solve(new int[]{-2, 4, 4, -3, 0})));
System.out.println(json(Solution36.solve(new int[]{-2, 4, 4, -3, 0})));
System.out.println(json(Solution37.solve(new int[]{-2, 4, 4, -3, 0})));
System.out.println(json(Solution38.solve(new int[]{-2, 4, 4, -3, 0})));
System.out.println(json(Solution39.solve(new int[]{-2, 4, 4, -3, 0}, 7)));
System.out.println(json(Solution40.solve(new int[]{-2, 4, 4, -3, 0})));
System.out.println(json(Solution41.solve(new int[]{-2, 4, 4, -3, 0}, 6)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 0, 1, 0})));
System.out.println(json(Solution43.solve(new int[]{-2, 4, 4, -3, 0}, 7)));
System.out.println(json(Solution44.solve(new int[]{-2, 4, 4, -3, 0}, -2, 7)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{7, 12}, new int[]{-3, 2}, new int[]{-5, -4}, new int[]{7, 11}, new int[]{7, 10}, new int[]{-4, -3}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{7, 12}, new int[]{-3, 2}, new int[]{-5, -4}, new int[]{7, 11}, new int[]{7, 10}, new int[]{-4, -3}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{7, 12}, new int[]{-3, 2}, new int[]{-5, -4}, new int[]{7, 11}, new int[]{7, 10}, new int[]{-4, -3}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, 2}, new int[]{7, 12}}, new int[]{4, 8})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, 2}, new int[]{7, 12}}, new int[][]{new int[]{-3, 2}, new int[]{5, 5}, new int[]{8, 10}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{7, 12}, new int[]{-3, 2}, new int[]{-5, -4}, new int[]{7, 11}, new int[]{7, 10}, new int[]{-4, -3}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{7, 12, 0}, new int[]{-3, 2, 7}, new int[]{-5, -4, 2}, new int[]{7, 11, 1}, new int[]{7, 10, 4}, new int[]{-4, -3, 3}})));
System.out.println(json(Solution33.solve(new int[]{-2, 4, 1, -4, -2, 3, 4})));
System.out.println(json(Solution34.solve(new int[]{-2, 4, 1, -4, -2, 3, 4})));
System.out.println(json(Solution35.solve(new int[]{-2, 4, 1, -4, -2, 3, 4})));
System.out.println(json(Solution36.solve(new int[]{-2, 4, 1, -4, -2, 3, 4})));
System.out.println(json(Solution37.solve(new int[]{-2, 4, 1, -4, -2, 3, 4})));
System.out.println(json(Solution38.solve(new int[]{-2, 4, 1, -4, -2, 3, 4})));
System.out.println(json(Solution39.solve(new int[]{-2, 4, 1, -4, -2, 3, 4}, 7)));
System.out.println(json(Solution40.solve(new int[]{-2, 4, 1, -4, -2, 3, 4})));
System.out.println(json(Solution41.solve(new int[]{-2, 4, 1, -4, -2, 3, 4}, 6)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 1, 0, 0, 1, 0})));
System.out.println(json(Solution43.solve(new int[]{-2, 4, 1, -4, -2, 3, 4}, 7)));
System.out.println(json(Solution44.solve(new int[]{-2, 4, 1, -4, -2, 3, 4}, -2, -1)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-5, 0}, new int[]{0, 1}, new int[]{7, 10}, new int[]{2, 5}, new int[]{1, 4}, new int[]{7, 8}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-5, 0}, new int[]{0, 1}, new int[]{7, 10}, new int[]{2, 5}, new int[]{1, 4}, new int[]{7, 8}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-5, 0}, new int[]{0, 1}, new int[]{7, 10}, new int[]{2, 5}, new int[]{1, 4}, new int[]{7, 8}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, 5}, new int[]{7, 10}}, new int[]{-2, -1})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, 5}, new int[]{7, 10}}, new int[][]{new int[]{0, 3}, new int[]{9, 9}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-5, 0}, new int[]{0, 1}, new int[]{7, 10}, new int[]{2, 5}, new int[]{1, 4}, new int[]{7, 8}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-5, 0, 0}, new int[]{0, 1, 7}, new int[]{7, 10, 2}, new int[]{2, 5, 1}, new int[]{1, 4, 4}, new int[]{7, 8, 3}})));
System.out.println(json(Solution33.solve(new int[]{-1, -1, 0, 3, 0, -4, -2})));
System.out.println(json(Solution34.solve(new int[]{-1, -1, 0, 3, 0, -4, -2})));
System.out.println(json(Solution35.solve(new int[]{-1, -1, 0, 3, 0, -4, -2})));
System.out.println(json(Solution36.solve(new int[]{-1, -1, 0, 3, 0, -4, -2})));
System.out.println(json(Solution37.solve(new int[]{-1, -1, 0, 3, 0, -4, -2})));
System.out.println(json(Solution38.solve(new int[]{-1, -1, 0, 3, 0, -4, -2})));
System.out.println(json(Solution39.solve(new int[]{-1, -1, 0, 3, 0, -4, -2}, 3)));
}
static void batch2700() {
System.out.println(json(Solution40.solve(new int[]{-1, -1, 0, 3, 0, -4, -2})));
System.out.println(json(Solution41.solve(new int[]{-1, -1, 0, 3, 0, -4, -2}, 2)));
System.out.println(json(Solution42.solve(new int[]{1, 1, 0, 1, 0, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{-1, -1, 0, 3, 0, -4, -2}, 3)));
System.out.println(json(Solution44.solve(new int[]{-1, -1, 0, 3, 0, -4, -2}, 0, 5)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-5, 0}, new int[]{-5, -4}, new int[]{-3, 1}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-5, 0}, new int[]{-5, -4}, new int[]{-3, 1}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-5, 0}, new int[]{-5, -4}, new int[]{-3, 1}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, 1}}, new int[]{-2, 2})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, 1}}, new int[][]{new int[]{-2, -1}, new int[]{1, 2}, new int[]{5, 10}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-5, 0}, new int[]{-5, -4}, new int[]{-3, 1}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-5, 0, 6}, new int[]{-5, -4, 5}, new int[]{-3, 1, 0}})));
System.out.println(json(Solution33.solve(new int[]{4, -2, -3, 2, 4, 1, 0, -2})));
System.out.println(json(Solution34.solve(new int[]{4, -2, -3, 2, 4, 1, 0, -2})));
System.out.println(json(Solution35.solve(new int[]{4, -2, -3, 2, 4, 1, 0, -2})));
System.out.println(json(Solution36.solve(new int[]{4, -2, -3, 2, 4, 1, 0, -2})));
System.out.println(json(Solution37.solve(new int[]{4, -2, -3, 2, 4, 1, 0, -2})));
System.out.println(json(Solution38.solve(new int[]{4, -2, -3, 2, 4, 1, 0, -2})));
System.out.println(json(Solution39.solve(new int[]{4, -2, -3, 2, 4, 1, 0, -2}, -1)));
System.out.println(json(Solution40.solve(new int[]{4, -2, -3, 2, 4, 1, 0, -2})));
System.out.println(json(Solution41.solve(new int[]{4, -2, -3, 2, 4, 1, 0, -2}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 1, 0, 0, 1, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{4, -2, -3, 2, 4, 1, 0, -2}, -1)));
System.out.println(json(Solution44.solve(new int[]{4, -2, -3, 2, 4, 1, 0, -2}, 2, 3)));
System.out.println(json(Solution45.solve(new int[][]{})));
System.out.println(json(Solution46.solve(new int[][]{})));
System.out.println(json(Solution49.solve(new int[][]{})));
System.out.println(json(Solution47.solve(new int[][]{}, new int[]{6, 6})));
System.out.println(json(Solution48.solve(new int[][]{}, new int[][]{new int[]{-2, -1}, new int[]{3, 3}, new int[]{5, 13}})));
System.out.println(json(Solution50.solve(new int[][]{})));
System.out.println(json(Solution51.solve(new int[][]{})));
System.out.println(json(Solution33.solve(new int[]{3, 1, -3, -4, 0})));
System.out.println(json(Solution34.solve(new int[]{3, 1, -3, -4, 0})));
System.out.println(json(Solution35.solve(new int[]{3, 1, -3, -4, 0})));
System.out.println(json(Solution36.solve(new int[]{3, 1, -3, -4, 0})));
System.out.println(json(Solution37.solve(new int[]{3, 1, -3, -4, 0})));
System.out.println(json(Solution38.solve(new int[]{3, 1, -3, -4, 0})));
System.out.println(json(Solution39.solve(new int[]{3, 1, -3, -4, 0}, 7)));
System.out.println(json(Solution40.solve(new int[]{3, 1, -3, -4, 0})));
System.out.println(json(Solution41.solve(new int[]{3, 1, -3, -4, 0}, 2)));
System.out.println(json(Solution42.solve(new int[]{1, 1, 1, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{3, 1, -3, -4, 0}, 7)));
System.out.println(json(Solution44.solve(new int[]{3, 1, -3, -4, 0}, -6, -1)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{3, 6}, new int[]{0, 4}, new int[]{2, 7}, new int[]{9, 10}, new int[]{4, 8}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{3, 6}, new int[]{0, 4}, new int[]{2, 7}, new int[]{9, 10}, new int[]{4, 8}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{3, 6}, new int[]{0, 4}, new int[]{2, 7}, new int[]{9, 10}, new int[]{4, 8}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{0, 8}, new int[]{9, 10}}, new int[]{2, 3})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{0, 8}, new int[]{9, 10}}, new int[][]{new int[]{-5, -1}, new int[]{8, 12}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{3, 6}, new int[]{0, 4}, new int[]{2, 7}, new int[]{9, 10}, new int[]{4, 8}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{3, 6, 2}, new int[]{0, 4, 1}, new int[]{2, 7, 4}, new int[]{9, 10, 3}, new int[]{4, 8, 6}})));
System.out.println(json(Solution33.solve(new int[]{1, 2, -3, -2, -4, 1})));
System.out.println(json(Solution34.solve(new int[]{1, 2, -3, -2, -4, 1})));
System.out.println(json(Solution35.solve(new int[]{1, 2, -3, -2, -4, 1})));
System.out.println(json(Solution36.solve(new int[]{1, 2, -3, -2, -4, 1})));
System.out.println(json(Solution37.solve(new int[]{1, 2, -3, -2, -4, 1})));
System.out.println(json(Solution38.solve(new int[]{1, 2, -3, -2, -4, 1})));
System.out.println(json(Solution39.solve(new int[]{1, 2, -3, -2, -4, 1}, 7)));
System.out.println(json(Solution40.solve(new int[]{1, 2, -3, -2, -4, 1})));
System.out.println(json(Solution41.solve(new int[]{1, 2, -3, -2, -4, 1}, 6)));
System.out.println(json(Solution42.solve(new int[]{1, 0, 1, 0, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{1, 2, -3, -2, -4, 1}, 7)));
System.out.println(json(Solution44.solve(new int[]{1, 2, -3, -2, -4, 1}, -5, -4)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{7, 10}, new int[]{7, 12}, new int[]{-1, 0}, new int[]{-2, 0}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{7, 10}, new int[]{7, 12}, new int[]{-1, 0}, new int[]{-2, 0}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{7, 10}, new int[]{7, 12}, new int[]{-1, 0}, new int[]{-2, 0}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-2, 0}, new int[]{7, 12}}, new int[]{6, 9})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-2, 0}, new int[]{7, 12}}, new int[][]{new int[]{-4, -1}, new int[]{2, 3}, new int[]{4, 7}, new int[]{9, 12}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{7, 10}, new int[]{7, 12}, new int[]{-1, 0}, new int[]{-2, 0}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{7, 10, 0}, new int[]{7, 12, 7}, new int[]{-1, 0, 2}, new int[]{-2, 0, 1}})));
System.out.println(json(Solution33.solve(new int[]{-2, 1, -1, -4, -3})));
System.out.println(json(Solution34.solve(new int[]{-2, 1, -1, -4, -3})));
System.out.println(json(Solution35.solve(new int[]{-2, 1, -1, -4, -3})));
System.out.println(json(Solution36.solve(new int[]{-2, 1, -1, -4, -3})));
System.out.println(json(Solution37.solve(new int[]{-2, 1, -1, -4, -3})));
System.out.println(json(Solution38.solve(new int[]{-2, 1, -1, -4, -3})));
System.out.println(json(Solution39.solve(new int[]{-2, 1, -1, -4, -3}, 7)));
System.out.println(json(Solution40.solve(new int[]{-2, 1, -1, -4, -3})));
System.out.println(json(Solution41.solve(new int[]{-2, 1, -1, -4, -3}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 1, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{-2, 1, -1, -4, -3}, 7)));
System.out.println(json(Solution44.solve(new int[]{-2, 1, -1, -4, -3}, -4, 3)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-4, -3}, new int[]{8, 11}, new int[]{0, 2}, new int[]{3, 5}, new int[]{-2, -1}, new int[]{8, 10}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-4, -3}, new int[]{8, 11}, new int[]{0, 2}, new int[]{3, 5}, new int[]{-2, -1}, new int[]{8, 10}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-4, -3}, new int[]{8, 11}, new int[]{0, 2}, new int[]{3, 5}, new int[]{-2, -1}, new int[]{8, 10}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-4, -3}, new int[]{-2, -1}, new int[]{0, 2}, new int[]{3, 5}, new int[]{8, 11}}, new int[]{6, 8})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-4, -3}, new int[]{-2, -1}, new int[]{0, 2}, new int[]{3, 5}, new int[]{8, 11}}, new int[][]{new int[]{-4, -1}, new int[]{3, 8}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-4, -3}, new int[]{8, 11}, new int[]{0, 2}, new int[]{3, 5}, new int[]{-2, -1}, new int[]{8, 10}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-4, -3, 0}, new int[]{8, 11, 7}, new int[]{0, 2, 2}, new int[]{3, 5, 1}, new int[]{-2, -1, 4}, new int[]{8, 10, 3}})));
System.out.println(json(Solution33.solve(new int[]{4, -3, 1, -3, 2, -3, 0})));
System.out.println(json(Solution34.solve(new int[]{4, -3, 1, -3, 2, -3, 0})));
System.out.println(json(Solution35.solve(new int[]{4, -3, 1, -3, 2, -3, 0})));
System.out.println(json(Solution36.solve(new int[]{4, -3, 1, -3, 2, -3, 0})));
System.out.println(json(Solution37.solve(new int[]{4, -3, 1, -3, 2, -3, 0})));
System.out.println(json(Solution38.solve(new int[]{4, -3, 1, -3, 2, -3, 0})));
System.out.println(json(Solution39.solve(new int[]{4, -3, 1, -3, 2, -3, 0}, 7)));
System.out.println(json(Solution40.solve(new int[]{4, -3, 1, -3, 2, -3, 0})));
System.out.println(json(Solution41.solve(new int[]{4, -3, 1, -3, 2, -3, 0}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 1, 1, 0, 1, 0})));
System.out.println(json(Solution43.solve(new int[]{4, -3, 1, -3, 2, -3, 0}, 7)));
System.out.println(json(Solution44.solve(new int[]{4, -3, 1, -3, 2, -3, 0}, 2, 7)));
}
static void batch2800() {
System.out.println(json(Solution45.solve(new int[][]{})));
System.out.println(json(Solution46.solve(new int[][]{})));
System.out.println(json(Solution49.solve(new int[][]{})));
System.out.println(json(Solution47.solve(new int[][]{}, new int[]{6, 8})));
System.out.println(json(Solution48.solve(new int[][]{}, new int[][]{new int[]{-5, -5}, new int[]{-2, 0}, new int[]{4, 4}, new int[]{9, 12}})));
System.out.println(json(Solution50.solve(new int[][]{})));
System.out.println(json(Solution51.solve(new int[][]{})));
System.out.println(json(Solution33.solve(new int[]{-2})));
System.out.println(json(Solution34.solve(new int[]{-2})));
System.out.println(json(Solution35.solve(new int[]{-2})));
System.out.println(json(Solution36.solve(new int[]{-2})));
System.out.println(json(Solution37.solve(new int[]{-2})));
System.out.println(json(Solution38.solve(new int[]{-2})));
System.out.println(json(Solution39.solve(new int[]{-2}, -1)));
System.out.println(json(Solution40.solve(new int[]{-2})));
System.out.println(json(Solution41.solve(new int[]{-2}, 4)));
System.out.println(json(Solution42.solve(new int[]{0})));
System.out.println(json(Solution43.solve(new int[]{-2}, -1)));
System.out.println(json(Solution44.solve(new int[]{-2}, 2, 5)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{0, 1}, new int[]{-3, -2}, new int[]{-5, -2}, new int[]{7, 9}, new int[]{8, 9}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{0, 1}, new int[]{-3, -2}, new int[]{-5, -2}, new int[]{7, 9}, new int[]{8, 9}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{0, 1}, new int[]{-3, -2}, new int[]{-5, -2}, new int[]{7, 9}, new int[]{8, 9}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, -2}, new int[]{0, 1}, new int[]{7, 9}}, new int[]{6, 8})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, -2}, new int[]{0, 1}, new int[]{7, 9}}, new int[][]{new int[]{-5, -3}, new int[]{0, 0}, new int[]{8, 12}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{0, 1}, new int[]{-3, -2}, new int[]{-5, -2}, new int[]{7, 9}, new int[]{8, 9}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{0, 1, 2}, new int[]{-3, -2, 1}, new int[]{-5, -2, 4}, new int[]{7, 9, 3}, new int[]{8, 9, 6}})));
System.out.println(json(Solution33.solve(new int[]{-2, 2, 0, 4, 4, 3})));
System.out.println(json(Solution34.solve(new int[]{-2, 2, 0, 4, 4, 3})));
System.out.println(json(Solution35.solve(new int[]{-2, 2, 0, 4, 4, 3})));
System.out.println(json(Solution36.solve(new int[]{-2, 2, 0, 4, 4, 3})));
System.out.println(json(Solution37.solve(new int[]{-2, 2, 0, 4, 4, 3})));
System.out.println(json(Solution38.solve(new int[]{-2, 2, 0, 4, 4, 3})));
System.out.println(json(Solution39.solve(new int[]{-2, 2, 0, 4, 4, 3}, 3)));
System.out.println(json(Solution40.solve(new int[]{-2, 2, 0, 4, 4, 3})));
System.out.println(json(Solution41.solve(new int[]{-2, 2, 0, 4, 4, 3}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 0, 0, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{-2, 2, 0, 4, 4, 3}, 3)));
System.out.println(json(Solution44.solve(new int[]{-2, 2, 0, 4, 4, 3}, -4, 5)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{0, 5}, new int[]{5, 9}, new int[]{-4, 0}, new int[]{2, 3}, new int[]{-4, 0}, new int[]{9, 14}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{0, 5}, new int[]{5, 9}, new int[]{-4, 0}, new int[]{2, 3}, new int[]{-4, 0}, new int[]{9, 14}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{0, 5}, new int[]{5, 9}, new int[]{-4, 0}, new int[]{2, 3}, new int[]{-4, 0}, new int[]{9, 14}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-4, 14}}, new int[]{2, 5})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-4, 14}}, new int[][]{new int[]{-5, -2}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{0, 5}, new int[]{5, 9}, new int[]{-4, 0}, new int[]{2, 3}, new int[]{-4, 0}, new int[]{9, 14}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{0, 5, 4}, new int[]{5, 9, 3}, new int[]{-4, 0, 6}, new int[]{2, 3, 5}, new int[]{-4, 0, 0}, new int[]{9, 14, 7}})));
System.out.println(json(Solution33.solve(new int[]{-4, 2, -4})));
System.out.println(json(Solution34.solve(new int[]{-4, 2, -4})));
System.out.println(json(Solution35.solve(new int[]{-4, 2, -4})));
System.out.println(json(Solution36.solve(new int[]{-4, 2, -4})));
System.out.println(json(Solution37.solve(new int[]{-4, 2, -4})));
System.out.println(json(Solution38.solve(new int[]{-4, 2, -4})));
System.out.println(json(Solution39.solve(new int[]{-4, 2, -4}, -1)));
System.out.println(json(Solution40.solve(new int[]{-4, 2, -4})));
System.out.println(json(Solution41.solve(new int[]{-4, 2, -4}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{-4, 2, -4}, -1)));
System.out.println(json(Solution44.solve(new int[]{-4, 2, -4}, -4, -3)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{4, 5}, new int[]{6, 7}, new int[]{7, 12}, new int[]{-5, -4}, new int[]{-3, -2}, new int[]{9, 11}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{4, 5}, new int[]{6, 7}, new int[]{7, 12}, new int[]{-5, -4}, new int[]{-3, -2}, new int[]{9, 11}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{4, 5}, new int[]{6, 7}, new int[]{7, 12}, new int[]{-5, -4}, new int[]{-3, -2}, new int[]{9, 11}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, -4}, new int[]{-3, -2}, new int[]{4, 5}, new int[]{6, 12}}, new int[]{4, 4})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, -4}, new int[]{-3, -2}, new int[]{4, 5}, new int[]{6, 12}}, new int[][]{new int[]{6, 9}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{4, 5}, new int[]{6, 7}, new int[]{7, 12}, new int[]{-5, -4}, new int[]{-3, -2}, new int[]{9, 11}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{4, 5, 4}, new int[]{6, 7, 3}, new int[]{7, 12, 6}, new int[]{-5, -4, 5}, new int[]{-3, -2, 0}, new int[]{9, 11, 7}})));
System.out.println(json(Solution33.solve(new int[]{-2, 1, 0})));
System.out.println(json(Solution34.solve(new int[]{-2, 1, 0})));
System.out.println(json(Solution35.solve(new int[]{-2, 1, 0})));
System.out.println(json(Solution36.solve(new int[]{-2, 1, 0})));
System.out.println(json(Solution37.solve(new int[]{-2, 1, 0})));
System.out.println(json(Solution38.solve(new int[]{-2, 1, 0})));
System.out.println(json(Solution39.solve(new int[]{-2, 1, 0}, 7)));
System.out.println(json(Solution40.solve(new int[]{-2, 1, 0})));
System.out.println(json(Solution41.solve(new int[]{-2, 1, 0}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 0})));
System.out.println(json(Solution43.solve(new int[]{-2, 1, 0}, 7)));
System.out.println(json(Solution44.solve(new int[]{-2, 1, 0}, -5, -2)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{8, 11}, new int[]{6, 11}, new int[]{-1, 0}, new int[]{0, 4}, new int[]{0, 4}, new int[]{2, 4}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{8, 11}, new int[]{6, 11}, new int[]{-1, 0}, new int[]{0, 4}, new int[]{0, 4}, new int[]{2, 4}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{8, 11}, new int[]{6, 11}, new int[]{-1, 0}, new int[]{0, 4}, new int[]{0, 4}, new int[]{2, 4}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-1, 4}, new int[]{6, 11}}, new int[]{2, 3})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-1, 4}, new int[]{6, 11}}, new int[][]{new int[]{0, 1}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{8, 11}, new int[]{6, 11}, new int[]{-1, 0}, new int[]{0, 4}, new int[]{0, 4}, new int[]{2, 4}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{8, 11, 4}, new int[]{6, 11, 3}, new int[]{-1, 0, 6}, new int[]{0, 4, 5}, new int[]{0, 4, 0}, new int[]{2, 4, 7}})));
System.out.println(json(Solution33.solve(new int[]{1, -4, -3})));
System.out.println(json(Solution34.solve(new int[]{1, -4, -3})));
System.out.println(json(Solution35.solve(new int[]{1, -4, -3})));
System.out.println(json(Solution36.solve(new int[]{1, -4, -3})));
System.out.println(json(Solution37.solve(new int[]{1, -4, -3})));
System.out.println(json(Solution38.solve(new int[]{1, -4, -3})));
System.out.println(json(Solution39.solve(new int[]{1, -4, -3}, 7)));
System.out.println(json(Solution40.solve(new int[]{1, -4, -3})));
System.out.println(json(Solution41.solve(new int[]{1, -4, -3}, 4)));
System.out.println(json(Solution42.solve(new int[]{1, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{1, -4, -3}, 7)));
System.out.println(json(Solution44.solve(new int[]{1, -4, -3}, 2, 7)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{1, 3}, new int[]{2, 3}, new int[]{0, 1}, new int[]{-2, -1}, new int[]{0, 2}, new int[]{8, 10}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{1, 3}, new int[]{2, 3}, new int[]{0, 1}, new int[]{-2, -1}, new int[]{0, 2}, new int[]{8, 10}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{1, 3}, new int[]{2, 3}, new int[]{0, 1}, new int[]{-2, -1}, new int[]{0, 2}, new int[]{8, 10}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-2, -1}, new int[]{0, 3}, new int[]{8, 10}}, new int[]{-2, 1})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-2, -1}, new int[]{0, 3}, new int[]{8, 10}}, new int[][]{new int[]{-3, -2}, new int[]{-1, 3}, new int[]{8, 11}})));
}
static void batch2900() {
System.out.println(json(Solution50.solve(new int[][]{new int[]{1, 3}, new int[]{2, 3}, new int[]{0, 1}, new int[]{-2, -1}, new int[]{0, 2}, new int[]{8, 10}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{1, 3, 4}, new int[]{2, 3, 3}, new int[]{0, 1, 6}, new int[]{-2, -1, 5}, new int[]{0, 2, 0}, new int[]{8, 10, 7}})));
System.out.println(json(Solution33.solve(new int[]{-2, 2, -1})));
System.out.println(json(Solution34.solve(new int[]{-2, 2, -1})));
System.out.println(json(Solution35.solve(new int[]{-2, 2, -1})));
System.out.println(json(Solution36.solve(new int[]{-2, 2, -1})));
System.out.println(json(Solution37.solve(new int[]{-2, 2, -1})));
System.out.println(json(Solution38.solve(new int[]{-2, 2, -1})));
System.out.println(json(Solution39.solve(new int[]{-2, 2, -1}, 7)));
System.out.println(json(Solution40.solve(new int[]{-2, 2, -1})));
System.out.println(json(Solution41.solve(new int[]{-2, 2, -1}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{-2, 2, -1}, 7)));
System.out.println(json(Solution44.solve(new int[]{-2, 2, -1}, -4, 3)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-1, 1}, new int[]{7, 8}, new int[]{3, 5}, new int[]{6, 8}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-1, 1}, new int[]{7, 8}, new int[]{3, 5}, new int[]{6, 8}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-1, 1}, new int[]{7, 8}, new int[]{3, 5}, new int[]{6, 8}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-1, 1}, new int[]{3, 5}, new int[]{6, 8}}, new int[]{4, 7})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-1, 1}, new int[]{3, 5}, new int[]{6, 8}}, new int[][]{new int[]{-5, -1}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-1, 1}, new int[]{7, 8}, new int[]{3, 5}, new int[]{6, 8}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-1, 1, 0}, new int[]{7, 8, 7}, new int[]{3, 5, 2}, new int[]{6, 8, 1}})));
System.out.println(json(Solution33.solve(new int[]{1, -2, 1, 4, 2})));
System.out.println(json(Solution34.solve(new int[]{1, -2, 1, 4, 2})));
System.out.println(json(Solution35.solve(new int[]{1, -2, 1, 4, 2})));
System.out.println(json(Solution36.solve(new int[]{1, -2, 1, 4, 2})));
System.out.println(json(Solution37.solve(new int[]{1, -2, 1, 4, 2})));
System.out.println(json(Solution38.solve(new int[]{1, -2, 1, 4, 2})));
System.out.println(json(Solution39.solve(new int[]{1, -2, 1, 4, 2}, 7)));
System.out.println(json(Solution40.solve(new int[]{1, -2, 1, 4, 2})));
System.out.println(json(Solution41.solve(new int[]{1, -2, 1, 4, 2}, 6)));
System.out.println(json(Solution42.solve(new int[]{1, 0, 1, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{1, -2, 1, 4, 2}, 7)));
System.out.println(json(Solution44.solve(new int[]{1, -2, 1, 4, 2}, -1, 4)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-1, 1}, new int[]{9, 14}, new int[]{9, 11}, new int[]{0, 1}, new int[]{-1, 4}, new int[]{-3, 1}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-1, 1}, new int[]{9, 14}, new int[]{9, 11}, new int[]{0, 1}, new int[]{-1, 4}, new int[]{-3, 1}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-1, 1}, new int[]{9, 14}, new int[]{9, 11}, new int[]{0, 1}, new int[]{-1, 4}, new int[]{-3, 1}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-3, 4}, new int[]{9, 14}}, new int[]{0, 3})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-3, 4}, new int[]{9, 14}}, new int[][]{new int[]{-3, 2}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-1, 1}, new int[]{9, 14}, new int[]{9, 11}, new int[]{0, 1}, new int[]{-1, 4}, new int[]{-3, 1}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-1, 1, 4}, new int[]{9, 14, 3}, new int[]{9, 11, 6}, new int[]{0, 1, 5}, new int[]{-1, 4, 0}, new int[]{-3, 1, 7}})));
System.out.println(json(Solution33.solve(new int[]{-2, -4, 0})));
System.out.println(json(Solution34.solve(new int[]{-2, -4, 0})));
System.out.println(json(Solution35.solve(new int[]{-2, -4, 0})));
System.out.println(json(Solution36.solve(new int[]{-2, -4, 0})));
System.out.println(json(Solution37.solve(new int[]{-2, -4, 0})));
System.out.println(json(Solution38.solve(new int[]{-2, -4, 0})));
System.out.println(json(Solution39.solve(new int[]{-2, -4, 0}, -1)));
System.out.println(json(Solution40.solve(new int[]{-2, -4, 0})));
System.out.println(json(Solution41.solve(new int[]{-2, -4, 0}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{-2, -4, 0}, -1)));
System.out.println(json(Solution44.solve(new int[]{-2, -4, 0}, -3, -2)));
System.out.println(json(Solution45.solve(new int[][]{})));
System.out.println(json(Solution46.solve(new int[][]{})));
System.out.println(json(Solution49.solve(new int[][]{})));
System.out.println(json(Solution47.solve(new int[][]{}, new int[]{2, 5})));
System.out.println(json(Solution48.solve(new int[][]{}, new int[][]{new int[]{-1, 0}})));
System.out.println(json(Solution50.solve(new int[][]{})));
System.out.println(json(Solution51.solve(new int[][]{})));
System.out.println(json(Solution33.solve(new int[]{3})));
System.out.println(json(Solution34.solve(new int[]{3})));
System.out.println(json(Solution35.solve(new int[]{3})));
System.out.println(json(Solution36.solve(new int[]{3})));
System.out.println(json(Solution37.solve(new int[]{3})));
System.out.println(json(Solution38.solve(new int[]{3})));
System.out.println(json(Solution39.solve(new int[]{3}, 3)));
System.out.println(json(Solution40.solve(new int[]{3})));
System.out.println(json(Solution41.solve(new int[]{3}, 6)));
System.out.println(json(Solution42.solve(new int[]{1})));
System.out.println(json(Solution43.solve(new int[]{3}, 3)));
System.out.println(json(Solution44.solve(new int[]{3}, -2, 5)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{4, 8}, new int[]{-5, -4}, new int[]{-3, 1}, new int[]{9, 10}, new int[]{2, 5}, new int[]{9, 14}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{4, 8}, new int[]{-5, -4}, new int[]{-3, 1}, new int[]{9, 10}, new int[]{2, 5}, new int[]{9, 14}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{4, 8}, new int[]{-5, -4}, new int[]{-3, 1}, new int[]{9, 10}, new int[]{2, 5}, new int[]{9, 14}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, -4}, new int[]{-3, 1}, new int[]{2, 8}, new int[]{9, 14}}, new int[]{6, 10})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, -4}, new int[]{-3, 1}, new int[]{2, 8}, new int[]{9, 14}}, new int[][]{new int[]{-2, 3}, new int[]{6, 10}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{4, 8}, new int[]{-5, -4}, new int[]{-3, 1}, new int[]{9, 10}, new int[]{2, 5}, new int[]{9, 14}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{4, 8, 0}, new int[]{-5, -4, 7}, new int[]{-3, 1, 2}, new int[]{9, 10, 1}, new int[]{2, 5, 4}, new int[]{9, 14, 3}})));
System.out.println(json(Solution33.solve(new int[]{-2, 4, 3, 2, 1, -1, -4})));
System.out.println(json(Solution34.solve(new int[]{-2, 4, 3, 2, 1, -1, -4})));
System.out.println(json(Solution35.solve(new int[]{-2, 4, 3, 2, 1, -1, -4})));
System.out.println(json(Solution36.solve(new int[]{-2, 4, 3, 2, 1, -1, -4})));
System.out.println(json(Solution37.solve(new int[]{-2, 4, 3, 2, 1, -1, -4})));
System.out.println(json(Solution38.solve(new int[]{-2, 4, 3, 2, 1, -1, -4})));
System.out.println(json(Solution39.solve(new int[]{-2, 4, 3, 2, 1, -1, -4}, -1)));
System.out.println(json(Solution40.solve(new int[]{-2, 4, 3, 2, 1, -1, -4})));
System.out.println(json(Solution41.solve(new int[]{-2, 4, 3, 2, 1, -1, -4}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 1, 0, 1, 1, 0})));
System.out.println(json(Solution43.solve(new int[]{-2, 4, 3, 2, 1, -1, -4}, -1)));
System.out.println(json(Solution44.solve(new int[]{-2, 4, 3, 2, 1, -1, -4}, -2, 7)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{0, 3}, new int[]{-2, 2}, new int[]{3, 6}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{0, 3}, new int[]{-2, 2}, new int[]{3, 6}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{0, 3}, new int[]{-2, 2}, new int[]{3, 6}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-2, 6}}, new int[]{6, 7})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-2, 6}}, new int[][]{new int[]{-5, -1}, new int[]{1, 4}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{0, 3}, new int[]{-2, 2}, new int[]{3, 6}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{0, 3, 2}, new int[]{-2, 2, 1}, new int[]{3, 6, 4}})));
System.out.println(json(Solution33.solve(new int[]{3, 1, -3, 4})));
System.out.println(json(Solution34.solve(new int[]{3, 1, -3, 4})));
System.out.println(json(Solution35.solve(new int[]{3, 1, -3, 4})));
}
static void batch3000() {
System.out.println(json(Solution36.solve(new int[]{3, 1, -3, 4})));
System.out.println(json(Solution37.solve(new int[]{3, 1, -3, 4})));
System.out.println(json(Solution38.solve(new int[]{3, 1, -3, 4})));
System.out.println(json(Solution39.solve(new int[]{3, 1, -3, 4}, 7)));
System.out.println(json(Solution40.solve(new int[]{3, 1, -3, 4})));
System.out.println(json(Solution41.solve(new int[]{3, 1, -3, 4}, 6)));
System.out.println(json(Solution42.solve(new int[]{1, 1, 1, 0})));
System.out.println(json(Solution43.solve(new int[]{3, 1, -3, 4}, 7)));
System.out.println(json(Solution44.solve(new int[]{3, 1, -3, 4}, -2, 5)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{1, 3}, new int[]{-5, -1}, new int[]{9, 13}, new int[]{0, 5}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{1, 3}, new int[]{-5, -1}, new int[]{9, 13}, new int[]{0, 5}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{1, 3}, new int[]{-5, -1}, new int[]{9, 13}, new int[]{0, 5}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, -1}, new int[]{0, 5}, new int[]{9, 13}}, new int[]{2, 2})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, -1}, new int[]{0, 5}, new int[]{9, 13}}, new int[][]{new int[]{-3, 0}, new int[]{1, 5}, new int[]{6, 9}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{1, 3}, new int[]{-5, -1}, new int[]{9, 13}, new int[]{0, 5}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{1, 3, 4}, new int[]{-5, -1, 3}, new int[]{9, 13, 6}, new int[]{0, 5, 5}})));
System.out.println(json(Solution33.solve(new int[]{-4})));
System.out.println(json(Solution34.solve(new int[]{-4})));
System.out.println(json(Solution35.solve(new int[]{-4})));
System.out.println(json(Solution36.solve(new int[]{-4})));
System.out.println(json(Solution37.solve(new int[]{-4})));
System.out.println(json(Solution38.solve(new int[]{-4})));
System.out.println(json(Solution39.solve(new int[]{-4}, 7)));
System.out.println(json(Solution40.solve(new int[]{-4})));
System.out.println(json(Solution41.solve(new int[]{-4}, 4)));
System.out.println(json(Solution42.solve(new int[]{0})));
System.out.println(json(Solution43.solve(new int[]{-4}, 7)));
System.out.println(json(Solution44.solve(new int[]{-4}, 0, 5)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{9, 11}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{9, 11}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{9, 11}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{9, 11}}, new int[]{2, 6})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{9, 11}}, new int[][]{new int[]{-1, -1}, new int[]{2, 6}, new int[]{8, 9}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{9, 11}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{9, 11, 6}})));
System.out.println(json(Solution33.solve(new int[]{0, 3, -4, -2, -1, 0})));
System.out.println(json(Solution34.solve(new int[]{0, 3, -4, -2, -1, 0})));
System.out.println(json(Solution35.solve(new int[]{0, 3, -4, -2, -1, 0})));
System.out.println(json(Solution36.solve(new int[]{0, 3, -4, -2, -1, 0})));
System.out.println(json(Solution37.solve(new int[]{0, 3, -4, -2, -1, 0})));
System.out.println(json(Solution38.solve(new int[]{0, 3, -4, -2, -1, 0})));
System.out.println(json(Solution39.solve(new int[]{0, 3, -4, -2, -1, 0}, 3)));
System.out.println(json(Solution40.solve(new int[]{0, 3, -4, -2, -1, 0})));
System.out.println(json(Solution41.solve(new int[]{0, 3, -4, -2, -1, 0}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 0, 0, 1, 0})));
System.out.println(json(Solution43.solve(new int[]{0, 3, -4, -2, -1, 0}, 3)));
System.out.println(json(Solution44.solve(new int[]{0, 3, -4, -2, -1, 0}, -6, -5)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{5, 6}, new int[]{8, 13}, new int[]{-4, -1}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{5, 6}, new int[]{8, 13}, new int[]{-4, -1}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{5, 6}, new int[]{8, 13}, new int[]{-4, -1}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-4, -1}, new int[]{5, 6}, new int[]{8, 13}}, new int[]{-2, 1})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-4, -1}, new int[]{5, 6}, new int[]{8, 13}}, new int[][]{new int[]{9, 11}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{5, 6}, new int[]{8, 13}, new int[]{-4, -1}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{5, 6, 6}, new int[]{8, 13, 5}, new int[]{-4, -1, 0}})));
System.out.println(json(Solution33.solve(new int[]{4, -2, -2, -1, -2, -2, -2, 4})));
System.out.println(json(Solution34.solve(new int[]{4, -2, -2, -1, -2, -2, -2, 4})));
System.out.println(json(Solution35.solve(new int[]{4, -2, -2, -1, -2, -2, -2, 4})));
System.out.println(json(Solution36.solve(new int[]{4, -2, -2, -1, -2, -2, -2, 4})));
System.out.println(json(Solution37.solve(new int[]{4, -2, -2, -1, -2, -2, -2, 4})));
System.out.println(json(Solution38.solve(new int[]{4, -2, -2, -1, -2, -2, -2, 4})));
System.out.println(json(Solution39.solve(new int[]{4, -2, -2, -1, -2, -2, -2, 4}, -1)));
System.out.println(json(Solution40.solve(new int[]{4, -2, -2, -1, -2, -2, -2, 4})));
System.out.println(json(Solution41.solve(new int[]{4, -2, -2, -1, -2, -2, -2, 4}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 0, 1, 0, 0, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{4, -2, -2, -1, -2, -2, -2, 4}, -1)));
System.out.println(json(Solution44.solve(new int[]{4, -2, -2, -1, -2, -2, -2, 4}, 1, 6)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{4, 7}, new int[]{1, 4}, new int[]{-3, -2}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{4, 7}, new int[]{1, 4}, new int[]{-3, -2}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{4, 7}, new int[]{1, 4}, new int[]{-3, -2}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-3, -2}, new int[]{1, 7}}, new int[]{6, 6})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-3, -2}, new int[]{1, 7}}, new int[][]{new int[]{4, 4}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{4, 7}, new int[]{1, 4}, new int[]{-3, -2}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{4, 7, 2}, new int[]{1, 4, 1}, new int[]{-3, -2, 4}})));
System.out.println(json(Solution33.solve(new int[]{-4, 3, 1, 0})));
System.out.println(json(Solution34.solve(new int[]{-4, 3, 1, 0})));
System.out.println(json(Solution35.solve(new int[]{-4, 3, 1, 0})));
System.out.println(json(Solution36.solve(new int[]{-4, 3, 1, 0})));
System.out.println(json(Solution37.solve(new int[]{-4, 3, 1, 0})));
System.out.println(json(Solution38.solve(new int[]{-4, 3, 1, 0})));
System.out.println(json(Solution39.solve(new int[]{-4, 3, 1, 0}, -1)));
System.out.println(json(Solution40.solve(new int[]{-4, 3, 1, 0})));
System.out.println(json(Solution41.solve(new int[]{-4, 3, 1, 0}, 6)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 1, 0})));
System.out.println(json(Solution43.solve(new int[]{-4, 3, 1, 0}, -1)));
System.out.println(json(Solution44.solve(new int[]{-4, 3, 1, 0}, 0, 5)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-4, 1}, new int[]{5, 10}, new int[]{0, 5}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-4, 1}, new int[]{5, 10}, new int[]{0, 5}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-4, 1}, new int[]{5, 10}, new int[]{0, 5}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-4, 10}}, new int[]{2, 3})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-4, 10}}, new int[][]{new int[]{-3, 5}, new int[]{6, 7}, new int[]{9, 12}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-4, 1}, new int[]{5, 10}, new int[]{0, 5}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-4, 1, 2}, new int[]{5, 10, 1}, new int[]{0, 5, 4}})));
System.out.println(json(Solution33.solve(new int[]{-4, 3, -3, 2})));
System.out.println(json(Solution34.solve(new int[]{-4, 3, -3, 2})));
System.out.println(json(Solution35.solve(new int[]{-4, 3, -3, 2})));
System.out.println(json(Solution36.solve(new int[]{-4, 3, -3, 2})));
System.out.println(json(Solution37.solve(new int[]{-4, 3, -3, 2})));
System.out.println(json(Solution38.solve(new int[]{-4, 3, -3, 2})));
System.out.println(json(Solution39.solve(new int[]{-4, 3, -3, 2}, 7)));
System.out.println(json(Solution40.solve(new int[]{-4, 3, -3, 2})));
}
static void batch3100() {
System.out.println(json(Solution41.solve(new int[]{-4, 3, -3, 2}, 6)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 1, 0})));
System.out.println(json(Solution43.solve(new int[]{-4, 3, -3, 2}, 7)));
System.out.println(json(Solution44.solve(new int[]{-4, 3, -3, 2}, 2, 11)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-2, 1}, new int[]{-4, -1}, new int[]{4, 9}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-2, 1}, new int[]{-4, -1}, new int[]{4, 9}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-2, 1}, new int[]{-4, -1}, new int[]{4, 9}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-4, 1}, new int[]{4, 9}}, new int[]{-2, 0})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-4, 1}, new int[]{4, 9}}, new int[][]{new int[]{-1, 1}, new int[]{9, 11}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-2, 1}, new int[]{-4, -1}, new int[]{4, 9}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-2, 1, 6}, new int[]{-4, -1, 5}, new int[]{4, 9, 0}})));
System.out.println(json(Solution33.solve(new int[]{2, -4, 2, -2, 2, -1, 4, -3})));
System.out.println(json(Solution34.solve(new int[]{2, -4, 2, -2, 2, -1, 4, -3})));
System.out.println(json(Solution35.solve(new int[]{2, -4, 2, -2, 2, -1, 4, -3})));
System.out.println(json(Solution36.solve(new int[]{2, -4, 2, -2, 2, -1, 4, -3})));
System.out.println(json(Solution37.solve(new int[]{2, -4, 2, -2, 2, -1, 4, -3})));
System.out.println(json(Solution38.solve(new int[]{2, -4, 2, -2, 2, -1, 4, -3})));
System.out.println(json(Solution39.solve(new int[]{2, -4, 2, -2, 2, -1, 4, -3}, 7)));
System.out.println(json(Solution40.solve(new int[]{2, -4, 2, -2, 2, -1, 4, -3})));
System.out.println(json(Solution41.solve(new int[]{2, -4, 2, -2, 2, -1, 4, -3}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 0, 0, 0, 1, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{2, -4, 2, -2, 2, -1, 4, -3}, 7)));
System.out.println(json(Solution44.solve(new int[]{2, -4, 2, -2, 2, -1, 4, -3}, 2, 7)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-1, 0}, new int[]{-5, -4}, new int[]{9, 13}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-1, 0}, new int[]{-5, -4}, new int[]{9, 13}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-1, 0}, new int[]{-5, -4}, new int[]{9, 13}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, -4}, new int[]{-1, 0}, new int[]{9, 13}}, new int[]{2, 6})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, -4}, new int[]{-1, 0}, new int[]{9, 13}}, new int[][]{new int[]{0, 3}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-1, 0}, new int[]{-5, -4}, new int[]{9, 13}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-1, 0, 2}, new int[]{-5, -4, 1}, new int[]{9, 13, 4}})));
System.out.println(json(Solution33.solve(new int[]{-3, 2, 3, -1})));
System.out.println(json(Solution34.solve(new int[]{-3, 2, 3, -1})));
System.out.println(json(Solution35.solve(new int[]{-3, 2, 3, -1})));
System.out.println(json(Solution36.solve(new int[]{-3, 2, 3, -1})));
System.out.println(json(Solution37.solve(new int[]{-3, 2, 3, -1})));
System.out.println(json(Solution38.solve(new int[]{-3, 2, 3, -1})));
System.out.println(json(Solution39.solve(new int[]{-3, 2, 3, -1}, 7)));
System.out.println(json(Solution40.solve(new int[]{-3, 2, 3, -1})));
System.out.println(json(Solution41.solve(new int[]{-3, 2, 3, -1}, 4)));
System.out.println(json(Solution42.solve(new int[]{1, 0, 1, 1})));
System.out.println(json(Solution43.solve(new int[]{-3, 2, 3, -1}, 7)));
System.out.println(json(Solution44.solve(new int[]{-3, 2, 3, -1}, -4, -3)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{3, 5}, new int[]{4, 6}, new int[]{5, 10}, new int[]{-2, 3}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{3, 5}, new int[]{4, 6}, new int[]{5, 10}, new int[]{-2, 3}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{3, 5}, new int[]{4, 6}, new int[]{5, 10}, new int[]{-2, 3}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-2, 10}}, new int[]{4, 8})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-2, 10}}, new int[][]{new int[]{-3, -1}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{3, 5}, new int[]{4, 6}, new int[]{5, 10}, new int[]{-2, 3}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{3, 5, 4}, new int[]{4, 6, 3}, new int[]{5, 10, 6}, new int[]{-2, 3, 5}})));
System.out.println(json(Solution33.solve(new int[]{2})));
System.out.println(json(Solution34.solve(new int[]{2})));
System.out.println(json(Solution35.solve(new int[]{2})));
System.out.println(json(Solution36.solve(new int[]{2})));
System.out.println(json(Solution37.solve(new int[]{2})));
System.out.println(json(Solution38.solve(new int[]{2})));
System.out.println(json(Solution39.solve(new int[]{2}, 3)));
System.out.println(json(Solution40.solve(new int[]{2})));
System.out.println(json(Solution41.solve(new int[]{2}, 2)));
System.out.println(json(Solution42.solve(new int[]{0})));
System.out.println(json(Solution43.solve(new int[]{2}, 3)));
System.out.println(json(Solution44.solve(new int[]{2}, 1, 2)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{4, 6}, new int[]{8, 12}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{4, 6}, new int[]{8, 12}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{4, 6}, new int[]{8, 12}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{4, 6}, new int[]{8, 12}}, new int[]{2, 6})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{4, 6}, new int[]{8, 12}}, new int[][]{new int[]{-1, -1}, new int[]{1, 1}, new int[]{7, 10}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{4, 6}, new int[]{8, 12}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{4, 6, 4}, new int[]{8, 12, 3}})));
System.out.println(json(Solution33.solve(new int[]{4, 0, 3, 4, -4, -4, 4})));
System.out.println(json(Solution34.solve(new int[]{4, 0, 3, 4, -4, -4, 4})));
System.out.println(json(Solution35.solve(new int[]{4, 0, 3, 4, -4, -4, 4})));
System.out.println(json(Solution36.solve(new int[]{4, 0, 3, 4, -4, -4, 4})));
System.out.println(json(Solution37.solve(new int[]{4, 0, 3, 4, -4, -4, 4})));
System.out.println(json(Solution38.solve(new int[]{4, 0, 3, 4, -4, -4, 4})));
System.out.println(json(Solution39.solve(new int[]{4, 0, 3, 4, -4, -4, 4}, 3)));
System.out.println(json(Solution40.solve(new int[]{4, 0, 3, 4, -4, -4, 4})));
System.out.println(json(Solution41.solve(new int[]{4, 0, 3, 4, -4, -4, 4}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 1, 0, 0, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{4, 0, 3, 4, -4, -4, 4}, 3)));
System.out.println(json(Solution44.solve(new int[]{4, 0, 3, 4, -4, -4, 4}, 2, 9)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-5, 0}, new int[]{9, 11}, new int[]{4, 7}, new int[]{0, 3}, new int[]{-1, 0}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-5, 0}, new int[]{9, 11}, new int[]{4, 7}, new int[]{0, 3}, new int[]{-1, 0}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-5, 0}, new int[]{9, 11}, new int[]{4, 7}, new int[]{0, 3}, new int[]{-1, 0}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, 3}, new int[]{4, 7}, new int[]{9, 11}}, new int[]{2, 5})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, 3}, new int[]{4, 7}, new int[]{9, 11}}, new int[][]{new int[]{-2, 1}, new int[]{2, 4}, new int[]{6, 6}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-5, 0}, new int[]{9, 11}, new int[]{4, 7}, new int[]{0, 3}, new int[]{-1, 0}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-5, 0, 6}, new int[]{9, 11, 5}, new int[]{4, 7, 0}, new int[]{0, 3, 7}, new int[]{-1, 0, 2}})));
System.out.println(json(Solution33.solve(new int[]{3, 3})));
System.out.println(json(Solution34.solve(new int[]{3, 3})));
System.out.println(json(Solution35.solve(new int[]{3, 3})));
System.out.println(json(Solution36.solve(new int[]{3, 3})));
System.out.println(json(Solution37.solve(new int[]{3, 3})));
System.out.println(json(Solution38.solve(new int[]{3, 3})));
System.out.println(json(Solution39.solve(new int[]{3, 3}, 3)));
System.out.println(json(Solution40.solve(new int[]{3, 3})));
System.out.println(json(Solution41.solve(new int[]{3, 3}, 4)));
System.out.println(json(Solution42.solve(new int[]{1, 1})));
System.out.println(json(Solution43.solve(new int[]{3, 3}, 3)));
System.out.println(json(Solution44.solve(new int[]{3, 3}, -5, -2)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{8, 13}})));
}
static void batch3200() {
System.out.println(json(Solution46.solve(new int[][]{new int[]{8, 13}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{8, 13}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{8, 13}}, new int[]{0, 0})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{8, 13}}, new int[][]{new int[]{-5, -1}, new int[]{0, 5}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{8, 13}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{8, 13, 6}})));
System.out.println(json(Solution33.solve(new int[]{1, 3, 2, 3, -3, 1})));
System.out.println(json(Solution34.solve(new int[]{1, 3, 2, 3, -3, 1})));
System.out.println(json(Solution35.solve(new int[]{1, 3, 2, 3, -3, 1})));
System.out.println(json(Solution36.solve(new int[]{1, 3, 2, 3, -3, 1})));
System.out.println(json(Solution37.solve(new int[]{1, 3, 2, 3, -3, 1})));
System.out.println(json(Solution38.solve(new int[]{1, 3, 2, 3, -3, 1})));
System.out.println(json(Solution39.solve(new int[]{1, 3, 2, 3, -3, 1}, 7)));
System.out.println(json(Solution40.solve(new int[]{1, 3, 2, 3, -3, 1})));
System.out.println(json(Solution41.solve(new int[]{1, 3, 2, 3, -3, 1}, 2)));
System.out.println(json(Solution42.solve(new int[]{1, 1, 0, 1, 1, 1})));
System.out.println(json(Solution43.solve(new int[]{1, 3, 2, 3, -3, 1}, 7)));
System.out.println(json(Solution44.solve(new int[]{1, 3, 2, 3, -3, 1}, -1, 2)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-2, 0}, new int[]{6, 11}, new int[]{-3, 1}, new int[]{-3, 2}, new int[]{1, 6}, new int[]{7, 12}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-2, 0}, new int[]{6, 11}, new int[]{-3, 1}, new int[]{-3, 2}, new int[]{1, 6}, new int[]{7, 12}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-2, 0}, new int[]{6, 11}, new int[]{-3, 1}, new int[]{-3, 2}, new int[]{1, 6}, new int[]{7, 12}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-3, 12}}, new int[]{6, 10})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-3, 12}}, new int[][]{new int[]{-4, 0}, new int[]{1, 1}, new int[]{6, 10}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-2, 0}, new int[]{6, 11}, new int[]{-3, 1}, new int[]{-3, 2}, new int[]{1, 6}, new int[]{7, 12}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-2, 0, 4}, new int[]{6, 11, 3}, new int[]{-3, 1, 6}, new int[]{-3, 2, 5}, new int[]{1, 6, 0}, new int[]{7, 12, 7}})));
System.out.println(json(Solution33.solve(new int[]{-1, 4, -1})));
System.out.println(json(Solution34.solve(new int[]{-1, 4, -1})));
System.out.println(json(Solution35.solve(new int[]{-1, 4, -1})));
System.out.println(json(Solution36.solve(new int[]{-1, 4, -1})));
System.out.println(json(Solution37.solve(new int[]{-1, 4, -1})));
System.out.println(json(Solution38.solve(new int[]{-1, 4, -1})));
System.out.println(json(Solution39.solve(new int[]{-1, 4, -1}, 7)));
System.out.println(json(Solution40.solve(new int[]{-1, 4, -1})));
System.out.println(json(Solution41.solve(new int[]{-1, 4, -1}, 2)));
System.out.println(json(Solution42.solve(new int[]{1, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{-1, 4, -1}, 7)));
System.out.println(json(Solution44.solve(new int[]{-1, 4, -1}, -1, 4)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{4, 5}, new int[]{0, 3}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{4, 5}, new int[]{0, 3}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{4, 5}, new int[]{0, 3}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{0, 3}, new int[]{4, 5}}, new int[]{0, 3})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{0, 3}, new int[]{4, 5}}, new int[][]{new int[]{1, 6}, new int[]{7, 11}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{4, 5}, new int[]{0, 3}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{4, 5, 4}, new int[]{0, 3, 3}})));
System.out.println(json(Solution33.solve(new int[]{-3, 0, -4, 4, 4, 4, 1})));
System.out.println(json(Solution34.solve(new int[]{-3, 0, -4, 4, 4, 4, 1})));
System.out.println(json(Solution35.solve(new int[]{-3, 0, -4, 4, 4, 4, 1})));
System.out.println(json(Solution36.solve(new int[]{-3, 0, -4, 4, 4, 4, 1})));
System.out.println(json(Solution37.solve(new int[]{-3, 0, -4, 4, 4, 4, 1})));
System.out.println(json(Solution38.solve(new int[]{-3, 0, -4, 4, 4, 4, 1})));
System.out.println(json(Solution39.solve(new int[]{-3, 0, -4, 4, 4, 4, 1}, -1)));
System.out.println(json(Solution40.solve(new int[]{-3, 0, -4, 4, 4, 4, 1})));
System.out.println(json(Solution41.solve(new int[]{-3, 0, -4, 4, 4, 4, 1}, 2)));
System.out.println(json(Solution42.solve(new int[]{1, 0, 0, 0, 0, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{-3, 0, -4, 4, 4, 4, 1}, -1)));
System.out.println(json(Solution44.solve(new int[]{-3, 0, -4, 4, 4, 4, 1}, -5, 2)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{9, 13}, new int[]{-5, 0}, new int[]{6, 10}, new int[]{-2, 0}, new int[]{7, 10}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{9, 13}, new int[]{-5, 0}, new int[]{6, 10}, new int[]{-2, 0}, new int[]{7, 10}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{9, 13}, new int[]{-5, 0}, new int[]{6, 10}, new int[]{-2, 0}, new int[]{7, 10}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, 0}, new int[]{6, 13}}, new int[]{2, 3})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, 0}, new int[]{6, 13}}, new int[][]{new int[]{-5, -5}, new int[]{-2, -2}, new int[]{-1, 1}, new int[]{3, 7}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{9, 13}, new int[]{-5, 0}, new int[]{6, 10}, new int[]{-2, 0}, new int[]{7, 10}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{9, 13, 2}, new int[]{-5, 0, 1}, new int[]{6, 10, 4}, new int[]{-2, 0, 3}, new int[]{7, 10, 6}})));
System.out.println(json(Solution33.solve(new int[]{4, -1, -4, -3, -1, -3})));
System.out.println(json(Solution34.solve(new int[]{4, -1, -4, -3, -1, -3})));
System.out.println(json(Solution35.solve(new int[]{4, -1, -4, -3, -1, -3})));
System.out.println(json(Solution36.solve(new int[]{4, -1, -4, -3, -1, -3})));
System.out.println(json(Solution37.solve(new int[]{4, -1, -4, -3, -1, -3})));
System.out.println(json(Solution38.solve(new int[]{4, -1, -4, -3, -1, -3})));
System.out.println(json(Solution39.solve(new int[]{4, -1, -4, -3, -1, -3}, -1)));
System.out.println(json(Solution40.solve(new int[]{4, -1, -4, -3, -1, -3})));
System.out.println(json(Solution41.solve(new int[]{4, -1, -4, -3, -1, -3}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 0, 1, 1, 1})));
System.out.println(json(Solution43.solve(new int[]{4, -1, -4, -3, -1, -3}, -1)));
System.out.println(json(Solution44.solve(new int[]{4, -1, -4, -3, -1, -3}, 2, 9)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{1, 6}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{1, 6}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{1, 6}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{1, 6}}, new int[]{6, 7})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{1, 6}}, new int[][]{new int[]{-3, 5}, new int[]{9, 12}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{1, 6}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{1, 6, 2}})));
System.out.println(json(Solution33.solve(new int[]{-2, 3})));
System.out.println(json(Solution34.solve(new int[]{-2, 3})));
System.out.println(json(Solution35.solve(new int[]{-2, 3})));
System.out.println(json(Solution36.solve(new int[]{-2, 3})));
System.out.println(json(Solution37.solve(new int[]{-2, 3})));
System.out.println(json(Solution38.solve(new int[]{-2, 3})));
System.out.println(json(Solution39.solve(new int[]{-2, 3}, 7)));
System.out.println(json(Solution40.solve(new int[]{-2, 3})));
System.out.println(json(Solution41.solve(new int[]{-2, 3}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 1})));
System.out.println(json(Solution43.solve(new int[]{-2, 3}, 7)));
System.out.println(json(Solution44.solve(new int[]{-2, 3}, -6, -5)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{3, 4}, new int[]{-1, 2}, new int[]{6, 11}, new int[]{0, 2}, new int[]{0, 3}, new int[]{-1, 3}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{3, 4}, new int[]{-1, 2}, new int[]{6, 11}, new int[]{0, 2}, new int[]{0, 3}, new int[]{-1, 3}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{3, 4}, new int[]{-1, 2}, new int[]{6, 11}, new int[]{0, 2}, new int[]{0, 3}, new int[]{-1, 3}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-1, 4}, new int[]{6, 11}}, new int[]{4, 7})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-1, 4}, new int[]{6, 11}}, new int[][]{new int[]{-1, 2}, new int[]{9, 13}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{3, 4}, new int[]{-1, 2}, new int[]{6, 11}, new int[]{0, 2}, new int[]{0, 3}, new int[]{-1, 3}})));
}
static void batch3300() {
System.out.println(json(Solution51.solve(new int[][]{new int[]{3, 4, 4}, new int[]{-1, 2, 3}, new int[]{6, 11, 6}, new int[]{0, 2, 5}, new int[]{0, 3, 0}, new int[]{-1, 3, 7}})));
System.out.println(json(Solution33.solve(new int[]{2, 1, 3})));
System.out.println(json(Solution34.solve(new int[]{2, 1, 3})));
System.out.println(json(Solution35.solve(new int[]{2, 1, 3})));
System.out.println(json(Solution36.solve(new int[]{2, 1, 3})));
System.out.println(json(Solution37.solve(new int[]{2, 1, 3})));
System.out.println(json(Solution38.solve(new int[]{2, 1, 3})));
System.out.println(json(Solution39.solve(new int[]{2, 1, 3}, -1)));
System.out.println(json(Solution40.solve(new int[]{2, 1, 3})));
System.out.println(json(Solution41.solve(new int[]{2, 1, 3}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 1})));
System.out.println(json(Solution43.solve(new int[]{2, 1, 3}, -1)));
System.out.println(json(Solution44.solve(new int[]{2, 1, 3}, -1, 2)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{7, 12}, new int[]{8, 11}, new int[]{-3, 2}, new int[]{4, 5}, new int[]{5, 7}, new int[]{4, 5}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{7, 12}, new int[]{8, 11}, new int[]{-3, 2}, new int[]{4, 5}, new int[]{5, 7}, new int[]{4, 5}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{7, 12}, new int[]{8, 11}, new int[]{-3, 2}, new int[]{4, 5}, new int[]{5, 7}, new int[]{4, 5}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-3, 2}, new int[]{4, 12}}, new int[]{2, 4})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-3, 2}, new int[]{4, 12}}, new int[][]{new int[]{3, 5}, new int[]{7, 7}, new int[]{8, 11}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{7, 12}, new int[]{8, 11}, new int[]{-3, 2}, new int[]{4, 5}, new int[]{5, 7}, new int[]{4, 5}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{7, 12, 0}, new int[]{8, 11, 7}, new int[]{-3, 2, 2}, new int[]{4, 5, 1}, new int[]{5, 7, 4}, new int[]{4, 5, 3}})));
System.out.println(json(Solution33.solve(new int[]{4, -3, 4, 2, 1, 1, 1})));
System.out.println(json(Solution34.solve(new int[]{4, -3, 4, 2, 1, 1, 1})));
System.out.println(json(Solution35.solve(new int[]{4, -3, 4, 2, 1, 1, 1})));
System.out.println(json(Solution36.solve(new int[]{4, -3, 4, 2, 1, 1, 1})));
System.out.println(json(Solution37.solve(new int[]{4, -3, 4, 2, 1, 1, 1})));
System.out.println(json(Solution38.solve(new int[]{4, -3, 4, 2, 1, 1, 1})));
System.out.println(json(Solution39.solve(new int[]{4, -3, 4, 2, 1, 1, 1}, 7)));
System.out.println(json(Solution40.solve(new int[]{4, -3, 4, 2, 1, 1, 1})));
System.out.println(json(Solution41.solve(new int[]{4, -3, 4, 2, 1, 1, 1}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 0, 0, 1, 1, 1})));
System.out.println(json(Solution43.solve(new int[]{4, -3, 4, 2, 1, 1, 1}, 7)));
System.out.println(json(Solution44.solve(new int[]{4, -3, 4, 2, 1, 1, 1}, 1, 8)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{1, 3}, new int[]{8, 12}, new int[]{7, 8}, new int[]{-5, 0}, new int[]{-3, 1}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{1, 3}, new int[]{8, 12}, new int[]{7, 8}, new int[]{-5, 0}, new int[]{-3, 1}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{1, 3}, new int[]{8, 12}, new int[]{7, 8}, new int[]{-5, 0}, new int[]{-3, 1}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, 3}, new int[]{7, 12}}, new int[]{6, 6})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, 3}, new int[]{7, 12}}, new int[][]{new int[]{-5, -2}, new int[]{-1, 6}, new int[]{7, 10}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{1, 3}, new int[]{8, 12}, new int[]{7, 8}, new int[]{-5, 0}, new int[]{-3, 1}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{1, 3, 2}, new int[]{8, 12, 1}, new int[]{7, 8, 4}, new int[]{-5, 0, 3}, new int[]{-3, 1, 6}})));
System.out.println(json(Solution33.solve(new int[]{-4, 0, -4, -1, -2, 4})));
System.out.println(json(Solution34.solve(new int[]{-4, 0, -4, -1, -2, 4})));
System.out.println(json(Solution35.solve(new int[]{-4, 0, -4, -1, -2, 4})));
System.out.println(json(Solution36.solve(new int[]{-4, 0, -4, -1, -2, 4})));
System.out.println(json(Solution37.solve(new int[]{-4, 0, -4, -1, -2, 4})));
System.out.println(json(Solution38.solve(new int[]{-4, 0, -4, -1, -2, 4})));
System.out.println(json(Solution39.solve(new int[]{-4, 0, -4, -1, -2, 4}, 3)));
System.out.println(json(Solution40.solve(new int[]{-4, 0, -4, -1, -2, 4})));
System.out.println(json(Solution41.solve(new int[]{-4, 0, -4, -1, -2, 4}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 0, 1, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{-4, 0, -4, -1, -2, 4}, 3)));
System.out.println(json(Solution44.solve(new int[]{-4, 0, -4, -1, -2, 4}, -2, 7)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{2, 3}, new int[]{-3, -1}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{2, 3}, new int[]{-3, -1}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{2, 3}, new int[]{-3, -1}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-3, -1}, new int[]{2, 3}}, new int[]{-2, 0})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-3, -1}, new int[]{2, 3}}, new int[][]{new int[]{2, 2}, new int[]{4, 6}, new int[]{9, 11}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{2, 3}, new int[]{-3, -1}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{2, 3, 0}, new int[]{-3, -1, 7}})));
System.out.println(json(Solution33.solve(new int[]{-1, -4, -4})));
System.out.println(json(Solution34.solve(new int[]{-1, -4, -4})));
System.out.println(json(Solution35.solve(new int[]{-1, -4, -4})));
System.out.println(json(Solution36.solve(new int[]{-1, -4, -4})));
System.out.println(json(Solution37.solve(new int[]{-1, -4, -4})));
System.out.println(json(Solution38.solve(new int[]{-1, -4, -4})));
System.out.println(json(Solution39.solve(new int[]{-1, -4, -4}, 7)));
System.out.println(json(Solution40.solve(new int[]{-1, -4, -4})));
System.out.println(json(Solution41.solve(new int[]{-1, -4, -4}, 6)));
System.out.println(json(Solution42.solve(new int[]{1, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{-1, -4, -4}, 7)));
System.out.println(json(Solution44.solve(new int[]{-1, -4, -4}, -2, 7)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{1, 2}, new int[]{6, 10}, new int[]{6, 10}, new int[]{8, 12}, new int[]{2, 5}, new int[]{-5, -2}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{1, 2}, new int[]{6, 10}, new int[]{6, 10}, new int[]{8, 12}, new int[]{2, 5}, new int[]{-5, -2}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{1, 2}, new int[]{6, 10}, new int[]{6, 10}, new int[]{8, 12}, new int[]{2, 5}, new int[]{-5, -2}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, -2}, new int[]{1, 5}, new int[]{6, 12}}, new int[]{-2, 1})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, -2}, new int[]{1, 5}, new int[]{6, 12}}, new int[][]{new int[]{2, 3}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{1, 2}, new int[]{6, 10}, new int[]{6, 10}, new int[]{8, 12}, new int[]{2, 5}, new int[]{-5, -2}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{1, 2, 4}, new int[]{6, 10, 3}, new int[]{6, 10, 6}, new int[]{8, 12, 5}, new int[]{2, 5, 0}, new int[]{-5, -2, 7}})));
System.out.println(json(Solution33.solve(new int[]{4, -3, -1})));
System.out.println(json(Solution34.solve(new int[]{4, -3, -1})));
System.out.println(json(Solution35.solve(new int[]{4, -3, -1})));
System.out.println(json(Solution36.solve(new int[]{4, -3, -1})));
System.out.println(json(Solution37.solve(new int[]{4, -3, -1})));
System.out.println(json(Solution38.solve(new int[]{4, -3, -1})));
System.out.println(json(Solution39.solve(new int[]{4, -3, -1}, 3)));
System.out.println(json(Solution40.solve(new int[]{4, -3, -1})));
System.out.println(json(Solution41.solve(new int[]{4, -3, -1}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 1})));
System.out.println(json(Solution43.solve(new int[]{4, -3, -1}, 3)));
System.out.println(json(Solution44.solve(new int[]{4, -3, -1}, -5, 4)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-3, -2}, new int[]{-2, 2}, new int[]{3, 5}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-3, -2}, new int[]{-2, 2}, new int[]{3, 5}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-3, -2}, new int[]{-2, 2}, new int[]{3, 5}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-3, 2}, new int[]{3, 5}}, new int[]{-2, 2})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-3, 2}, new int[]{3, 5}}, new int[][]{new int[]{-4, 4}, new int[]{5, 5}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-3, -2}, new int[]{-2, 2}, new int[]{3, 5}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-3, -2, 6}, new int[]{-2, 2, 5}, new int[]{3, 5, 0}})));
System.out.println(json(Solution33.solve(new int[]{-2, -2, 2, 4, 4, -2, 0, -1})));
System.out.println(json(Solution34.solve(new int[]{-2, -2, 2, 4, 4, -2, 0, -1})));
System.out.println(json(Solution35.solve(new int[]{-2, -2, 2, 4, 4, -2, 0, -1})));
System.out.println(json(Solution36.solve(new int[]{-2, -2, 2, 4, 4, -2, 0, -1})));
}
static void batch3400() {
System.out.println(json(Solution37.solve(new int[]{-2, -2, 2, 4, 4, -2, 0, -1})));
System.out.println(json(Solution38.solve(new int[]{-2, -2, 2, 4, 4, -2, 0, -1})));
System.out.println(json(Solution39.solve(new int[]{-2, -2, 2, 4, 4, -2, 0, -1}, -1)));
System.out.println(json(Solution40.solve(new int[]{-2, -2, 2, 4, 4, -2, 0, -1})));
System.out.println(json(Solution41.solve(new int[]{-2, -2, 2, 4, 4, -2, 0, -1}, 6)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 0, 0, 0, 0, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{-2, -2, 2, 4, 4, -2, 0, -1}, -1)));
System.out.println(json(Solution44.solve(new int[]{-2, -2, 2, 4, 4, -2, 0, -1}, -4, 3)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{5, 6}, new int[]{7, 10}, new int[]{8, 11}, new int[]{1, 2}, new int[]{3, 6}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{5, 6}, new int[]{7, 10}, new int[]{8, 11}, new int[]{1, 2}, new int[]{3, 6}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{5, 6}, new int[]{7, 10}, new int[]{8, 11}, new int[]{1, 2}, new int[]{3, 6}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{1, 2}, new int[]{3, 6}, new int[]{7, 11}}, new int[]{0, 0})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{1, 2}, new int[]{3, 6}, new int[]{7, 11}}, new int[][]{new int[]{-3, -1}, new int[]{1, 1}, new int[]{2, 5}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{5, 6}, new int[]{7, 10}, new int[]{8, 11}, new int[]{1, 2}, new int[]{3, 6}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{5, 6, 6}, new int[]{7, 10, 5}, new int[]{8, 11, 0}, new int[]{1, 2, 7}, new int[]{3, 6, 2}})));
System.out.println(json(Solution33.solve(new int[]{-4, -1})));
System.out.println(json(Solution34.solve(new int[]{-4, -1})));
System.out.println(json(Solution35.solve(new int[]{-4, -1})));
System.out.println(json(Solution36.solve(new int[]{-4, -1})));
System.out.println(json(Solution37.solve(new int[]{-4, -1})));
System.out.println(json(Solution38.solve(new int[]{-4, -1})));
System.out.println(json(Solution39.solve(new int[]{-4, -1}, -1)));
System.out.println(json(Solution40.solve(new int[]{-4, -1})));
System.out.println(json(Solution41.solve(new int[]{-4, -1}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 1})));
System.out.println(json(Solution43.solve(new int[]{-4, -1}, -1)));
System.out.println(json(Solution44.solve(new int[]{-4, -1}, 0, 5)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{8, 12}, new int[]{-3, -1}, new int[]{0, 5}, new int[]{5, 10}, new int[]{8, 9}, new int[]{-4, 1}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{8, 12}, new int[]{-3, -1}, new int[]{0, 5}, new int[]{5, 10}, new int[]{8, 9}, new int[]{-4, 1}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{8, 12}, new int[]{-3, -1}, new int[]{0, 5}, new int[]{5, 10}, new int[]{8, 9}, new int[]{-4, 1}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-4, 12}}, new int[]{0, 2})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-4, 12}}, new int[][]{new int[]{-2, -2}, new int[]{3, 6}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{8, 12}, new int[]{-3, -1}, new int[]{0, 5}, new int[]{5, 10}, new int[]{8, 9}, new int[]{-4, 1}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{8, 12, 0}, new int[]{-3, -1, 7}, new int[]{0, 5, 2}, new int[]{5, 10, 1}, new int[]{8, 9, 4}, new int[]{-4, 1, 3}})));
System.out.println(json(Solution33.solve(new int[]{0, -2, 2, -3, 1, 2, 0})));
System.out.println(json(Solution34.solve(new int[]{0, -2, 2, -3, 1, 2, 0})));
System.out.println(json(Solution35.solve(new int[]{0, -2, 2, -3, 1, 2, 0})));
System.out.println(json(Solution36.solve(new int[]{0, -2, 2, -3, 1, 2, 0})));
System.out.println(json(Solution37.solve(new int[]{0, -2, 2, -3, 1, 2, 0})));
System.out.println(json(Solution38.solve(new int[]{0, -2, 2, -3, 1, 2, 0})));
System.out.println(json(Solution39.solve(new int[]{0, -2, 2, -3, 1, 2, 0}, 3)));
System.out.println(json(Solution40.solve(new int[]{0, -2, 2, -3, 1, 2, 0})));
System.out.println(json(Solution41.solve(new int[]{0, -2, 2, -3, 1, 2, 0}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 0, 1, 1, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{0, -2, 2, -3, 1, 2, 0}, 3)));
System.out.println(json(Solution44.solve(new int[]{0, -2, 2, -3, 1, 2, 0}, -5, 0)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{6, 9}, new int[]{6, 11}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{6, 9}, new int[]{6, 11}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{6, 9}, new int[]{6, 11}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{6, 11}}, new int[]{-2, -1})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{6, 11}}, new int[][]{new int[]{1, 4}, new int[]{5, 9}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{6, 9}, new int[]{6, 11}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{6, 9, 4}, new int[]{6, 11, 3}})));
System.out.println(json(Solution33.solve(new int[]{2, 1, 1, 3, -1, 4, -4})));
System.out.println(json(Solution34.solve(new int[]{2, 1, 1, 3, -1, 4, -4})));
System.out.println(json(Solution35.solve(new int[]{2, 1, 1, 3, -1, 4, -4})));
System.out.println(json(Solution36.solve(new int[]{2, 1, 1, 3, -1, 4, -4})));
System.out.println(json(Solution37.solve(new int[]{2, 1, 1, 3, -1, 4, -4})));
System.out.println(json(Solution38.solve(new int[]{2, 1, 1, 3, -1, 4, -4})));
System.out.println(json(Solution39.solve(new int[]{2, 1, 1, 3, -1, 4, -4}, 3)));
System.out.println(json(Solution40.solve(new int[]{2, 1, 1, 3, -1, 4, -4})));
System.out.println(json(Solution41.solve(new int[]{2, 1, 1, 3, -1, 4, -4}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 1, 1, 1, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{2, 1, 1, 3, -1, 4, -4}, 3)));
System.out.println(json(Solution44.solve(new int[]{2, 1, 1, 3, -1, 4, -4}, 0, 7)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-2, 1}, new int[]{-2, -1}, new int[]{-4, -2}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-2, 1}, new int[]{-2, -1}, new int[]{-4, -2}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-2, 1}, new int[]{-2, -1}, new int[]{-4, -2}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-4, 1}}, new int[]{0, 3})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-4, 1}}, new int[][]{new int[]{0, 0}, new int[]{2, 8}, new int[]{9, 9}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-2, 1}, new int[]{-2, -1}, new int[]{-4, -2}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-2, 1, 6}, new int[]{-2, -1, 5}, new int[]{-4, -2, 0}})));
System.out.println(json(Solution33.solve(new int[]{-4, 1, 4, -2, 0, 0, 3, 4})));
System.out.println(json(Solution34.solve(new int[]{-4, 1, 4, -2, 0, 0, 3, 4})));
System.out.println(json(Solution35.solve(new int[]{-4, 1, 4, -2, 0, 0, 3, 4})));
System.out.println(json(Solution36.solve(new int[]{-4, 1, 4, -2, 0, 0, 3, 4})));
System.out.println(json(Solution37.solve(new int[]{-4, 1, 4, -2, 0, 0, 3, 4})));
System.out.println(json(Solution38.solve(new int[]{-4, 1, 4, -2, 0, 0, 3, 4})));
System.out.println(json(Solution39.solve(new int[]{-4, 1, 4, -2, 0, 0, 3, 4}, 3)));
System.out.println(json(Solution40.solve(new int[]{-4, 1, 4, -2, 0, 0, 3, 4})));
System.out.println(json(Solution41.solve(new int[]{-4, 1, 4, -2, 0, 0, 3, 4}, 6)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 0, 0, 0, 0, 1, 0})));
System.out.println(json(Solution43.solve(new int[]{-4, 1, 4, -2, 0, 0, 3, 4}, 3)));
System.out.println(json(Solution44.solve(new int[]{-4, 1, 4, -2, 0, 0, 3, 4}, -4, -3)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-5, -4}, new int[]{1, 5}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-5, -4}, new int[]{1, 5}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-5, -4}, new int[]{1, 5}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, -4}, new int[]{1, 5}}, new int[]{2, 3})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, -4}, new int[]{1, 5}}, new int[][]{new int[]{-3, 1}, new int[]{3, 10}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-5, -4}, new int[]{1, 5}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-5, -4, 0}, new int[]{1, 5, 7}})));
System.out.println(json(Solution33.solve(new int[]{-3, 2, -2})));
System.out.println(json(Solution34.solve(new int[]{-3, 2, -2})));
System.out.println(json(Solution35.solve(new int[]{-3, 2, -2})));
System.out.println(json(Solution36.solve(new int[]{-3, 2, -2})));
System.out.println(json(Solution37.solve(new int[]{-3, 2, -2})));
System.out.println(json(Solution38.solve(new int[]{-3, 2, -2})));
System.out.println(json(Solution39.solve(new int[]{-3, 2, -2}, 7)));
System.out.println(json(Solution40.solve(new int[]{-3, 2, -2})));
System.out.println(json(Solution41.solve(new int[]{-3, 2, -2}, 2)));
}
static void batch3500() {
System.out.println(json(Solution42.solve(new int[]{1, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{-3, 2, -2}, 7)));
System.out.println(json(Solution44.solve(new int[]{-3, 2, -2}, -2, 7)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-5, -1}, new int[]{9, 10}, new int[]{-3, 1}, new int[]{6, 10}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-5, -1}, new int[]{9, 10}, new int[]{-3, 1}, new int[]{6, 10}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-5, -1}, new int[]{9, 10}, new int[]{-3, 1}, new int[]{6, 10}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, 1}, new int[]{6, 10}}, new int[]{2, 2})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, 1}, new int[]{6, 10}}, new int[][]{new int[]{-3, 0}, new int[]{2, 4}, new int[]{5, 7}, new int[]{8, 11}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-5, -1}, new int[]{9, 10}, new int[]{-3, 1}, new int[]{6, 10}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-5, -1, 0}, new int[]{9, 10, 7}, new int[]{-3, 1, 2}, new int[]{6, 10, 1}})));
System.out.println(json(Solution33.solve(new int[]{3, 3, -3, 1, -1})));
System.out.println(json(Solution34.solve(new int[]{3, 3, -3, 1, -1})));
System.out.println(json(Solution35.solve(new int[]{3, 3, -3, 1, -1})));
System.out.println(json(Solution36.solve(new int[]{3, 3, -3, 1, -1})));
System.out.println(json(Solution37.solve(new int[]{3, 3, -3, 1, -1})));
System.out.println(json(Solution38.solve(new int[]{3, 3, -3, 1, -1})));
System.out.println(json(Solution39.solve(new int[]{3, 3, -3, 1, -1}, -1)));
System.out.println(json(Solution40.solve(new int[]{3, 3, -3, 1, -1})));
System.out.println(json(Solution41.solve(new int[]{3, 3, -3, 1, -1}, 4)));
System.out.println(json(Solution42.solve(new int[]{1, 1, 1, 1, 1})));
System.out.println(json(Solution43.solve(new int[]{3, 3, -3, 1, -1}, -1)));
System.out.println(json(Solution44.solve(new int[]{3, 3, -3, 1, -1}, 0, 3)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{0, 1}, new int[]{6, 10}, new int[]{-4, -2}, new int[]{-5, -4}, new int[]{5, 7}, new int[]{4, 8}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{0, 1}, new int[]{6, 10}, new int[]{-4, -2}, new int[]{-5, -4}, new int[]{5, 7}, new int[]{4, 8}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{0, 1}, new int[]{6, 10}, new int[]{-4, -2}, new int[]{-5, -4}, new int[]{5, 7}, new int[]{4, 8}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, -2}, new int[]{0, 1}, new int[]{4, 10}}, new int[]{6, 7})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, -2}, new int[]{0, 1}, new int[]{4, 10}}, new int[][]{new int[]{-5, -4}, new int[]{-3, -2}, new int[]{-1, -1}, new int[]{2, 3}, new int[]{7, 11}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{0, 1}, new int[]{6, 10}, new int[]{-4, -2}, new int[]{-5, -4}, new int[]{5, 7}, new int[]{4, 8}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{0, 1, 0}, new int[]{6, 10, 7}, new int[]{-4, -2, 2}, new int[]{-5, -4, 1}, new int[]{5, 7, 4}, new int[]{4, 8, 3}})));
System.out.println(json(Solution33.solve(new int[]{2, 0, -1, -1, -4, -2, 3})));
System.out.println(json(Solution34.solve(new int[]{2, 0, -1, -1, -4, -2, 3})));
System.out.println(json(Solution35.solve(new int[]{2, 0, -1, -1, -4, -2, 3})));
System.out.println(json(Solution36.solve(new int[]{2, 0, -1, -1, -4, -2, 3})));
System.out.println(json(Solution37.solve(new int[]{2, 0, -1, -1, -4, -2, 3})));
System.out.println(json(Solution38.solve(new int[]{2, 0, -1, -1, -4, -2, 3})));
System.out.println(json(Solution39.solve(new int[]{2, 0, -1, -1, -4, -2, 3}, -1)));
System.out.println(json(Solution40.solve(new int[]{2, 0, -1, -1, -4, -2, 3})));
System.out.println(json(Solution41.solve(new int[]{2, 0, -1, -1, -4, -2, 3}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 1, 1, 0, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{2, 0, -1, -1, -4, -2, 3}, -1)));
System.out.println(json(Solution44.solve(new int[]{2, 0, -1, -1, -4, -2, 3}, 1, 8)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{1, 6}, new int[]{8, 13}, new int[]{1, 2}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{1, 6}, new int[]{8, 13}, new int[]{1, 2}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{1, 6}, new int[]{8, 13}, new int[]{1, 2}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{1, 6}, new int[]{8, 13}}, new int[]{2, 5})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{1, 6}, new int[]{8, 13}}, new int[][]{new int[]{-5, -2}, new int[]{-1, 5}, new int[]{8, 10}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{1, 6}, new int[]{8, 13}, new int[]{1, 2}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{1, 6, 6}, new int[]{8, 13, 5}, new int[]{1, 2, 0}})));
System.out.println(json(Solution33.solve(new int[]{3, 1, -1, -2, -4, 3, 4, -4})));
System.out.println(json(Solution34.solve(new int[]{3, 1, -1, -2, -4, 3, 4, -4})));
System.out.println(json(Solution35.solve(new int[]{3, 1, -1, -2, -4, 3, 4, -4})));
System.out.println(json(Solution36.solve(new int[]{3, 1, -1, -2, -4, 3, 4, -4})));
System.out.println(json(Solution37.solve(new int[]{3, 1, -1, -2, -4, 3, 4, -4})));
System.out.println(json(Solution38.solve(new int[]{3, 1, -1, -2, -4, 3, 4, -4})));
System.out.println(json(Solution39.solve(new int[]{3, 1, -1, -2, -4, 3, 4, -4}, -1)));
System.out.println(json(Solution40.solve(new int[]{3, 1, -1, -2, -4, 3, 4, -4})));
System.out.println(json(Solution41.solve(new int[]{3, 1, -1, -2, -4, 3, 4, -4}, 4)));
System.out.println(json(Solution42.solve(new int[]{1, 1, 1, 0, 0, 1, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{3, 1, -1, -2, -4, 3, 4, -4}, -1)));
System.out.println(json(Solution44.solve(new int[]{3, 1, -1, -2, -4, 3, 4, -4}, 0, 7)));
System.out.println(json(Solution45.solve(new int[][]{})));
System.out.println(json(Solution46.solve(new int[][]{})));
System.out.println(json(Solution49.solve(new int[][]{})));
System.out.println(json(Solution47.solve(new int[][]{}, new int[]{6, 8})));
System.out.println(json(Solution48.solve(new int[][]{}, new int[][]{new int[]{5, 13}})));
System.out.println(json(Solution50.solve(new int[][]{})));
System.out.println(json(Solution51.solve(new int[][]{})));
System.out.println(json(Solution33.solve(new int[]{-1})));
System.out.println(json(Solution34.solve(new int[]{-1})));
System.out.println(json(Solution35.solve(new int[]{-1})));
System.out.println(json(Solution36.solve(new int[]{-1})));
System.out.println(json(Solution37.solve(new int[]{-1})));
System.out.println(json(Solution38.solve(new int[]{-1})));
System.out.println(json(Solution39.solve(new int[]{-1}, 7)));
System.out.println(json(Solution40.solve(new int[]{-1})));
System.out.println(json(Solution41.solve(new int[]{-1}, 6)));
System.out.println(json(Solution42.solve(new int[]{1})));
System.out.println(json(Solution43.solve(new int[]{-1}, 7)));
System.out.println(json(Solution44.solve(new int[]{-1}, -6, -5)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{3, 8}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{3, 8}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{3, 8}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{3, 8}}, new int[]{0, 3})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{3, 8}}, new int[][]{new int[]{0, 4}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{3, 8}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{3, 8, 6}})));
System.out.println(json(Solution33.solve(new int[]{4, 1, 2, -2, -4, 3})));
System.out.println(json(Solution34.solve(new int[]{4, 1, 2, -2, -4, 3})));
System.out.println(json(Solution35.solve(new int[]{4, 1, 2, -2, -4, 3})));
System.out.println(json(Solution36.solve(new int[]{4, 1, 2, -2, -4, 3})));
System.out.println(json(Solution37.solve(new int[]{4, 1, 2, -2, -4, 3})));
System.out.println(json(Solution38.solve(new int[]{4, 1, 2, -2, -4, 3})));
System.out.println(json(Solution39.solve(new int[]{4, 1, 2, -2, -4, 3}, 3)));
System.out.println(json(Solution40.solve(new int[]{4, 1, 2, -2, -4, 3})));
System.out.println(json(Solution41.solve(new int[]{4, 1, 2, -2, -4, 3}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 0, 0, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{4, 1, 2, -2, -4, 3}, 3)));
System.out.println(json(Solution44.solve(new int[]{4, 1, 2, -2, -4, 3}, -2, -1)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{7, 11}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{7, 11}})));
}
static void batch3600() {
System.out.println(json(Solution49.solve(new int[][]{new int[]{7, 11}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{7, 11}}, new int[]{-2, 0})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{7, 11}}, new int[][]{new int[]{-3, -3}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{7, 11}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{7, 11, 2}})));
System.out.println(json(Solution33.solve(new int[]{1, 4})));
System.out.println(json(Solution34.solve(new int[]{1, 4})));
System.out.println(json(Solution35.solve(new int[]{1, 4})));
System.out.println(json(Solution36.solve(new int[]{1, 4})));
System.out.println(json(Solution37.solve(new int[]{1, 4})));
System.out.println(json(Solution38.solve(new int[]{1, 4})));
System.out.println(json(Solution39.solve(new int[]{1, 4}, 7)));
System.out.println(json(Solution40.solve(new int[]{1, 4})));
System.out.println(json(Solution41.solve(new int[]{1, 4}, 4)));
System.out.println(json(Solution42.solve(new int[]{1, 0})));
System.out.println(json(Solution43.solve(new int[]{1, 4}, 7)));
System.out.println(json(Solution44.solve(new int[]{1, 4}, -3, -2)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-5, -2}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-5, -2}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-5, -2}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, -2}}, new int[]{4, 8})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, -2}}, new int[][]{new int[]{-5, -1}, new int[]{8, 10}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-5, -2}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-5, -2, 6}})));
System.out.println(json(Solution33.solve(new int[]{4, 3, -4, 2, 4, -3})));
System.out.println(json(Solution34.solve(new int[]{4, 3, -4, 2, 4, -3})));
System.out.println(json(Solution35.solve(new int[]{4, 3, -4, 2, 4, -3})));
System.out.println(json(Solution36.solve(new int[]{4, 3, -4, 2, 4, -3})));
System.out.println(json(Solution37.solve(new int[]{4, 3, -4, 2, 4, -3})));
System.out.println(json(Solution38.solve(new int[]{4, 3, -4, 2, 4, -3})));
System.out.println(json(Solution39.solve(new int[]{4, 3, -4, 2, 4, -3}, -1)));
System.out.println(json(Solution40.solve(new int[]{4, 3, -4, 2, 4, -3})));
System.out.println(json(Solution41.solve(new int[]{4, 3, -4, 2, 4, -3}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 0, 0, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{4, 3, -4, 2, 4, -3}, -1)));
System.out.println(json(Solution44.solve(new int[]{4, 3, -4, 2, 4, -3}, -2, 7)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{4, 8}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{4, 8}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{4, 8}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{4, 8}}, new int[]{4, 6})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{4, 8}}, new int[][]{new int[]{-5, -2}, new int[]{4, 7}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{4, 8}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{4, 8, 6}})));
System.out.println(json(Solution33.solve(new int[]{-2, 3, 3, -2, 2, -1})));
System.out.println(json(Solution34.solve(new int[]{-2, 3, 3, -2, 2, -1})));
System.out.println(json(Solution35.solve(new int[]{-2, 3, 3, -2, 2, -1})));
System.out.println(json(Solution36.solve(new int[]{-2, 3, 3, -2, 2, -1})));
System.out.println(json(Solution37.solve(new int[]{-2, 3, 3, -2, 2, -1})));
System.out.println(json(Solution38.solve(new int[]{-2, 3, 3, -2, 2, -1})));
System.out.println(json(Solution39.solve(new int[]{-2, 3, 3, -2, 2, -1}, -1)));
System.out.println(json(Solution40.solve(new int[]{-2, 3, 3, -2, 2, -1})));
System.out.println(json(Solution41.solve(new int[]{-2, 3, 3, -2, 2, -1}, 6)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 1, 0, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{-2, 3, 3, -2, 2, -1}, -1)));
System.out.println(json(Solution44.solve(new int[]{-2, 3, 3, -2, 2, -1}, -5, 4)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{5, 6}, new int[]{-4, -2}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{5, 6}, new int[]{-4, -2}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{5, 6}, new int[]{-4, -2}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-4, -2}, new int[]{5, 6}}, new int[]{0, 2})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-4, -2}, new int[]{5, 6}}, new int[][]{new int[]{-2, 11}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{5, 6}, new int[]{-4, -2}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{5, 6, 4}, new int[]{-4, -2, 3}})));
System.out.println(json(Solution33.solve(new int[]{2, -4, 2, 1, 2, 0, 3})));
System.out.println(json(Solution34.solve(new int[]{2, -4, 2, 1, 2, 0, 3})));
System.out.println(json(Solution35.solve(new int[]{2, -4, 2, 1, 2, 0, 3})));
System.out.println(json(Solution36.solve(new int[]{2, -4, 2, 1, 2, 0, 3})));
System.out.println(json(Solution37.solve(new int[]{2, -4, 2, 1, 2, 0, 3})));
System.out.println(json(Solution38.solve(new int[]{2, -4, 2, 1, 2, 0, 3})));
System.out.println(json(Solution39.solve(new int[]{2, -4, 2, 1, 2, 0, 3}, 7)));
System.out.println(json(Solution40.solve(new int[]{2, -4, 2, 1, 2, 0, 3})));
System.out.println(json(Solution41.solve(new int[]{2, -4, 2, 1, 2, 0, 3}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 0, 1, 0, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{2, -4, 2, 1, 2, 0, 3}, 7)));
System.out.println(json(Solution44.solve(new int[]{2, -4, 2, 1, 2, 0, 3}, -4, 3)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-2, 2}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-2, 2}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-2, 2}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-2, 2}}, new int[]{-2, -1})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-2, 2}}, new int[][]{new int[]{-3, 0}, new int[]{4, 4}, new int[]{8, 11}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-2, 2}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-2, 2, 6}})));
System.out.println(json(Solution33.solve(new int[]{1, -3, 3, 0, 2, -1})));
System.out.println(json(Solution34.solve(new int[]{1, -3, 3, 0, 2, -1})));
System.out.println(json(Solution35.solve(new int[]{1, -3, 3, 0, 2, -1})));
System.out.println(json(Solution36.solve(new int[]{1, -3, 3, 0, 2, -1})));
System.out.println(json(Solution37.solve(new int[]{1, -3, 3, 0, 2, -1})));
System.out.println(json(Solution38.solve(new int[]{1, -3, 3, 0, 2, -1})));
System.out.println(json(Solution39.solve(new int[]{1, -3, 3, 0, 2, -1}, -1)));
System.out.println(json(Solution40.solve(new int[]{1, -3, 3, 0, 2, -1})));
System.out.println(json(Solution41.solve(new int[]{1, -3, 3, 0, 2, -1}, 2)));
System.out.println(json(Solution42.solve(new int[]{1, 1, 1, 0, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{1, -3, 3, 0, 2, -1}, -1)));
System.out.println(json(Solution44.solve(new int[]{1, -3, 3, 0, 2, -1}, 0, 9)));
System.out.println(json(Solution45.solve(new int[][]{})));
System.out.println(json(Solution46.solve(new int[][]{})));
System.out.println(json(Solution49.solve(new int[][]{})));
System.out.println(json(Solution47.solve(new int[][]{}, new int[]{-2, -2})));
System.out.println(json(Solution48.solve(new int[][]{}, new int[][]{new int[]{-3, -1}, new int[]{2, 6}, new int[]{7, 7}})));
System.out.println(json(Solution50.solve(new int[][]{})));
System.out.println(json(Solution51.solve(new int[][]{})));
}
static void batch3700() {
System.out.println(json(Solution33.solve(new int[]{4})));
System.out.println(json(Solution34.solve(new int[]{4})));
System.out.println(json(Solution35.solve(new int[]{4})));
System.out.println(json(Solution36.solve(new int[]{4})));
System.out.println(json(Solution37.solve(new int[]{4})));
System.out.println(json(Solution38.solve(new int[]{4})));
System.out.println(json(Solution39.solve(new int[]{4}, 7)));
System.out.println(json(Solution40.solve(new int[]{4})));
System.out.println(json(Solution41.solve(new int[]{4}, 2)));
System.out.println(json(Solution42.solve(new int[]{0})));
System.out.println(json(Solution43.solve(new int[]{4}, 7)));
System.out.println(json(Solution44.solve(new int[]{4}, 2, 9)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-1, 2}, new int[]{7, 12}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-1, 2}, new int[]{7, 12}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-1, 2}, new int[]{7, 12}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-1, 2}, new int[]{7, 12}}, new int[]{6, 10})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-1, 2}, new int[]{7, 12}}, new int[][]{new int[]{1, 2}, new int[]{3, 6}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-1, 2}, new int[]{7, 12}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-1, 2, 4}, new int[]{7, 12, 3}})));
System.out.println(json(Solution33.solve(new int[]{0, -1, 2, -4, 2, 0, -2})));
System.out.println(json(Solution34.solve(new int[]{0, -1, 2, -4, 2, 0, -2})));
System.out.println(json(Solution35.solve(new int[]{0, -1, 2, -4, 2, 0, -2})));
System.out.println(json(Solution36.solve(new int[]{0, -1, 2, -4, 2, 0, -2})));
System.out.println(json(Solution37.solve(new int[]{0, -1, 2, -4, 2, 0, -2})));
System.out.println(json(Solution38.solve(new int[]{0, -1, 2, -4, 2, 0, -2})));
System.out.println(json(Solution39.solve(new int[]{0, -1, 2, -4, 2, 0, -2}, -1)));
System.out.println(json(Solution40.solve(new int[]{0, -1, 2, -4, 2, 0, -2})));
System.out.println(json(Solution41.solve(new int[]{0, -1, 2, -4, 2, 0, -2}, 6)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 0, 0, 0, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{0, -1, 2, -4, 2, 0, -2}, -1)));
System.out.println(json(Solution44.solve(new int[]{0, -1, 2, -4, 2, 0, -2}, -4, 1)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{1, 5}, new int[]{-3, 1}, new int[]{-1, 1}, new int[]{8, 11}, new int[]{-1, 4}, new int[]{5, 10}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{1, 5}, new int[]{-3, 1}, new int[]{-1, 1}, new int[]{8, 11}, new int[]{-1, 4}, new int[]{5, 10}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{1, 5}, new int[]{-3, 1}, new int[]{-1, 1}, new int[]{8, 11}, new int[]{-1, 4}, new int[]{5, 10}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-3, 11}}, new int[]{4, 5})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-3, 11}}, new int[][]{new int[]{2, 6}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{1, 5}, new int[]{-3, 1}, new int[]{-1, 1}, new int[]{8, 11}, new int[]{-1, 4}, new int[]{5, 10}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{1, 5, 4}, new int[]{-3, 1, 3}, new int[]{-1, 1, 6}, new int[]{8, 11, 5}, new int[]{-1, 4, 0}, new int[]{5, 10, 7}})));
System.out.println(json(Solution33.solve(new int[]{1, 0, 0})));
System.out.println(json(Solution34.solve(new int[]{1, 0, 0})));
System.out.println(json(Solution35.solve(new int[]{1, 0, 0})));
System.out.println(json(Solution36.solve(new int[]{1, 0, 0})));
System.out.println(json(Solution37.solve(new int[]{1, 0, 0})));
System.out.println(json(Solution38.solve(new int[]{1, 0, 0})));
System.out.println(json(Solution39.solve(new int[]{1, 0, 0}, 3)));
System.out.println(json(Solution40.solve(new int[]{1, 0, 0})));
System.out.println(json(Solution41.solve(new int[]{1, 0, 0}, 4)));
System.out.println(json(Solution42.solve(new int[]{1, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{1, 0, 0}, 3)));
System.out.println(json(Solution44.solve(new int[]{1, 0, 0}, -2, 3)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{3, 4}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{3, 4}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{3, 4}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{3, 4}}, new int[]{2, 4})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{3, 4}}, new int[][]{new int[]{-5, -4}, new int[]{3, 4}, new int[]{7, 9}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{3, 4}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{3, 4, 6}})));
System.out.println(json(Solution33.solve(new int[]{-4, -1, 2, 0, -3, -3})));
System.out.println(json(Solution34.solve(new int[]{-4, -1, 2, 0, -3, -3})));
System.out.println(json(Solution35.solve(new int[]{-4, -1, 2, 0, -3, -3})));
System.out.println(json(Solution36.solve(new int[]{-4, -1, 2, 0, -3, -3})));
System.out.println(json(Solution37.solve(new int[]{-4, -1, 2, 0, -3, -3})));
System.out.println(json(Solution38.solve(new int[]{-4, -1, 2, 0, -3, -3})));
System.out.println(json(Solution39.solve(new int[]{-4, -1, 2, 0, -3, -3}, 3)));
System.out.println(json(Solution40.solve(new int[]{-4, -1, 2, 0, -3, -3})));
System.out.println(json(Solution41.solve(new int[]{-4, -1, 2, 0, -3, -3}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 0, 0, 1, 1})));
System.out.println(json(Solution43.solve(new int[]{-4, -1, 2, 0, -3, -3}, 3)));
System.out.println(json(Solution44.solve(new int[]{-4, -1, 2, 0, -3, -3}, -6, -5)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-4, -1}, new int[]{-1, 4}, new int[]{4, 6}, new int[]{-3, -2}, new int[]{5, 8}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-4, -1}, new int[]{-1, 4}, new int[]{4, 6}, new int[]{-3, -2}, new int[]{5, 8}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-4, -1}, new int[]{-1, 4}, new int[]{4, 6}, new int[]{-3, -2}, new int[]{5, 8}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-4, 8}}, new int[]{0, 3})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-4, 8}}, new int[][]{new int[]{-3, 1}, new int[]{7, 11}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-4, -1}, new int[]{-1, 4}, new int[]{4, 6}, new int[]{-3, -2}, new int[]{5, 8}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-4, -1, 6}, new int[]{-1, 4, 5}, new int[]{4, 6, 0}, new int[]{-3, -2, 7}, new int[]{5, 8, 2}})));
System.out.println(json(Solution33.solve(new int[]{-3, 0})));
System.out.println(json(Solution34.solve(new int[]{-3, 0})));
System.out.println(json(Solution35.solve(new int[]{-3, 0})));
System.out.println(json(Solution36.solve(new int[]{-3, 0})));
System.out.println(json(Solution37.solve(new int[]{-3, 0})));
System.out.println(json(Solution38.solve(new int[]{-3, 0})));
System.out.println(json(Solution39.solve(new int[]{-3, 0}, -1)));
System.out.println(json(Solution40.solve(new int[]{-3, 0})));
System.out.println(json(Solution41.solve(new int[]{-3, 0}, 6)));
System.out.println(json(Solution42.solve(new int[]{1, 0})));
System.out.println(json(Solution43.solve(new int[]{-3, 0}, -1)));
System.out.println(json(Solution44.solve(new int[]{-3, 0}, -2, 3)));
System.out.println(json(Solution45.solve(new int[][]{})));
System.out.println(json(Solution46.solve(new int[][]{})));
System.out.println(json(Solution49.solve(new int[][]{})));
System.out.println(json(Solution47.solve(new int[][]{}, new int[]{-2, -1})));
System.out.println(json(Solution48.solve(new int[][]{}, new int[][]{new int[]{-3, -2}, new int[]{5, 6}, new int[]{7, 8}})));
System.out.println(json(Solution50.solve(new int[][]{})));
System.out.println(json(Solution51.solve(new int[][]{})));
System.out.println(json(Solution33.solve(new int[]{-4})));
System.out.println(json(Solution34.solve(new int[]{-4})));
System.out.println(json(Solution35.solve(new int[]{-4})));
System.out.println(json(Solution36.solve(new int[]{-4})));
System.out.println(json(Solution37.solve(new int[]{-4})));
}
static void batch3800() {
System.out.println(json(Solution38.solve(new int[]{-4})));
System.out.println(json(Solution39.solve(new int[]{-4}, 3)));
System.out.println(json(Solution40.solve(new int[]{-4})));
System.out.println(json(Solution41.solve(new int[]{-4}, 2)));
System.out.println(json(Solution42.solve(new int[]{0})));
System.out.println(json(Solution43.solve(new int[]{-4}, 3)));
System.out.println(json(Solution44.solve(new int[]{-4}, -1, 2)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{7, 8}, new int[]{5, 10}, new int[]{-1, 3}, new int[]{4, 5}, new int[]{-2, 3}, new int[]{-2, 0}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{7, 8}, new int[]{5, 10}, new int[]{-1, 3}, new int[]{4, 5}, new int[]{-2, 3}, new int[]{-2, 0}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{7, 8}, new int[]{5, 10}, new int[]{-1, 3}, new int[]{4, 5}, new int[]{-2, 3}, new int[]{-2, 0}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-2, 3}, new int[]{4, 10}}, new int[]{4, 4})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-2, 3}, new int[]{4, 10}}, new int[][]{new int[]{3, 5}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{7, 8}, new int[]{5, 10}, new int[]{-1, 3}, new int[]{4, 5}, new int[]{-2, 3}, new int[]{-2, 0}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{7, 8, 0}, new int[]{5, 10, 7}, new int[]{-1, 3, 2}, new int[]{4, 5, 1}, new int[]{-2, 3, 4}, new int[]{-2, 0, 3}})));
System.out.println(json(Solution33.solve(new int[]{1, 3, 0, 4, -1, -1, 0})));
System.out.println(json(Solution34.solve(new int[]{1, 3, 0, 4, -1, -1, 0})));
System.out.println(json(Solution35.solve(new int[]{1, 3, 0, 4, -1, -1, 0})));
System.out.println(json(Solution36.solve(new int[]{1, 3, 0, 4, -1, -1, 0})));
System.out.println(json(Solution37.solve(new int[]{1, 3, 0, 4, -1, -1, 0})));
System.out.println(json(Solution38.solve(new int[]{1, 3, 0, 4, -1, -1, 0})));
System.out.println(json(Solution39.solve(new int[]{1, 3, 0, 4, -1, -1, 0}, 3)));
System.out.println(json(Solution40.solve(new int[]{1, 3, 0, 4, -1, -1, 0})));
System.out.println(json(Solution41.solve(new int[]{1, 3, 0, 4, -1, -1, 0}, 2)));
System.out.println(json(Solution42.solve(new int[]{1, 1, 0, 0, 1, 1, 0})));
System.out.println(json(Solution43.solve(new int[]{1, 3, 0, 4, -1, -1, 0}, 3)));
System.out.println(json(Solution44.solve(new int[]{1, 3, 0, 4, -1, -1, 0}, -3, 6)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-1, 1}, new int[]{8, 13}, new int[]{0, 1}, new int[]{1, 5}, new int[]{7, 11}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-1, 1}, new int[]{8, 13}, new int[]{0, 1}, new int[]{1, 5}, new int[]{7, 11}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-1, 1}, new int[]{8, 13}, new int[]{0, 1}, new int[]{1, 5}, new int[]{7, 11}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-1, 5}, new int[]{7, 13}}, new int[]{6, 9})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-1, 5}, new int[]{7, 13}}, new int[][]{new int[]{3, 10}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-1, 1}, new int[]{8, 13}, new int[]{0, 1}, new int[]{1, 5}, new int[]{7, 11}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-1, 1, 2}, new int[]{8, 13, 1}, new int[]{0, 1, 4}, new int[]{1, 5, 3}, new int[]{7, 11, 6}})));
System.out.println(json(Solution33.solve(new int[]{-1, 3, -3, 1, -1, 0})));
System.out.println(json(Solution34.solve(new int[]{-1, 3, -3, 1, -1, 0})));
System.out.println(json(Solution35.solve(new int[]{-1, 3, -3, 1, -1, 0})));
System.out.println(json(Solution36.solve(new int[]{-1, 3, -3, 1, -1, 0})));
System.out.println(json(Solution37.solve(new int[]{-1, 3, -3, 1, -1, 0})));
System.out.println(json(Solution38.solve(new int[]{-1, 3, -3, 1, -1, 0})));
System.out.println(json(Solution39.solve(new int[]{-1, 3, -3, 1, -1, 0}, -1)));
System.out.println(json(Solution40.solve(new int[]{-1, 3, -3, 1, -1, 0})));
System.out.println(json(Solution41.solve(new int[]{-1, 3, -3, 1, -1, 0}, 4)));
System.out.println(json(Solution42.solve(new int[]{1, 1, 1, 1, 1, 0})));
System.out.println(json(Solution43.solve(new int[]{-1, 3, -3, 1, -1, 0}, -1)));
System.out.println(json(Solution44.solve(new int[]{-1, 3, -3, 1, -1, 0}, -2, 3)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{6, 9}, new int[]{1, 5}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{6, 9}, new int[]{1, 5}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{6, 9}, new int[]{1, 5}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{1, 5}, new int[]{6, 9}}, new int[]{6, 8})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{1, 5}, new int[]{6, 9}}, new int[][]{new int[]{-4, 2}, new int[]{5, 7}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{6, 9}, new int[]{1, 5}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{6, 9, 0}, new int[]{1, 5, 7}})));
System.out.println(json(Solution33.solve(new int[]{0, 3, -2})));
System.out.println(json(Solution34.solve(new int[]{0, 3, -2})));
System.out.println(json(Solution35.solve(new int[]{0, 3, -2})));
System.out.println(json(Solution36.solve(new int[]{0, 3, -2})));
System.out.println(json(Solution37.solve(new int[]{0, 3, -2})));
System.out.println(json(Solution38.solve(new int[]{0, 3, -2})));
System.out.println(json(Solution39.solve(new int[]{0, 3, -2}, 7)));
System.out.println(json(Solution40.solve(new int[]{0, 3, -2})));
System.out.println(json(Solution41.solve(new int[]{0, 3, -2}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 0})));
System.out.println(json(Solution43.solve(new int[]{0, 3, -2}, 7)));
System.out.println(json(Solution44.solve(new int[]{0, 3, -2}, -1, 6)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-4, -3}, new int[]{0, 1}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-4, -3}, new int[]{0, 1}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-4, -3}, new int[]{0, 1}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-4, -3}, new int[]{0, 1}}, new int[]{4, 4})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-4, -3}, new int[]{0, 1}}, new int[][]{new int[]{5, 6}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-4, -3}, new int[]{0, 1}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-4, -3, 4}, new int[]{0, 1, 3}})));
System.out.println(json(Solution33.solve(new int[]{-1, -2, -2, -4, -1, 0, -3})));
System.out.println(json(Solution34.solve(new int[]{-1, -2, -2, -4, -1, 0, -3})));
System.out.println(json(Solution35.solve(new int[]{-1, -2, -2, -4, -1, 0, -3})));
System.out.println(json(Solution36.solve(new int[]{-1, -2, -2, -4, -1, 0, -3})));
System.out.println(json(Solution37.solve(new int[]{-1, -2, -2, -4, -1, 0, -3})));
System.out.println(json(Solution38.solve(new int[]{-1, -2, -2, -4, -1, 0, -3})));
System.out.println(json(Solution39.solve(new int[]{-1, -2, -2, -4, -1, 0, -3}, 7)));
System.out.println(json(Solution40.solve(new int[]{-1, -2, -2, -4, -1, 0, -3})));
System.out.println(json(Solution41.solve(new int[]{-1, -2, -2, -4, -1, 0, -3}, 4)));
System.out.println(json(Solution42.solve(new int[]{1, 0, 0, 0, 1, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{-1, -2, -2, -4, -1, 0, -3}, 7)));
System.out.println(json(Solution44.solve(new int[]{-1, -2, -2, -4, -1, 0, -3}, -5, 0)));
System.out.println(json(Solution45.solve(new int[][]{})));
System.out.println(json(Solution46.solve(new int[][]{})));
System.out.println(json(Solution49.solve(new int[][]{})));
System.out.println(json(Solution47.solve(new int[][]{}, new int[]{-2, 1})));
System.out.println(json(Solution48.solve(new int[][]{}, new int[][]{new int[]{-4, -2}, new int[]{3, 5}})));
System.out.println(json(Solution50.solve(new int[][]{})));
System.out.println(json(Solution51.solve(new int[][]{})));
System.out.println(json(Solution33.solve(new int[]{1, -2, 0, 4, -2})));
System.out.println(json(Solution34.solve(new int[]{1, -2, 0, 4, -2})));
System.out.println(json(Solution35.solve(new int[]{1, -2, 0, 4, -2})));
System.out.println(json(Solution36.solve(new int[]{1, -2, 0, 4, -2})));
System.out.println(json(Solution37.solve(new int[]{1, -2, 0, 4, -2})));
System.out.println(json(Solution38.solve(new int[]{1, -2, 0, 4, -2})));
System.out.println(json(Solution39.solve(new int[]{1, -2, 0, 4, -2}, -1)));
System.out.println(json(Solution40.solve(new int[]{1, -2, 0, 4, -2})));
System.out.println(json(Solution41.solve(new int[]{1, -2, 0, 4, -2}, 2)));
System.out.println(json(Solution42.solve(new int[]{1, 0, 0, 0, 0})));
}
static void batch3900() {
System.out.println(json(Solution43.solve(new int[]{1, -2, 0, 4, -2}, -1)));
System.out.println(json(Solution44.solve(new int[]{1, -2, 0, 4, -2}, -6, -3)));
System.out.println(json(Solution45.solve(new int[][]{})));
System.out.println(json(Solution46.solve(new int[][]{})));
System.out.println(json(Solution49.solve(new int[][]{})));
System.out.println(json(Solution47.solve(new int[][]{}, new int[]{6, 8})));
System.out.println(json(Solution48.solve(new int[][]{}, new int[][]{new int[]{-5, -3}, new int[]{2, 12}})));
System.out.println(json(Solution50.solve(new int[][]{})));
System.out.println(json(Solution51.solve(new int[][]{})));
System.out.println(json(Solution33.solve(new int[]{-2, 4, 4, -1, 0})));
System.out.println(json(Solution34.solve(new int[]{-2, 4, 4, -1, 0})));
System.out.println(json(Solution35.solve(new int[]{-2, 4, 4, -1, 0})));
System.out.println(json(Solution36.solve(new int[]{-2, 4, 4, -1, 0})));
System.out.println(json(Solution37.solve(new int[]{-2, 4, 4, -1, 0})));
System.out.println(json(Solution38.solve(new int[]{-2, 4, 4, -1, 0})));
System.out.println(json(Solution39.solve(new int[]{-2, 4, 4, -1, 0}, 3)));
System.out.println(json(Solution40.solve(new int[]{-2, 4, 4, -1, 0})));
System.out.println(json(Solution41.solve(new int[]{-2, 4, 4, -1, 0}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 0, 1, 0})));
System.out.println(json(Solution43.solve(new int[]{-2, 4, 4, -1, 0}, 3)));
System.out.println(json(Solution44.solve(new int[]{-2, 4, 4, -1, 0}, -6, 1)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{2, 4}, new int[]{9, 10}, new int[]{6, 11}, new int[]{-1, 4}, new int[]{8, 12}, new int[]{-1, 0}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{2, 4}, new int[]{9, 10}, new int[]{6, 11}, new int[]{-1, 4}, new int[]{8, 12}, new int[]{-1, 0}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{2, 4}, new int[]{9, 10}, new int[]{6, 11}, new int[]{-1, 4}, new int[]{8, 12}, new int[]{-1, 0}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-1, 4}, new int[]{6, 12}}, new int[]{4, 5})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-1, 4}, new int[]{6, 12}}, new int[][]{new int[]{-2, 0}, new int[]{6, 13}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{2, 4}, new int[]{9, 10}, new int[]{6, 11}, new int[]{-1, 4}, new int[]{8, 12}, new int[]{-1, 0}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{2, 4, 0}, new int[]{9, 10, 7}, new int[]{6, 11, 2}, new int[]{-1, 4, 1}, new int[]{8, 12, 4}, new int[]{-1, 0, 3}})));
System.out.println(json(Solution33.solve(new int[]{-2, 0, -1, 0, 2, -2, 3})));
System.out.println(json(Solution34.solve(new int[]{-2, 0, -1, 0, 2, -2, 3})));
System.out.println(json(Solution35.solve(new int[]{-2, 0, -1, 0, 2, -2, 3})));
System.out.println(json(Solution36.solve(new int[]{-2, 0, -1, 0, 2, -2, 3})));
System.out.println(json(Solution37.solve(new int[]{-2, 0, -1, 0, 2, -2, 3})));
System.out.println(json(Solution38.solve(new int[]{-2, 0, -1, 0, 2, -2, 3})));
System.out.println(json(Solution39.solve(new int[]{-2, 0, -1, 0, 2, -2, 3}, 3)));
System.out.println(json(Solution40.solve(new int[]{-2, 0, -1, 0, 2, -2, 3})));
System.out.println(json(Solution41.solve(new int[]{-2, 0, -1, 0, 2, -2, 3}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 1, 0, 0, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{-2, 0, -1, 0, 2, -2, 3}, 3)));
System.out.println(json(Solution44.solve(new int[]{-2, 0, -1, 0, 2, -2, 3}, -1, 0)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-1, 3}, new int[]{5, 9}, new int[]{2, 6}, new int[]{-5, -1}, new int[]{4, 9}, new int[]{0, 1}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-1, 3}, new int[]{5, 9}, new int[]{2, 6}, new int[]{-5, -1}, new int[]{4, 9}, new int[]{0, 1}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-1, 3}, new int[]{5, 9}, new int[]{2, 6}, new int[]{-5, -1}, new int[]{4, 9}, new int[]{0, 1}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, 9}}, new int[]{4, 5})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, 9}}, new int[][]{new int[]{-2, 2}, new int[]{6, 7}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-1, 3}, new int[]{5, 9}, new int[]{2, 6}, new int[]{-5, -1}, new int[]{4, 9}, new int[]{0, 1}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-1, 3, 0}, new int[]{5, 9, 7}, new int[]{2, 6, 2}, new int[]{-5, -1, 1}, new int[]{4, 9, 4}, new int[]{0, 1, 3}})));
System.out.println(json(Solution33.solve(new int[]{2, 2, 3, 4, -3, -4, 2})));
System.out.println(json(Solution34.solve(new int[]{2, 2, 3, 4, -3, -4, 2})));
System.out.println(json(Solution35.solve(new int[]{2, 2, 3, 4, -3, -4, 2})));
System.out.println(json(Solution36.solve(new int[]{2, 2, 3, 4, -3, -4, 2})));
System.out.println(json(Solution37.solve(new int[]{2, 2, 3, 4, -3, -4, 2})));
System.out.println(json(Solution38.solve(new int[]{2, 2, 3, 4, -3, -4, 2})));
System.out.println(json(Solution39.solve(new int[]{2, 2, 3, 4, -3, -4, 2}, -1)));
System.out.println(json(Solution40.solve(new int[]{2, 2, 3, 4, -3, -4, 2})));
System.out.println(json(Solution41.solve(new int[]{2, 2, 3, 4, -3, -4, 2}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 1, 0, 1, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{2, 2, 3, 4, -3, -4, 2}, -1)));
System.out.println(json(Solution44.solve(new int[]{2, 2, 3, 4, -3, -4, 2}, -2, 1)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{0, 1}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{0, 1}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{0, 1}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{0, 1}}, new int[]{0, 2})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{0, 1}}, new int[][]{new int[]{1, 1}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{0, 1}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{0, 1, 2}})));
System.out.println(json(Solution33.solve(new int[]{-2, -3})));
System.out.println(json(Solution34.solve(new int[]{-2, -3})));
System.out.println(json(Solution35.solve(new int[]{-2, -3})));
System.out.println(json(Solution36.solve(new int[]{-2, -3})));
System.out.println(json(Solution37.solve(new int[]{-2, -3})));
System.out.println(json(Solution38.solve(new int[]{-2, -3})));
System.out.println(json(Solution39.solve(new int[]{-2, -3}, 7)));
System.out.println(json(Solution40.solve(new int[]{-2, -3})));
System.out.println(json(Solution41.solve(new int[]{-2, -3}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 1})));
System.out.println(json(Solution43.solve(new int[]{-2, -3}, 7)));
System.out.println(json(Solution44.solve(new int[]{-2, -3}, -2, 7)));
System.out.println(json(Solution45.solve(new int[][]{})));
System.out.println(json(Solution46.solve(new int[][]{})));
System.out.println(json(Solution49.solve(new int[][]{})));
System.out.println(json(Solution47.solve(new int[][]{}, new int[]{4, 8})));
System.out.println(json(Solution48.solve(new int[][]{}, new int[][]{new int[]{0, 0}, new int[]{2, 2}, new int[]{3, 6}})));
System.out.println(json(Solution50.solve(new int[][]{})));
System.out.println(json(Solution51.solve(new int[][]{})));
System.out.println(json(Solution33.solve(new int[]{-3, 0, -1, -2, -3})));
System.out.println(json(Solution34.solve(new int[]{-3, 0, -1, -2, -3})));
System.out.println(json(Solution35.solve(new int[]{-3, 0, -1, -2, -3})));
System.out.println(json(Solution36.solve(new int[]{-3, 0, -1, -2, -3})));
System.out.println(json(Solution37.solve(new int[]{-3, 0, -1, -2, -3})));
System.out.println(json(Solution38.solve(new int[]{-3, 0, -1, -2, -3})));
System.out.println(json(Solution39.solve(new int[]{-3, 0, -1, -2, -3}, 7)));
System.out.println(json(Solution40.solve(new int[]{-3, 0, -1, -2, -3})));
System.out.println(json(Solution41.solve(new int[]{-3, 0, -1, -2, -3}, 6)));
System.out.println(json(Solution42.solve(new int[]{1, 0, 1, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{-3, 0, -1, -2, -3}, 7)));
System.out.println(json(Solution44.solve(new int[]{-3, 0, -1, -2, -3}, -6, -5)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{1, 2}, new int[]{-5, -4}, new int[]{-4, 0}, new int[]{1, 3}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{1, 2}, new int[]{-5, -4}, new int[]{-4, 0}, new int[]{1, 3}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{1, 2}, new int[]{-5, -4}, new int[]{-4, 0}, new int[]{1, 3}})));
}
static void batch4000() {
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, 0}, new int[]{1, 3}}, new int[]{0, 1})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, 0}, new int[]{1, 3}}, new int[][]{new int[]{1, 1}, new int[]{2, 5}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{1, 2}, new int[]{-5, -4}, new int[]{-4, 0}, new int[]{1, 3}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{1, 2, 0}, new int[]{-5, -4, 7}, new int[]{-4, 0, 2}, new int[]{1, 3, 1}})));
System.out.println(json(Solution33.solve(new int[]{2, 4, 2, -3, -3})));
System.out.println(json(Solution34.solve(new int[]{2, 4, 2, -3, -3})));
System.out.println(json(Solution35.solve(new int[]{2, 4, 2, -3, -3})));
System.out.println(json(Solution36.solve(new int[]{2, 4, 2, -3, -3})));
System.out.println(json(Solution37.solve(new int[]{2, 4, 2, -3, -3})));
System.out.println(json(Solution38.solve(new int[]{2, 4, 2, -3, -3})));
System.out.println(json(Solution39.solve(new int[]{2, 4, 2, -3, -3}, 7)));
System.out.println(json(Solution40.solve(new int[]{2, 4, 2, -3, -3})));
System.out.println(json(Solution41.solve(new int[]{2, 4, 2, -3, -3}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 0, 1, 1})));
System.out.println(json(Solution43.solve(new int[]{2, 4, 2, -3, -3}, 7)));
System.out.println(json(Solution44.solve(new int[]{2, 4, 2, -3, -3}, -3, 4)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{9, 11}, new int[]{7, 9}, new int[]{-4, 0}, new int[]{5, 8}, new int[]{1, 3}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{9, 11}, new int[]{7, 9}, new int[]{-4, 0}, new int[]{5, 8}, new int[]{1, 3}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{9, 11}, new int[]{7, 9}, new int[]{-4, 0}, new int[]{5, 8}, new int[]{1, 3}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-4, 0}, new int[]{1, 3}, new int[]{5, 11}}, new int[]{4, 4})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-4, 0}, new int[]{1, 3}, new int[]{5, 11}}, new int[][]{new int[]{-1, -1}, new int[]{3, 5}, new int[]{8, 8}, new int[]{9, 10}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{9, 11}, new int[]{7, 9}, new int[]{-4, 0}, new int[]{5, 8}, new int[]{1, 3}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{9, 11, 6}, new int[]{7, 9, 5}, new int[]{-4, 0, 0}, new int[]{5, 8, 7}, new int[]{1, 3, 2}})));
System.out.println(json(Solution33.solve(new int[]{2, -1})));
System.out.println(json(Solution34.solve(new int[]{2, -1})));
System.out.println(json(Solution35.solve(new int[]{2, -1})));
System.out.println(json(Solution36.solve(new int[]{2, -1})));
System.out.println(json(Solution37.solve(new int[]{2, -1})));
System.out.println(json(Solution38.solve(new int[]{2, -1})));
System.out.println(json(Solution39.solve(new int[]{2, -1}, -1)));
System.out.println(json(Solution40.solve(new int[]{2, -1})));
System.out.println(json(Solution41.solve(new int[]{2, -1}, 6)));
System.out.println(json(Solution42.solve(new int[]{0, 1})));
System.out.println(json(Solution43.solve(new int[]{2, -1}, -1)));
System.out.println(json(Solution44.solve(new int[]{2, -1}, -5, -4)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{4, 9}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{4, 9}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{4, 9}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{4, 9}}, new int[]{-2, -2})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{4, 9}}, new int[][]{new int[]{6, 12}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{4, 9}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{4, 9, 6}})));
System.out.println(json(Solution33.solve(new int[]{-1, -3, 2, 2, -2, 3})));
System.out.println(json(Solution34.solve(new int[]{-1, -3, 2, 2, -2, 3})));
System.out.println(json(Solution35.solve(new int[]{-1, -3, 2, 2, -2, 3})));
System.out.println(json(Solution36.solve(new int[]{-1, -3, 2, 2, -2, 3})));
System.out.println(json(Solution37.solve(new int[]{-1, -3, 2, 2, -2, 3})));
System.out.println(json(Solution38.solve(new int[]{-1, -3, 2, 2, -2, 3})));
System.out.println(json(Solution39.solve(new int[]{-1, -3, 2, 2, -2, 3}, 3)));
System.out.println(json(Solution40.solve(new int[]{-1, -3, 2, 2, -2, 3})));
System.out.println(json(Solution41.solve(new int[]{-1, -3, 2, 2, -2, 3}, 2)));
System.out.println(json(Solution42.solve(new int[]{1, 1, 0, 0, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{-1, -3, 2, 2, -2, 3}, 3)));
System.out.println(json(Solution44.solve(new int[]{-1, -3, 2, 2, -2, 3}, -2, 7)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-3, -2}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-3, -2}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-3, -2}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-3, -2}}, new int[]{2, 2})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-3, -2}}, new int[][]{new int[]{-2, 1}, new int[]{4, 8}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-3, -2}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-3, -2, 2}})));
System.out.println(json(Solution33.solve(new int[]{-4, -3})));
System.out.println(json(Solution34.solve(new int[]{-4, -3})));
System.out.println(json(Solution35.solve(new int[]{-4, -3})));
System.out.println(json(Solution36.solve(new int[]{-4, -3})));
System.out.println(json(Solution37.solve(new int[]{-4, -3})));
System.out.println(json(Solution38.solve(new int[]{-4, -3})));
System.out.println(json(Solution39.solve(new int[]{-4, -3}, 7)));
System.out.println(json(Solution40.solve(new int[]{-4, -3})));
System.out.println(json(Solution41.solve(new int[]{-4, -3}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 1})));
System.out.println(json(Solution43.solve(new int[]{-4, -3}, 7)));
System.out.println(json(Solution44.solve(new int[]{-4, -3}, 1, 4)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-3, -2}, new int[]{-1, 2}, new int[]{8, 12}, new int[]{7, 11}, new int[]{-3, 0}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-3, -2}, new int[]{-1, 2}, new int[]{8, 12}, new int[]{7, 11}, new int[]{-3, 0}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-3, -2}, new int[]{-1, 2}, new int[]{8, 12}, new int[]{7, 11}, new int[]{-3, 0}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-3, 2}, new int[]{7, 12}}, new int[]{6, 8})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-3, 2}, new int[]{7, 12}}, new int[][]{new int[]{-4, -2}, new int[]{2, 3}, new int[]{4, 6}, new int[]{8, 9}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-3, -2}, new int[]{-1, 2}, new int[]{8, 12}, new int[]{7, 11}, new int[]{-3, 0}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-3, -2, 2}, new int[]{-1, 2, 1}, new int[]{8, 12, 4}, new int[]{7, 11, 3}, new int[]{-3, 0, 6}})));
System.out.println(json(Solution33.solve(new int[]{0, -1, -4, 1, -3, -4})));
System.out.println(json(Solution34.solve(new int[]{0, -1, -4, 1, -3, -4})));
System.out.println(json(Solution35.solve(new int[]{0, -1, -4, 1, -3, -4})));
System.out.println(json(Solution36.solve(new int[]{0, -1, -4, 1, -3, -4})));
System.out.println(json(Solution37.solve(new int[]{0, -1, -4, 1, -3, -4})));
System.out.println(json(Solution38.solve(new int[]{0, -1, -4, 1, -3, -4})));
System.out.println(json(Solution39.solve(new int[]{0, -1, -4, 1, -3, -4}, 7)));
System.out.println(json(Solution40.solve(new int[]{0, -1, -4, 1, -3, -4})));
System.out.println(json(Solution41.solve(new int[]{0, -1, -4, 1, -3, -4}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 0, 1, 1, 0})));
System.out.println(json(Solution43.solve(new int[]{0, -1, -4, 1, -3, -4}, 7)));
System.out.println(json(Solution44.solve(new int[]{0, -1, -4, 1, -3, -4}, -3, 2)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{9, 12}, new int[]{7, 8}, new int[]{3, 6}, new int[]{9, 10}, new int[]{-2, -1}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{9, 12}, new int[]{7, 8}, new int[]{3, 6}, new int[]{9, 10}, new int[]{-2, -1}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{9, 12}, new int[]{7, 8}, new int[]{3, 6}, new int[]{9, 10}, new int[]{-2, -1}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-2, -1}, new int[]{3, 6}, new int[]{7, 8}, new int[]{9, 12}}, new int[]{2, 3})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-2, -1}, new int[]{3, 6}, new int[]{7, 8}, new int[]{9, 12}}, new int[][]{new int[]{-5, -5}, new int[]{-3, -2}, new int[]{-1, 3}, new int[]{9, 11}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{9, 12}, new int[]{7, 8}, new int[]{3, 6}, new int[]{9, 10}, new int[]{-2, -1}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{9, 12, 2}, new int[]{7, 8, 1}, new int[]{3, 6, 4}, new int[]{9, 10, 3}, new int[]{-2, -1, 6}})));
System.out.println(json(Solution33.solve(new int[]{1, -3, 3, -4, 1, -2})));
}
static void batch4100() {
System.out.println(json(Solution34.solve(new int[]{1, -3, 3, -4, 1, -2})));
System.out.println(json(Solution35.solve(new int[]{1, -3, 3, -4, 1, -2})));
System.out.println(json(Solution36.solve(new int[]{1, -3, 3, -4, 1, -2})));
System.out.println(json(Solution37.solve(new int[]{1, -3, 3, -4, 1, -2})));
System.out.println(json(Solution38.solve(new int[]{1, -3, 3, -4, 1, -2})));
System.out.println(json(Solution39.solve(new int[]{1, -3, 3, -4, 1, -2}, 3)));
System.out.println(json(Solution40.solve(new int[]{1, -3, 3, -4, 1, -2})));
System.out.println(json(Solution41.solve(new int[]{1, -3, 3, -4, 1, -2}, 2)));
System.out.println(json(Solution42.solve(new int[]{1, 1, 1, 0, 1, 0})));
System.out.println(json(Solution43.solve(new int[]{1, -3, 3, -4, 1, -2}, 3)));
System.out.println(json(Solution44.solve(new int[]{1, -3, 3, -4, 1, -2}, 0, 1)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-5, -1}, new int[]{-1, 3}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-5, -1}, new int[]{-1, 3}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-5, -1}, new int[]{-1, 3}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, 3}}, new int[]{2, 6})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, 3}}, new int[][]{new int[]{-3, 1}, new int[]{5, 7}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-5, -1}, new int[]{-1, 3}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-5, -1, 4}, new int[]{-1, 3, 3}})));
System.out.println(json(Solution33.solve(new int[]{-2, 3, -1, -2, -3, 3, 0})));
System.out.println(json(Solution34.solve(new int[]{-2, 3, -1, -2, -3, 3, 0})));
System.out.println(json(Solution35.solve(new int[]{-2, 3, -1, -2, -3, 3, 0})));
System.out.println(json(Solution36.solve(new int[]{-2, 3, -1, -2, -3, 3, 0})));
System.out.println(json(Solution37.solve(new int[]{-2, 3, -1, -2, -3, 3, 0})));
System.out.println(json(Solution38.solve(new int[]{-2, 3, -1, -2, -3, 3, 0})));
System.out.println(json(Solution39.solve(new int[]{-2, 3, -1, -2, -3, 3, 0}, -1)));
System.out.println(json(Solution40.solve(new int[]{-2, 3, -1, -2, -3, 3, 0})));
System.out.println(json(Solution41.solve(new int[]{-2, 3, -1, -2, -3, 3, 0}, 4)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 1, 0, 1, 1, 0})));
System.out.println(json(Solution43.solve(new int[]{-2, 3, -1, -2, -3, 3, 0}, -1)));
System.out.println(json(Solution44.solve(new int[]{-2, 3, -1, -2, -3, 3, 0}, 1, 10)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-2, 3}, new int[]{-3, 1}, new int[]{6, 8}, new int[]{-1, 4}, new int[]{2, 3}, new int[]{-5, -3}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-2, 3}, new int[]{-3, 1}, new int[]{6, 8}, new int[]{-1, 4}, new int[]{2, 3}, new int[]{-5, -3}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-2, 3}, new int[]{-3, 1}, new int[]{6, 8}, new int[]{-1, 4}, new int[]{2, 3}, new int[]{-5, -3}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, 4}, new int[]{6, 8}}, new int[]{0, 0})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, 4}, new int[]{6, 8}}, new int[][]{new int[]{-5, -1}, new int[]{0, 1}, new int[]{6, 7}, new int[]{9, 13}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-2, 3}, new int[]{-3, 1}, new int[]{6, 8}, new int[]{-1, 4}, new int[]{2, 3}, new int[]{-5, -3}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-2, 3, 4}, new int[]{-3, 1, 3}, new int[]{6, 8, 6}, new int[]{-1, 4, 5}, new int[]{2, 3, 0}, new int[]{-5, -3, 7}})));
System.out.println(json(Solution33.solve(new int[]{3, -4, -1})));
System.out.println(json(Solution34.solve(new int[]{3, -4, -1})));
System.out.println(json(Solution35.solve(new int[]{3, -4, -1})));
System.out.println(json(Solution36.solve(new int[]{3, -4, -1})));
System.out.println(json(Solution37.solve(new int[]{3, -4, -1})));
System.out.println(json(Solution38.solve(new int[]{3, -4, -1})));
System.out.println(json(Solution39.solve(new int[]{3, -4, -1}, 7)));
System.out.println(json(Solution40.solve(new int[]{3, -4, -1})));
System.out.println(json(Solution41.solve(new int[]{3, -4, -1}, 2)));
System.out.println(json(Solution42.solve(new int[]{1, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{3, -4, -1}, 7)));
System.out.println(json(Solution44.solve(new int[]{3, -4, -1}, 2, 9)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{7, 9}, new int[]{6, 11}, new int[]{4, 9}, new int[]{2, 4}, new int[]{2, 5}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{7, 9}, new int[]{6, 11}, new int[]{4, 9}, new int[]{2, 4}, new int[]{2, 5}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{7, 9}, new int[]{6, 11}, new int[]{4, 9}, new int[]{2, 4}, new int[]{2, 5}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{2, 11}}, new int[]{0, 2})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{2, 11}}, new int[][]{new int[]{-4, -1}, new int[]{3, 6}, new int[]{8, 11}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{7, 9}, new int[]{6, 11}, new int[]{4, 9}, new int[]{2, 4}, new int[]{2, 5}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{7, 9, 2}, new int[]{6, 11, 1}, new int[]{4, 9, 4}, new int[]{2, 4, 3}, new int[]{2, 5, 6}})));
System.out.println(json(Solution33.solve(new int[]{0, -4, -2, -2, 0, 0})));
System.out.println(json(Solution34.solve(new int[]{0, -4, -2, -2, 0, 0})));
System.out.println(json(Solution35.solve(new int[]{0, -4, -2, -2, 0, 0})));
System.out.println(json(Solution36.solve(new int[]{0, -4, -2, -2, 0, 0})));
System.out.println(json(Solution37.solve(new int[]{0, -4, -2, -2, 0, 0})));
System.out.println(json(Solution38.solve(new int[]{0, -4, -2, -2, 0, 0})));
System.out.println(json(Solution39.solve(new int[]{0, -4, -2, -2, 0, 0}, 7)));
System.out.println(json(Solution40.solve(new int[]{0, -4, -2, -2, 0, 0})));
System.out.println(json(Solution41.solve(new int[]{0, -4, -2, -2, 0, 0}, 6)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 0, 0, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{0, -4, -2, -2, 0, 0}, 7)));
System.out.println(json(Solution44.solve(new int[]{0, -4, -2, -2, 0, 0}, -5, -4)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{0, 5}, new int[]{9, 12}, new int[]{3, 4}, new int[]{-3, 2}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{0, 5}, new int[]{9, 12}, new int[]{3, 4}, new int[]{-3, 2}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{0, 5}, new int[]{9, 12}, new int[]{3, 4}, new int[]{-3, 2}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-3, 5}, new int[]{9, 12}}, new int[]{4, 7})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-3, 5}, new int[]{9, 12}}, new int[][]{new int[]{-5, -5}, new int[]{-2, 0}, new int[]{2, 2}, new int[]{7, 11}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{0, 5}, new int[]{9, 12}, new int[]{3, 4}, new int[]{-3, 2}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{0, 5, 0}, new int[]{9, 12, 7}, new int[]{3, 4, 2}, new int[]{-3, 2, 1}})));
System.out.println(json(Solution33.solve(new int[]{-2, 2, 4, -1, 3})));
System.out.println(json(Solution34.solve(new int[]{-2, 2, 4, -1, 3})));
System.out.println(json(Solution35.solve(new int[]{-2, 2, 4, -1, 3})));
System.out.println(json(Solution36.solve(new int[]{-2, 2, 4, -1, 3})));
System.out.println(json(Solution37.solve(new int[]{-2, 2, 4, -1, 3})));
System.out.println(json(Solution38.solve(new int[]{-2, 2, 4, -1, 3})));
System.out.println(json(Solution39.solve(new int[]{-2, 2, 4, -1, 3}, 7)));
System.out.println(json(Solution40.solve(new int[]{-2, 2, 4, -1, 3})));
System.out.println(json(Solution41.solve(new int[]{-2, 2, 4, -1, 3}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 0, 1, 1})));
System.out.println(json(Solution43.solve(new int[]{-2, 2, 4, -1, 3}, 7)));
System.out.println(json(Solution44.solve(new int[]{-2, 2, 4, -1, 3}, -5, 2)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{2, 6}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{2, 6}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{2, 6}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{2, 6}}, new int[]{-2, 0})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{2, 6}}, new int[][]{new int[]{-5, -2}, new int[]{-1, -1}, new int[]{1, 6}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{2, 6}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{2, 6, 6}})));
System.out.println(json(Solution33.solve(new int[]{1, -4, -3, -4, -2, -1})));
System.out.println(json(Solution34.solve(new int[]{1, -4, -3, -4, -2, -1})));
System.out.println(json(Solution35.solve(new int[]{1, -4, -3, -4, -2, -1})));
System.out.println(json(Solution36.solve(new int[]{1, -4, -3, -4, -2, -1})));
System.out.println(json(Solution37.solve(new int[]{1, -4, -3, -4, -2, -1})));
System.out.println(json(Solution38.solve(new int[]{1, -4, -3, -4, -2, -1})));
}
static void batch4200() {
System.out.println(json(Solution39.solve(new int[]{1, -4, -3, -4, -2, -1}, 3)));
System.out.println(json(Solution40.solve(new int[]{1, -4, -3, -4, -2, -1})));
System.out.println(json(Solution41.solve(new int[]{1, -4, -3, -4, -2, -1}, 6)));
System.out.println(json(Solution42.solve(new int[]{1, 0, 1, 0, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{1, -4, -3, -4, -2, -1}, 3)));
System.out.println(json(Solution44.solve(new int[]{1, -4, -3, -4, -2, -1}, 0, 5)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{4, 5}, new int[]{-3, 0}, new int[]{6, 10}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{4, 5}, new int[]{-3, 0}, new int[]{6, 10}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{4, 5}, new int[]{-3, 0}, new int[]{6, 10}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-3, 0}, new int[]{4, 5}, new int[]{6, 10}}, new int[]{6, 9})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-3, 0}, new int[]{4, 5}, new int[]{6, 10}}, new int[][]{new int[]{-2, 1}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{4, 5}, new int[]{-3, 0}, new int[]{6, 10}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{4, 5, 6}, new int[]{-3, 0, 5}, new int[]{6, 10, 0}})));
System.out.println(json(Solution33.solve(new int[]{3, -2, 1, 4, -4, 3, -4, 4})));
System.out.println(json(Solution34.solve(new int[]{3, -2, 1, 4, -4, 3, -4, 4})));
System.out.println(json(Solution35.solve(new int[]{3, -2, 1, 4, -4, 3, -4, 4})));
System.out.println(json(Solution36.solve(new int[]{3, -2, 1, 4, -4, 3, -4, 4})));
System.out.println(json(Solution37.solve(new int[]{3, -2, 1, 4, -4, 3, -4, 4})));
System.out.println(json(Solution38.solve(new int[]{3, -2, 1, 4, -4, 3, -4, 4})));
System.out.println(json(Solution39.solve(new int[]{3, -2, 1, 4, -4, 3, -4, 4}, 7)));
System.out.println(json(Solution40.solve(new int[]{3, -2, 1, 4, -4, 3, -4, 4})));
System.out.println(json(Solution41.solve(new int[]{3, -2, 1, 4, -4, 3, -4, 4}, 2)));
System.out.println(json(Solution42.solve(new int[]{1, 0, 1, 0, 0, 1, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{3, -2, 1, 4, -4, 3, -4, 4}, 7)));
System.out.println(json(Solution44.solve(new int[]{3, -2, 1, 4, -4, 3, -4, 4}, -5, -4)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{2, 7}, new int[]{7, 12}, new int[]{-2, 1}, new int[]{6, 7}, new int[]{4, 9}, new int[]{9, 14}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{2, 7}, new int[]{7, 12}, new int[]{-2, 1}, new int[]{6, 7}, new int[]{4, 9}, new int[]{9, 14}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{2, 7}, new int[]{7, 12}, new int[]{-2, 1}, new int[]{6, 7}, new int[]{4, 9}, new int[]{9, 14}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-2, 1}, new int[]{2, 14}}, new int[]{-2, 1})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-2, 1}, new int[]{2, 14}}, new int[][]{new int[]{-2, -1}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{2, 7}, new int[]{7, 12}, new int[]{-2, 1}, new int[]{6, 7}, new int[]{4, 9}, new int[]{9, 14}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{2, 7, 0}, new int[]{7, 12, 7}, new int[]{-2, 1, 2}, new int[]{6, 7, 1}, new int[]{4, 9, 4}, new int[]{9, 14, 3}})));
System.out.println(json(Solution33.solve(new int[]{0, 3, -3, -3, -2, 0, -4})));
System.out.println(json(Solution34.solve(new int[]{0, 3, -3, -3, -2, 0, -4})));
System.out.println(json(Solution35.solve(new int[]{0, 3, -3, -3, -2, 0, -4})));
System.out.println(json(Solution36.solve(new int[]{0, 3, -3, -3, -2, 0, -4})));
System.out.println(json(Solution37.solve(new int[]{0, 3, -3, -3, -2, 0, -4})));
System.out.println(json(Solution38.solve(new int[]{0, 3, -3, -3, -2, 0, -4})));
System.out.println(json(Solution39.solve(new int[]{0, 3, -3, -3, -2, 0, -4}, 3)));
System.out.println(json(Solution40.solve(new int[]{0, 3, -3, -3, -2, 0, -4})));
System.out.println(json(Solution41.solve(new int[]{0, 3, -3, -3, -2, 0, -4}, 6)));
System.out.println(json(Solution42.solve(new int[]{0, 1, 1, 1, 0, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{0, 3, -3, -3, -2, 0, -4}, 3)));
System.out.println(json(Solution44.solve(new int[]{0, 3, -3, -3, -2, 0, -4}, -3, -2)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{6, 11}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{6, 11}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{6, 11}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{6, 11}}, new int[]{2, 5})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{6, 11}}, new int[][]{new int[]{3, 3}, new int[]{5, 12}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{6, 11}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{6, 11, 6}})));
System.out.println(json(Solution33.solve(new int[]{-3, 2, -2, 1, 3, -4})));
System.out.println(json(Solution34.solve(new int[]{-3, 2, -2, 1, 3, -4})));
System.out.println(json(Solution35.solve(new int[]{-3, 2, -2, 1, 3, -4})));
System.out.println(json(Solution36.solve(new int[]{-3, 2, -2, 1, 3, -4})));
System.out.println(json(Solution37.solve(new int[]{-3, 2, -2, 1, 3, -4})));
System.out.println(json(Solution38.solve(new int[]{-3, 2, -2, 1, 3, -4})));
System.out.println(json(Solution39.solve(new int[]{-3, 2, -2, 1, 3, -4}, 3)));
System.out.println(json(Solution40.solve(new int[]{-3, 2, -2, 1, 3, -4})));
System.out.println(json(Solution41.solve(new int[]{-3, 2, -2, 1, 3, -4}, 4)));
System.out.println(json(Solution42.solve(new int[]{1, 0, 0, 1, 1, 0})));
System.out.println(json(Solution43.solve(new int[]{-3, 2, -2, 1, 3, -4}, 3)));
System.out.println(json(Solution44.solve(new int[]{-3, 2, -2, 1, 3, -4}, 0, 3)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-5, -4}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-5, -4}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-5, -4}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-5, -4}}, new int[]{6, 7})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-5, -4}}, new int[][]{new int[]{-5, -4}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-5, -4}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-5, -4, 2}})));
System.out.println(json(Solution33.solve(new int[]{-2, -1})));
System.out.println(json(Solution34.solve(new int[]{-2, -1})));
System.out.println(json(Solution35.solve(new int[]{-2, -1})));
System.out.println(json(Solution36.solve(new int[]{-2, -1})));
System.out.println(json(Solution37.solve(new int[]{-2, -1})));
System.out.println(json(Solution38.solve(new int[]{-2, -1})));
System.out.println(json(Solution39.solve(new int[]{-2, -1}, -1)));
System.out.println(json(Solution40.solve(new int[]{-2, -1})));
System.out.println(json(Solution41.solve(new int[]{-2, -1}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 1})));
System.out.println(json(Solution43.solve(new int[]{-2, -1}, -1)));
System.out.println(json(Solution44.solve(new int[]{-2, -1}, -4, -1)));
System.out.println(json(Solution45.solve(new int[][]{})));
System.out.println(json(Solution46.solve(new int[][]{})));
System.out.println(json(Solution49.solve(new int[][]{})));
System.out.println(json(Solution47.solve(new int[][]{}, new int[]{-2, 2})));
System.out.println(json(Solution48.solve(new int[][]{}, new int[][]{new int[]{-4, 0}, new int[]{1, 3}})));
System.out.println(json(Solution50.solve(new int[][]{})));
System.out.println(json(Solution51.solve(new int[][]{})));
System.out.println(json(Solution33.solve(new int[]{3})));
System.out.println(json(Solution34.solve(new int[]{3})));
System.out.println(json(Solution35.solve(new int[]{3})));
System.out.println(json(Solution36.solve(new int[]{3})));
System.out.println(json(Solution37.solve(new int[]{3})));
System.out.println(json(Solution38.solve(new int[]{3})));
System.out.println(json(Solution39.solve(new int[]{3}, 7)));
System.out.println(json(Solution40.solve(new int[]{3})));
System.out.println(json(Solution41.solve(new int[]{3}, 4)));
System.out.println(json(Solution42.solve(new int[]{1})));
System.out.println(json(Solution43.solve(new int[]{3}, 7)));
}
static void batch4300() {
System.out.println(json(Solution44.solve(new int[]{3}, 1, 4)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{0, 2}, new int[]{3, 4}, new int[]{6, 7}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{0, 2}, new int[]{3, 4}, new int[]{6, 7}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{0, 2}, new int[]{3, 4}, new int[]{6, 7}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{0, 2}, new int[]{3, 4}, new int[]{6, 7}}, new int[]{0, 3})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{0, 2}, new int[]{3, 4}, new int[]{6, 7}}, new int[][]{new int[]{6, 8}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{0, 2}, new int[]{3, 4}, new int[]{6, 7}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{0, 2, 2}, new int[]{3, 4, 1}, new int[]{6, 7, 4}})));
System.out.println(json(Solution33.solve(new int[]{-3, 2, 0, 4})));
System.out.println(json(Solution34.solve(new int[]{-3, 2, 0, 4})));
System.out.println(json(Solution35.solve(new int[]{-3, 2, 0, 4})));
System.out.println(json(Solution36.solve(new int[]{-3, 2, 0, 4})));
System.out.println(json(Solution37.solve(new int[]{-3, 2, 0, 4})));
System.out.println(json(Solution38.solve(new int[]{-3, 2, 0, 4})));
System.out.println(json(Solution39.solve(new int[]{-3, 2, 0, 4}, 7)));
System.out.println(json(Solution40.solve(new int[]{-3, 2, 0, 4})));
System.out.println(json(Solution41.solve(new int[]{-3, 2, 0, 4}, 2)));
System.out.println(json(Solution42.solve(new int[]{1, 0, 0, 0})));
System.out.println(json(Solution43.solve(new int[]{-3, 2, 0, 4}, 7)));
System.out.println(json(Solution44.solve(new int[]{-3, 2, 0, 4}, 0, 3)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{7, 11}, new int[]{-2, 0}, new int[]{7, 9}, new int[]{-4, -2}, new int[]{3, 6}, new int[]{9, 10}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{7, 11}, new int[]{-2, 0}, new int[]{7, 9}, new int[]{-4, -2}, new int[]{3, 6}, new int[]{9, 10}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{7, 11}, new int[]{-2, 0}, new int[]{7, 9}, new int[]{-4, -2}, new int[]{3, 6}, new int[]{9, 10}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-4, 0}, new int[]{3, 6}, new int[]{7, 11}}, new int[]{2, 5})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-4, 0}, new int[]{3, 6}, new int[]{7, 11}}, new int[][]{new int[]{-1, -1}, new int[]{0, 3}, new int[]{5, 8}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{7, 11}, new int[]{-2, 0}, new int[]{7, 9}, new int[]{-4, -2}, new int[]{3, 6}, new int[]{9, 10}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{7, 11, 4}, new int[]{-2, 0, 3}, new int[]{7, 9, 6}, new int[]{-4, -2, 5}, new int[]{3, 6, 0}, new int[]{9, 10, 7}})));
System.out.println(json(Solution33.solve(new int[]{4, -4, 1})));
System.out.println(json(Solution34.solve(new int[]{4, -4, 1})));
System.out.println(json(Solution35.solve(new int[]{4, -4, 1})));
System.out.println(json(Solution36.solve(new int[]{4, -4, 1})));
System.out.println(json(Solution37.solve(new int[]{4, -4, 1})));
System.out.println(json(Solution38.solve(new int[]{4, -4, 1})));
System.out.println(json(Solution39.solve(new int[]{4, -4, 1}, 3)));
System.out.println(json(Solution40.solve(new int[]{4, -4, 1})));
System.out.println(json(Solution41.solve(new int[]{4, -4, 1}, 2)));
System.out.println(json(Solution42.solve(new int[]{0, 0, 1})));
System.out.println(json(Solution43.solve(new int[]{4, -4, 1}, 3)));
System.out.println(json(Solution44.solve(new int[]{4, -4, 1}, -5, -4)));
System.out.println(json(Solution45.solve(new int[][]{new int[]{-4, -3}})));
System.out.println(json(Solution46.solve(new int[][]{new int[]{-4, -3}})));
System.out.println(json(Solution49.solve(new int[][]{new int[]{-4, -3}})));
System.out.println(json(Solution47.solve(new int[][]{new int[]{-4, -3}}, new int[]{6, 10})));
System.out.println(json(Solution48.solve(new int[][]{new int[]{-4, -3}}, new int[][]{new int[]{-4, -2}, new int[]{1, 8}})));
System.out.println(json(Solution50.solve(new int[][]{new int[]{-4, -3}})));
System.out.println(json(Solution51.solve(new int[][]{new int[]{-4, -3, 2}})));
System.out.println(json(Solution52.solve(new int[]{4})));
System.out.println(json(Solution54.solve(new int[]{4})));
System.out.println(json(Solution53.solve(new int[]{4}, 1, 1)));
System.out.println(json(Solution55.solve(new int[]{4}, 1)));
System.out.println(json(Solution56.solve(new int[]{4})));
System.out.println(json(Solution57.solve(new int[]{4}, 92)));
System.out.println(json(Solution58.solve("aaaaba")));
System.out.println(json(Solution59.solve("aaaaba", 5)));
System.out.println(json(Solution60.solve("{){]{)(]{}{")));
System.out.println(json(Solution61.solve("aaaaba")));
System.out.println(json(Solution62.solve(new int[]{4})));
System.out.println(json(Solution63.solve(new int[]{4})));
System.out.println(json(Solution64.solve(new int[]{4})));
System.out.println(json(Solution65.solve("/b/a/.././/./b")));
System.out.println(json(Solution66.solve("49", 0)));
System.out.println(json(Solution67.solve("aaaaba")));
System.out.println(json(Solution68.solve("yxyaoobooaoxnaoo")));
System.out.println(json(Solution69.solve("aaaaba")));
System.out.println(json(Solution70.solve("aaaaba", "cbaba")));
System.out.println(json(Solution52.solve(new int[]{7, 2, -3, 0})));
System.out.println(json(Solution54.solve(new int[]{7, 2, -3, 0})));
System.out.println(json(Solution53.solve(new int[]{7, 2, -3, 0}, 4, 4)));
System.out.println(json(Solution55.solve(new int[]{7, 2, -3, 0}, 2)));
System.out.println(json(Solution56.solve(new int[]{7, 2, -3, 0})));
System.out.println(json(Solution57.solve(new int[]{7, 2, -3, 0}, 40)));
System.out.println(json(Solution58.solve("aa")));
System.out.println(json(Solution59.solve("aa", 2)));
System.out.println(json(Solution60.solve("{)[")));
System.out.println(json(Solution61.solve("aa")));
System.out.println(json(Solution62.solve(new int[]{7, 2, -3, 0})));
System.out.println(json(Solution63.solve(new int[]{7, 2, -3, 0})));
System.out.println(json(Solution64.solve(new int[]{7, 2, -3, 0})));
System.out.println(json(Solution65.solve("/")));
System.out.println(json(Solution66.solve("1", 0)));
System.out.println(json(Solution67.solve("aa")));
System.out.println(json(Solution68.solve("bobonzoloaozoanzyoolozoaya")));
System.out.println(json(Solution69.solve("aa")));
System.out.println(json(Solution70.solve("aa", "a")));
System.out.println(json(Solution52.solve(new int[]{-3, -4, 7, -2})));
System.out.println(json(Solution54.solve(new int[]{-3, -4, 7, -2})));
System.out.println(json(Solution53.solve(new int[]{-3, -4, 7, -2}, 1, 4)));
System.out.println(json(Solution55.solve(new int[]{-3, -4, 7, -2}, 3)));
System.out.println(json(Solution56.solve(new int[]{-3, -4, 7, -2})));
System.out.println(json(Solution57.solve(new int[]{-3, -4, 7, -2}, 1)));
System.out.println(json(Solution58.solve("cca")));
System.out.println(json(Solution59.solve("cca", 1)));
System.out.println(json(Solution60.solve("){)[](][")));
System.out.println(json(Solution61.solve("cca")));
System.out.println(json(Solution62.solve(new int[]{-3, -4, 7, -2})));
System.out.println(json(Solution63.solve(new int[]{-3, -4, 7, -2})));
System.out.println(json(Solution64.solve(new int[]{-3, -4, 7, -2})));
System.out.println(json(Solution65.solve("/b")));
System.out.println(json(Solution66.solve("0", 1)));
System.out.println(json(Solution67.solve("cca")));
}
static void batch4400() {
System.out.println(json(Solution68.solve("xnzozylbznalooxbxlxboblllblbobxyxl")));
System.out.println(json(Solution69.solve("cca")));
System.out.println(json(Solution70.solve("cca", "aca")));
System.out.println(json(Solution52.solve(new int[]{2, 5, 0, -1, -2})));
System.out.println(json(Solution54.solve(new int[]{2, 5, 0, -1, -2})));
System.out.println(json(Solution53.solve(new int[]{2, 5, 0, -1, -2}, 5, 5)));
System.out.println(json(Solution55.solve(new int[]{2, 5, 0, -1, -2}, 5)));
System.out.println(json(Solution56.solve(new int[]{2, 5, 0, -1, -2})));
System.out.println(json(Solution57.solve(new int[]{2, 5, 0, -1, -2}, 15)));
System.out.println(json(Solution58.solve("b")));
System.out.println(json(Solution59.solve("b", 1)));
System.out.println(json(Solution60.solve(")[)(}{")));
System.out.println(json(Solution61.solve("b")));
System.out.println(json(Solution62.solve(new int[]{2, 5, 0, -1, -2})));
System.out.println(json(Solution63.solve(new int[]{2, 5, 0, -1, -2})));
System.out.println(json(Solution64.solve(new int[]{2, 5, 0, -1, -2})));
System.out.println(json(Solution65.solve("//.../b/a")));
System.out.println(json(Solution66.solve("3698701", 2)));
System.out.println(json(Solution67.solve("b")));
System.out.println(json(Solution68.solve("lzyxoabolxnznoooyllz")));
System.out.println(json(Solution69.solve("b")));
System.out.println(json(Solution70.solve("b", "cabcc")));
System.out.println(json(Solution52.solve(new int[]{})));
System.out.println(json(Solution54.solve(new int[]{})));
System.out.println(json(Solution57.solve(new int[]{}, 35)));
System.out.println(json(Solution58.solve("")));
System.out.println(json(Solution59.solve("", 5)));
System.out.println(json(Solution60.solve(")(")));
System.out.println(json(Solution61.solve("")));
System.out.println(json(Solution62.solve(new int[]{})));
System.out.println(json(Solution63.solve(new int[]{})));
System.out.println(json(Solution64.solve(new int[]{})));
System.out.println(json(Solution65.solve("/b/a/../.")));
System.out.println(json(Solution66.solve("3654505", 2)));
System.out.println(json(Solution67.solve("")));
System.out.println(json(Solution68.solve("yooxbxlxooyalxoxlalzbanaoznll")));
System.out.println(json(Solution69.solve("")));
System.out.println(json(Solution70.solve("", "bc")));
System.out.println(json(Solution52.solve(new int[]{7, -2, -3, 4, -1, -2})));
System.out.println(json(Solution54.solve(new int[]{7, -2, -3, 4, -1, -2})));
System.out.println(json(Solution53.solve(new int[]{7, -2, -3, 4, -1, -2}, 3, 4)));
System.out.println(json(Solution55.solve(new int[]{7, -2, -3, 4, -1, -2}, 1)));
System.out.println(json(Solution56.solve(new int[]{7, -2, -3, 4, -1, -2})));
System.out.println(json(Solution57.solve(new int[]{7, -2, -3, 4, -1, -2}, 47)));
System.out.println(json(Solution58.solve("b")));
System.out.println(json(Solution59.solve("b", 4)));
System.out.println(json(Solution60.solve("]{")));
System.out.println(json(Solution61.solve("b")));
System.out.println(json(Solution62.solve(new int[]{7, -2, -3, 4, -1, -2})));
System.out.println(json(Solution63.solve(new int[]{7, -2, -3, 4, -1, -2})));
System.out.println(json(Solution64.solve(new int[]{7, -2, -3, 4, -1, -2})));
System.out.println(json(Solution65.solve("//./")));
System.out.println(json(Solution66.solve("6187698", 7)));
System.out.println(json(Solution67.solve("b")));
System.out.println(json(Solution68.solve("oyzlolonabaoxy")));
System.out.println(json(Solution69.solve("b")));
System.out.println(json(Solution70.solve("b", "bcaacc")));
System.out.println(json(Solution52.solve(new int[]{-1, 6})));
System.out.println(json(Solution54.solve(new int[]{-1, 6})));
System.out.println(json(Solution53.solve(new int[]{-1, 6}, 1, 2)));
System.out.println(json(Solution55.solve(new int[]{-1, 6}, 1)));
System.out.println(json(Solution56.solve(new int[]{-1, 6})));
System.out.println(json(Solution57.solve(new int[]{-1, 6}, 95)));
System.out.println(json(Solution58.solve("caacb")));
System.out.println(json(Solution59.solve("caacb", 2)));
System.out.println(json(Solution60.solve("}{")));
System.out.println(json(Solution61.solve("caacb")));
System.out.println(json(Solution62.solve(new int[]{-1, 6})));
System.out.println(json(Solution63.solve(new int[]{-1, 6})));
System.out.println(json(Solution64.solve(new int[]{-1, 6})));
System.out.println(json(Solution65.solve("//.../.././b")));
System.out.println(json(Solution66.solve("01898", 1)));
System.out.println(json(Solution67.solve("caacb")));
System.out.println(json(Solution68.solve("alanlnzl")));
System.out.println(json(Solution69.solve("caacb")));
System.out.println(json(Solution70.solve("caacb", "bcaaccba")));
System.out.println(json(Solution52.solve(new int[]{7, 2, 5, 0, 7, 2, -3, -4})));
System.out.println(json(Solution54.solve(new int[]{7, 2, 5, 0, 7, 2, -3, -4})));
System.out.println(json(Solution53.solve(new int[]{7, 2, 5, 0, 7, 2, -3, -4}, 5, 8)));
System.out.println(json(Solution55.solve(new int[]{7, 2, 5, 0, 7, 2, -3, -4}, 7)));
System.out.println(json(Solution56.solve(new int[]{7, 2, 5, 0, 7, 2, -3, -4})));
System.out.println(json(Solution57.solve(new int[]{7, 2, 5, 0, 7, 2, -3, -4}, 9)));
System.out.println(json(Solution58.solve("b")));
System.out.println(json(Solution59.solve("b", 3)));
System.out.println(json(Solution60.solve("}{}[](}{")));
System.out.println(json(Solution61.solve("b")));
System.out.println(json(Solution62.solve(new int[]{7, 2, 5, 0, 7, 2, -3, -4})));
System.out.println(json(Solution63.solve(new int[]{7, 2, 5, 0, 7, 2, -3, -4})));
System.out.println(json(Solution64.solve(new int[]{7, 2, 5, 0, 7, 2, -3, -4})));
System.out.println(json(Solution65.solve("/../.../..")));
System.out.println(json(Solution66.solve("016389", 5)));
System.out.println(json(Solution67.solve("b")));
System.out.println(json(Solution68.solve("oxy")));
System.out.println(json(Solution69.solve("b")));
System.out.println(json(Solution70.solve("b", "b")));
System.out.println(json(Solution52.solve(new int[]{4, 3, 6, 5, -4, 3, 2})));
System.out.println(json(Solution54.solve(new int[]{4, 3, 6, 5, -4, 3, 2})));
System.out.println(json(Solution53.solve(new int[]{4, 3, 6, 5, -4, 3, 2}, 1, 2)));
System.out.println(json(Solution55.solve(new int[]{4, 3, 6, 5, -4, 3, 2}, 6)));
System.out.println(json(Solution56.solve(new int[]{4, 3, 6, 5, -4, 3, 2})));
}
static void batch4500() {
System.out.println(json(Solution57.solve(new int[]{4, 3, 6, 5, -4, 3, 2}, 15)));
System.out.println(json(Solution58.solve("c")));
System.out.println(json(Solution59.solve("c", 4)));
System.out.println(json(Solution60.solve(")(")));
System.out.println(json(Solution61.solve("c")));
System.out.println(json(Solution62.solve(new int[]{4, 3, 6, 5, -4, 3, 2})));
System.out.println(json(Solution63.solve(new int[]{4, 3, 6, 5, -4, 3, 2})));
System.out.println(json(Solution64.solve(new int[]{4, 3, 6, 5, -4, 3, 2})));
System.out.println(json(Solution65.solve("/../.")));
System.out.println(json(Solution66.solve("14921", 0)));
System.out.println(json(Solution67.solve("c")));
System.out.println(json(Solution68.solve("nayxoonxbxb")));
System.out.println(json(Solution69.solve("c")));
System.out.println(json(Solution70.solve("c", "b")));
System.out.println(json(Solution52.solve(new int[]{-2, 1, -4, 3, -2, -3, -4})));
System.out.println(json(Solution54.solve(new int[]{-2, 1, -4, 3, -2, -3, -4})));
System.out.println(json(Solution53.solve(new int[]{-2, 1, -4, 3, -2, -3, -4}, 4, 4)));
System.out.println(json(Solution55.solve(new int[]{-2, 1, -4, 3, -2, -3, -4}, 1)));
System.out.println(json(Solution56.solve(new int[]{-2, 1, -4, 3, -2, -3, -4})));
System.out.println(json(Solution57.solve(new int[]{-2, 1, -4, 3, -2, -3, -4}, 82)));
System.out.println(json(Solution58.solve("cabb")));
System.out.println(json(Solution59.solve("cabb", 5)));
System.out.println(json(Solution60.solve("{){]{){][")));
System.out.println(json(Solution61.solve("cabb")));
System.out.println(json(Solution62.solve(new int[]{-2, 1, -4, 3, -2, -3, -4})));
System.out.println(json(Solution63.solve(new int[]{-2, 1, -4, 3, -2, -3, -4})));
System.out.println(json(Solution64.solve(new int[]{-2, 1, -4, 3, -2, -3, -4})));
System.out.println(json(Solution65.solve("/../...//./.././../...")));
System.out.println(json(Solution66.solve("383472", 3)));
System.out.println(json(Solution67.solve("cabb")));
System.out.println(json(Solution68.solve("zyoollonayonooabzoooan")));
System.out.println(json(Solution69.solve("cabb")));
System.out.println(json(Solution70.solve("cabb", "bccaabac")));
System.out.println(json(Solution52.solve(new int[]{5, -4, 7, 6})));
System.out.println(json(Solution54.solve(new int[]{5, -4, 7, 6})));
System.out.println(json(Solution53.solve(new int[]{5, -4, 7, 6}, 4, 4)));
System.out.println(json(Solution55.solve(new int[]{5, -4, 7, 6}, 2)));
System.out.println(json(Solution56.solve(new int[]{5, -4, 7, 6})));
System.out.println(json(Solution57.solve(new int[]{5, -4, 7, 6}, 16)));
System.out.println(json(Solution58.solve("babbcaac")));
System.out.println(json(Solution59.solve("babbcaac", 3)));
System.out.println(json(Solution60.solve("(}[]{)(){}(")));
System.out.println(json(Solution61.solve("babbcaac")));
System.out.println(json(Solution62.solve(new int[]{5, -4, 7, 6})));
System.out.println(json(Solution63.solve(new int[]{5, -4, 7, 6})));
System.out.println(json(Solution64.solve(new int[]{5, -4, 7, 6})));
System.out.println(json(Solution65.solve("/../a/b/a")));
System.out.println(json(Solution66.solve("92", 1)));
System.out.println(json(Solution67.solve("babbcaac")));
System.out.println(json(Solution68.solve("xlalxoxyxyooxyxyxollxbayxyonl")));
System.out.println(json(Solution69.solve("babbcaac")));
System.out.println(json(Solution70.solve("babbcaac", "bbbbccb")));
System.out.println(json(Solution52.solve(new int[]{-1, -2})));
System.out.println(json(Solution54.solve(new int[]{-1, -2})));
System.out.println(json(Solution53.solve(new int[]{-1, -2}, 2, 2)));
System.out.println(json(Solution55.solve(new int[]{-1, -2}, 2)));
System.out.println(json(Solution56.solve(new int[]{-1, -2})));
System.out.println(json(Solution57.solve(new int[]{-1, -2}, 76)));
System.out.println(json(Solution58.solve("bbacacba")));
System.out.println(json(Solution59.solve("bbacacba", 3)));
System.out.println(json(Solution60.solve("[}(")));
System.out.println(json(Solution61.solve("bbacacba")));
System.out.println(json(Solution62.solve(new int[]{-1, -2})));
System.out.println(json(Solution63.solve(new int[]{-1, -2})));
System.out.println(json(Solution64.solve(new int[]{-1, -2})));
System.out.println(json(Solution65.solve("/../a/b")));
System.out.println(json(Solution66.solve("0727054", 7)));
System.out.println(json(Solution67.solve("bbacacba")));
System.out.println(json(Solution68.solve("olzooyoozyzoa")));
System.out.println(json(Solution69.solve("bbacacba")));
System.out.println(json(Solution70.solve("bbacacba", "")));
System.out.println(json(Solution52.solve(new int[]{-2, 5, -4})));
System.out.println(json(Solution54.solve(new int[]{-2, 5, -4})));
System.out.println(json(Solution53.solve(new int[]{-2, 5, -4}, 3, 3)));
System.out.println(json(Solution55.solve(new int[]{-2, 5, -4}, 3)));
System.out.println(json(Solution56.solve(new int[]{-2, 5, -4})));
System.out.println(json(Solution57.solve(new int[]{-2, 5, -4}, 67)));
System.out.println(json(Solution58.solve("bacccca")));
System.out.println(json(Solution59.solve("bacccca", 3)));
System.out.println(json(Solution60.solve("][]{){][](")));
System.out.println(json(Solution61.solve("bacccca")));
System.out.println(json(Solution62.solve(new int[]{-2, 5, -4})));
System.out.println(json(Solution63.solve(new int[]{-2, 5, -4})));
System.out.println(json(Solution64.solve(new int[]{-2, 5, -4})));
System.out.println(json(Solution65.solve("/../...//a")));
System.out.println(json(Solution66.solve("34", 2)));
System.out.println(json(Solution67.solve("bacccca")));
System.out.println(json(Solution68.solve("xoznzoxlxbonayloabonzoaboyayllan")));
System.out.println(json(Solution69.solve("bacccca")));
System.out.println(json(Solution70.solve("bacccca", "")));
System.out.println(json(Solution52.solve(new int[]{3, 2, 1, 4, 7, 6})));
System.out.println(json(Solution54.solve(new int[]{3, 2, 1, 4, 7, 6})));
System.out.println(json(Solution53.solve(new int[]{3, 2, 1, 4, 7, 6}, 3, 4)));
System.out.println(json(Solution55.solve(new int[]{3, 2, 1, 4, 7, 6}, 3)));
System.out.println(json(Solution56.solve(new int[]{3, 2, 1, 4, 7, 6})));
System.out.println(json(Solution57.solve(new int[]{3, 2, 1, 4, 7, 6}, 59)));
System.out.println(json(Solution58.solve("aaacb")));
System.out.println(json(Solution59.solve("aaacb", 2)));
System.out.println(json(Solution60.solve("][){}[")));
System.out.println(json(Solution61.solve("aaacb")));
}
static void batch4600() {
System.out.println(json(Solution62.solve(new int[]{3, 2, 1, 4, 7, 6})));
System.out.println(json(Solution63.solve(new int[]{3, 2, 1, 4, 7, 6})));
System.out.println(json(Solution64.solve(new int[]{3, 2, 1, 4, 7, 6})));
System.out.println(json(Solution65.solve("/b")));
System.out.println(json(Solution66.solve("87218", 1)));
System.out.println(json(Solution67.solve("aaacb")));
System.out.println(json(Solution68.solve("onxyxolnoyxnznonaboyxloyolloobab")));
System.out.println(json(Solution69.solve("aaacb")));
System.out.println(json(Solution70.solve("aaacb", "ca")));
System.out.println(json(Solution52.solve(new int[]{})));
System.out.println(json(Solution54.solve(new int[]{})));
System.out.println(json(Solution57.solve(new int[]{}, 39)));
System.out.println(json(Solution58.solve("")));
System.out.println(json(Solution59.solve("", 4)));
System.out.println(json(Solution60.solve(")[){)[]{)[")));
System.out.println(json(Solution61.solve("")));
System.out.println(json(Solution62.solve(new int[]{})));
System.out.println(json(Solution63.solve(new int[]{})));
System.out.println(json(Solution64.solve(new int[]{})));
System.out.println(json(Solution65.solve("//a/..")));
System.out.println(json(Solution66.solve("6", 1)));
System.out.println(json(Solution67.solve("")));
System.out.println(json(Solution68.solve("xbxyxnonlyaoxbxlzol")));
System.out.println(json(Solution69.solve("")));
System.out.println(json(Solution70.solve("", "cbacc")));
System.out.println(json(Solution52.solve(new int[]{5, 4, 3, 2, 5, 0, 7, 6})));
System.out.println(json(Solution54.solve(new int[]{5, 4, 3, 2, 5, 0, 7, 6})));
System.out.println(json(Solution53.solve(new int[]{5, 4, 3, 2, 5, 0, 7, 6}, 8, 8)));
System.out.println(json(Solution55.solve(new int[]{5, 4, 3, 2, 5, 0, 7, 6}, 2)));
System.out.println(json(Solution56.solve(new int[]{5, 4, 3, 2, 5, 0, 7, 6})));
System.out.println(json(Solution57.solve(new int[]{5, 4, 3, 2, 5, 0, 7, 6}, 52)));
System.out.println(json(Solution58.solve("abbababb")));
System.out.println(json(Solution59.solve("abbababb", 1)));
System.out.println(json(Solution60.solve("{](]{](")));
System.out.println(json(Solution61.solve("abbababb")));
System.out.println(json(Solution62.solve(new int[]{5, 4, 3, 2, 5, 0, 7, 6})));
System.out.println(json(Solution63.solve(new int[]{5, 4, 3, 2, 5, 0, 7, 6})));
System.out.println(json(Solution64.solve(new int[]{5, 4, 3, 2, 5, 0, 7, 6})));
System.out.println(json(Solution65.solve("//./../...//.")));
System.out.println(json(Solution66.solve("303", 0)));
System.out.println(json(Solution67.solve("abbababb")));
System.out.println(json(Solution68.solve("nxnxl")));
System.out.println(json(Solution69.solve("abbababb")));
System.out.println(json(Solution70.solve("abbababb", "ca")));
System.out.println(json(Solution52.solve(new int[]{-3, 0, 7, -2, 1, -4, 7, 2})));
System.out.println(json(Solution54.solve(new int[]{-3, 0, 7, -2, 1, -4, 7, 2})));
System.out.println(json(Solution53.solve(new int[]{-3, 0, 7, -2, 1, -4, 7, 2}, 7, 8)));
System.out.println(json(Solution55.solve(new int[]{-3, 0, 7, -2, 1, -4, 7, 2}, 1)));
System.out.println(json(Solution56.solve(new int[]{-3, 0, 7, -2, 1, -4, 7, 2})));
System.out.println(json(Solution57.solve(new int[]{-3, 0, 7, -2, 1, -4, 7, 2}, 19)));
System.out.println(json(Solution58.solve("c")));
System.out.println(json(Solution59.solve("c", 5)));
System.out.println(json(Solution60.solve("}[}{)[")));
System.out.println(json(Solution61.solve("c")));
System.out.println(json(Solution62.solve(new int[]{-3, 0, 7, -2, 1, -4, 7, 2})));
System.out.println(json(Solution63.solve(new int[]{-3, 0, 7, -2, 1, -4, 7, 2})));
System.out.println(json(Solution64.solve(new int[]{-3, 0, 7, -2, 1, -4, 7, 2})));
System.out.println(json(Solution65.solve("/b/a//a/")));
System.out.println(json(Solution66.solve("6907", 0)));
System.out.println(json(Solution67.solve("c")));
System.out.println(json(Solution68.solve("yoozyxnoyabzblnonalayonabloolabaoa")));
System.out.println(json(Solution69.solve("c")));
System.out.println(json(Solution70.solve("c", "bacbbcc")));
System.out.println(json(Solution52.solve(new int[]{3, -2})));
System.out.println(json(Solution54.solve(new int[]{3, -2})));
System.out.println(json(Solution53.solve(new int[]{3, -2}, 1, 2)));
System.out.println(json(Solution55.solve(new int[]{3, -2}, 1)));
System.out.println(json(Solution56.solve(new int[]{3, -2})));
System.out.println(json(Solution57.solve(new int[]{3, -2}, 99)));
System.out.println(json(Solution58.solve("bcaac")));
System.out.println(json(Solution59.solve("bcaac", 3)));
System.out.println(json(Solution60.solve("}[)()(")));
System.out.println(json(Solution61.solve("bcaac")));
System.out.println(json(Solution62.solve(new int[]{3, -2})));
System.out.println(json(Solution63.solve(new int[]{3, -2})));
System.out.println(json(Solution64.solve(new int[]{3, -2})));
System.out.println(json(Solution65.solve("/.././/.../..")));
System.out.println(json(Solution66.solve("41", 2)));
System.out.println(json(Solution67.solve("bcaac")));
System.out.println(json(Solution68.solve("nallnoylolyooxlz")));
System.out.println(json(Solution69.solve("bcaac")));
System.out.println(json(Solution70.solve("bcaac", "abbbac")));
System.out.println(json(Solution52.solve(new int[]{4, 7, -2, 1, 0})));
System.out.println(json(Solution54.solve(new int[]{4, 7, -2, 1, 0})));
System.out.println(json(Solution53.solve(new int[]{4, 7, -2, 1, 0}, 5, 5)));
System.out.println(json(Solution55.solve(new int[]{4, 7, -2, 1, 0}, 3)));
System.out.println(json(Solution56.solve(new int[]{4, 7, -2, 1, 0})));
System.out.println(json(Solution57.solve(new int[]{4, 7, -2, 1, 0}, 70)));
System.out.println(json(Solution58.solve("bbab")));
System.out.println(json(Solution59.solve("bbab", 2)));
System.out.println(json(Solution60.solve("[][]{")));
System.out.println(json(Solution61.solve("bbab")));
System.out.println(json(Solution62.solve(new int[]{4, 7, -2, 1, 0})));
System.out.println(json(Solution63.solve(new int[]{4, 7, -2, 1, 0})));
System.out.println(json(Solution64.solve(new int[]{4, 7, -2, 1, 0})));
System.out.println(json(Solution65.solve("/")));
System.out.println(json(Solution66.solve("1436", 2)));
System.out.println(json(Solution67.solve("bbab")));
System.out.println(json(Solution68.solve("lylyxoznonolznan")));
System.out.println(json(Solution69.solve("bbab")));
}
static void batch4700() {
System.out.println(json(Solution70.solve("bbab", "bbcbaccb")));
System.out.println(json(Solution52.solve(new int[]{5, 4})));
System.out.println(json(Solution54.solve(new int[]{5, 4})));
System.out.println(json(Solution53.solve(new int[]{5, 4}, 2, 2)));
System.out.println(json(Solution55.solve(new int[]{5, 4}, 2)));
System.out.println(json(Solution56.solve(new int[]{5, 4})));
System.out.println(json(Solution57.solve(new int[]{5, 4}, 2)));
System.out.println(json(Solution58.solve("aabbcbab")));
System.out.println(json(Solution59.solve("aabbcbab", 1)));
System.out.println(json(Solution60.solve("[)[){}{][")));
System.out.println(json(Solution61.solve("aabbcbab")));
System.out.println(json(Solution62.solve(new int[]{5, 4})));
System.out.println(json(Solution63.solve(new int[]{5, 4})));
System.out.println(json(Solution64.solve(new int[]{5, 4})));
System.out.println(json(Solution65.solve("/../a/b/.../.././b/.")));
System.out.println(json(Solution66.solve("9", 0)));
System.out.println(json(Solution67.solve("aabbcbab")));
System.out.println(json(Solution68.solve("nznlololbxylll")));
System.out.println(json(Solution69.solve("aabbcbab")));
System.out.println(json(Solution70.solve("aabbcbab", "")));
System.out.println(json(Solution52.solve(new int[]{6})));
System.out.println(json(Solution54.solve(new int[]{6})));
System.out.println(json(Solution53.solve(new int[]{6}, 1, 1)));
System.out.println(json(Solution55.solve(new int[]{6}, 1)));
System.out.println(json(Solution56.solve(new int[]{6})));
System.out.println(json(Solution57.solve(new int[]{6}, 73)));
System.out.println(json(Solution58.solve("abccbcc")));
System.out.println(json(Solution59.solve("abccbcc", 2)));
System.out.println(json(Solution60.solve("}[](](){")));
System.out.println(json(Solution61.solve("abccbcc")));
System.out.println(json(Solution62.solve(new int[]{6})));
System.out.println(json(Solution63.solve(new int[]{6})));
System.out.println(json(Solution64.solve(new int[]{6})));
System.out.println(json(Solution65.solve("/../.../b")));
System.out.println(json(Solution66.solve("0107058", 7)));
System.out.println(json(Solution67.solve("abccbcc")));
System.out.println(json(Solution68.solve("xnzyayaolnobaolyolob")));
System.out.println(json(Solution69.solve("abccbcc")));
System.out.println(json(Solution70.solve("abccbcc", "bacb")));
System.out.println(json(Solution52.solve(new int[]{3, 6, -3, -4, 3, 6, 5, 4})));
System.out.println(json(Solution54.solve(new int[]{3, 6, -3, -4, 3, 6, 5, 4})));
System.out.println(json(Solution53.solve(new int[]{3, 6, -3, -4, 3, 6, 5, 4}, 7, 8)));
System.out.println(json(Solution55.solve(new int[]{3, 6, -3, -4, 3, 6, 5, 4}, 1)));
System.out.println(json(Solution56.solve(new int[]{3, 6, -3, -4, 3, 6, 5, 4})));
System.out.println(json(Solution57.solve(new int[]{3, 6, -3, -4, 3, 6, 5, 4}, 27)));
System.out.println(json(Solution58.solve("acaaccc")));
System.out.println(json(Solution59.solve("acaaccc", 1)));
System.out.println(json(Solution60.solve("}{){){")));
System.out.println(json(Solution61.solve("acaaccc")));
System.out.println(json(Solution62.solve(new int[]{3, 6, -3, -4, 3, 6, 5, 4})));
System.out.println(json(Solution63.solve(new int[]{3, 6, -3, -4, 3, 6, 5, 4})));
System.out.println(json(Solution64.solve(new int[]{3, 6, -3, -4, 3, 6, 5, 4})));
System.out.println(json(Solution65.solve("//./../a/b/.")));
System.out.println(json(Solution66.solve("785674", 5)));
System.out.println(json(Solution67.solve("acaaccc")));
System.out.println(json(Solution68.solve("zb")));
System.out.println(json(Solution69.solve("acaaccc")));
System.out.println(json(Solution70.solve("acaaccc", "b")));
System.out.println(json(Solution52.solve(new int[]{6, 5, -4})));
System.out.println(json(Solution54.solve(new int[]{6, 5, -4})));
System.out.println(json(Solution53.solve(new int[]{6, 5, -4}, 1, 3)));
System.out.println(json(Solution55.solve(new int[]{6, 5, -4}, 3)));
System.out.println(json(Solution56.solve(new int[]{6, 5, -4})));
System.out.println(json(Solution57.solve(new int[]{6, 5, -4}, 22)));
System.out.println(json(Solution58.solve("")));
System.out.println(json(Solution59.solve("", 1)));
System.out.println(json(Solution60.solve("{}[}(][][")));
System.out.println(json(Solution61.solve("")));
System.out.println(json(Solution62.solve(new int[]{6, 5, -4})));
System.out.println(json(Solution63.solve(new int[]{6, 5, -4})));
System.out.println(json(Solution64.solve(new int[]{6, 5, -4})));
System.out.println(json(Solution65.solve("/")));
System.out.println(json(Solution66.solve("583092", 5)));
System.out.println(json(Solution67.solve("")));
System.out.println(json(Solution68.solve("alxnlnoboylnaballnxb")));
System.out.println(json(Solution69.solve("")));
System.out.println(json(Solution70.solve("", "abcbaccc")));
System.out.println(json(Solution52.solve(new int[]{})));
System.out.println(json(Solution54.solve(new int[]{})));
System.out.println(json(Solution57.solve(new int[]{}, 15)));
System.out.println(json(Solution58.solve("ab")));
System.out.println(json(Solution59.solve("ab", 4)));
System.out.println(json(Solution60.solve("}[]{][]{][")));
System.out.println(json(Solution61.solve("ab")));
System.out.println(json(Solution62.solve(new int[]{})));
System.out.println(json(Solution63.solve(new int[]{})));
System.out.println(json(Solution64.solve(new int[]{})));
System.out.println(json(Solution65.solve("//a/b/.../..")));
System.out.println(json(Solution66.solve("0949", 2)));
System.out.println(json(Solution67.solve("ab")));
System.out.println(json(Solution68.solve("olnoyoyzyxbzbzyxlolzn")));
System.out.println(json(Solution69.solve("ab")));
System.out.println(json(Solution70.solve("ab", "ca")));
System.out.println(json(Solution52.solve(new int[]{-3, 4, -1, -2, 1, -4, -1, 2})));
System.out.println(json(Solution54.solve(new int[]{-3, 4, -1, -2, 1, -4, -1, 2})));
System.out.println(json(Solution53.solve(new int[]{-3, 4, -1, -2, 1, -4, -1, 2}, 8, 8)));
System.out.println(json(Solution55.solve(new int[]{-3, 4, -1, -2, 1, -4, -1, 2}, 2)));
System.out.println(json(Solution56.solve(new int[]{-3, 4, -1, -2, 1, -4, -1, 2})));
System.out.println(json(Solution57.solve(new int[]{-3, 4, -1, -2, 1, -4, -1, 2}, 48)));
System.out.println(json(Solution58.solve("cbabaaba")));
}
static void batch4800() {
System.out.println(json(Solution59.solve("cbabaaba", 2)));
System.out.println(json(Solution60.solve("[]()(]{){}(")));
System.out.println(json(Solution61.solve("cbabaaba")));
System.out.println(json(Solution62.solve(new int[]{-3, 4, -1, -2, 1, -4, -1, 2})));
System.out.println(json(Solution63.solve(new int[]{-3, 4, -1, -2, 1, -4, -1, 2})));
System.out.println(json(Solution64.solve(new int[]{-3, 4, -1, -2, 1, -4, -1, 2})));
System.out.println(json(Solution65.solve("/b")));
System.out.println(json(Solution66.solve("8187", 2)));
System.out.println(json(Solution67.solve("cbabaaba")));
System.out.println(json(Solution68.solve("loyzbabolzbabxnon")));
System.out.println(json(Solution69.solve("cbabaaba")));
System.out.println(json(Solution70.solve("cbabaaba", "cbccbacb")));
System.out.println(json(Solution52.solve(new int[]{7, -2, 1, 0, 7, -2, -3, 0})));
System.out.println(json(Solution54.solve(new int[]{7, -2, 1, 0, 7, -2, -3, 0})));
System.out.println(json(Solution53.solve(new int[]{7, -2, 1, 0, 7, -2, -3, 0}, 1, 8)));
System.out.println(json(Solution55.solve(new int[]{7, -2, 1, 0, 7, -2, -3, 0}, 3)));
System.out.println(json(Solution56.solve(new int[]{7, -2, 1, 0, 7, -2, -3, 0})));
System.out.println(json(Solution57.solve(new int[]{7, -2, 1, 0, 7, -2, -3, 0}, 37)));
System.out.println(json(Solution58.solve("a")));
System.out.println(json(Solution59.solve("a", 4)));
System.out.println(json(Solution60.solve("}(}{")));
System.out.println(json(Solution61.solve("a")));
System.out.println(json(Solution62.solve(new int[]{7, -2, 1, 0, 7, -2, -3, 0})));
System.out.println(json(Solution63.solve(new int[]{7, -2, 1, 0, 7, -2, -3, 0})));
System.out.println(json(Solution64.solve(new int[]{7, -2, 1, 0, 7, -2, -3, 0})));
System.out.println(json(Solution65.solve("//.../../.../b/./b/...")));
System.out.println(json(Solution66.solve("1090365", 2)));
System.out.println(json(Solution67.solve("a")));
System.out.println(json(Solution68.solve("yllaylbobobznxllyab")));
System.out.println(json(Solution69.solve("a")));
System.out.println(json(Solution70.solve("a", "b")));
System.out.println(json(Solution52.solve(new int[]{6, 1, -4, 7, -2, 5, 4})));
System.out.println(json(Solution54.solve(new int[]{6, 1, -4, 7, -2, 5, 4})));
System.out.println(json(Solution53.solve(new int[]{6, 1, -4, 7, -2, 5, 4}, 6, 7)));
System.out.println(json(Solution55.solve(new int[]{6, 1, -4, 7, -2, 5, 4}, 5)));
System.out.println(json(Solution56.solve(new int[]{6, 1, -4, 7, -2, 5, 4})));
System.out.println(json(Solution57.solve(new int[]{6, 1, -4, 7, -2, 5, 4}, 33)));
System.out.println(json(Solution58.solve("a")));
System.out.println(json(Solution59.solve("a", 5)));
System.out.println(json(Solution60.solve(")(}(}(}(")));
System.out.println(json(Solution61.solve("a")));
System.out.println(json(Solution62.solve(new int[]{6, 1, -4, 7, -2, 5, 4})));
System.out.println(json(Solution63.solve(new int[]{6, 1, -4, 7, -2, 5, 4})));
System.out.println(json(Solution64.solve(new int[]{6, 1, -4, 7, -2, 5, 4})));
System.out.println(json(Solution65.solve("/../.")));
System.out.println(json(Solution66.solve("14", 2)));
System.out.println(json(Solution67.solve("a")));
System.out.println(json(Solution68.solve("oyzyzlob")));
System.out.println(json(Solution69.solve("a")));
System.out.println(json(Solution70.solve("a", "cbccbb")));
System.out.println(json(Solution52.solve(new int[]{})));
System.out.println(json(Solution54.solve(new int[]{})));
System.out.println(json(Solution57.solve(new int[]{}, 59)));
System.out.println(json(Solution58.solve("aa")));
System.out.println(json(Solution59.solve("aa", 3)));
System.out.println(json(Solution60.solve(")[")));
System.out.println(json(Solution61.solve("aa")));
System.out.println(json(Solution62.solve(new int[]{})));
System.out.println(json(Solution63.solve(new int[]{})));
System.out.println(json(Solution64.solve(new int[]{})));
System.out.println(json(Solution65.solve("//.")));
System.out.println(json(Solution66.solve("3212721", 4)));
System.out.println(json(Solution67.solve("aa")));
System.out.println(json(Solution68.solve("lzoollyaoxozbxlxoznonlno")));
System.out.println(json(Solution69.solve("aa")));
System.out.println(json(Solution70.solve("aa", "abbbc")));
System.out.println(json(Solution52.solve(new int[]{7, 6, 5, 4})));
System.out.println(json(Solution54.solve(new int[]{7, 6, 5, 4})));
System.out.println(json(Solution53.solve(new int[]{7, 6, 5, 4}, 4, 4)));
System.out.println(json(Solution55.solve(new int[]{7, 6, 5, 4}, 2)));
System.out.println(json(Solution56.solve(new int[]{7, 6, 5, 4})));
System.out.println(json(Solution57.solve(new int[]{7, 6, 5, 4}, 8)));
System.out.println(json(Solution58.solve("cccbbb")));
System.out.println(json(Solution59.solve("cccbbb", 4)));
System.out.println(json(Solution60.solve("{)[]{}{){]{")));
System.out.println(json(Solution61.solve("cccbbb")));
System.out.println(json(Solution62.solve(new int[]{7, 6, 5, 4})));
System.out.println(json(Solution63.solve(new int[]{7, 6, 5, 4})));
System.out.println(json(Solution64.solve(new int[]{7, 6, 5, 4})));
System.out.println(json(Solution65.solve("//a/b/a/../a")));
System.out.println(json(Solution66.solve("129454", 6)));
System.out.println(json(Solution67.solve("cccbbb")));
System.out.println(json(Solution68.solve("xnanalzlxlznayxollxbaoonxbxbo")));
System.out.println(json(Solution69.solve("cccbbb")));
System.out.println(json(Solution70.solve("cccbbb", "c")));
System.out.println(json(Solution52.solve(new int[]{7, -2, 5, 0})));
System.out.println(json(Solution54.solve(new int[]{7, -2, 5, 0})));
System.out.println(json(Solution53.solve(new int[]{7, -2, 5, 0}, 2, 3)));
System.out.println(json(Solution55.solve(new int[]{7, -2, 5, 0}, 4)));
System.out.println(json(Solution56.solve(new int[]{7, -2, 5, 0})));
System.out.println(json(Solution57.solve(new int[]{7, -2, 5, 0}, 2)));
System.out.println(json(Solution58.solve("bcbb")));
System.out.println(json(Solution59.solve("bcbb", 1)));
System.out.println(json(Solution60.solve("{](){){){")));
System.out.println(json(Solution61.solve("bcbb")));
System.out.println(json(Solution62.solve(new int[]{7, -2, 5, 0})));
System.out.println(json(Solution63.solve(new int[]{7, -2, 5, 0})));
System.out.println(json(Solution64.solve(new int[]{7, -2, 5, 0})));
System.out.println(json(Solution65.solve("/b/.../../.../..")));
System.out.println(json(Solution66.solve("894765", 2)));
}
static void batch4900() {
System.out.println(json(Solution67.solve("bcbb")));
System.out.println(json(Solution68.solve("lobonayxylbznalxoxlzoobxnlbooona")));
System.out.println(json(Solution69.solve("bcbb")));
System.out.println(json(Solution70.solve("bcbb", "a")));
System.out.println(json(Solution52.solve(new int[]{5, 0})));
System.out.println(json(Solution54.solve(new int[]{5, 0})));
System.out.println(json(Solution53.solve(new int[]{5, 0}, 2, 2)));
System.out.println(json(Solution55.solve(new int[]{5, 0}, 2)));
System.out.println(json(Solution56.solve(new int[]{5, 0})));
System.out.println(json(Solution57.solve(new int[]{5, 0}, 2)));
System.out.println(json(Solution58.solve("")));
System.out.println(json(Solution59.solve("", 1)));
System.out.println(json(Solution60.solve("(}(){")));
System.out.println(json(Solution61.solve("")));
System.out.println(json(Solution62.solve(new int[]{5, 0})));
System.out.println(json(Solution63.solve(new int[]{5, 0})));
System.out.println(json(Solution64.solve(new int[]{5, 0})));
System.out.println(json(Solution65.solve("/b/./../a//a")));
System.out.println(json(Solution66.solve("545", 0)));
System.out.println(json(Solution67.solve("")));
System.out.println(json(Solution68.solve("ooolnloxoloxbalxyayaooyzolyabxoz")));
System.out.println(json(Solution69.solve("")));
System.out.println(json(Solution70.solve("", "")));
System.out.println(json(Solution52.solve(new int[]{0, 3, -2, -3, -4})));
System.out.println(json(Solution54.solve(new int[]{0, 3, -2, -3, -4})));
System.out.println(json(Solution53.solve(new int[]{0, 3, -2, -3, -4}, 3, 3)));
System.out.println(json(Solution55.solve(new int[]{0, 3, -2, -3, -4}, 1)));
System.out.println(json(Solution56.solve(new int[]{0, 3, -2, -3, -4})));
System.out.println(json(Solution57.solve(new int[]{0, 3, -2, -3, -4}, 37)));
System.out.println(json(Solution58.solve("bccca")));
System.out.println(json(Solution59.solve("bccca", 1)));
System.out.println(json(Solution60.solve("")));
System.out.println(json(Solution61.solve("bccca")));
System.out.println(json(Solution62.solve(new int[]{0, 3, -2, -3, -4})));
System.out.println(json(Solution63.solve(new int[]{0, 3, -2, -3, -4})));
System.out.println(json(Solution64.solve(new int[]{0, 3, -2, -3, -4})));
System.out.println(json(Solution65.solve("/")));
System.out.println(json(Solution66.solve("941474", 1)));
System.out.println(json(Solution67.solve("bccca")));
System.out.println(json(Solution68.solve("llollbzozoxlola")));
System.out.println(json(Solution69.solve("bccca")));
System.out.println(json(Solution70.solve("bccca", "")));
System.out.println(json(Solution52.solve(new int[]{4, 3, -2, -3, 0, -1, 6})));
System.out.println(json(Solution54.solve(new int[]{4, 3, -2, -3, 0, -1, 6})));
System.out.println(json(Solution53.solve(new int[]{4, 3, -2, -3, 0, -1, 6}, 1, 3)));
System.out.println(json(Solution55.solve(new int[]{4, 3, -2, -3, 0, -1, 6}, 2)));
System.out.println(json(Solution56.solve(new int[]{4, 3, -2, -3, 0, -1, 6})));
System.out.println(json(Solution57.solve(new int[]{4, 3, -2, -3, 0, -1, 6}, 29)));
System.out.println(json(Solution58.solve("cab")));
System.out.println(json(Solution59.solve("cab", 1)));
System.out.println(json(Solution60.solve(")[)(")));
System.out.println(json(Solution61.solve("cab")));
System.out.println(json(Solution62.solve(new int[]{4, 3, -2, -3, 0, -1, 6})));
System.out.println(json(Solution63.solve(new int[]{4, 3, -2, -3, 0, -1, 6})));
System.out.println(json(Solution64.solve(new int[]{4, 3, -2, -3, 0, -1, 6})));
System.out.println(json(Solution65.solve("/")));
System.out.println(json(Solution66.solve("4527490", 1)));
System.out.println(json(Solution67.solve("cab")));
System.out.println(json(Solution68.solve("zyanxlxlonxolooyznalzblb")));
System.out.println(json(Solution69.solve("cab")));
System.out.println(json(Solution70.solve("cab", "bbacac")));
System.out.println(json(Solution52.solve(new int[]{})));
System.out.println(json(Solution54.solve(new int[]{})));
System.out.println(json(Solution57.solve(new int[]{}, 91)));
System.out.println(json(Solution58.solve("ba")));
System.out.println(json(Solution59.solve("ba", 1)));
System.out.println(json(Solution60.solve("}[)(}{}(]{")));
System.out.println(json(Solution61.solve("ba")));
System.out.println(json(Solution62.solve(new int[]{})));
System.out.println(json(Solution63.solve(new int[]{})));
System.out.println(json(Solution64.solve(new int[]{})));
System.out.println(json(Solution65.solve("//.../b/a/.././/a")));
System.out.println(json(Solution66.solve("9410", 4)));
System.out.println(json(Solution67.solve("ba")));
System.out.println(json(Solution68.solve("anooaox")));
System.out.println(json(Solution69.solve("ba")));
System.out.println(json(Solution70.solve("ba", "baaaabc")));
System.out.println(json(Solution52.solve(new int[]{7, 2, -3, -4})));
System.out.println(json(Solution54.solve(new int[]{7, 2, -3, -4})));
System.out.println(json(Solution53.solve(new int[]{7, 2, -3, -4}, 4, 4)));
System.out.println(json(Solution55.solve(new int[]{7, 2, -3, -4}, 2)));
System.out.println(json(Solution56.solve(new int[]{7, 2, -3, -4})));
System.out.println(json(Solution57.solve(new int[]{7, 2, -3, -4}, 36)));
System.out.println(json(Solution58.solve("bb")));
System.out.println(json(Solution59.solve("bb", 3)));
System.out.println(json(Solution60.solve("[](}{]{}{]{")));
System.out.println(json(Solution61.solve("bb")));
System.out.println(json(Solution62.solve(new int[]{7, 2, -3, -4})));
System.out.println(json(Solution63.solve(new int[]{7, 2, -3, -4})));
System.out.println(json(Solution64.solve(new int[]{7, 2, -3, -4})));
System.out.println(json(Solution65.solve("/../.")));
System.out.println(json(Solution66.solve("9032181", 0)));
System.out.println(json(Solution67.solve("bb")));
System.out.println(json(Solution68.solve("lllooxlalznoyxyobablbl")));
System.out.println(json(Solution69.solve("bb")));
System.out.println(json(Solution70.solve("bb", "c")));
System.out.println(json(Solution52.solve(new int[]{5, 0, 3, 2, -3, 4})));
System.out.println(json(Solution54.solve(new int[]{5, 0, 3, 2, -3, 4})));
System.out.println(json(Solution53.solve(new int[]{5, 0, 3, 2, -3, 4}, 6, 6)));
System.out.println(json(Solution55.solve(new int[]{5, 0, 3, 2, -3, 4}, 2)));
}
static void batch5000() {
System.out.println(json(Solution56.solve(new int[]{5, 0, 3, 2, -3, 4})));
System.out.println(json(Solution57.solve(new int[]{5, 0, 3, 2, -3, 4}, 42)));
System.out.println(json(Solution58.solve("aacc")));
System.out.println(json(Solution59.solve("aacc", 2)));
System.out.println(json(Solution60.solve("()[}{")));
System.out.println(json(Solution61.solve("aacc")));
System.out.println(json(Solution62.solve(new int[]{5, 0, 3, 2, -3, 4})));
System.out.println(json(Solution63.solve(new int[]{5, 0, 3, 2, -3, 4})));
System.out.println(json(Solution64.solve(new int[]{5, 0, 3, 2, -3, 4})));
System.out.println(json(Solution65.solve("/b/.//.../b/a/../...")));
System.out.println(json(Solution66.solve("7878", 2)));
System.out.println(json(Solution67.solve("aacc")));
System.out.println(json(Solution68.solve("lnxbonlbzlloobobabxyxnznlyxnal")));
System.out.println(json(Solution69.solve("aacc")));
System.out.println(json(Solution70.solve("aacc", "b")));
System.out.println(json(Solution52.solve(new int[]{6})));
System.out.println(json(Solution54.solve(new int[]{6})));
System.out.println(json(Solution53.solve(new int[]{6}, 1, 1)));
System.out.println(json(Solution55.solve(new int[]{6}, 1)));
System.out.println(json(Solution56.solve(new int[]{6})));
System.out.println(json(Solution57.solve(new int[]{6}, 67)));
System.out.println(json(Solution58.solve("a")));
System.out.println(json(Solution59.solve("a", 5)));
System.out.println(json(Solution60.solve("}{]{}[)()(")));
System.out.println(json(Solution61.solve("a")));
System.out.println(json(Solution62.solve(new int[]{6})));
System.out.println(json(Solution63.solve(new int[]{6})));
System.out.println(json(Solution64.solve(new int[]{6})));
System.out.println(json(Solution65.solve("/b/.../b")));
System.out.println(json(Solution66.solve("032969", 5)));
System.out.println(json(Solution67.solve("a")));
System.out.println(json(Solution68.solve("oaozoanxo")));
System.out.println(json(Solution69.solve("a")));
System.out.println(json(Solution70.solve("a", "ccabacca")));
System.out.println(json(Solution52.solve(new int[]{-1, 2, 1, -4, 3, -2, 1, -4})));
System.out.println(json(Solution54.solve(new int[]{-1, 2, 1, -4, 3, -2, 1, -4})));
System.out.println(json(Solution53.solve(new int[]{-1, 2, 1, -4, 3, -2, 1, -4}, 6, 8)));
System.out.println(json(Solution55.solve(new int[]{-1, 2, 1, -4, 3, -2, 1, -4}, 8)));
System.out.println(json(Solution56.solve(new int[]{-1, 2, 1, -4, 3, -2, 1, -4})));
System.out.println(json(Solution57.solve(new int[]{-1, 2, 1, -4, 3, -2, 1, -4}, 2)));
System.out.println(json(Solution58.solve("ccacbbcb")));
System.out.println(json(Solution59.solve("ccacbbcb", 1)));
System.out.println(json(Solution60.solve("[](){][][")));
System.out.println(json(Solution61.solve("ccacbbcb")));
System.out.println(json(Solution62.solve(new int[]{-1, 2, 1, -4, 3, -2, 1, -4})));
System.out.println(json(Solution63.solve(new int[]{-1, 2, 1, -4, 3, -2, 1, -4})));
System.out.println(json(Solution64.solve(new int[]{-1, 2, 1, -4, 3, -2, 1, -4})));
System.out.println(json(Solution65.solve("/b/.../../a/b/a")));
System.out.println(json(Solution66.solve("54903", 4)));
System.out.println(json(Solution67.solve("ccacbbcb")));
System.out.println(json(Solution68.solve("oonzbayoooyalxnxy")));
System.out.println(json(Solution69.solve("ccacbbcb")));
System.out.println(json(Solution70.solve("ccacbbcb", "bacbc")));
System.out.println(json(Solution52.solve(new int[]{4, 3, -2})));
System.out.println(json(Solution54.solve(new int[]{4, 3, -2})));
System.out.println(json(Solution53.solve(new int[]{4, 3, -2}, 1, 3)));
System.out.println(json(Solution55.solve(new int[]{4, 3, -2}, 1)));
System.out.println(json(Solution56.solve(new int[]{4, 3, -2})));
System.out.println(json(Solution57.solve(new int[]{4, 3, -2}, 28)));
System.out.println(json(Solution58.solve("bbbc")));
System.out.println(json(Solution59.solve("bbbc", 2)));
System.out.println(json(Solution60.solve("(){}(}()(){")));
System.out.println(json(Solution61.solve("bbbc")));
System.out.println(json(Solution62.solve(new int[]{4, 3, -2})));
System.out.println(json(Solution63.solve(new int[]{4, 3, -2})));
System.out.println(json(Solution64.solve(new int[]{4, 3, -2})));
System.out.println(json(Solution65.solve("/b")));
System.out.println(json(Solution66.solve("4163854", 5)));
System.out.println(json(Solution67.solve("bbbc")));
System.out.println(json(Solution68.solve("loobayzblnxlolxnzlznzylnxlxlxyaoln")));
System.out.println(json(Solution69.solve("bbbc")));
System.out.println(json(Solution70.solve("bbbc", "bac")));
System.out.println(json(Solution52.solve(new int[]{4, -1, 2, 5, -4})));
System.out.println(json(Solution54.solve(new int[]{4, -1, 2, 5, -4})));
System.out.println(json(Solution53.solve(new int[]{4, -1, 2, 5, -4}, 2, 4)));
System.out.println(json(Solution55.solve(new int[]{4, -1, 2, 5, -4}, 2)));
System.out.println(json(Solution56.solve(new int[]{4, -1, 2, 5, -4})));
System.out.println(json(Solution57.solve(new int[]{4, -1, 2, 5, -4}, 44)));
System.out.println(json(Solution58.solve("bb")));
System.out.println(json(Solution59.solve("bb", 3)));
System.out.println(json(Solution60.solve("{)[)[){}{]{")));
System.out.println(json(Solution61.solve("bb")));
System.out.println(json(Solution62.solve(new int[]{4, -1, 2, 5, -4})));
System.out.println(json(Solution63.solve(new int[]{4, -1, 2, 5, -4})));
System.out.println(json(Solution64.solve(new int[]{4, -1, 2, 5, -4})));
System.out.println(json(Solution65.solve("/.././../.../b/a")));
System.out.println(json(Solution66.solve("52505", 0)));
System.out.println(json(Solution67.solve("bb")));
System.out.println(json(Solution68.solve("nznalz")));
System.out.println(json(Solution69.solve("bb")));
System.out.println(json(Solution70.solve("bb", "accac")));
System.out.println(json(Solution52.solve(new int[]{3, 2, -3, 4})));
System.out.println(json(Solution54.solve(new int[]{3, 2, -3, 4})));
System.out.println(json(Solution53.solve(new int[]{3, 2, -3, 4}, 2, 2)));
System.out.println(json(Solution55.solve(new int[]{3, 2, -3, 4}, 4)));
System.out.println(json(Solution56.solve(new int[]{3, 2, -3, 4})));
System.out.println(json(Solution57.solve(new int[]{3, 2, -3, 4}, 82)));
System.out.println(json(Solution58.solve("bcba")));
System.out.println(json(Solution59.solve("bcba", 4)));
System.out.println(json(Solution60.solve("{)[}[}()(")));
}
static void batch5100() {
System.out.println(json(Solution61.solve("bcba")));
System.out.println(json(Solution62.solve(new int[]{3, 2, -3, 4})));
System.out.println(json(Solution63.solve(new int[]{3, 2, -3, 4})));
System.out.println(json(Solution64.solve(new int[]{3, 2, -3, 4})));
System.out.println(json(Solution65.solve("/b/./b/./b/a//a")));
System.out.println(json(Solution66.solve("109", 2)));
System.out.println(json(Solution67.solve("bcba")));
System.out.println(json(Solution68.solve("lxyxlaoaolnlyxbonxoolxl")));
System.out.println(json(Solution69.solve("bcba")));
System.out.println(json(Solution70.solve("bcba", "acba")));
System.out.println(json(Solution52.solve(new int[]{7, 2, -3, -4, 3, -2, 5, 4})));
System.out.println(json(Solution54.solve(new int[]{7, 2, -3, -4, 3, -2, 5, 4})));
System.out.println(json(Solution53.solve(new int[]{7, 2, -3, -4, 3, -2, 5, 4}, 3, 6)));
System.out.println(json(Solution55.solve(new int[]{7, 2, -3, -4, 3, -2, 5, 4}, 5)));
System.out.println(json(Solution56.solve(new int[]{7, 2, -3, -4, 3, -2, 5, 4})));
System.out.println(json(Solution57.solve(new int[]{7, 2, -3, -4, 3, -2, 5, 4}, 87)));
System.out.println(json(Solution58.solve("ccbaaac")));
System.out.println(json(Solution59.solve("ccbaaac", 2)));
System.out.println(json(Solution60.solve("}[){}(}()(")));
System.out.println(json(Solution61.solve("ccbaaac")));
System.out.println(json(Solution62.solve(new int[]{7, 2, -3, -4, 3, -2, 5, 4})));
System.out.println(json(Solution63.solve(new int[]{7, 2, -3, -4, 3, -2, 5, 4})));
System.out.println(json(Solution64.solve(new int[]{7, 2, -3, -4, 3, -2, 5, 4})));
System.out.println(json(Solution65.solve("/../.../../a/b/a/")));
System.out.println(json(Solution66.solve("0129238", 7)));
System.out.println(json(Solution67.solve("ccbaaac")));
System.out.println(json(Solution68.solve("onzllbaoayooxo")));
System.out.println(json(Solution69.solve("ccbaaac")));
System.out.println(json(Solution70.solve("ccbaaac", "babacc")));
System.out.println(json(Solution52.solve(new int[]{3, -2, 5, 0})));
System.out.println(json(Solution54.solve(new int[]{3, -2, 5, 0})));
System.out.println(json(Solution53.solve(new int[]{3, -2, 5, 0}, 2, 3)));
System.out.println(json(Solution55.solve(new int[]{3, -2, 5, 0}, 4)));
System.out.println(json(Solution56.solve(new int[]{3, -2, 5, 0})));
System.out.println(json(Solution57.solve(new int[]{3, -2, 5, 0}, 78)));
System.out.println(json(Solution58.solve("bbcc")));
System.out.println(json(Solution59.solve("bbcc", 3)));
System.out.println(json(Solution60.solve("{)[][")));
System.out.println(json(Solution61.solve("bbcc")));
System.out.println(json(Solution62.solve(new int[]{3, -2, 5, 0})));
System.out.println(json(Solution63.solve(new int[]{3, -2, 5, 0})));
System.out.println(json(Solution64.solve(new int[]{3, -2, 5, 0})));
System.out.println(json(Solution65.solve("/.././.././")));
System.out.println(json(Solution66.solve("2709", 3)));
System.out.println(json(Solution67.solve("bbcc")));
System.out.println(json(Solution68.solve("bzlzlaoz")));
System.out.println(json(Solution69.solve("bbcc")));
System.out.println(json(Solution70.solve("bbcc", "b")));
System.out.println(json(Solution52.solve(new int[]{7, 2, -3, -4, -1, -2, 1, -4})));
System.out.println(json(Solution54.solve(new int[]{7, 2, -3, -4, -1, -2, 1, -4})));
System.out.println(json(Solution53.solve(new int[]{7, 2, -3, -4, -1, -2, 1, -4}, 8, 8)));
System.out.println(json(Solution55.solve(new int[]{7, 2, -3, -4, -1, -2, 1, -4}, 2)));
System.out.println(json(Solution56.solve(new int[]{7, 2, -3, -4, -1, -2, 1, -4})));
System.out.println(json(Solution57.solve(new int[]{7, 2, -3, -4, -1, -2, 1, -4}, 84)));
System.out.println(json(Solution58.solve("bc")));
System.out.println(json(Solution59.solve("bc", 4)));
System.out.println(json(Solution60.solve("(}[)(){](}[")));
System.out.println(json(Solution61.solve("bc")));
System.out.println(json(Solution62.solve(new int[]{7, 2, -3, -4, -1, -2, 1, -4})));
System.out.println(json(Solution63.solve(new int[]{7, 2, -3, -4, -1, -2, 1, -4})));
System.out.println(json(Solution64.solve(new int[]{7, 2, -3, -4, -1, -2, 1, -4})));
System.out.println(json(Solution65.solve("/b/a/b/.//a/b/.")));
System.out.println(json(Solution66.solve("92503", 2)));
System.out.println(json(Solution67.solve("bc")));
System.out.println(json(Solution68.solve("yoozozo")));
System.out.println(json(Solution69.solve("bc")));
System.out.println(json(Solution70.solve("bc", "")));
System.out.println(json(Solution52.solve(new int[]{-3, 0, -1, -2})));
System.out.println(json(Solution54.solve(new int[]{-3, 0, -1, -2})));
System.out.println(json(Solution53.solve(new int[]{-3, 0, -1, -2}, 1, 4)));
System.out.println(json(Solution55.solve(new int[]{-3, 0, -1, -2}, 3)));
System.out.println(json(Solution56.solve(new int[]{-3, 0, -1, -2})));
System.out.println(json(Solution57.solve(new int[]{-3, 0, -1, -2}, 33)));
System.out.println(json(Solution58.solve("aab")));
System.out.println(json(Solution59.solve("aab", 5)));
System.out.println(json(Solution60.solve(")[][}[){")));
System.out.println(json(Solution61.solve("aab")));
System.out.println(json(Solution62.solve(new int[]{-3, 0, -1, -2})));
System.out.println(json(Solution63.solve(new int[]{-3, 0, -1, -2})));
System.out.println(json(Solution64.solve(new int[]{-3, 0, -1, -2})));
System.out.println(json(Solution65.solve("//a/..")));
System.out.println(json(Solution66.solve("0", 1)));
System.out.println(json(Solution67.solve("aab")));
System.out.println(json(Solution68.solve("oyloxnoba")));
System.out.println(json(Solution69.solve("aab")));
System.out.println(json(Solution70.solve("aab", "abca")));
System.out.println(json(Solution52.solve(new int[]{4, 3, 2})));
System.out.println(json(Solution54.solve(new int[]{4, 3, 2})));
System.out.println(json(Solution53.solve(new int[]{4, 3, 2}, 3, 3)));
System.out.println(json(Solution55.solve(new int[]{4, 3, 2}, 1)));
System.out.println(json(Solution56.solve(new int[]{4, 3, 2})));
System.out.println(json(Solution57.solve(new int[]{4, 3, 2}, 25)));
System.out.println(json(Solution58.solve("ccaccbc")));
System.out.println(json(Solution59.solve("ccaccbc", 1)));
System.out.println(json(Solution60.solve("}(}(")));
System.out.println(json(Solution61.solve("ccaccbc")));
System.out.println(json(Solution62.solve(new int[]{4, 3, 2})));
System.out.println(json(Solution63.solve(new int[]{4, 3, 2})));
System.out.println(json(Solution64.solve(new int[]{4, 3, 2})));
System.out.println(json(Solution65.solve("//...")));
}
static void batch5200() {
System.out.println(json(Solution66.solve("32727", 4)));
System.out.println(json(Solution67.solve("ccaccbc")));
System.out.println(json(Solution68.solve("yxnoyzyayayooobzyxoxbonzyzyl")));
System.out.println(json(Solution69.solve("ccaccbc")));
System.out.println(json(Solution70.solve("ccaccbc", "")));
System.out.println(json(Solution52.solve(new int[]{6, -3, -4})));
System.out.println(json(Solution54.solve(new int[]{6, -3, -4})));
System.out.println(json(Solution53.solve(new int[]{6, -3, -4}, 2, 3)));
System.out.println(json(Solution55.solve(new int[]{6, -3, -4}, 1)));
System.out.println(json(Solution56.solve(new int[]{6, -3, -4})));
System.out.println(json(Solution57.solve(new int[]{6, -3, -4}, 9)));
System.out.println(json(Solution58.solve("baaac")));
System.out.println(json(Solution59.solve("baaac", 5)));
System.out.println(json(Solution60.solve("")));
System.out.println(json(Solution61.solve("baaac")));
System.out.println(json(Solution62.solve(new int[]{6, -3, -4})));
System.out.println(json(Solution63.solve(new int[]{6, -3, -4})));
System.out.println(json(Solution64.solve(new int[]{6, -3, -4})));
System.out.println(json(Solution65.solve("/../a/b")));
System.out.println(json(Solution66.solve("8509", 4)));
System.out.println(json(Solution67.solve("baaac")));
System.out.println(json(Solution68.solve("nzoznx")));
System.out.println(json(Solution69.solve("baaac")));
System.out.println(json(Solution70.solve("baaac", "b")));
System.out.println(json(Solution52.solve(new int[]{7, 6, -3, -4})));
System.out.println(json(Solution54.solve(new int[]{7, 6, -3, -4})));
System.out.println(json(Solution53.solve(new int[]{7, 6, -3, -4}, 4, 4)));
System.out.println(json(Solution55.solve(new int[]{7, 6, -3, -4}, 2)));
System.out.println(json(Solution56.solve(new int[]{7, 6, -3, -4})));
System.out.println(json(Solution57.solve(new int[]{7, 6, -3, -4}, 44)));
System.out.println(json(Solution58.solve("ca")));
System.out.println(json(Solution59.solve("ca", 1)));
System.out.println(json(Solution60.solve("(}[)(}{")));
System.out.println(json(Solution61.solve("ca")));
System.out.println(json(Solution62.solve(new int[]{7, 6, -3, -4})));
System.out.println(json(Solution63.solve(new int[]{7, 6, -3, -4})));
System.out.println(json(Solution64.solve(new int[]{7, 6, -3, -4})));
System.out.println(json(Solution65.solve("/b/./../a//.../b")));
System.out.println(json(Solution66.solve("694963", 4)));
System.out.println(json(Solution67.solve("ca")));
System.out.println(json(Solution68.solve("nobabzo")));
System.out.println(json(Solution69.solve("ca")));
System.out.println(json(Solution70.solve("ca", "cccbb")));
System.out.println(json(Solution52.solve(new int[]{0, 3, -2})));
System.out.println(json(Solution54.solve(new int[]{0, 3, -2})));
System.out.println(json(Solution53.solve(new int[]{0, 3, -2}, 2, 2)));
System.out.println(json(Solution55.solve(new int[]{0, 3, -2}, 2)));
System.out.println(json(Solution56.solve(new int[]{0, 3, -2})));
System.out.println(json(Solution57.solve(new int[]{0, 3, -2}, 24)));
System.out.println(json(Solution58.solve("")));
System.out.println(json(Solution59.solve("", 3)));
System.out.println(json(Solution60.solve("[)(]{][")));
System.out.println(json(Solution61.solve("")));
System.out.println(json(Solution62.solve(new int[]{0, 3, -2})));
System.out.println(json(Solution63.solve(new int[]{0, 3, -2})));
System.out.println(json(Solution64.solve(new int[]{0, 3, -2})));
System.out.println(json(Solution65.solve("/b/...//.../")));
System.out.println(json(Solution66.solve("45", 2)));
System.out.println(json(Solution67.solve("")));
System.out.println(json(Solution68.solve("nolzl")));
System.out.println(json(Solution69.solve("")));
System.out.println(json(Solution70.solve("", "a")));
System.out.println(json(Solution52.solve(new int[]{-4})));
System.out.println(json(Solution54.solve(new int[]{-4})));
System.out.println(json(Solution53.solve(new int[]{-4}, 1, 1)));
System.out.println(json(Solution55.solve(new int[]{-4}, 1)));
System.out.println(json(Solution56.solve(new int[]{-4})));
System.out.println(json(Solution57.solve(new int[]{-4}, 22)));
System.out.println(json(Solution58.solve("babbaccb")));
System.out.println(json(Solution59.solve("babbaccb", 5)));
System.out.println(json(Solution60.solve("{")));
System.out.println(json(Solution61.solve("babbaccb")));
System.out.println(json(Solution62.solve(new int[]{-4})));
System.out.println(json(Solution63.solve(new int[]{-4})));
System.out.println(json(Solution64.solve(new int[]{-4})));
System.out.println(json(Solution65.solve("/b/.../.././../...")));
System.out.println(json(Solution66.solve("10", 0)));
System.out.println(json(Solution67.solve("babbaccb")));
System.out.println(json(Solution68.solve("xbzblbobolaozllnolooloonl")));
System.out.println(json(Solution69.solve("babbaccb")));
System.out.println(json(Solution70.solve("babbaccb", "caacb")));
System.out.println(json(Solution52.solve(new int[]{})));
System.out.println(json(Solution54.solve(new int[]{})));
System.out.println(json(Solution57.solve(new int[]{}, 90)));
System.out.println(json(Solution58.solve("cba")));
System.out.println(json(Solution59.solve("cba", 1)));
System.out.println(json(Solution60.solve("{")));
System.out.println(json(Solution61.solve("cba")));
System.out.println(json(Solution62.solve(new int[]{})));
System.out.println(json(Solution63.solve(new int[]{})));
System.out.println(json(Solution64.solve(new int[]{})));
System.out.println(json(Solution65.solve("/b")));
System.out.println(json(Solution66.solve("8747492", 5)));
System.out.println(json(Solution67.solve("cba")));
System.out.println(json(Solution68.solve("abzba")));
System.out.println(json(Solution69.solve("cba")));
System.out.println(json(Solution70.solve("cba", "ccabbb")));
System.out.println(json(Solution52.solve(new int[]{-2})));
System.out.println(json(Solution54.solve(new int[]{-2})));
System.out.println(json(Solution53.solve(new int[]{-2}, 1, 1)));
}
static void batch5300() {
System.out.println(json(Solution55.solve(new int[]{-2}, 1)));
System.out.println(json(Solution56.solve(new int[]{-2})));
System.out.println(json(Solution57.solve(new int[]{-2}, 9)));
System.out.println(json(Solution58.solve("caacccb")));
System.out.println(json(Solution59.solve("caacccb", 3)));
System.out.println(json(Solution60.solve("")));
System.out.println(json(Solution61.solve("caacccb")));
System.out.println(json(Solution62.solve(new int[]{-2})));
System.out.println(json(Solution63.solve(new int[]{-2})));
System.out.println(json(Solution64.solve(new int[]{-2})));
System.out.println(json(Solution65.solve("/.././../a")));
System.out.println(json(Solution66.solve("5", 0)));
System.out.println(json(Solution67.solve("caacccb")));
System.out.println(json(Solution68.solve("yxbxnlnooaoanxlx")));
System.out.println(json(Solution69.solve("caacccb")));
System.out.println(json(Solution70.solve("caacccb", "b")));
System.out.println(json(Solution52.solve(new int[]{7, 2, 1, 4, 7, -2, 5, -4})));
System.out.println(json(Solution54.solve(new int[]{7, 2, 1, 4, 7, -2, 5, -4})));
System.out.println(json(Solution53.solve(new int[]{7, 2, 1, 4, 7, -2, 5, -4}, 2, 5)));
System.out.println(json(Solution55.solve(new int[]{7, 2, 1, 4, 7, -2, 5, -4}, 4)));
System.out.println(json(Solution56.solve(new int[]{7, 2, 1, 4, 7, -2, 5, -4})));
System.out.println(json(Solution57.solve(new int[]{7, 2, 1, 4, 7, -2, 5, -4}, 10)));
System.out.println(json(Solution58.solve("")));
System.out.println(json(Solution59.solve("", 4)));
System.out.println(json(Solution60.solve("{](}(){)(")));
System.out.println(json(Solution61.solve("")));
System.out.println(json(Solution62.solve(new int[]{7, 2, 1, 4, 7, -2, 5, -4})));
System.out.println(json(Solution63.solve(new int[]{7, 2, 1, 4, 7, -2, 5, -4})));
System.out.println(json(Solution64.solve(new int[]{7, 2, 1, 4, 7, -2, 5, -4})));
System.out.println(json(Solution65.solve("/")));
System.out.println(json(Solution66.solve("709", 2)));
System.out.println(json(Solution67.solve("")));
System.out.println(json(Solution68.solve("babzllblb")));
System.out.println(json(Solution69.solve("")));
System.out.println(json(Solution70.solve("", "cac")));
System.out.println(json(Solution52.solve(new int[]{6, -3, 0, -1, -2})));
System.out.println(json(Solution54.solve(new int[]{6, -3, 0, -1, -2})));
System.out.println(json(Solution53.solve(new int[]{6, -3, 0, -1, -2}, 5, 5)));
System.out.println(json(Solution55.solve(new int[]{6, -3, 0, -1, -2}, 5)));
System.out.println(json(Solution56.solve(new int[]{6, -3, 0, -1, -2})));
System.out.println(json(Solution57.solve(new int[]{6, -3, 0, -1, -2}, 19)));
System.out.println(json(Solution58.solve("c")));
System.out.println(json(Solution59.solve("c", 4)));
System.out.println(json(Solution60.solve("){)[)[}{)[")));
System.out.println(json(Solution61.solve("c")));
System.out.println(json(Solution62.solve(new int[]{6, -3, 0, -1, -2})));
System.out.println(json(Solution63.solve(new int[]{6, -3, 0, -1, -2})));
System.out.println(json(Solution64.solve(new int[]{6, -3, 0, -1, -2})));
System.out.println(json(Solution65.solve("/../.../b/a/b")));
System.out.println(json(Solution66.solve("8705894", 1)));
System.out.println(json(Solution67.solve("c")));
System.out.println(json(Solution68.solve("z")));
System.out.println(json(Solution69.solve("c")));
System.out.println(json(Solution70.solve("c", "a")));
System.out.println(json(Solution52.solve(new int[]{})));
System.out.println(json(Solution54.solve(new int[]{})));
System.out.println(json(Solution57.solve(new int[]{}, 13)));
System.out.println(json(Solution58.solve("bcacbaca")));
System.out.println(json(Solution59.solve("bcacbaca", 1)));
System.out.println(json(Solution60.solve("}{)(")));
System.out.println(json(Solution61.solve("bcacbaca")));
System.out.println(json(Solution62.solve(new int[]{})));
System.out.println(json(Solution63.solve(new int[]{})));
System.out.println(json(Solution64.solve(new int[]{})));
System.out.println(json(Solution65.solve("/")));
System.out.println(json(Solution66.solve("296369", 5)));
System.out.println(json(Solution67.solve("bcacbaca")));
System.out.println(json(Solution68.solve("lznlloyzbonlnxlayznllxlo")));
System.out.println(json(Solution69.solve("bcacbaca")));
System.out.println(json(Solution70.solve("bcacbaca", "bbacc")));
System.out.println(json(Solution52.solve(new int[]{5, -4, 7, 2, 1, 0, 3, 2})));
System.out.println(json(Solution54.solve(new int[]{5, -4, 7, 2, 1, 0, 3, 2})));
System.out.println(json(Solution53.solve(new int[]{5, -4, 7, 2, 1, 0, 3, 2}, 5, 8)));
System.out.println(json(Solution55.solve(new int[]{5, -4, 7, 2, 1, 0, 3, 2}, 7)));
System.out.println(json(Solution56.solve(new int[]{5, -4, 7, 2, 1, 0, 3, 2})));
System.out.println(json(Solution57.solve(new int[]{5, -4, 7, 2, 1, 0, 3, 2}, 89)));
System.out.println(json(Solution58.solve("ccabacc")));
System.out.println(json(Solution59.solve("ccabacc", 2)));
System.out.println(json(Solution60.solve(")(][}(}[")));
System.out.println(json(Solution61.solve("ccabacc")));
System.out.println(json(Solution62.solve(new int[]{5, -4, 7, 2, 1, 0, 3, 2})));
System.out.println(json(Solution63.solve(new int[]{5, -4, 7, 2, 1, 0, 3, 2})));
System.out.println(json(Solution64.solve(new int[]{5, -4, 7, 2, 1, 0, 3, 2})));
System.out.println(json(Solution65.solve("//./b")));
System.out.println(json(Solution66.solve("012703", 1)));
System.out.println(json(Solution67.solve("ccabacc")));
System.out.println(json(Solution68.solve("loylnly")));
System.out.println(json(Solution69.solve("ccabacc")));
System.out.println(json(Solution70.solve("ccabacc", "aacaaa")));
System.out.println(json(Solution52.solve(new int[]{3, 6, 5, 4, -1, 2})));
System.out.println(json(Solution54.solve(new int[]{3, 6, 5, 4, -1, 2})));
System.out.println(json(Solution53.solve(new int[]{3, 6, 5, 4, -1, 2}, 6, 6)));
System.out.println(json(Solution55.solve(new int[]{3, 6, 5, 4, -1, 2}, 6)));
System.out.println(json(Solution56.solve(new int[]{3, 6, 5, 4, -1, 2})));
System.out.println(json(Solution57.solve(new int[]{3, 6, 5, 4, -1, 2}, 94)));
System.out.println(json(Solution58.solve("ccacbb")));
System.out.println(json(Solution59.solve("ccacbb", 5)));
System.out.println(json(Solution60.solve("()[)()[}[")));
System.out.println(json(Solution61.solve("ccacbb")));
System.out.println(json(Solution62.solve(new int[]{3, 6, 5, 4, -1, 2})));
}
static void batch5400() {
System.out.println(json(Solution63.solve(new int[]{3, 6, 5, 4, -1, 2})));
System.out.println(json(Solution64.solve(new int[]{3, 6, 5, 4, -1, 2})));
System.out.println(json(Solution65.solve("//a//./..")));
System.out.println(json(Solution66.solve("490", 1)));
System.out.println(json(Solution67.solve("ccacbb")));
System.out.println(json(Solution68.solve("llzyxyzyxlaylbanobzoolzoabzoxnly")));
System.out.println(json(Solution69.solve("ccacbb")));
System.out.println(json(Solution70.solve("ccacbb", "bbacacca")));
System.out.println(json(Solution52.solve(new int[]{})));
System.out.println(json(Solution54.solve(new int[]{})));
System.out.println(json(Solution57.solve(new int[]{}, 24)));
System.out.println(json(Solution58.solve("cccbbcc")));
System.out.println(json(Solution59.solve("cccbbcc", 4)));
System.out.println(json(Solution60.solve("{][]{}[}(){")));
System.out.println(json(Solution61.solve("cccbbcc")));
System.out.println(json(Solution62.solve(new int[]{})));
System.out.println(json(Solution63.solve(new int[]{})));
System.out.println(json(Solution64.solve(new int[]{})));
System.out.println(json(Solution65.solve("//...//./b/a/..")));
System.out.println(json(Solution66.solve("61436", 5)));
System.out.println(json(Solution67.solve("cccbbcc")));
System.out.println(json(Solution68.solve("zyanxnznzylbxyoyl")));
System.out.println(json(Solution69.solve("cccbbcc")));
System.out.println(json(Solution70.solve("cccbbcc", "aabaaba")));
System.out.println(json(Solution52.solve(new int[]{-3, 4, -1, 6})));
System.out.println(json(Solution54.solve(new int[]{-3, 4, -1, 6})));
System.out.println(json(Solution53.solve(new int[]{-3, 4, -1, 6}, 4, 4)));
System.out.println(json(Solution55.solve(new int[]{-3, 4, -1, 6}, 2)));
System.out.println(json(Solution56.solve(new int[]{-3, 4, -1, 6})));
System.out.println(json(Solution57.solve(new int[]{-3, 4, -1, 6}, 64)));
System.out.println(json(Solution58.solve("bccc")));
System.out.println(json(Solution59.solve("bccc", 3)));
System.out.println(json(Solution60.solve("(][}{}{)[}(")));
System.out.println(json(Solution61.solve("bccc")));
System.out.println(json(Solution62.solve(new int[]{-3, 4, -1, 6})));
System.out.println(json(Solution63.solve(new int[]{-3, 4, -1, 6})));
System.out.println(json(Solution64.solve(new int[]{-3, 4, -1, 6})));
System.out.println(json(Solution65.solve("//...")));
System.out.println(json(Solution66.solve("949054", 0)));
System.out.println(json(Solution67.solve("bccc")));
System.out.println(json(Solution68.solve("alllabolxbanxbz")));
System.out.println(json(Solution69.solve("bccc")));
System.out.println(json(Solution70.solve("bccc", "ba")));
System.out.println(json(Solution52.solve(new int[]{-4, -1, -2, -3, 0, 7, 6, 5, 0})));
System.out.println(json(Solution54.solve(new int[]{-4, -1, -2, -3, 0, 7, 6, 5, 0})));
System.out.println(json(Solution53.solve(new int[]{-4, -1, -2, -3, 0, 7, 6, 5, 0}, 1, 9)));
System.out.println(json(Solution55.solve(new int[]{-4, -1, -2, -3, 0, 7, 6, 5, 0}, 1)));
System.out.println(json(Solution56.solve(new int[]{-4, -1, -2, -3, 0, 7, 6, 5, 0})));
System.out.println(json(Solution57.solve(new int[]{-4, -1, -2, -3, 0, 7, 6, 5, 0}, 86)));
System.out.println(json(Solution58.solve("bbbbacab")));
System.out.println(json(Solution59.solve("bbbbacab", 5)));
System.out.println(json(Solution60.solve("[]{]{]{)(")));
System.out.println(json(Solution61.solve("bbbbacab")));
System.out.println(json(Solution62.solve(new int[]{-4, -1, -2, -3, 0, 7, 6, 5, 0})));
System.out.println(json(Solution63.solve(new int[]{-4, -1, -2, -3, 0, 7, 6, 5, 0})));
System.out.println(json(Solution64.solve(new int[]{-4, -1, -2, -3, 0, 7, 6, 5, 0})));
System.out.println(json(Solution65.solve("/../.")));
System.out.println(json(Solution66.solve("12521", 2)));
System.out.println(json(Solution67.solve("bbbbacab")));
System.out.println(json(Solution68.solve("lolxbzbxlzlzyoba")));
System.out.println(json(Solution69.solve("bbbbacab")));
System.out.println(json(Solution70.solve("bbbbacab", "bca")));
System.out.println(json(Solution52.solve(new int[]{3, -2, 1, -4})));
System.out.println(json(Solution54.solve(new int[]{3, -2, 1, -4})));
System.out.println(json(Solution53.solve(new int[]{3, -2, 1, -4}, 2, 3)));
System.out.println(json(Solution55.solve(new int[]{3, -2, 1, -4}, 4)));
System.out.println(json(Solution56.solve(new int[]{3, -2, 1, -4})));
System.out.println(json(Solution57.solve(new int[]{3, -2, 1, -4}, 50)));
System.out.println(json(Solution58.solve("bbcc")));
System.out.println(json(Solution59.solve("bbcc", 5)));
System.out.println(json(Solution60.solve("[)(]{")));
System.out.println(json(Solution61.solve("bbcc")));
System.out.println(json(Solution62.solve(new int[]{3, -2, 1, -4})));
System.out.println(json(Solution63.solve(new int[]{3, -2, 1, -4})));
System.out.println(json(Solution64.solve(new int[]{3, -2, 1, -4})));
System.out.println(json(Solution65.solve("//.//...//.")));
System.out.println(json(Solution66.solve("327632", 2)));
System.out.println(json(Solution67.solve("bbcc")));
System.out.println(json(Solution68.solve("ananxyxololol")));
System.out.println(json(Solution69.solve("bbcc")));
System.out.println(json(Solution70.solve("bbcc", "aaccc")));
System.out.println(json(Solution52.solve(new int[]{3, 2, -3, 0, -1, -2})));
System.out.println(json(Solution54.solve(new int[]{3, 2, -3, 0, -1, -2})));
System.out.println(json(Solution53.solve(new int[]{3, 2, -3, 0, -1, -2}, 1, 4)));
System.out.println(json(Solution55.solve(new int[]{3, 2, -3, 0, -1, -2}, 5)));
System.out.println(json(Solution56.solve(new int[]{3, 2, -3, 0, -1, -2})));
System.out.println(json(Solution57.solve(new int[]{3, 2, -3, 0, -1, -2}, 9)));
System.out.println(json(Solution58.solve("abbccaa")));
System.out.println(json(Solution59.solve("abbccaa", 1)));
System.out.println(json(Solution60.solve("}{](")));
System.out.println(json(Solution61.solve("abbccaa")));
System.out.println(json(Solution62.solve(new int[]{3, 2, -3, 0, -1, -2})));
System.out.println(json(Solution63.solve(new int[]{3, 2, -3, 0, -1, -2})));
System.out.println(json(Solution64.solve(new int[]{3, 2, -3, 0, -1, -2})));
System.out.println(json(Solution65.solve("/")));
System.out.println(json(Solution66.solve("85470", 1)));
System.out.println(json(Solution67.solve("abbccaa")));
System.out.println(json(Solution68.solve("zylblbxbanayxlayz")));
System.out.println(json(Solution69.solve("abbccaa")));
System.out.println(json(Solution70.solve("abbccaa", "bbbacabc")));
}
static void batch5500() {
System.out.println(json(Solution52.solve(new int[]{-2, -3, 0, 7, -2, -3, 0, -1, 2})));
System.out.println(json(Solution54.solve(new int[]{-2, -3, 0, 7, -2, -3, 0, -1, 2})));
System.out.println(json(Solution53.solve(new int[]{-2, -3, 0, 7, -2, -3, 0, -1, 2}, 7, 9)));
System.out.println(json(Solution55.solve(new int[]{-2, -3, 0, 7, -2, -3, 0, -1, 2}, 7)));
System.out.println(json(Solution56.solve(new int[]{-2, -3, 0, 7, -2, -3, 0, -1, 2})));
System.out.println(json(Solution57.solve(new int[]{-2, -3, 0, 7, -2, -3, 0, -1, 2}, 63)));
System.out.println(json(Solution58.solve("b")));
System.out.println(json(Solution59.solve("b", 3)));
System.out.println(json(Solution60.solve("][)[)(")));
System.out.println(json(Solution61.solve("b")));
System.out.println(json(Solution62.solve(new int[]{-2, -3, 0, 7, -2, -3, 0, -1, 2})));
System.out.println(json(Solution63.solve(new int[]{-2, -3, 0, 7, -2, -3, 0, -1, 2})));
System.out.println(json(Solution64.solve(new int[]{-2, -3, 0, 7, -2, -3, 0, -1, 2})));
System.out.println(json(Solution65.solve("/b/.../../.../b/.../")));
System.out.println(json(Solution66.solve("4907254", 3)));
System.out.println(json(Solution67.solve("b")));
System.out.println(json(Solution68.solve("o")));
System.out.println(json(Solution69.solve("b")));
System.out.println(json(Solution70.solve("b", "acaaa")));
System.out.println(json(Solution52.solve(new int[]{1, 0, 7, -2})));
System.out.println(json(Solution54.solve(new int[]{1, 0, 7, -2})));
System.out.println(json(Solution53.solve(new int[]{1, 0, 7, -2}, 1, 4)));
System.out.println(json(Solution55.solve(new int[]{1, 0, 7, -2}, 3)));
System.out.println(json(Solution56.solve(new int[]{1, 0, 7, -2})));
System.out.println(json(Solution57.solve(new int[]{1, 0, 7, -2}, 25)));
System.out.println(json(Solution58.solve("abacbac")));
System.out.println(json(Solution59.solve("abacbac", 4)));
System.out.println(json(Solution60.solve("")));
System.out.println(json(Solution61.solve("abacbac")));
System.out.println(json(Solution62.solve(new int[]{1, 0, 7, -2})));
System.out.println(json(Solution63.solve(new int[]{1, 0, 7, -2})));
System.out.println(json(Solution64.solve(new int[]{1, 0, 7, -2})));
System.out.println(json(Solution65.solve("/b/a/../.../b/a")));
System.out.println(json(Solution66.solve("78981", 4)));
System.out.println(json(Solution67.solve("abacbac")));
System.out.println(json(Solution68.solve("ylbayxyonxyaloylboyzlaoxnxolo")));
System.out.println(json(Solution69.solve("abacbac")));
System.out.println(json(Solution70.solve("abacbac", "cacabac")));
System.out.println(json(Solution52.solve(new int[]{6, 1, -4})));
System.out.println(json(Solution54.solve(new int[]{6, 1, -4})));
System.out.println(json(Solution53.solve(new int[]{6, 1, -4}, 1, 1)));
System.out.println(json(Solution55.solve(new int[]{6, 1, -4}, 2)));
System.out.println(json(Solution56.solve(new int[]{6, 1, -4})));
System.out.println(json(Solution57.solve(new int[]{6, 1, -4}, 28)));
System.out.println(json(Solution58.solve("ab")));
System.out.println(json(Solution59.solve("ab", 4)));
System.out.println(json(Solution60.solve("(){){){][)(")));
System.out.println(json(Solution61.solve("ab")));
System.out.println(json(Solution62.solve(new int[]{6, 1, -4})));
System.out.println(json(Solution63.solve(new int[]{6, 1, -4})));
System.out.println(json(Solution64.solve(new int[]{6, 1, -4})));
System.out.println(json(Solution65.solve("/b/a/b/a//.//a")));
System.out.println(json(Solution66.solve("9474", 2)));
System.out.println(json(Solution67.solve("ab")));
System.out.println(json(Solution68.solve("zbzbzyanx")));
System.out.println(json(Solution69.solve("ab")));
System.out.println(json(Solution70.solve("ab", "acba")));
System.out.println(json(Solution52.solve(new int[]{0, 3, 2})));
System.out.println(json(Solution54.solve(new int[]{0, 3, 2})));
System.out.println(json(Solution53.solve(new int[]{0, 3, 2}, 1, 2)));
System.out.println(json(Solution55.solve(new int[]{0, 3, 2}, 1)));
System.out.println(json(Solution56.solve(new int[]{0, 3, 2})));
System.out.println(json(Solution57.solve(new int[]{0, 3, 2}, 71)));
System.out.println(json(Solution58.solve("a")));
System.out.println(json(Solution59.solve("a", 4)));
System.out.println(json(Solution60.solve("){)(](]()(")));
System.out.println(json(Solution61.solve("a")));
System.out.println(json(Solution62.solve(new int[]{0, 3, 2})));
System.out.println(json(Solution63.solve(new int[]{0, 3, 2})));
System.out.println(json(Solution64.solve(new int[]{0, 3, 2})));
System.out.println(json(Solution65.solve("//a/.././/.")));
System.out.println(json(Solution66.solve("16941", 2)));
System.out.println(json(Solution67.solve("a")));
System.out.println(json(Solution68.solve("yaoxbzlolabzox")));
System.out.println(json(Solution69.solve("a")));
System.out.println(json(Solution70.solve("a", "caacbbba")));
System.out.println(json(Solution52.solve(new int[]{-4, -1, -2})));
System.out.println(json(Solution54.solve(new int[]{-4, -1, -2})));
System.out.println(json(Solution53.solve(new int[]{-4, -1, -2}, 3, 3)));
System.out.println(json(Solution55.solve(new int[]{-4, -1, -2}, 1)));
System.out.println(json(Solution56.solve(new int[]{-4, -1, -2})));
System.out.println(json(Solution57.solve(new int[]{-4, -1, -2}, 37)));
System.out.println(json(Solution58.solve("abb")));
System.out.println(json(Solution59.solve("abb", 1)));
System.out.println(json(Solution60.solve("")));
System.out.println(json(Solution61.solve("abb")));
System.out.println(json(Solution62.solve(new int[]{-4, -1, -2})));
System.out.println(json(Solution63.solve(new int[]{-4, -1, -2})));
System.out.println(json(Solution64.solve(new int[]{-4, -1, -2})));
System.out.println(json(Solution65.solve("/../.../..")));
System.out.println(json(Solution66.solve("2309456", 7)));
System.out.println(json(Solution67.solve("abb")));
System.out.println(json(Solution68.solve("oyaoanlnooobxyonxoanonllayx")));
System.out.println(json(Solution69.solve("abb")));
System.out.println(json(Solution70.solve("abb", "bcabcbc")));
System.out.println(json(Solution52.solve(new int[]{5, 4, -1, 6, 5, 0, -1, -2})));
System.out.println(json(Solution54.solve(new int[]{5, 4, -1, 6, 5, 0, -1, -2})));
System.out.println(json(Solution53.solve(new int[]{5, 4, -1, 6, 5, 0, -1, -2}, 7, 8)));
System.out.println(json(Solution55.solve(new int[]{5, 4, -1, 6, 5, 0, -1, -2}, 1)));
System.out.println(json(Solution56.solve(new int[]{5, 4, -1, 6, 5, 0, -1, -2})));
}
static void batch5600() {
System.out.println(json(Solution57.solve(new int[]{5, 4, -1, 6, 5, 0, -1, -2}, 31)));
System.out.println(json(Solution58.solve("acbcc")));
System.out.println(json(Solution59.solve("acbcc", 5)));
System.out.println(json(Solution60.solve("]()(]{](][")));
System.out.println(json(Solution61.solve("acbcc")));
System.out.println(json(Solution62.solve(new int[]{5, 4, -1, 6, 5, 0, -1, -2})));
System.out.println(json(Solution63.solve(new int[]{5, 4, -1, 6, 5, 0, -1, -2})));
System.out.println(json(Solution64.solve(new int[]{5, 4, -1, 6, 5, 0, -1, -2})));
System.out.println(json(Solution65.solve("/b")));
System.out.println(json(Solution66.solve("45830", 5)));
System.out.println(json(Solution67.solve("acbcc")));
System.out.println(json(Solution68.solve("ayxooyzbzbanxlanxlanxbxllyly")));
System.out.println(json(Solution69.solve("acbcc")));
System.out.println(json(Solution70.solve("acbcc", "acbcaab")));
System.out.println(json(Solution52.solve(new int[]{-4, 3, -2, 1, 0, 7, 6, -3, 4})));
System.out.println(json(Solution54.solve(new int[]{-4, 3, -2, 1, 0, 7, 6, -3, 4})));
System.out.println(json(Solution53.solve(new int[]{-4, 3, -2, 1, 0, 7, 6, -3, 4}, 7, 8)));
System.out.println(json(Solution55.solve(new int[]{-4, 3, -2, 1, 0, 7, 6, -3, 4}, 8)));
System.out.println(json(Solution56.solve(new int[]{-4, 3, -2, 1, 0, 7, 6, -3, 4})));
System.out.println(json(Solution57.solve(new int[]{-4, 3, -2, 1, 0, 7, 6, -3, 4}, 24)));
System.out.println(json(Solution58.solve("ccabbc")));
System.out.println(json(Solution59.solve("ccabbc", 4)));
System.out.println(json(Solution60.solve("[]{}{)()[}(")));
System.out.println(json(Solution61.solve("ccabbc")));
System.out.println(json(Solution62.solve(new int[]{-4, 3, -2, 1, 0, 7, 6, -3, 4})));
System.out.println(json(Solution63.solve(new int[]{-4, 3, -2, 1, 0, 7, 6, -3, 4})));
System.out.println(json(Solution64.solve(new int[]{-4, 3, -2, 1, 0, 7, 6, -3, 4})));
System.out.println(json(Solution65.solve("/.././../a/")));
System.out.println(json(Solution66.solve("61052", 5)));
System.out.println(json(Solution67.solve("ccabbc")));
System.out.println(json(Solution68.solve("xyonoy")));
System.out.println(json(Solution69.solve("ccabbc")));
System.out.println(json(Solution70.solve("ccabbc", "c")));
System.out.println(json(Solution52.solve(new int[]{0, 7, -2, 1, 0})));
System.out.println(json(Solution54.solve(new int[]{0, 7, -2, 1, 0})));
System.out.println(json(Solution53.solve(new int[]{0, 7, -2, 1, 0}, 3, 3)));
System.out.println(json(Solution55.solve(new int[]{0, 7, -2, 1, 0}, 3)));
System.out.println(json(Solution56.solve(new int[]{0, 7, -2, 1, 0})));
System.out.println(json(Solution57.solve(new int[]{0, 7, -2, 1, 0}, 17)));
System.out.println(json(Solution58.solve("bccac")));
System.out.println(json(Solution59.solve("bccac", 4)));
System.out.println(json(Solution60.solve(")(](")));
System.out.println(json(Solution61.solve("bccac")));
System.out.println(json(Solution62.solve(new int[]{0, 7, -2, 1, 0})));
System.out.println(json(Solution63.solve(new int[]{0, 7, -2, 1, 0})));
System.out.println(json(Solution64.solve(new int[]{0, 7, -2, 1, 0})));
System.out.println(json(Solution65.solve("/b/.//...//.//a")));
System.out.println(json(Solution66.solve("727", 2)));
System.out.println(json(Solution67.solve("bccac")));
System.out.println(json(Solution68.solve("noy")));
System.out.println(json(Solution69.solve("bccac")));
System.out.println(json(Solution70.solve("bccac", "bb")));
System.out.println(json(Solution52.solve(new int[]{5, -4, -1, 6})));
System.out.println(json(Solution54.solve(new int[]{5, -4, -1, 6})));
System.out.println(json(Solution53.solve(new int[]{5, -4, -1, 6}, 2, 4)));
System.out.println(json(Solution55.solve(new int[]{5, -4, -1, 6}, 4)));
System.out.println(json(Solution56.solve(new int[]{5, -4, -1, 6})));
System.out.println(json(Solution57.solve(new int[]{5, -4, -1, 6}, 54)));
System.out.println(json(Solution58.solve("cc")));
System.out.println(json(Solution59.solve("cc", 5)));
System.out.println(json(Solution60.solve("[}{){")));
System.out.println(json(Solution61.solve("cc")));
System.out.println(json(Solution62.solve(new int[]{5, -4, -1, 6})));
System.out.println(json(Solution63.solve(new int[]{5, -4, -1, 6})));
System.out.println(json(Solution64.solve(new int[]{5, -4, -1, 6})));
System.out.println(json(Solution65.solve("/b/./b/.../b")));
System.out.println(json(Solution66.solve("0549", 3)));
System.out.println(json(Solution67.solve("cc")));
System.out.println(json(Solution68.solve("nxyzlxboyxbaboblb")));
System.out.println(json(Solution69.solve("cc")));
System.out.println(json(Solution70.solve("cc", "bbabcba")));
System.out.println(json(Solution52.solve(new int[]{0, 7, 6})));
System.out.println(json(Solution54.solve(new int[]{0, 7, 6})));
System.out.println(json(Solution53.solve(new int[]{0, 7, 6}, 1, 1)));
System.out.println(json(Solution55.solve(new int[]{0, 7, 6}, 3)));
System.out.println(json(Solution56.solve(new int[]{0, 7, 6})));
System.out.println(json(Solution57.solve(new int[]{0, 7, 6}, 26)));
System.out.println(json(Solution58.solve("ac")));
System.out.println(json(Solution59.solve("ac", 2)));
System.out.println(json(Solution60.solve("{")));
System.out.println(json(Solution61.solve("ac")));
System.out.println(json(Solution62.solve(new int[]{0, 7, 6})));
System.out.println(json(Solution63.solve(new int[]{0, 7, 6})));
System.out.println(json(Solution64.solve(new int[]{0, 7, 6})));
System.out.println(json(Solution65.solve("/")));
System.out.println(json(Solution66.solve("183012", 4)));
System.out.println(json(Solution67.solve("ac")));
System.out.println(json(Solution68.solve("xl")));
System.out.println(json(Solution69.solve("ac")));
System.out.println(json(Solution70.solve("ac", "")));
System.out.println(json(Solution52.solve(new int[]{-3, 0})));
System.out.println(json(Solution54.solve(new int[]{-3, 0})));
System.out.println(json(Solution53.solve(new int[]{-3, 0}, 2, 2)));
System.out.println(json(Solution55.solve(new int[]{-3, 0}, 2)));
System.out.println(json(Solution56.solve(new int[]{-3, 0})));
System.out.println(json(Solution57.solve(new int[]{-3, 0}, 52)));
System.out.println(json(Solution58.solve("ba")));
System.out.println(json(Solution59.solve("ba", 5)));
System.out.println(json(Solution60.solve("{](")));
System.out.println(json(Solution61.solve("ba")));
}
static void batch5700() {
System.out.println(json(Solution62.solve(new int[]{-3, 0})));
System.out.println(json(Solution63.solve(new int[]{-3, 0})));
System.out.println(json(Solution64.solve(new int[]{-3, 0})));
System.out.println(json(Solution65.solve("/")));
System.out.println(json(Solution66.solve("761", 2)));
System.out.println(json(Solution67.solve("ba")));
System.out.println(json(Solution68.solve("oalzolboyanxnzbxooo")));
System.out.println(json(Solution69.solve("ba")));
System.out.println(json(Solution70.solve("ba", "a")));
System.out.println(json(Solution52.solve(new int[]{6})));
System.out.println(json(Solution54.solve(new int[]{6})));
System.out.println(json(Solution53.solve(new int[]{6}, 1, 1)));
System.out.println(json(Solution55.solve(new int[]{6}, 1)));
System.out.println(json(Solution56.solve(new int[]{6})));
System.out.println(json(Solution57.solve(new int[]{6}, 12)));
System.out.println(json(Solution58.solve("")));
System.out.println(json(Solution59.solve("", 3)));
System.out.println(json(Solution60.solve("[][]{](][][")));
System.out.println(json(Solution61.solve("")));
System.out.println(json(Solution62.solve(new int[]{6})));
System.out.println(json(Solution63.solve(new int[]{6})));
System.out.println(json(Solution64.solve(new int[]{6})));
System.out.println(json(Solution65.solve("/../.../b/./b/...")));
System.out.println(json(Solution66.solve("967290", 1)));
System.out.println(json(Solution67.solve("")));
System.out.println(json(Solution68.solve("zyzbalonzy")));
System.out.println(json(Solution69.solve("")));
System.out.println(json(Solution70.solve("", "b")));
System.out.println(json(Solution52.solve(new int[]{6, 5, 4, 3, 2})));
System.out.println(json(Solution54.solve(new int[]{6, 5, 4, 3, 2})));
System.out.println(json(Solution53.solve(new int[]{6, 5, 4, 3, 2}, 1, 1)));
System.out.println(json(Solution55.solve(new int[]{6, 5, 4, 3, 2}, 5)));
System.out.println(json(Solution56.solve(new int[]{6, 5, 4, 3, 2})));
System.out.println(json(Solution57.solve(new int[]{6, 5, 4, 3, 2}, 84)));
System.out.println(json(Solution58.solve("bcabaccc")));
System.out.println(json(Solution59.solve("bcabaccc", 2)));
System.out.println(json(Solution60.solve("{)(")));
System.out.println(json(Solution61.solve("bcabaccc")));
System.out.println(json(Solution62.solve(new int[]{6, 5, 4, 3, 2})));
System.out.println(json(Solution63.solve(new int[]{6, 5, 4, 3, 2})));
System.out.println(json(Solution64.solve(new int[]{6, 5, 4, 3, 2})));
System.out.println(json(Solution65.solve("//a//a")));
System.out.println(json(Solution66.solve("309038", 3)));
System.out.println(json(Solution67.solve("bcabaccc")));
System.out.println(json(Solution68.solve("")));
System.out.println(json(Solution69.solve("bcabaccc")));
System.out.println(json(Solution70.solve("bcabaccc", "cbbacaba")));
System.out.println(json(Solution52.solve(new int[]{3, -2, 5, 0, -1, 6, -3, -4})));
System.out.println(json(Solution54.solve(new int[]{3, -2, 5, 0, -1, 6, -3, -4})));
System.out.println(json(Solution53.solve(new int[]{3, -2, 5, 0, -1, 6, -3, -4}, 7, 8)));
System.out.println(json(Solution55.solve(new int[]{3, -2, 5, 0, -1, 6, -3, -4}, 1)));
System.out.println(json(Solution56.solve(new int[]{3, -2, 5, 0, -1, 6, -3, -4})));
System.out.println(json(Solution57.solve(new int[]{3, -2, 5, 0, -1, 6, -3, -4}, 39)));
System.out.println(json(Solution58.solve("ccccacb")));
System.out.println(json(Solution59.solve("ccccacb", 5)));
System.out.println(json(Solution60.solve("](")));
System.out.println(json(Solution61.solve("ccccacb")));
System.out.println(json(Solution62.solve(new int[]{3, -2, 5, 0, -1, 6, -3, -4})));
System.out.println(json(Solution63.solve(new int[]{3, -2, 5, 0, -1, 6, -3, -4})));
System.out.println(json(Solution64.solve(new int[]{3, -2, 5, 0, -1, 6, -3, -4})));
System.out.println(json(Solution65.solve("/b/./b/a//a/b")));
System.out.println(json(Solution66.solve("274", 3)));
System.out.println(json(Solution67.solve("ccccacb")));
System.out.println(json(Solution68.solve("o")));
System.out.println(json(Solution69.solve("ccccacb")));
System.out.println(json(Solution70.solve("ccccacb", "bb")));
System.out.println(json(Solution52.solve(new int[]{-4, -1, 6, 1, 0})));
System.out.println(json(Solution54.solve(new int[]{-4, -1, 6, 1, 0})));
System.out.println(json(Solution53.solve(new int[]{-4, -1, 6, 1, 0}, 5, 5)));
System.out.println(json(Solution55.solve(new int[]{-4, -1, 6, 1, 0}, 5)));
System.out.println(json(Solution56.solve(new int[]{-4, -1, 6, 1, 0})));
System.out.println(json(Solution57.solve(new int[]{-4, -1, 6, 1, 0}, 9)));
System.out.println(json(Solution58.solve("cacac")));
System.out.println(json(Solution59.solve("cacac", 3)));
System.out.println(json(Solution60.solve(")[}()(){")));
System.out.println(json(Solution61.solve("cacac")));
System.out.println(json(Solution62.solve(new int[]{-4, -1, 6, 1, 0})));
System.out.println(json(Solution63.solve(new int[]{-4, -1, 6, 1, 0})));
System.out.println(json(Solution64.solve(new int[]{-4, -1, 6, 1, 0})));
System.out.println(json(Solution65.solve("/")));
System.out.println(json(Solution66.solve("2", 1)));
System.out.println(json(Solution67.solve("cacac")));
System.out.println(json(Solution68.solve("onobxboyzyoyxlzoxox")));
System.out.println(json(Solution69.solve("cacac")));
System.out.println(json(Solution70.solve("cacac", "bcac")));
System.out.println(json(Solution52.solve(new int[]{-4, 3, 6, 1, 4})));
System.out.println(json(Solution54.solve(new int[]{-4, 3, 6, 1, 4})));
System.out.println(json(Solution53.solve(new int[]{-4, 3, 6, 1, 4}, 4, 4)));
System.out.println(json(Solution55.solve(new int[]{-4, 3, 6, 1, 4}, 5)));
System.out.println(json(Solution56.solve(new int[]{-4, 3, 6, 1, 4})));
System.out.println(json(Solution57.solve(new int[]{-4, 3, 6, 1, 4}, 84)));
System.out.println(json(Solution58.solve("aa")));
System.out.println(json(Solution59.solve("aa", 5)));
System.out.println(json(Solution60.solve("(}{")));
System.out.println(json(Solution61.solve("aa")));
System.out.println(json(Solution62.solve(new int[]{-4, 3, 6, 1, 4})));
System.out.println(json(Solution63.solve(new int[]{-4, 3, 6, 1, 4})));
System.out.println(json(Solution64.solve(new int[]{-4, 3, 6, 1, 4})));
System.out.println(json(Solution65.solve("/../a/../.")));
System.out.println(json(Solution66.solve("547", 2)));
}
static void batch5800() {
System.out.println(json(Solution67.solve("aa")));
System.out.println(json(Solution68.solve("olnonzbooxoanxlxyayaoall")));
System.out.println(json(Solution69.solve("aa")));
System.out.println(json(Solution70.solve("aa", "cccab")));
System.out.println(json(Solution52.solve(new int[]{-3, 0, 7, 2, 5, 0})));
System.out.println(json(Solution54.solve(new int[]{-3, 0, 7, 2, 5, 0})));
System.out.println(json(Solution53.solve(new int[]{-3, 0, 7, 2, 5, 0}, 5, 6)));
System.out.println(json(Solution55.solve(new int[]{-3, 0, 7, 2, 5, 0}, 3)));
System.out.println(json(Solution56.solve(new int[]{-3, 0, 7, 2, 5, 0})));
System.out.println(json(Solution57.solve(new int[]{-3, 0, 7, 2, 5, 0}, 57)));
System.out.println(json(Solution58.solve("ccccc")));
System.out.println(json(Solution59.solve("ccccc", 4)));
System.out.println(json(Solution60.solve("")));
System.out.println(json(Solution61.solve("ccccc")));
System.out.println(json(Solution62.solve(new int[]{-3, 0, 7, 2, 5, 0})));
System.out.println(json(Solution63.solve(new int[]{-3, 0, 7, 2, 5, 0})));
System.out.println(json(Solution64.solve(new int[]{-3, 0, 7, 2, 5, 0})));
System.out.println(json(Solution65.solve("//...//...//.../../...")));
System.out.println(json(Solution66.solve("385850", 5)));
System.out.println(json(Solution67.solve("ccccc")));
System.out.println(json(Solution68.solve("aboyzbonaloylnanolzbllayolonxyona")));
System.out.println(json(Solution69.solve("ccccc")));
System.out.println(json(Solution70.solve("ccccc", "c")));
System.out.println(json(Solution52.solve(new int[]{5, 4, 7, 2, 5, 0})));
System.out.println(json(Solution54.solve(new int[]{5, 4, 7, 2, 5, 0})));
System.out.println(json(Solution53.solve(new int[]{5, 4, 7, 2, 5, 0}, 5, 6)));
System.out.println(json(Solution55.solve(new int[]{5, 4, 7, 2, 5, 0}, 1)));
System.out.println(json(Solution56.solve(new int[]{5, 4, 7, 2, 5, 0})));
System.out.println(json(Solution57.solve(new int[]{5, 4, 7, 2, 5, 0}, 99)));
System.out.println(json(Solution58.solve("bac")));
System.out.println(json(Solution59.solve("bac", 1)));
System.out.println(json(Solution60.solve("]()()[")));
System.out.println(json(Solution61.solve("bac")));
System.out.println(json(Solution62.solve(new int[]{5, 4, 7, 2, 5, 0})));
System.out.println(json(Solution63.solve(new int[]{5, 4, 7, 2, 5, 0})));
System.out.println(json(Solution64.solve(new int[]{5, 4, 7, 2, 5, 0})));
System.out.println(json(Solution65.solve("/b/.//.")));
System.out.println(json(Solution66.solve("38905", 2)));
System.out.println(json(Solution67.solve("bac")));
System.out.println(json(Solution68.solve("bxoanlozbxlxlaooo")));
System.out.println(json(Solution69.solve("bac")));
System.out.println(json(Solution70.solve("bac", "babacc")));
System.out.println(json(Solution52.solve(new int[]{-3, 4, 3, -2, 1, -4, 3, 2})));
System.out.println(json(Solution54.solve(new int[]{-3, 4, 3, -2, 1, -4, 3, 2})));
System.out.println(json(Solution53.solve(new int[]{-3, 4, 3, -2, 1, -4, 3, 2}, 3, 6)));
System.out.println(json(Solution55.solve(new int[]{-3, 4, 3, -2, 1, -4, 3, 2}, 5)));
System.out.println(json(Solution56.solve(new int[]{-3, 4, 3, -2, 1, -4, 3, 2})));
System.out.println(json(Solution57.solve(new int[]{-3, 4, 3, -2, 1, -4, 3, 2}, 15)));
System.out.println(json(Solution58.solve("c")));
System.out.println(json(Solution59.solve("c", 1)));
System.out.println(json(Solution60.solve("][}[}(]{}[")));
System.out.println(json(Solution61.solve("c")));
System.out.println(json(Solution62.solve(new int[]{-3, 4, 3, -2, 1, -4, 3, 2})));
System.out.println(json(Solution63.solve(new int[]{-3, 4, 3, -2, 1, -4, 3, 2})));
System.out.println(json(Solution64.solve(new int[]{-3, 4, 3, -2, 1, -4, 3, 2})));
System.out.println(json(Solution65.solve("/.././..")));
System.out.println(json(Solution66.solve("2943296", 3)));
System.out.println(json(Solution67.solve("c")));
System.out.println(json(Solution68.solve("llayolxbzo")));
System.out.println(json(Solution69.solve("c")));
System.out.println(json(Solution70.solve("c", "bcabbb")));
System.out.println(json(Solution52.solve(new int[]{7, 6, 5, -4, -1, 2})));
System.out.println(json(Solution54.solve(new int[]{7, 6, 5, -4, -1, 2})));
System.out.println(json(Solution53.solve(new int[]{7, 6, 5, -4, -1, 2}, 6, 6)));
System.out.println(json(Solution55.solve(new int[]{7, 6, 5, -4, -1, 2}, 4)));
System.out.println(json(Solution56.solve(new int[]{7, 6, 5, -4, -1, 2})));
System.out.println(json(Solution57.solve(new int[]{7, 6, 5, -4, -1, 2}, 32)));
System.out.println(json(Solution58.solve("bbba")));
System.out.println(json(Solution59.solve("bbba", 1)));
System.out.println(json(Solution60.solve("{][](]{")));
System.out.println(json(Solution61.solve("bbba")));
System.out.println(json(Solution62.solve(new int[]{7, 6, 5, -4, -1, 2})));
System.out.println(json(Solution63.solve(new int[]{7, 6, 5, -4, -1, 2})));
System.out.println(json(Solution64.solve(new int[]{7, 6, 5, -4, -1, 2})));
System.out.println(json(Solution65.solve("/")));
System.out.println(json(Solution66.solve("23090", 3)));
System.out.println(json(Solution67.solve("bbba")));
System.out.println(json(Solution68.solve("lnxbollbooobxyl")));
System.out.println(json(Solution69.solve("bbba")));
System.out.println(json(Solution70.solve("bbba", "aabcbcac")));
System.out.println(json(Solution52.solve(new int[]{4})));
System.out.println(json(Solution54.solve(new int[]{4})));
System.out.println(json(Solution53.solve(new int[]{4}, 1, 1)));
System.out.println(json(Solution55.solve(new int[]{4}, 1)));
System.out.println(json(Solution56.solve(new int[]{4})));
System.out.println(json(Solution57.solve(new int[]{4}, 14)));
System.out.println(json(Solution58.solve("cccc")));
System.out.println(json(Solution59.solve("cccc", 4)));
System.out.println(json(Solution60.solve("{}(}[){](")));
System.out.println(json(Solution61.solve("cccc")));
System.out.println(json(Solution62.solve(new int[]{4})));
System.out.println(json(Solution63.solve(new int[]{4})));
System.out.println(json(Solution64.solve(new int[]{4})));
System.out.println(json(Solution65.solve("/b/.../.././../.../../...")));
System.out.println(json(Solution66.solve("50129", 4)));
System.out.println(json(Solution67.solve("cccc")));
System.out.println(json(Solution68.solve("nalabxoooooxyaoobzoxnabxbxbzblnlb")));
System.out.println(json(Solution69.solve("cccc")));
System.out.println(json(Solution70.solve("cccc", "baaac")));
System.out.println(json(Solution52.solve(new int[]{6})));
}
static void batch5900() {
System.out.println(json(Solution54.solve(new int[]{6})));
System.out.println(json(Solution53.solve(new int[]{6}, 1, 1)));
System.out.println(json(Solution55.solve(new int[]{6}, 1)));
System.out.println(json(Solution56.solve(new int[]{6})));
System.out.println(json(Solution57.solve(new int[]{6}, 40)));
System.out.println(json(Solution58.solve("bbac")));
System.out.println(json(Solution59.solve("bbac", 1)));
System.out.println(json(Solution60.solve("(](")));
System.out.println(json(Solution61.solve("bbac")));
System.out.println(json(Solution62.solve(new int[]{6})));
System.out.println(json(Solution63.solve(new int[]{6})));
System.out.println(json(Solution64.solve(new int[]{6})));
System.out.println(json(Solution65.solve("/")));
System.out.println(json(Solution66.solve("123", 2)));
System.out.println(json(Solution67.solve("bbac")));
System.out.println(json(Solution68.solve("bznlbablyalloxlobznzlllxn")));
System.out.println(json(Solution69.solve("bbac")));
System.out.println(json(Solution70.solve("bbac", "")));
System.out.println(json(Solution52.solve(new int[]{-3, 0, 7, 6})));
System.out.println(json(Solution54.solve(new int[]{-3, 0, 7, 6})));
System.out.println(json(Solution53.solve(new int[]{-3, 0, 7, 6}, 2, 4)));
System.out.println(json(Solution55.solve(new int[]{-3, 0, 7, 6}, 4)));
System.out.println(json(Solution56.solve(new int[]{-3, 0, 7, 6})));
System.out.println(json(Solution57.solve(new int[]{-3, 0, 7, 6}, 74)));
System.out.println(json(Solution58.solve("bb")));
System.out.println(json(Solution59.solve("bb", 2)));
System.out.println(json(Solution60.solve("(]{}[}[}[")));
System.out.println(json(Solution61.solve("bb")));
System.out.println(json(Solution62.solve(new int[]{-3, 0, 7, 6})));
System.out.println(json(Solution63.solve(new int[]{-3, 0, 7, 6})));
System.out.println(json(Solution64.solve(new int[]{-3, 0, 7, 6})));
System.out.println(json(Solution65.solve("/b/.")));
System.out.println(json(Solution66.solve("783494", 5)));
System.out.println(json(Solution67.solve("bb")));
System.out.println(json(Solution68.solve("xlallb")));
System.out.println(json(Solution69.solve("bb")));
System.out.println(json(Solution70.solve("bb", "abbbbb")));
System.out.println(json(Solution52.solve(new int[]{5, 0, -1, 6, -3, 0})));
System.out.println(json(Solution54.solve(new int[]{5, 0, -1, 6, -3, 0})));
System.out.println(json(Solution53.solve(new int[]{5, 0, -1, 6, -3, 0}, 6, 6)));
System.out.println(json(Solution55.solve(new int[]{5, 0, -1, 6, -3, 0}, 4)));
System.out.println(json(Solution56.solve(new int[]{5, 0, -1, 6, -3, 0})));
System.out.println(json(Solution57.solve(new int[]{5, 0, -1, 6, -3, 0}, 52)));
System.out.println(json(Solution58.solve("cc")));
System.out.println(json(Solution59.solve("cc", 1)));
System.out.println(json(Solution60.solve("(][}{}(")));
System.out.println(json(Solution61.solve("cc")));
System.out.println(json(Solution62.solve(new int[]{5, 0, -1, 6, -3, 0})));
System.out.println(json(Solution63.solve(new int[]{5, 0, -1, 6, -3, 0})));
System.out.println(json(Solution64.solve(new int[]{5, 0, -1, 6, -3, 0})));
System.out.println(json(Solution65.solve("/b/.../.././b")));
System.out.println(json(Solution66.solve("4121270", 5)));
System.out.println(json(Solution67.solve("cc")));
System.out.println(json(Solution68.solve("")));
System.out.println(json(Solution69.solve("cc")));
System.out.println(json(Solution70.solve("cc", "")));
System.out.println(json(Solution52.solve(new int[]{1, 4})));
System.out.println(json(Solution54.solve(new int[]{1, 4})));
System.out.println(json(Solution53.solve(new int[]{1, 4}, 1, 2)));
System.out.println(json(Solution55.solve(new int[]{1, 4}, 1)));
System.out.println(json(Solution56.solve(new int[]{1, 4})));
System.out.println(json(Solution57.solve(new int[]{1, 4}, 33)));
System.out.println(json(Solution58.solve("cbcba")));
System.out.println(json(Solution59.solve("cbcba", 3)));
System.out.println(json(Solution60.solve(")(}{")));
System.out.println(json(Solution61.solve("cbcba")));
System.out.println(json(Solution62.solve(new int[]{1, 4})));
System.out.println(json(Solution63.solve(new int[]{1, 4})));
System.out.println(json(Solution64.solve(new int[]{1, 4})));
System.out.println(json(Solution65.solve("/..")));
System.out.println(json(Solution66.solve("0583", 2)));
System.out.println(json(Solution67.solve("cbcba")));
System.out.println(json(Solution68.solve("naloylyxllbznzyllx")));
System.out.println(json(Solution69.solve("cbcba")));
System.out.println(json(Solution70.solve("cbcba", "bcbbcca")));
System.out.println(json(Solution52.solve(new int[]{-1, 2})));
System.out.println(json(Solution54.solve(new int[]{-1, 2})));
System.out.println(json(Solution53.solve(new int[]{-1, 2}, 2, 2)));
System.out.println(json(Solution55.solve(new int[]{-1, 2}, 2)));
System.out.println(json(Solution56.solve(new int[]{-1, 2})));
System.out.println(json(Solution57.solve(new int[]{-1, 2}, 58)));
System.out.println(json(Solution58.solve("ba")));
System.out.println(json(Solution59.solve("ba", 4)));
System.out.println(json(Solution60.solve("{}(]()[)[")));
System.out.println(json(Solution61.solve("ba")));
System.out.println(json(Solution62.solve(new int[]{-1, 2})));
System.out.println(json(Solution63.solve(new int[]{-1, 2})));
System.out.println(json(Solution64.solve(new int[]{-1, 2})));
System.out.println(json(Solution65.solve("//.../b/a/b/a")));
System.out.println(json(Solution66.solve("5436327", 4)));
System.out.println(json(Solution67.solve("ba")));
System.out.println(json(Solution68.solve("lllonalayxnxyxyloaoaoln")));
System.out.println(json(Solution69.solve("ba")));
System.out.println(json(Solution70.solve("ba", "caccb")));
System.out.println(json(Solution52.solve(new int[]{-4, -1, 6, 1, -4})));
System.out.println(json(Solution54.solve(new int[]{-4, -1, 6, 1, -4})));
System.out.println(json(Solution53.solve(new int[]{-4, -1, 6, 1, -4}, 2, 3)));
System.out.println(json(Solution55.solve(new int[]{-4, -1, 6, 1, -4}, 4)));
System.out.println(json(Solution56.solve(new int[]{-4, -1, 6, 1, -4})));
System.out.println(json(Solution57.solve(new int[]{-4, -1, 6, 1, -4}, 27)));
}
static void batch6000() {
System.out.println(json(Solution58.solve("caa")));
System.out.println(json(Solution59.solve("caa", 5)));
System.out.println(json(Solution60.solve("]{")));
System.out.println(json(Solution61.solve("caa")));
System.out.println(json(Solution62.solve(new int[]{-4, -1, 6, 1, -4})));
System.out.println(json(Solution63.solve(new int[]{-4, -1, 6, 1, -4})));
System.out.println(json(Solution64.solve(new int[]{-4, -1, 6, 1, -4})));
System.out.println(json(Solution65.solve("//.//...")));
System.out.println(json(Solution66.solve("503490", 4)));
System.out.println(json(Solution67.solve("caa")));
System.out.println(json(Solution68.solve("xlxbonoloyxnlnxolbzn")));
System.out.println(json(Solution69.solve("caa")));
System.out.println(json(Solution70.solve("caa", "")));
System.out.println(json(Solution52.solve(new int[]{7, 2, 1, 4, -1, 6, 5, 4})));
System.out.println(json(Solution54.solve(new int[]{7, 2, 1, 4, -1, 6, 5, 4})));
System.out.println(json(Solution53.solve(new int[]{7, 2, 1, 4, -1, 6, 5, 4}, 8, 8)));
System.out.println(json(Solution55.solve(new int[]{7, 2, 1, 4, -1, 6, 5, 4}, 2)));
System.out.println(json(Solution56.solve(new int[]{7, 2, 1, 4, -1, 6, 5, 4})));
System.out.println(json(Solution57.solve(new int[]{7, 2, 1, 4, -1, 6, 5, 4}, 88)));
System.out.println(json(Solution58.solve("ccbaaa")));
System.out.println(json(Solution59.solve("ccbaaa", 2)));
System.out.println(json(Solution60.solve("(][")));
System.out.println(json(Solution61.solve("ccbaaa")));
System.out.println(json(Solution62.solve(new int[]{7, 2, 1, 4, -1, 6, 5, 4})));
System.out.println(json(Solution63.solve(new int[]{7, 2, 1, 4, -1, 6, 5, 4})));
System.out.println(json(Solution64.solve(new int[]{7, 2, 1, 4, -1, 6, 5, 4})));
System.out.println(json(Solution65.solve("/..")));
System.out.println(json(Solution66.solve("6", 1)));
System.out.println(json(Solution67.solve("ccbaaa")));
System.out.println(json(Solution68.solve("llll")));
System.out.println(json(Solution69.solve("ccbaaa")));
System.out.println(json(Solution70.solve("ccbaaa", "bcc")));
System.out.println(json(Solution52.solve(new int[]{-4, 7, -2, -3, -4, -1, -2})));
System.out.println(json(Solution54.solve(new int[]{-4, 7, -2, -3, -4, -1, -2})));
System.out.println(json(Solution53.solve(new int[]{-4, 7, -2, -3, -4, -1, -2}, 3, 6)));
System.out.println(json(Solution55.solve(new int[]{-4, 7, -2, -3, -4, -1, -2}, 6)));
System.out.println(json(Solution56.solve(new int[]{-4, 7, -2, -3, -4, -1, -2})));
System.out.println(json(Solution57.solve(new int[]{-4, 7, -2, -3, -4, -1, -2}, 51)));
System.out.println(json(Solution58.solve("cbbaa")));
System.out.println(json(Solution59.solve("cbbaa", 3)));
System.out.println(json(Solution60.solve("){")));
System.out.println(json(Solution61.solve("cbbaa")));
System.out.println(json(Solution62.solve(new int[]{-4, 7, -2, -3, -4, -1, -2})));
System.out.println(json(Solution63.solve(new int[]{-4, 7, -2, -3, -4, -1, -2})));
System.out.println(json(Solution64.solve(new int[]{-4, 7, -2, -3, -4, -1, -2})));
System.out.println(json(Solution65.solve("/")));
System.out.println(json(Solution66.solve("2", 1)));
System.out.println(json(Solution67.solve("cbbaa")));
System.out.println(json(Solution68.solve("obalxnzbzlabobxboloozozbabxl")));
System.out.println(json(Solution69.solve("cbbaa")));
System.out.println(json(Solution70.solve("cbbaa", "aacbac")));
System.out.println(json(Solution52.solve(new int[]{-3, 0, 7, -2})));
System.out.println(json(Solution54.solve(new int[]{-3, 0, 7, -2})));
System.out.println(json(Solution53.solve(new int[]{-3, 0, 7, -2}, 2, 2)));
System.out.println(json(Solution55.solve(new int[]{-3, 0, 7, -2}, 4)));
System.out.println(json(Solution56.solve(new int[]{-3, 0, 7, -2})));
System.out.println(json(Solution57.solve(new int[]{-3, 0, 7, -2}, 46)));
System.out.println(json(Solution58.solve("babcbb")));
System.out.println(json(Solution59.solve("babcbb", 5)));
System.out.println(json(Solution60.solve("[]()[")));
System.out.println(json(Solution61.solve("babcbb")));
System.out.println(json(Solution62.solve(new int[]{-3, 0, 7, -2})));
System.out.println(json(Solution63.solve(new int[]{-3, 0, 7, -2})));
System.out.println(json(Solution64.solve(new int[]{-3, 0, 7, -2})));
System.out.println(json(Solution65.solve("/b/.../../a/../.../b/.")));
System.out.println(json(Solution66.solve("7058", 1)));
System.out.println(json(Solution67.solve("babcbb")));
System.out.println(json(Solution68.solve("olzb")));
System.out.println(json(Solution69.solve("babcbb")));
System.out.println(json(Solution70.solve("babcbb", "bbaccba")));
System.out.println(json(Solution52.solve(new int[]{-2, 5, 4, 7, 2, 1, -4, -1, 2})));
System.out.println(json(Solution54.solve(new int[]{-2, 5, 4, 7, 2, 1, -4, -1, 2})));
System.out.println(json(Solution53.solve(new int[]{-2, 5, 4, 7, 2, 1, -4, -1, 2}, 6, 9)));
System.out.println(json(Solution55.solve(new int[]{-2, 5, 4, 7, 2, 1, -4, -1, 2}, 2)));
System.out.println(json(Solution56.solve(new int[]{-2, 5, 4, 7, 2, 1, -4, -1, 2})));
System.out.println(json(Solution57.solve(new int[]{-2, 5, 4, 7, 2, 1, -4, -1, 2}, 17)));
System.out.println(json(Solution58.solve("caa")));
System.out.println(json(Solution59.solve("caa", 2)));
System.out.println(json(Solution60.solve("")));
System.out.println(json(Solution61.solve("caa")));
System.out.println(json(Solution62.solve(new int[]{-2, 5, 4, 7, 2, 1, -4, -1, 2})));
System.out.println(json(Solution63.solve(new int[]{-2, 5, 4, 7, 2, 1, -4, -1, 2})));
System.out.println(json(Solution64.solve(new int[]{-2, 5, 4, 7, 2, 1, -4, -1, 2})));
System.out.println(json(Solution65.solve("/.././../...")));
System.out.println(json(Solution66.solve("78705", 2)));
System.out.println(json(Solution67.solve("caa")));
System.out.println(json(Solution68.solve("bobzbanoyln")));
System.out.println(json(Solution69.solve("caa")));
System.out.println(json(Solution70.solve("caa", "babaaaca")));
System.out.println(json(Solution52.solve(new int[]{-3, 0, 3, 6, 1, 0, 3, 6})));
System.out.println(json(Solution54.solve(new int[]{-3, 0, 3, 6, 1, 0, 3, 6})));
System.out.println(json(Solution53.solve(new int[]{-3, 0, 3, 6, 1, 0, 3, 6}, 8, 8)));
System.out.println(json(Solution55.solve(new int[]{-3, 0, 3, 6, 1, 0, 3, 6}, 2)));
System.out.println(json(Solution56.solve(new int[]{-3, 0, 3, 6, 1, 0, 3, 6})));
System.out.println(json(Solution57.solve(new int[]{-3, 0, 3, 6, 1, 0, 3, 6}, 76)));
System.out.println(json(Solution58.solve("")));
System.out.println(json(Solution59.solve("", 4)));
System.out.println(json(Solution60.solve("{}{)[}{")));
System.out.println(json(Solution61.solve("")));
System.out.println(json(Solution62.solve(new int[]{-3, 0, 3, 6, 1, 0, 3, 6})));
}
static void batch6100() {
System.out.println(json(Solution63.solve(new int[]{-3, 0, 3, 6, 1, 0, 3, 6})));
System.out.println(json(Solution64.solve(new int[]{-3, 0, 3, 6, 1, 0, 3, 6})));
System.out.println(json(Solution65.solve("/../.../..")));
System.out.println(json(Solution66.solve("8", 1)));
System.out.println(json(Solution67.solve("")));
System.out.println(json(Solution68.solve("alayxblnanzbalxoalxlzozlonolao")));
System.out.println(json(Solution69.solve("")));
System.out.println(json(Solution70.solve("", "acb")));
System.out.println(json(Solution52.solve(new int[]{0, -1, 2, 5, 4, 7, 2, -3, 0})));
System.out.println(json(Solution54.solve(new int[]{0, -1, 2, 5, 4, 7, 2, -3, 0})));
System.out.println(json(Solution53.solve(new int[]{0, -1, 2, 5, 4, 7, 2, -3, 0}, 6, 8)));
System.out.println(json(Solution55.solve(new int[]{0, -1, 2, 5, 4, 7, 2, -3, 0}, 4)));
System.out.println(json(Solution56.solve(new int[]{0, -1, 2, 5, 4, 7, 2, -3, 0})));
System.out.println(json(Solution57.solve(new int[]{0, -1, 2, 5, 4, 7, 2, -3, 0}, 8)));
System.out.println(json(Solution58.solve("ac")));
System.out.println(json(Solution59.solve("ac", 5)));
System.out.println(json(Solution60.solve("{][")));
System.out.println(json(Solution61.solve("ac")));
System.out.println(json(Solution62.solve(new int[]{0, -1, 2, 5, 4, 7, 2, -3, 0})));
System.out.println(json(Solution63.solve(new int[]{0, -1, 2, 5, 4, 7, 2, -3, 0})));
System.out.println(json(Solution64.solve(new int[]{0, -1, 2, 5, 4, 7, 2, -3, 0})));
System.out.println(json(Solution65.solve("/b/a/b/a")));
System.out.println(json(Solution66.solve("34", 0)));
System.out.println(json(Solution67.solve("ac")));
System.out.println(json(Solution68.solve("alxnlla")));
System.out.println(json(Solution69.solve("ac")));
System.out.println(json(Solution70.solve("ac", "cabcaabc")));
System.out.println(json(Solution52.solve(new int[]{-4, 3, 2, 1, -4, -1, -2, 5, 0})));
System.out.println(json(Solution54.solve(new int[]{-4, 3, 2, 1, -4, -1, -2, 5, 0})));
System.out.println(json(Solution53.solve(new int[]{-4, 3, 2, 1, -4, -1, -2, 5, 0}, 4, 6)));
System.out.println(json(Solution55.solve(new int[]{-4, 3, 2, 1, -4, -1, -2, 5, 0}, 4)));
System.out.println(json(Solution56.solve(new int[]{-4, 3, 2, 1, -4, -1, -2, 5, 0})));
System.out.println(json(Solution57.solve(new int[]{-4, 3, 2, 1, -4, -1, -2, 5, 0}, 94)));
System.out.println(json(Solution58.solve("ccaaccbb")));
System.out.println(json(Solution59.solve("ccaaccbb", 2)));
System.out.println(json(Solution60.solve("(}{){)[)(")));
System.out.println(json(Solution61.solve("ccaaccbb")));
System.out.println(json(Solution62.solve(new int[]{-4, 3, 2, 1, -4, -1, -2, 5, 0})));
System.out.println(json(Solution63.solve(new int[]{-4, 3, 2, 1, -4, -1, -2, 5, 0})));
System.out.println(json(Solution64.solve(new int[]{-4, 3, 2, 1, -4, -1, -2, 5, 0})));
System.out.println(json(Solution65.solve("//...//./../.../b/.")));
System.out.println(json(Solution66.solve("547", 2)));
System.out.println(json(Solution67.solve("ccaaccbb")));
System.out.println(json(Solution68.solve("bznlyznxbzlllxnzblollan")));
System.out.println(json(Solution69.solve("ccaaccbb")));
System.out.println(json(Solution70.solve("ccaaccbb", "ab")));
System.out.println(json(Solution52.solve(new int[]{})));
System.out.println(json(Solution54.solve(new int[]{})));
System.out.println(json(Solution57.solve(new int[]{}, 40)));
System.out.println(json(Solution58.solve("cbbcaba")));
System.out.println(json(Solution59.solve("cbbcaba", 1)));
System.out.println(json(Solution60.solve("(}[}{]{}[]{")));
System.out.println(json(Solution61.solve("cbbcaba")));
System.out.println(json(Solution62.solve(new int[]{})));
System.out.println(json(Solution63.solve(new int[]{})));
System.out.println(json(Solution64.solve(new int[]{})));
System.out.println(json(Solution65.solve("/b/a/b/...")));
System.out.println(json(Solution66.solve("56", 2)));
System.out.println(json(Solution67.solve("cbbcaba")));
System.out.println(json(Solution68.solve("znabzbayxylyobzbx")));
System.out.println(json(Solution69.solve("cbbcaba")));
System.out.println(json(Solution70.solve("cbbcaba", "ac")));
System.out.println(json(Solution52.solve(new int[]{-4, -1, -2})));
System.out.println(json(Solution54.solve(new int[]{-4, -1, -2})));
System.out.println(json(Solution53.solve(new int[]{-4, -1, -2}, 1, 2)));
System.out.println(json(Solution55.solve(new int[]{-4, -1, -2}, 3)));
System.out.println(json(Solution56.solve(new int[]{-4, -1, -2})));
System.out.println(json(Solution57.solve(new int[]{-4, -1, -2}, 64)));
System.out.println(json(Solution58.solve("")));
System.out.println(json(Solution59.solve("", 2)));
System.out.println(json(Solution60.solve("{]{){](")));
System.out.println(json(Solution61.solve("")));
System.out.println(json(Solution62.solve(new int[]{-4, -1, -2})));
System.out.println(json(Solution63.solve(new int[]{-4, -1, -2})));
System.out.println(json(Solution64.solve(new int[]{-4, -1, -2})));
System.out.println(json(Solution65.solve("/b/.")));
System.out.println(json(Solution66.solve("5694", 3)));
System.out.println(json(Solution67.solve("")));
System.out.println(json(Solution68.solve("znalanzlzozlonznalxba")));
System.out.println(json(Solution69.solve("")));
System.out.println(json(Solution70.solve("", "bbbbbac")));
System.out.println(json(Solution52.solve(new int[]{-1, 2, -3, 4, -1, 2, 5, 4})));
System.out.println(json(Solution54.solve(new int[]{-1, 2, -3, 4, -1, 2, 5, 4})));
System.out.println(json(Solution53.solve(new int[]{-1, 2, -3, 4, -1, 2, 5, 4}, 7, 8)));
System.out.println(json(Solution55.solve(new int[]{-1, 2, -3, 4, -1, 2, 5, 4}, 1)));
System.out.println(json(Solution56.solve(new int[]{-1, 2, -3, 4, -1, 2, 5, 4})));
System.out.println(json(Solution57.solve(new int[]{-1, 2, -3, 4, -1, 2, 5, 4}, 95)));
System.out.println(json(Solution58.solve("cbc")));
System.out.println(json(Solution59.solve("cbc", 5)));
System.out.println(json(Solution60.solve("}[][)(")));
System.out.println(json(Solution61.solve("cbc")));
System.out.println(json(Solution62.solve(new int[]{-1, 2, -3, 4, -1, 2, 5, 4})));
System.out.println(json(Solution63.solve(new int[]{-1, 2, -3, 4, -1, 2, 5, 4})));
System.out.println(json(Solution64.solve(new int[]{-1, 2, -3, 4, -1, 2, 5, 4})));
System.out.println(json(Solution65.solve("/../.")));
System.out.println(json(Solution66.solve("961434", 3)));
System.out.println(json(Solution67.solve("cbc")));
System.out.println(json(Solution68.solve("allbao")));
System.out.println(json(Solution69.solve("cbc")));
System.out.println(json(Solution70.solve("cbc", "a")));
}
static void batch6200() {
System.out.println(json(Solution52.solve(new int[]{-2, 5, 4, 7, 2, -3, 4, -1, 6})));
System.out.println(json(Solution54.solve(new int[]{-2, 5, 4, 7, 2, -3, 4, -1, 6})));
System.out.println(json(Solution53.solve(new int[]{-2, 5, 4, 7, 2, -3, 4, -1, 6}, 6, 7)));
System.out.println(json(Solution55.solve(new int[]{-2, 5, 4, 7, 2, -3, 4, -1, 6}, 8)));
System.out.println(json(Solution56.solve(new int[]{-2, 5, 4, 7, 2, -3, 4, -1, 6})));
System.out.println(json(Solution57.solve(new int[]{-2, 5, 4, 7, 2, -3, 4, -1, 6}, 55)));
System.out.println(json(Solution58.solve("c")));
System.out.println(json(Solution59.solve("c", 4)));
System.out.println(json(Solution60.solve("){")));
System.out.println(json(Solution61.solve("c")));
System.out.println(json(Solution62.solve(new int[]{-2, 5, 4, 7, 2, -3, 4, -1, 6})));
System.out.println(json(Solution63.solve(new int[]{-2, 5, 4, 7, 2, -3, 4, -1, 6})));
System.out.println(json(Solution64.solve(new int[]{-2, 5, 4, 7, 2, -3, 4, -1, 6})));
System.out.println(json(Solution65.solve("/")));
System.out.println(json(Solution66.solve("2", 1)));
System.out.println(json(Solution67.solve("c")));
System.out.println(json(Solution68.solve("ayl")));
System.out.println(json(Solution69.solve("c")));
System.out.println(json(Solution70.solve("c", "babaccab")));
System.out.println(json(Solution56.solve(new int[]{1, 2, 3, 4, 5})));
System.out.println(json(Solution60.solve("{[()]}()")));
System.out.println(json(Solution60.solve("([)]")));
System.out.println(json(Solution65.solve("/../...//a/../")));
System.out.println(json(Solution66.solve("10200", 1)));
System.out.println(json(Solution59.solve("deeedbbcccbdaa", 3)));
System.out.println(json(Solution68.solve("balloonballoon")));
System.out.println(json(Solution69.solve("Aa")));
System.out.println(json(Solution71.solve(new int[]{-4, 1, 4, 6}, 8)));
System.out.println(json(Solution72.solve(new int[]{-4, 1, 4, 4, 6}, 8, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-4, 1, 4, 4, 6}, 8, "upper")));
System.out.println(json(Solution73.solve(new int[]{-4, 1, 4, 4, 6}, 8)));
System.out.println(json(Solution74.solve(new int[]{-4, 1, 4, 4, 6}, 8)));
System.out.println(json(Solution75.solve(new int[]{-4, 1, 4, 6}, 8)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10, 8, 6, 4, 2})));
System.out.println(json(Solution77.solve(new int[]{-3})));
System.out.println(json(Solution78.solve(new int[]{6, -4, 1, 4})));
System.out.println(json(Solution79.solve(new int[]{6, -4, 1, 4})));
System.out.println(json(Solution80.solve(new int[]{6, -4, 1, 4}, 8)));
System.out.println(json(Solution81.solve(new int[]{2, 10, 2, 6, 7}, 1)));
System.out.println(json(Solution82.solve(new int[]{2, 10, 2, 6, 7}, 3, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -6, 1}, 2)));
System.out.println(json(Solution84.solve(new int[]{2, 2, 6, 7, 10})));
System.out.println(json(Solution85.solve(new int[]{2, 10, 2, 6, 7}, 12)));
System.out.println(json(Solution86.solve(new int[]{2, 10, 2, 6, 7}, 7)));
System.out.println(json(Solution87.solve(new int[]{2, 10, 2, 6, 7}, 7)));
System.out.println(json(Solution88.solve(new int[]{1, 9, 1, 5, 6}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, 8)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -2}, new int[]{-2, -2}, new int[]{-2, 0}}, 8)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 2)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{-4, 1, 4, 4, 6}, new int[]{-6, -4, 3, 4, 6})));
System.out.println(json(Solution71.solve(new int[]{-4, -2, 6}, 7)));
System.out.println(json(Solution72.solve(new int[]{-4, -2, 6}, 7, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-4, -2, 6}, 7, "upper")));
System.out.println(json(Solution73.solve(new int[]{-4, -2, 6}, 7)));
System.out.println(json(Solution74.solve(new int[]{-4, -2, 6}, 7)));
System.out.println(json(Solution75.solve(new int[]{-4, -2, 6}, 7)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 4})));
System.out.println(json(Solution77.solve(new int[]{4, -3, 0, -1, 8, 7})));
System.out.println(json(Solution78.solve(new int[]{6, -4, -2})));
System.out.println(json(Solution79.solve(new int[]{6, -4, -2})));
System.out.println(json(Solution80.solve(new int[]{6, -4, -2}, 7)));
System.out.println(json(Solution81.solve(new int[]{2, 12, 4, 13, 6, 1, 6}, 12)));
System.out.println(json(Solution82.solve(new int[]{2, 12, 4, 13, 6, 1, 6}, 2, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -3, -4, 2}, 2)));
System.out.println(json(Solution84.solve(new int[]{1, 2, 4, 6, 6, 12, 13})));
System.out.println(json(Solution85.solve(new int[]{2, 12, 4, 13, 6, 1, 6}, 22)));
System.out.println(json(Solution86.solve(new int[]{2, 12, 4, 13, 6, 1, 6}, 4)));
System.out.println(json(Solution87.solve(new int[]{2, 12, 4, 13, 6, 1, 6}, 4)));
System.out.println(json(Solution88.solve(new int[]{1, 11, 3, 12, 5, 0, 5}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6, -4}}, 7)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -4, -3, -2}}, 7)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 5)));
System.out.println(json(Solution92.solve(4, 7, 14)));
System.out.println(json(Solution93.solve(new int[]{-4, -2, 6}, new int[]{-2, -2, 1, 2, 3})));
System.out.println(json(Solution71.solve(new int[]{-6, -2, 2, 3}, 9)));
System.out.println(json(Solution72.solve(new int[]{-6, -6, -2, 2, 3}, 9, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, -6, -2, 2, 3}, 9, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, -6, -2, 2, 3}, 9)));
System.out.println(json(Solution74.solve(new int[]{-6, -6, -2, 2, 3}, 9)));
System.out.println(json(Solution75.solve(new int[]{-6, -2, 2, 3}, 9)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1, -3, -5, -7})));
System.out.println(json(Solution77.solve(new int[]{6, -1, -2})));
System.out.println(json(Solution78.solve(new int[]{-2, 2, 3, -6})));
System.out.println(json(Solution79.solve(new int[]{-2, 2, 3, -6})));
System.out.println(json(Solution80.solve(new int[]{-2, 2, 3, -6}, 9)));
System.out.println(json(Solution81.solve(new int[]{12, 11, 3}, 6)));
System.out.println(json(Solution82.solve(new int[]{12, 11, 3}, 3, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 6, 5}, 3)));
System.out.println(json(Solution84.solve(new int[]{3, 11, 12})));
System.out.println(json(Solution85.solve(new int[]{12, 11, 3}, 27)));
System.out.println(json(Solution86.solve(new int[]{12, 11, 3}, 3)));
System.out.println(json(Solution87.solve(new int[]{12, 11, 3}, 3)));
System.out.println(json(Solution88.solve(new int[]{11, 10, 2}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, 9)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -4}, new int[]{-4, -2}, new int[]{-2, -1}}, 9)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 4)));
System.out.println(json(Solution92.solve(4, 7, 14)));
System.out.println(json(Solution93.solve(new int[]{-6, -6, -2, 2, 3}, new int[]{-3, 0, 1, 1, 2, 5, 5})));
System.out.println(json(Solution71.solve(new int[]{-5, -4, -1, 2, 3, 5, 6}, 4)));
}
static void batch6300() {
System.out.println(json(Solution72.solve(new int[]{-5, -5, -4, -1, 2, 3, 5, 6}, 4, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-5, -5, -4, -1, 2, 3, 5, 6}, 4, "upper")));
System.out.println(json(Solution73.solve(new int[]{-5, -5, -4, -1, 2, 3, 5, 6}, 4)));
System.out.println(json(Solution74.solve(new int[]{-5, -5, -4, -1, 2, 3, 5, 6}, 4)));
System.out.println(json(Solution75.solve(new int[]{-5, -4, -1, 2, 3, 5, 6}, 4)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1})));
System.out.println(json(Solution77.solve(new int[]{-2, 3, 7, 2, -3})));
System.out.println(json(Solution78.solve(new int[]{6, -5, -4, -1, 2, 3, 5})));
System.out.println(json(Solution79.solve(new int[]{6, -5, -4, -1, 2, 3, 5})));
System.out.println(json(Solution80.solve(new int[]{6, -5, -4, -1, 2, 3, 5}, 4)));
System.out.println(json(Solution81.solve(new int[]{1, 13, 10, 11}, 14)));
System.out.println(json(Solution82.solve(new int[]{1, 13, 10, 11}, 1, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -3, 8, -6, -5, -1}, 2)));
System.out.println(json(Solution84.solve(new int[]{1, 10, 11, 13})));
System.out.println(json(Solution85.solve(new int[]{1, 13, 10, 11}, 25)));
System.out.println(json(Solution86.solve(new int[]{1, 13, 10, 11}, 5)));
System.out.println(json(Solution87.solve(new int[]{1, 13, 10, 11}, 5)));
System.out.println(json(Solution88.solve(new int[]{0, 12, 9, 10}, 4)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, 4)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -3}, new int[]{-3, -3}, new int[]{-3, -1}}, 4)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 3)));
System.out.println(json(Solution92.solve(8, 3, 2)));
System.out.println(json(Solution93.solve(new int[]{-5, -5, -4, -1, 2, 3, 5, 6}, new int[]{-4, -4, -1, 5, 5, 5, 5})));
System.out.println(json(Solution71.solve(new int[]{0}, 1)));
System.out.println(json(Solution72.solve(new int[]{0}, 1, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{0}, 1, "upper")));
System.out.println(json(Solution73.solve(new int[]{0}, 1)));
System.out.println(json(Solution74.solve(new int[]{0}, 1)));
System.out.println(json(Solution75.solve(new int[]{0}, 1)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 15, 13, 11, 9})));
System.out.println(json(Solution77.solve(new int[]{1})));
System.out.println(json(Solution78.solve(new int[]{0})));
System.out.println(json(Solution79.solve(new int[]{0})));
System.out.println(json(Solution80.solve(new int[]{0}, 1)));
System.out.println(json(Solution81.solve(new int[]{2, 12, 11, 11}, 19)));
System.out.println(json(Solution82.solve(new int[]{2, 12, 11, 11}, 4, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -2}, 3)));
System.out.println(json(Solution84.solve(new int[]{2, 11, 11, 12})));
System.out.println(json(Solution85.solve(new int[]{2, 12, 11, 11}, 33)));
System.out.println(json(Solution86.solve(new int[]{2, 12, 11, 11}, 4)));
System.out.println(json(Solution87.solve(new int[]{2, 12, 11, 11}, 4)));
System.out.println(json(Solution88.solve(new int[]{1, 11, 10, 10}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, 1)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -2, -2}, new int[]{-4, 0, 2}, new int[]{-3, 2, 2}, new int[]{-1, 3, 3}}, 1)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 18)));
System.out.println(json(Solution93.solve(new int[]{0}, new int[]{})));
System.out.println(json(Solution71.solve(new int[]{0, 1, 5, 7}, 8)));
System.out.println(json(Solution72.solve(new int[]{0, 1, 5, 7, 7}, 8, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{0, 1, 5, 7, 7}, 8, "upper")));
System.out.println(json(Solution73.solve(new int[]{0, 1, 5, 7, 7}, 8)));
System.out.println(json(Solution74.solve(new int[]{0, 1, 5, 7, 7}, 8)));
System.out.println(json(Solution75.solve(new int[]{0, 1, 5, 7}, 8)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 15, 13, 11, 9})));
System.out.println(json(Solution77.solve(new int[]{1})));
System.out.println(json(Solution78.solve(new int[]{1, 5, 7, 0})));
System.out.println(json(Solution79.solve(new int[]{1, 5, 7, 0})));
System.out.println(json(Solution80.solve(new int[]{1, 5, 7, 0}, 8)));
System.out.println(json(Solution81.solve(new int[]{15, 8, 5, 7, 5}, 22)));
System.out.println(json(Solution82.solve(new int[]{15, 8, 5, 7, 5}, 3, 1)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 7}, 3)));
System.out.println(json(Solution84.solve(new int[]{5, 5, 7, 8, 15})));
System.out.println(json(Solution85.solve(new int[]{15, 8, 5, 7, 5}, 18)));
System.out.println(json(Solution86.solve(new int[]{15, 8, 5, 7, 5}, 6)));
System.out.println(json(Solution87.solve(new int[]{15, 8, 5, 7, 5}, 6)));
System.out.println(json(Solution88.solve(new int[]{14, 7, 4, 6, 4}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, 8)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4}, new int[]{-4}}, 8)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 14)));
System.out.println(json(Solution93.solve(new int[]{0, 1, 5, 7, 7}, new int[]{-6, -5, 0, 2, 2, 3, 3, 6})));
System.out.println(json(Solution71.solve(new int[]{-3, -1, 3, 7}, 12)));
System.out.println(json(Solution72.solve(new int[]{-3, -1, -1, 3, 7, 7}, 12, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-3, -1, -1, 3, 7, 7}, 12, "upper")));
System.out.println(json(Solution73.solve(new int[]{-3, -1, -1, 3, 7, 7}, 12)));
System.out.println(json(Solution74.solve(new int[]{-3, -1, -1, 3, 7, 7}, 12)));
System.out.println(json(Solution75.solve(new int[]{-3, -1, 3, 7}, 12)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1, -3, -5, -7})));
System.out.println(json(Solution77.solve(new int[]{1, 7, 1, 6, -4})));
System.out.println(json(Solution78.solve(new int[]{7, -3, -1, 3})));
System.out.println(json(Solution79.solve(new int[]{7, -3, -1, 3})));
System.out.println(json(Solution80.solve(new int[]{7, -3, -1, 3}, 12)));
System.out.println(json(Solution81.solve(new int[]{13, 13, 15, 14, 5}, 6)));
System.out.println(json(Solution82.solve(new int[]{13, 13, 15, 14, 5}, 5, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 1, -5, 7}, 3)));
System.out.println(json(Solution84.solve(new int[]{5, 13, 13, 14, 15})));
System.out.println(json(Solution85.solve(new int[]{13, 13, 15, 14, 5}, 26)));
System.out.println(json(Solution86.solve(new int[]{13, 13, 15, 14, 5}, 1)));
System.out.println(json(Solution87.solve(new int[]{13, 13, 15, 14, 5}, 1)));
System.out.println(json(Solution88.solve(new int[]{12, 12, 14, 13, 4}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, 12)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5}, new int[]{-5}}, 12)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 26)));
System.out.println(json(Solution93.solve(new int[]{-3, -1, -1, 3, 7, 7}, new int[]{-6, -2, 0, 1, 2})));
System.out.println(json(Solution71.solve(new int[]{}, -4)));
System.out.println(json(Solution72.solve(new int[]{}, -4, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{}, -4, "upper")));
System.out.println(json(Solution73.solve(new int[]{}, -4)));
System.out.println(json(Solution74.solve(new int[]{}, -4)));
}
static void batch6400() {
System.out.println(json(Solution75.solve(new int[]{}, -4)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10, 8, 6})));
System.out.println(json(Solution77.solve(new int[]{-1})));
System.out.println(json(Solution78.solve(new int[]{0})));
System.out.println(json(Solution79.solve(new int[]{0})));
System.out.println(json(Solution80.solve(new int[]{0}, -4)));
System.out.println(json(Solution81.solve(new int[]{6, 15}, 21)));
System.out.println(json(Solution82.solve(new int[]{6, 15}, 1, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -5, 8, 1}, 3)));
System.out.println(json(Solution84.solve(new int[]{6, 15})));
System.out.println(json(Solution85.solve(new int[]{6, 15}, 11)));
System.out.println(json(Solution86.solve(new int[]{6, 15}, 4)));
System.out.println(json(Solution87.solve(new int[]{6, 15}, 4)));
System.out.println(json(Solution88.solve(new int[]{5, 14}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, -4)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5}, new int[]{-3}}, -4)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 14)));
System.out.println(json(Solution93.solve(new int[]{}, new int[]{-6, 1, 2, 3, 5, 6})));
System.out.println(json(Solution71.solve(new int[]{}, 8)));
System.out.println(json(Solution72.solve(new int[]{}, 8, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{}, 8, "upper")));
System.out.println(json(Solution73.solve(new int[]{}, 8)));
System.out.println(json(Solution74.solve(new int[]{}, 8)));
System.out.println(json(Solution75.solve(new int[]{}, 8)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 15, 13, 11, 9, 7})));
System.out.println(json(Solution77.solve(new int[]{5, 6, 7, -5, -2, -3, 8, 6})));
System.out.println(json(Solution78.solve(new int[]{0})));
System.out.println(json(Solution79.solve(new int[]{0})));
System.out.println(json(Solution80.solve(new int[]{0}, 8)));
System.out.println(json(Solution81.solve(new int[]{14, 11, 6, 1, 3}, 18)));
System.out.println(json(Solution82.solve(new int[]{14, 11, 6, 1, 3}, 2, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 5}, 2)));
System.out.println(json(Solution84.solve(new int[]{1, 3, 6, 11, 14})));
System.out.println(json(Solution85.solve(new int[]{14, 11, 6, 1, 3}, 9)));
System.out.println(json(Solution86.solve(new int[]{14, 11, 6, 1, 3}, 6)));
System.out.println(json(Solution87.solve(new int[]{14, 11, 6, 1, 3}, 6)));
System.out.println(json(Solution88.solve(new int[]{13, 10, 5, 0, 2}, 3)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6, -4}}, 8)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -2, 0, 1}}, 8)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 5)));
System.out.println(json(Solution92.solve(4, 7, 22)));
System.out.println(json(Solution93.solve(new int[]{}, new int[]{-3, 4, 6, 8})));
System.out.println(json(Solution71.solve(new int[]{-4, 1, 6}, -6)));
System.out.println(json(Solution72.solve(new int[]{-4, 1, 6, 6}, -6, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-4, 1, 6, 6}, -6, "upper")));
System.out.println(json(Solution73.solve(new int[]{-4, 1, 6, 6}, -6)));
System.out.println(json(Solution74.solve(new int[]{-4, 1, 6, 6}, -6)));
System.out.println(json(Solution75.solve(new int[]{-4, 1, 6}, -6)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 15, 13, 11, 9, 7})));
System.out.println(json(Solution77.solve(new int[]{1})));
System.out.println(json(Solution78.solve(new int[]{6, -4, 1})));
System.out.println(json(Solution79.solve(new int[]{6, -4, 1})));
System.out.println(json(Solution80.solve(new int[]{6, -4, 1}, -6)));
System.out.println(json(Solution81.solve(new int[]{2, 11, 15, 5, 14, 14, 6}, 9)));
System.out.println(json(Solution82.solve(new int[]{2, 11, 15, 5, 14, 14, 6}, 2, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 8}, 3)));
System.out.println(json(Solution84.solve(new int[]{2, 5, 6, 11, 14, 14, 15})));
System.out.println(json(Solution85.solve(new int[]{2, 11, 15, 5, 14, 14, 6}, 26)));
System.out.println(json(Solution86.solve(new int[]{2, 11, 15, 5, 14, 14, 6}, 2)));
System.out.println(json(Solution87.solve(new int[]{2, 11, 15, 5, 14, 14, 6}, 2)));
System.out.println(json(Solution88.solve(new int[]{1, 10, 14, 4, 13, 13, 5}, 7)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, -6)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -1, 1}, new int[]{-1, 0, 3}, new int[]{1, 1, 3}, new int[]{2, 3, 3}}, -6)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 26)));
System.out.println(json(Solution93.solve(new int[]{-4, 1, 6, 6}, new int[]{-4, -1, 1, 2, 4})));
System.out.println(json(Solution71.solve(new int[]{-6, -3, -1, 4}, 13)));
System.out.println(json(Solution72.solve(new int[]{-6, -6, -3, -1, 4}, 13, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, -6, -3, -1, 4}, 13, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, -6, -3, -1, 4}, 13)));
System.out.println(json(Solution74.solve(new int[]{-6, -6, -3, -1, 4}, 13)));
System.out.println(json(Solution75.solve(new int[]{-6, -3, -1, 4}, 13)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 15, 13})));
System.out.println(json(Solution77.solve(new int[]{2})));
System.out.println(json(Solution78.solve(new int[]{4, -6, -3, -1})));
System.out.println(json(Solution79.solve(new int[]{4, -6, -3, -1})));
System.out.println(json(Solution80.solve(new int[]{4, -6, -3, -1}, 13)));
System.out.println(json(Solution81.solve(new int[]{14}, 18)));
System.out.println(json(Solution82.solve(new int[]{14}, 5, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 8, 1, 3, -3, -2, 2}, 2)));
System.out.println(json(Solution84.solve(new int[]{14})));
System.out.println(json(Solution85.solve(new int[]{14}, 29)));
System.out.println(json(Solution86.solve(new int[]{14}, 2)));
System.out.println(json(Solution87.solve(new int[]{14}, 2)));
System.out.println(json(Solution88.solve(new int[]{13}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6, -4}}, 13)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -1, -1, -1}}, 13)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 3)));
System.out.println(json(Solution92.solve(4, 7, 22)));
System.out.println(json(Solution93.solve(new int[]{-6, -6, -3, -1, 4}, new int[]{})));
System.out.println(json(Solution71.solve(new int[]{}, -2)));
System.out.println(json(Solution72.solve(new int[]{}, -2, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{}, -2, "upper")));
System.out.println(json(Solution73.solve(new int[]{}, -2)));
System.out.println(json(Solution74.solve(new int[]{}, -2)));
System.out.println(json(Solution75.solve(new int[]{}, -2)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1, -3})));
System.out.println(json(Solution77.solve(new int[]{2})));
System.out.println(json(Solution78.solve(new int[]{0})));
}
static void batch6500() {
System.out.println(json(Solution79.solve(new int[]{0})));
System.out.println(json(Solution80.solve(new int[]{0}, -2)));
System.out.println(json(Solution81.solve(new int[]{5, 14, 4, 6, 12, 8}, 1)));
System.out.println(json(Solution82.solve(new int[]{5, 14, 4, 6, 12, 8}, 5, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 3, -5, 4}, 4)));
System.out.println(json(Solution84.solve(new int[]{4, 5, 6, 8, 12, 14})));
System.out.println(json(Solution85.solve(new int[]{5, 14, 4, 6, 12, 8}, 14)));
System.out.println(json(Solution86.solve(new int[]{5, 14, 4, 6, 12, 8}, 1)));
System.out.println(json(Solution87.solve(new int[]{5, 14, 4, 6, 12, 8}, 1)));
System.out.println(json(Solution88.solve(new int[]{4, 13, 3, 5, 11, 7}, 6)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, -2)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -4}, new int[]{-4, -3}, new int[]{-4, -2}}, -2)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 2)));
System.out.println(json(Solution92.solve(4, 7, 14)));
System.out.println(json(Solution93.solve(new int[]{}, new int[]{-4, -3, 0, 4, 7})));
System.out.println(json(Solution71.solve(new int[]{-1, 1, 3, 4, 5, 8}, 4)));
System.out.println(json(Solution72.solve(new int[]{-1, 1, 3, 4, 5, 8}, 4, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-1, 1, 3, 4, 5, 8}, 4, "upper")));
System.out.println(json(Solution73.solve(new int[]{-1, 1, 3, 4, 5, 8}, 4)));
System.out.println(json(Solution74.solve(new int[]{-1, 1, 3, 4, 5, 8}, 4)));
System.out.println(json(Solution75.solve(new int[]{-1, 1, 3, 4, 5, 8}, 4)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1})));
System.out.println(json(Solution77.solve(new int[]{-6, 7, 3, -1, 6})));
System.out.println(json(Solution78.solve(new int[]{3, 4, 5, 8, -1, 1})));
System.out.println(json(Solution79.solve(new int[]{3, 4, 5, 8, -1, 1})));
System.out.println(json(Solution80.solve(new int[]{3, 4, 5, 8, -1, 1}, 4)));
System.out.println(json(Solution81.solve(new int[]{1, 1, 6}, 17)));
System.out.println(json(Solution82.solve(new int[]{1, 1, 6}, 2, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 5, 1, -2}, 3)));
System.out.println(json(Solution84.solve(new int[]{1, 1, 6})));
System.out.println(json(Solution85.solve(new int[]{1, 1, 6}, 15)));
System.out.println(json(Solution86.solve(new int[]{1, 1, 6}, 1)));
System.out.println(json(Solution87.solve(new int[]{1, 1, 6}, 1)));
System.out.println(json(Solution88.solve(new int[]{0, 0, 5}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, 4)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3}, new int[]{-3}}, 4)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{-1, 1, 3, 4, 5, 8}, new int[]{-4, -4, 6})));
System.out.println(json(Solution71.solve(new int[]{6}, 10)));
System.out.println(json(Solution72.solve(new int[]{6}, 10, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{6}, 10, "upper")));
System.out.println(json(Solution73.solve(new int[]{6}, 10)));
System.out.println(json(Solution74.solve(new int[]{6}, 10)));
System.out.println(json(Solution75.solve(new int[]{6}, 10)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 4, 2, 0, -2, -4})));
System.out.println(json(Solution77.solve(new int[]{-4, -5, 6, -1})));
System.out.println(json(Solution78.solve(new int[]{6})));
System.out.println(json(Solution79.solve(new int[]{6})));
System.out.println(json(Solution80.solve(new int[]{6}, 10)));
System.out.println(json(Solution81.solve(new int[]{1, 9, 3}, 8)));
System.out.println(json(Solution82.solve(new int[]{1, 9, 3}, 2, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 1, -4, -5}, 4)));
System.out.println(json(Solution84.solve(new int[]{1, 3, 9})));
System.out.println(json(Solution85.solve(new int[]{1, 9, 3}, 4)));
System.out.println(json(Solution86.solve(new int[]{1, 9, 3}, 5)));
System.out.println(json(Solution87.solve(new int[]{1, 9, 3}, 5)));
System.out.println(json(Solution88.solve(new int[]{0, 8, 2}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, 10)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -2}, new int[]{-3, 0}, new int[]{-3, 1}}, 10)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 2)));
System.out.println(json(Solution92.solve(4, 7, 26)));
System.out.println(json(Solution93.solve(new int[]{6}, new int[]{-4, -3, 2, 3})));
System.out.println(json(Solution71.solve(new int[]{-3, -2, 3, 5, 7, 8}, 10)));
System.out.println(json(Solution72.solve(new int[]{-3, -3, -2, 3, 5, 7, 8}, 10, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-3, -3, -2, 3, 5, 7, 8}, 10, "upper")));
System.out.println(json(Solution73.solve(new int[]{-3, -3, -2, 3, 5, 7, 8}, 10)));
System.out.println(json(Solution74.solve(new int[]{-3, -3, -2, 3, 5, 7, 8}, 10)));
System.out.println(json(Solution75.solve(new int[]{-3, -2, 3, 5, 7, 8}, 10)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10, 8, 6, 4})));
System.out.println(json(Solution77.solve(new int[]{-4, -2, -3, 6, 7, -1, 1, -5})));
System.out.println(json(Solution78.solve(new int[]{-2, 3, 5, 7, 8, -3})));
System.out.println(json(Solution79.solve(new int[]{-2, 3, 5, 7, 8, -3})));
System.out.println(json(Solution80.solve(new int[]{-2, 3, 5, 7, 8, -3}, 10)));
System.out.println(json(Solution81.solve(new int[]{4, 10, 15}, 17)));
System.out.println(json(Solution82.solve(new int[]{4, 10, 15}, 1, 1)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -1, 6}, 2)));
System.out.println(json(Solution84.solve(new int[]{4, 10, 15})));
System.out.println(json(Solution85.solve(new int[]{4, 10, 15}, 13)));
System.out.println(json(Solution86.solve(new int[]{4, 10, 15}, 5)));
System.out.println(json(Solution87.solve(new int[]{4, 10, 15}, 5)));
System.out.println(json(Solution88.solve(new int[]{3, 9, 14}, 3)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, 10)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -3, -2}, new int[]{-1, 1, 3}, new int[]{0, 3, 3}, new int[]{2, 3, 5}}, 10)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 6)));
System.out.println(json(Solution93.solve(new int[]{-3, -3, -2, 3, 5, 7, 8}, new int[]{-3, 1, 1, 4, 5, 5, 6})));
System.out.println(json(Solution71.solve(new int[]{4, 6}, -4)));
System.out.println(json(Solution72.solve(new int[]{4, 6}, -4, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{4, 6}, -4, "upper")));
System.out.println(json(Solution73.solve(new int[]{4, 6}, -4)));
System.out.println(json(Solution74.solve(new int[]{4, 6}, -4)));
System.out.println(json(Solution75.solve(new int[]{4, 6}, -4)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 7, 5, 3, 1})));
System.out.println(json(Solution77.solve(new int[]{1, -2, -3})));
System.out.println(json(Solution78.solve(new int[]{4, 6})));
System.out.println(json(Solution79.solve(new int[]{4, 6})));
System.out.println(json(Solution80.solve(new int[]{4, 6}, -4)));
System.out.println(json(Solution81.solve(new int[]{10, 13, 10}, 8)));
System.out.println(json(Solution82.solve(new int[]{10, 13, 10}, 1, 4)));
}
static void batch6600() {
System.out.println(json(Solution83.solve(new int[]{0, 20, -4, -5, 1, 7, -3, 8}, 6)));
System.out.println(json(Solution84.solve(new int[]{10, 10, 13})));
System.out.println(json(Solution85.solve(new int[]{10, 13, 10}, 30)));
System.out.println(json(Solution86.solve(new int[]{10, 13, 10}, 5)));
System.out.println(json(Solution87.solve(new int[]{10, 13, 10}, 5)));
System.out.println(json(Solution88.solve(new int[]{9, 12, 9}, 3)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, -4)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -3}, new int[]{-4, -1}, new int[]{-4, 1}}, -4)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 9)));
System.out.println(json(Solution92.solve(8, 3, 2)));
System.out.println(json(Solution93.solve(new int[]{4, 6}, new int[]{0, 0, 7})));
System.out.println(json(Solution71.solve(new int[]{-6, -4, 0, 1}, -6)));
System.out.println(json(Solution72.solve(new int[]{-6, -4, 0, 1}, -6, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, -4, 0, 1}, -6, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, -4, 0, 1}, -6)));
System.out.println(json(Solution74.solve(new int[]{-6, -4, 0, 1}, -6)));
System.out.println(json(Solution75.solve(new int[]{-6, -4, 0, 1}, -6)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1, -3})));
System.out.println(json(Solution77.solve(new int[]{4, 3, 8, -5, -4})));
System.out.println(json(Solution78.solve(new int[]{0, 1, -6, -4})));
System.out.println(json(Solution79.solve(new int[]{0, 1, -6, -4})));
System.out.println(json(Solution80.solve(new int[]{0, 1, -6, -4}, -6)));
System.out.println(json(Solution81.solve(new int[]{7, 3, 8, 8, 1, 8, 2}, 20)));
System.out.println(json(Solution82.solve(new int[]{7, 3, 8, 8, 1, 8, 2}, 3, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20}, 2)));
System.out.println(json(Solution84.solve(new int[]{1, 2, 3, 7, 8, 8, 8})));
System.out.println(json(Solution85.solve(new int[]{7, 3, 8, 8, 1, 8, 2}, 10)));
System.out.println(json(Solution86.solve(new int[]{7, 3, 8, 8, 1, 8, 2}, 5)));
System.out.println(json(Solution87.solve(new int[]{7, 3, 8, 8, 1, 8, 2}, 5)));
System.out.println(json(Solution88.solve(new int[]{6, 2, 7, 7, 0, 7, 1}, 5)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, -6)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3}, new int[]{-2}}, -6)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 2)));
System.out.println(json(Solution93.solve(new int[]{-6, -4, 0, 1}, new int[]{})));
System.out.println(json(Solution71.solve(new int[]{}, -2)));
System.out.println(json(Solution72.solve(new int[]{}, -2, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{}, -2, "upper")));
System.out.println(json(Solution73.solve(new int[]{}, -2)));
System.out.println(json(Solution74.solve(new int[]{}, -2)));
System.out.println(json(Solution75.solve(new int[]{}, -2)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10, 8, 6})));
System.out.println(json(Solution77.solve(new int[]{8, 7, -4, -5, 2})));
System.out.println(json(Solution78.solve(new int[]{0})));
System.out.println(json(Solution79.solve(new int[]{0})));
System.out.println(json(Solution80.solve(new int[]{0}, -2)));
System.out.println(json(Solution81.solve(new int[]{8, 10, 6, 11, 9, 7, 14}, 12)));
System.out.println(json(Solution82.solve(new int[]{8, 10, 6, 11, 9, 7, 14}, 1, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20}, 2)));
System.out.println(json(Solution84.solve(new int[]{6, 7, 8, 9, 10, 11, 14})));
System.out.println(json(Solution85.solve(new int[]{8, 10, 6, 11, 9, 7, 14}, 33)));
System.out.println(json(Solution86.solve(new int[]{8, 10, 6, 11, 9, 7, 14}, 6)));
System.out.println(json(Solution87.solve(new int[]{8, 10, 6, 11, 9, 7, 14}, 6)));
System.out.println(json(Solution88.solve(new int[]{7, 9, 5, 10, 8, 6, 13}, 4)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6, -4}}, -2)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -4, -3, -3}}, -2)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 3)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{}, new int[]{-4, 0, 0, 1, 5, 8})));
System.out.println(json(Solution71.solve(new int[]{0}, -3)));
System.out.println(json(Solution72.solve(new int[]{0}, -3, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{0}, -3, "upper")));
System.out.println(json(Solution73.solve(new int[]{0}, -3)));
System.out.println(json(Solution74.solve(new int[]{0}, -3)));
System.out.println(json(Solution75.solve(new int[]{0}, -3)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 15, 13, 11})));
System.out.println(json(Solution77.solve(new int[]{4, -6, -4, 4, -3, -5, 5})));
System.out.println(json(Solution78.solve(new int[]{0})));
System.out.println(json(Solution79.solve(new int[]{0})));
System.out.println(json(Solution80.solve(new int[]{0}, -3)));
System.out.println(json(Solution81.solve(new int[]{2, 9, 8, 15}, 8)));
System.out.println(json(Solution82.solve(new int[]{2, 9, 8, 15}, 3, 1)));
System.out.println(json(Solution83.solve(new int[]{0, 20}, 2)));
System.out.println(json(Solution84.solve(new int[]{2, 8, 9, 15})));
System.out.println(json(Solution85.solve(new int[]{2, 9, 8, 15}, 18)));
System.out.println(json(Solution86.solve(new int[]{2, 9, 8, 15}, 3)));
System.out.println(json(Solution87.solve(new int[]{2, 9, 8, 15}, 3)));
System.out.println(json(Solution88.solve(new int[]{1, 8, 7, 14}, 4)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, -3)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -3}, new int[]{-2, -2}, new int[]{0, 0}}, -3)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 4)));
System.out.println(json(Solution92.solve(8, 3, 2)));
System.out.println(json(Solution93.solve(new int[]{0}, new int[]{-3, 1, 2, 4, 4, 5, 5})));
System.out.println(json(Solution71.solve(new int[]{}, -8)));
System.out.println(json(Solution72.solve(new int[]{}, -8, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{}, -8, "upper")));
System.out.println(json(Solution73.solve(new int[]{}, -8)));
System.out.println(json(Solution74.solve(new int[]{}, -8)));
System.out.println(json(Solution75.solve(new int[]{}, -8)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 7, 5})));
System.out.println(json(Solution77.solve(new int[]{-3, 5, 4, -6, 3, -4})));
System.out.println(json(Solution78.solve(new int[]{0})));
System.out.println(json(Solution79.solve(new int[]{0})));
System.out.println(json(Solution80.solve(new int[]{0}, -8)));
System.out.println(json(Solution81.solve(new int[]{5, 5, 1, 14, 4}, 15)));
System.out.println(json(Solution82.solve(new int[]{5, 5, 1, 14, 4}, 5, 1)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -5, 2, 6, 5, -1}, 5)));
System.out.println(json(Solution84.solve(new int[]{1, 4, 5, 5, 14})));
System.out.println(json(Solution85.solve(new int[]{5, 5, 1, 14, 4}, 4)));
System.out.println(json(Solution86.solve(new int[]{5, 5, 1, 14, 4}, 5)));
}
static void batch6700() {
System.out.println(json(Solution87.solve(new int[]{5, 5, 1, 14, 4}, 5)));
System.out.println(json(Solution88.solve(new int[]{4, 4, 0, 13, 3}, 5)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, -8)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -4, -4}, new int[]{-4, -3, -3}, new int[]{-4, -2, 0}, new int[]{-4, 0, 1}}, -8)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{}, new int[]{-6, -5, 8})));
System.out.println(json(Solution71.solve(new int[]{-6}, -5)));
System.out.println(json(Solution72.solve(new int[]{-6, -6}, -5, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, -6}, -5, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, -6}, -5)));
System.out.println(json(Solution74.solve(new int[]{-6, -6}, -5)));
System.out.println(json(Solution75.solve(new int[]{-6}, -5)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1, -3, -5})));
System.out.println(json(Solution77.solve(new int[]{2, -5, -4, 4})));
System.out.println(json(Solution78.solve(new int[]{-6})));
System.out.println(json(Solution79.solve(new int[]{-6})));
System.out.println(json(Solution80.solve(new int[]{-6}, -5)));
System.out.println(json(Solution81.solve(new int[]{2}, 3)));
System.out.println(json(Solution82.solve(new int[]{2}, 3, 1)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 7}, 3)));
System.out.println(json(Solution84.solve(new int[]{2})));
System.out.println(json(Solution85.solve(new int[]{2}, 16)));
System.out.println(json(Solution86.solve(new int[]{2}, 1)));
System.out.println(json(Solution87.solve(new int[]{2}, 1)));
System.out.println(json(Solution88.solve(new int[]{1}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, -5)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3}, new int[]{-1}}, -5)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 2)));
System.out.println(json(Solution93.solve(new int[]{-6, -6}, new int[]{-3, 0, 4, 5, 8})));
System.out.println(json(Solution71.solve(new int[]{1, 5}, 9)));
System.out.println(json(Solution72.solve(new int[]{1, 1, 5}, 9, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{1, 1, 5}, 9, "upper")));
System.out.println(json(Solution73.solve(new int[]{1, 1, 5}, 9)));
System.out.println(json(Solution74.solve(new int[]{1, 1, 5}, 9)));
System.out.println(json(Solution75.solve(new int[]{1, 5}, 9)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 7, 5, 3, 1})));
System.out.println(json(Solution77.solve(new int[]{-3, 8, 7, 2, -6})));
System.out.println(json(Solution78.solve(new int[]{5, 1})));
System.out.println(json(Solution79.solve(new int[]{5, 1})));
System.out.println(json(Solution80.solve(new int[]{5, 1}, 9)));
System.out.println(json(Solution81.solve(new int[]{12, 14, 2, 13}, 1)));
System.out.println(json(Solution82.solve(new int[]{12, 14, 2, 13}, 1, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 4, 7, -5, 6, 5, -3, -6, -4}, 7)));
System.out.println(json(Solution84.solve(new int[]{2, 12, 13, 14})));
System.out.println(json(Solution85.solve(new int[]{12, 14, 2, 13}, 17)));
System.out.println(json(Solution86.solve(new int[]{12, 14, 2, 13}, 6)));
System.out.println(json(Solution87.solve(new int[]{12, 14, 2, 13}, 6)));
System.out.println(json(Solution88.solve(new int[]{11, 13, 1, 12}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, 9)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -3, -1}, new int[]{-5, -3, 0}, new int[]{-4, -1, 2}, new int[]{-4, 1, 2}}, 9)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 2)));
System.out.println(json(Solution93.solve(new int[]{1, 1, 5}, new int[]{-6, -4, -2, 1, 5, 7, 7, 8})));
System.out.println(json(Solution71.solve(new int[]{-6, -5, -3, 0, 6, 7, 8}, 7)));
System.out.println(json(Solution72.solve(new int[]{-6, -5, -3, 0, 6, 7, 8}, 7, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, -5, -3, 0, 6, 7, 8}, 7, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, -5, -3, 0, 6, 7, 8}, 7)));
System.out.println(json(Solution74.solve(new int[]{-6, -5, -3, 0, 6, 7, 8}, 7)));
System.out.println(json(Solution75.solve(new int[]{-6, -5, -3, 0, 6, 7, 8}, 7)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1})));
System.out.println(json(Solution77.solve(new int[]{1})));
System.out.println(json(Solution78.solve(new int[]{-6, -5, -3, 0, 6, 7, 8})));
System.out.println(json(Solution79.solve(new int[]{-6, -5, -3, 0, 6, 7, 8})));
System.out.println(json(Solution80.solve(new int[]{-6, -5, -3, 0, 6, 7, 8}, 7)));
System.out.println(json(Solution81.solve(new int[]{7}, 21)));
System.out.println(json(Solution82.solve(new int[]{7}, 1, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -2, 6, -4, 2}, 2)));
System.out.println(json(Solution84.solve(new int[]{7})));
System.out.println(json(Solution85.solve(new int[]{7}, 18)));
System.out.println(json(Solution86.solve(new int[]{7}, 2)));
System.out.println(json(Solution87.solve(new int[]{7}, 2)));
System.out.println(json(Solution88.solve(new int[]{6}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, 7)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -4, -3}, new int[]{-4, -3, -2}, new int[]{-2, -1, 0}, new int[]{-1, 1, 2}}, 7)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 2)));
System.out.println(json(Solution93.solve(new int[]{-6, -5, -3, 0, 6, 7, 8}, new int[]{-6, -6, -4, -1, 0, 7})));
System.out.println(json(Solution71.solve(new int[]{-5, 0, 7}, 14)));
System.out.println(json(Solution72.solve(new int[]{-5, 0, 7}, 14, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-5, 0, 7}, 14, "upper")));
System.out.println(json(Solution73.solve(new int[]{-5, 0, 7}, 14)));
System.out.println(json(Solution74.solve(new int[]{-5, 0, 7}, 14)));
System.out.println(json(Solution75.solve(new int[]{-5, 0, 7}, 14)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1, -3})));
System.out.println(json(Solution77.solve(new int[]{-5, -3, -2})));
System.out.println(json(Solution78.solve(new int[]{7, -5, 0})));
System.out.println(json(Solution79.solve(new int[]{7, -5, 0})));
System.out.println(json(Solution80.solve(new int[]{7, -5, 0}, 14)));
System.out.println(json(Solution81.solve(new int[]{12, 7, 11, 10, 3, 9}, 12)));
System.out.println(json(Solution82.solve(new int[]{12, 7, 11, 10, 3, 9}, 4, 1)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 8, 1, -6}, 3)));
System.out.println(json(Solution84.solve(new int[]{3, 7, 9, 10, 11, 12})));
System.out.println(json(Solution85.solve(new int[]{12, 7, 11, 10, 3, 9}, 2)));
System.out.println(json(Solution86.solve(new int[]{12, 7, 11, 10, 3, 9}, 4)));
System.out.println(json(Solution87.solve(new int[]{12, 7, 11, 10, 3, 9}, 4)));
System.out.println(json(Solution88.solve(new int[]{11, 6, 10, 9, 2, 8}, 5)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, 14)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4}, new int[]{-4}}, 14)));
}
static void batch6800() {
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{-5, 0, 7}, new int[]{7})));
System.out.println(json(Solution71.solve(new int[]{-2, 1, 3}, -3)));
System.out.println(json(Solution72.solve(new int[]{-2, 1, 1, 3, 3, 3}, -3, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-2, 1, 1, 3, 3, 3}, -3, "upper")));
System.out.println(json(Solution73.solve(new int[]{-2, 1, 1, 3, 3, 3}, -3)));
System.out.println(json(Solution74.solve(new int[]{-2, 1, 1, 3, 3, 3}, -3)));
System.out.println(json(Solution75.solve(new int[]{-2, 1, 3}, -3)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 7})));
System.out.println(json(Solution77.solve(new int[]{-2, 0, 3, -2, 0, -1, 6, -5})));
System.out.println(json(Solution78.solve(new int[]{3, -2, 1})));
System.out.println(json(Solution79.solve(new int[]{3, -2, 1})));
System.out.println(json(Solution80.solve(new int[]{3, -2, 1}, -3)));
System.out.println(json(Solution81.solve(new int[]{5, 5}, 16)));
System.out.println(json(Solution82.solve(new int[]{5, 5}, 4, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20}, 2)));
System.out.println(json(Solution84.solve(new int[]{5, 5})));
System.out.println(json(Solution85.solve(new int[]{5, 5}, 6)));
System.out.println(json(Solution86.solve(new int[]{5, 5}, 2)));
System.out.println(json(Solution87.solve(new int[]{5, 5}, 2)));
System.out.println(json(Solution88.solve(new int[]{4, 4}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, -3)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -1, 0}, new int[]{-3, -1, 0}, new int[]{-1, 0, 1}, new int[]{0, 0, 1}}, -3)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{-2, 1, 1, 3, 3, 3}, new int[]{-4, -2, -1, 0, 1, 1, 2, 5})));
System.out.println(json(Solution71.solve(new int[]{-3, 3, 8}, 9)));
System.out.println(json(Solution72.solve(new int[]{-3, 3, 8}, 9, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-3, 3, 8}, 9, "upper")));
System.out.println(json(Solution73.solve(new int[]{-3, 3, 8}, 9)));
System.out.println(json(Solution74.solve(new int[]{-3, 3, 8}, 9)));
System.out.println(json(Solution75.solve(new int[]{-3, 3, 8}, 9)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10, 8, 6, 4})));
System.out.println(json(Solution77.solve(new int[]{6, 8, 2, -5, -2, -1, 4})));
System.out.println(json(Solution78.solve(new int[]{8, -3, 3})));
System.out.println(json(Solution79.solve(new int[]{8, -3, 3})));
System.out.println(json(Solution80.solve(new int[]{8, -3, 3}, 9)));
System.out.println(json(Solution81.solve(new int[]{1, 4, 13}, 24)));
System.out.println(json(Solution82.solve(new int[]{1, 4, 13}, 5, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 5, 1}, 2)));
System.out.println(json(Solution84.solve(new int[]{1, 4, 13})));
System.out.println(json(Solution85.solve(new int[]{1, 4, 13}, 12)));
System.out.println(json(Solution86.solve(new int[]{1, 4, 13}, 5)));
System.out.println(json(Solution87.solve(new int[]{1, 4, 13}, 5)));
System.out.println(json(Solution88.solve(new int[]{0, 3, 12}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, 9)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -4, -4}, new int[]{-5, -3, -3}, new int[]{-3, -3, -1}, new int[]{-1, 0, 2}}, 9)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{-3, 3, 8}, new int[]{})));
System.out.println(json(Solution71.solve(new int[]{-6, -4, -1, 0, 3, 4, 5}, 3)));
System.out.println(json(Solution72.solve(new int[]{-6, -4, -1, 0, 3, 4, 5}, 3, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, -4, -1, 0, 3, 4, 5}, 3, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, -4, -1, 0, 3, 4, 5}, 3)));
System.out.println(json(Solution74.solve(new int[]{-6, -4, -1, 0, 3, 4, 5}, 3)));
System.out.println(json(Solution75.solve(new int[]{-6, -4, -1, 0, 3, 4, 5}, 3)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 15, 13, 11, 9, 7})));
System.out.println(json(Solution77.solve(new int[]{6, -2})));
System.out.println(json(Solution78.solve(new int[]{-4, -1, 0, 3, 4, 5, -6})));
System.out.println(json(Solution79.solve(new int[]{-4, -1, 0, 3, 4, 5, -6})));
System.out.println(json(Solution80.solve(new int[]{-4, -1, 0, 3, 4, 5, -6}, 3)));
System.out.println(json(Solution81.solve(new int[]{13, 7, 4, 4}, 17)));
System.out.println(json(Solution82.solve(new int[]{13, 7, 4, 4}, 1, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 2, 3, -6}, 2)));
System.out.println(json(Solution84.solve(new int[]{4, 4, 7, 13})));
System.out.println(json(Solution85.solve(new int[]{13, 7, 4, 4}, 31)));
System.out.println(json(Solution86.solve(new int[]{13, 7, 4, 4}, 5)));
System.out.println(json(Solution87.solve(new int[]{13, 7, 4, 4}, 5)));
System.out.println(json(Solution88.solve(new int[]{12, 6, 3, 3}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6, -4}}, 3)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -2, -1, -1}}, 3)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 7)));
System.out.println(json(Solution92.solve(4, 7, 26)));
System.out.println(json(Solution93.solve(new int[]{-6, -4, -1, 0, 3, 4, 5}, new int[]{-6, -4, -1, 0, 0, 2, 6})));
System.out.println(json(Solution71.solve(new int[]{0, 3}, 7)));
System.out.println(json(Solution72.solve(new int[]{0, 3}, 7, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{0, 3}, 7, "upper")));
System.out.println(json(Solution73.solve(new int[]{0, 3}, 7)));
System.out.println(json(Solution74.solve(new int[]{0, 3}, 7)));
System.out.println(json(Solution75.solve(new int[]{0, 3}, 7)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 4, 2, 0, -2, -4})));
System.out.println(json(Solution77.solve(new int[]{0, -6, 8, 6})));
System.out.println(json(Solution78.solve(new int[]{3, 0})));
System.out.println(json(Solution79.solve(new int[]{3, 0})));
System.out.println(json(Solution80.solve(new int[]{3, 0}, 7)));
System.out.println(json(Solution81.solve(new int[]{10, 11, 2}, 15)));
System.out.println(json(Solution82.solve(new int[]{10, 11, 2}, 1, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -4, 6, -2, 5}, 3)));
System.out.println(json(Solution84.solve(new int[]{2, 10, 11})));
System.out.println(json(Solution85.solve(new int[]{10, 11, 2}, 14)));
System.out.println(json(Solution86.solve(new int[]{10, 11, 2}, 3)));
System.out.println(json(Solution87.solve(new int[]{10, 11, 2}, 3)));
System.out.println(json(Solution88.solve(new int[]{9, 10, 1}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6, -4}}, 7)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -2, 0, 0}}, 7)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 8)));
System.out.println(json(Solution92.solve(4, 7, 10)));
System.out.println(json(Solution93.solve(new int[]{0, 3}, new int[]{-4, 0, 3, 4, 6, 7, 7, 8})));
System.out.println(json(Solution71.solve(new int[]{-6, -5, -2, 2, 3}, 8)));
}
static void batch6900() {
System.out.println(json(Solution72.solve(new int[]{-6, -5, -2, 2, 3}, 8, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, -5, -2, 2, 3}, 8, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, -5, -2, 2, 3}, 8)));
System.out.println(json(Solution74.solve(new int[]{-6, -5, -2, 2, 3}, 8)));
System.out.println(json(Solution75.solve(new int[]{-6, -5, -2, 2, 3}, 8)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1})));
System.out.println(json(Solution77.solve(new int[]{1})));
System.out.println(json(Solution78.solve(new int[]{-2, 2, 3, -6, -5})));
System.out.println(json(Solution79.solve(new int[]{-2, 2, 3, -6, -5})));
System.out.println(json(Solution80.solve(new int[]{-2, 2, 3, -6, -5}, 8)));
System.out.println(json(Solution81.solve(new int[]{4, 13}, 1)));
System.out.println(json(Solution82.solve(new int[]{4, 13}, 2, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 1, -4, 3, -3}, 5)));
System.out.println(json(Solution84.solve(new int[]{4, 13})));
System.out.println(json(Solution85.solve(new int[]{4, 13}, 11)));
System.out.println(json(Solution86.solve(new int[]{4, 13}, 4)));
System.out.println(json(Solution87.solve(new int[]{4, 13}, 4)));
System.out.println(json(Solution88.solve(new int[]{3, 12}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, 8)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4}, new int[]{-4}}, 8)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 6)));
System.out.println(json(Solution93.solve(new int[]{-6, -5, -2, 2, 3}, new int[]{-4, -2, 0, 3, 3, 3, 8})));
System.out.println(json(Solution71.solve(new int[]{-4, 0, 7, 8}, 11)));
System.out.println(json(Solution72.solve(new int[]{-4, 0, 7, 7, 8, 8}, 11, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-4, 0, 7, 7, 8, 8}, 11, "upper")));
System.out.println(json(Solution73.solve(new int[]{-4, 0, 7, 7, 8, 8}, 11)));
System.out.println(json(Solution74.solve(new int[]{-4, 0, 7, 7, 8, 8}, 11)));
System.out.println(json(Solution75.solve(new int[]{-4, 0, 7, 8}, 11)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 4})));
System.out.println(json(Solution77.solve(new int[]{-6, 0, 4, 5, -1, 0, -2})));
System.out.println(json(Solution78.solve(new int[]{7, 8, -4, 0})));
System.out.println(json(Solution79.solve(new int[]{7, 8, -4, 0})));
System.out.println(json(Solution80.solve(new int[]{7, 8, -4, 0}, 11)));
System.out.println(json(Solution81.solve(new int[]{3, 1, 12, 6, 12, 2, 15}, 21)));
System.out.println(json(Solution82.solve(new int[]{3, 1, 12, 6, 12, 2, 15}, 2, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -5, -1, -4, 1}, 3)));
System.out.println(json(Solution84.solve(new int[]{1, 2, 3, 6, 12, 12, 15})));
System.out.println(json(Solution85.solve(new int[]{3, 1, 12, 6, 12, 2, 15}, 14)));
System.out.println(json(Solution86.solve(new int[]{3, 1, 12, 6, 12, 2, 15}, 1)));
System.out.println(json(Solution87.solve(new int[]{3, 1, 12, 6, 12, 2, 15}, 1)));
System.out.println(json(Solution88.solve(new int[]{2, 0, 11, 5, 11, 1, 14}, 6)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, 11)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3}, new int[]{-2}}, 11)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{-4, 0, 7, 7, 8, 8}, new int[]{-5, -4, -3, -3, 1, 4})));
System.out.println(json(Solution71.solve(new int[]{7}, -3)));
System.out.println(json(Solution72.solve(new int[]{7}, -3, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{7}, -3, "upper")));
System.out.println(json(Solution73.solve(new int[]{7}, -3)));
System.out.println(json(Solution74.solve(new int[]{7}, -3)));
System.out.println(json(Solution75.solve(new int[]{7}, -3)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1})));
System.out.println(json(Solution77.solve(new int[]{-3, -5})));
System.out.println(json(Solution78.solve(new int[]{7})));
System.out.println(json(Solution79.solve(new int[]{7})));
System.out.println(json(Solution80.solve(new int[]{7}, -3)));
System.out.println(json(Solution81.solve(new int[]{14, 15, 10, 11}, 15)));
System.out.println(json(Solution82.solve(new int[]{14, 15, 10, 11}, 2, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 8, 5, -2, 6}, 6)));
System.out.println(json(Solution84.solve(new int[]{10, 11, 14, 15})));
System.out.println(json(Solution85.solve(new int[]{14, 15, 10, 11}, 9)));
System.out.println(json(Solution86.solve(new int[]{14, 15, 10, 11}, 2)));
System.out.println(json(Solution87.solve(new int[]{14, 15, 10, 11}, 2)));
System.out.println(json(Solution88.solve(new int[]{13, 14, 9, 10}, 3)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, -3)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5}, new int[]{-5}}, -3)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{7}, new int[]{5, 5})));
System.out.println(json(Solution71.solve(new int[]{}, 11)));
System.out.println(json(Solution72.solve(new int[]{}, 11, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{}, 11, "upper")));
System.out.println(json(Solution73.solve(new int[]{}, 11)));
System.out.println(json(Solution74.solve(new int[]{}, 11)));
System.out.println(json(Solution75.solve(new int[]{}, 11)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 7, 5, 3, 1})));
System.out.println(json(Solution77.solve(new int[]{1, 6, 1, -6, -4, -3, 8})));
System.out.println(json(Solution78.solve(new int[]{0})));
System.out.println(json(Solution79.solve(new int[]{0})));
System.out.println(json(Solution80.solve(new int[]{0}, 11)));
System.out.println(json(Solution81.solve(new int[]{13, 11, 5, 8}, 11)));
System.out.println(json(Solution82.solve(new int[]{13, 11, 5, 8}, 1, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 1}, 2)));
System.out.println(json(Solution84.solve(new int[]{5, 8, 11, 13})));
System.out.println(json(Solution85.solve(new int[]{13, 11, 5, 8}, 10)));
System.out.println(json(Solution86.solve(new int[]{13, 11, 5, 8}, 1)));
System.out.println(json(Solution87.solve(new int[]{13, 11, 5, 8}, 1)));
System.out.println(json(Solution88.solve(new int[]{12, 10, 4, 7}, 4)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, 11)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -3}, new int[]{-5, -2}, new int[]{-4, -1}}, 11)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 9)));
System.out.println(json(Solution92.solve(4, 7, 22)));
System.out.println(json(Solution93.solve(new int[]{}, new int[]{-6, -5, -4, -1, 4, 4, 4, 5})));
System.out.println(json(Solution71.solve(new int[]{7}, 2)));
System.out.println(json(Solution72.solve(new int[]{7}, 2, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{7}, 2, "upper")));
System.out.println(json(Solution73.solve(new int[]{7}, 2)));
System.out.println(json(Solution74.solve(new int[]{7}, 2)));
}
static void batch7000() {
System.out.println(json(Solution75.solve(new int[]{7}, 2)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10, 8, 6, 4})));
System.out.println(json(Solution77.solve(new int[]{3, 7})));
System.out.println(json(Solution78.solve(new int[]{7})));
System.out.println(json(Solution79.solve(new int[]{7})));
System.out.println(json(Solution80.solve(new int[]{7}, 2)));
System.out.println(json(Solution81.solve(new int[]{4, 6, 3, 6, 11, 3, 5}, 21)));
System.out.println(json(Solution82.solve(new int[]{4, 6, 3, 6, 11, 3, 5}, 1, 1)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 1, -6, -3}, 4)));
System.out.println(json(Solution84.solve(new int[]{3, 3, 4, 5, 6, 6, 11})));
System.out.println(json(Solution85.solve(new int[]{4, 6, 3, 6, 11, 3, 5}, 16)));
System.out.println(json(Solution86.solve(new int[]{4, 6, 3, 6, 11, 3, 5}, 3)));
System.out.println(json(Solution87.solve(new int[]{4, 6, 3, 6, 11, 3, 5}, 3)));
System.out.println(json(Solution88.solve(new int[]{3, 5, 2, 5, 10, 2, 4}, 5)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, 2)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -4}, new int[]{-3, -3}, new int[]{-1, 1}}, 2)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 6)));
System.out.println(json(Solution92.solve(8, 3, 2)));
System.out.println(json(Solution93.solve(new int[]{7}, new int[]{4})));
System.out.println(json(Solution71.solve(new int[]{-5, -1, 1, 3, 6}, 9)));
System.out.println(json(Solution72.solve(new int[]{-5, -5, -1, 1, 3, 6, 6}, 9, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-5, -5, -1, 1, 3, 6, 6}, 9, "upper")));
System.out.println(json(Solution73.solve(new int[]{-5, -5, -1, 1, 3, 6, 6}, 9)));
System.out.println(json(Solution74.solve(new int[]{-5, -5, -1, 1, 3, 6, 6}, 9)));
System.out.println(json(Solution75.solve(new int[]{-5, -1, 1, 3, 6}, 9)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 15, 13, 11, 9, 7})));
System.out.println(json(Solution77.solve(new int[]{6})));
System.out.println(json(Solution78.solve(new int[]{-5, -1, 1, 3, 6})));
System.out.println(json(Solution79.solve(new int[]{-5, -1, 1, 3, 6})));
System.out.println(json(Solution80.solve(new int[]{-5, -1, 1, 3, 6}, 9)));
System.out.println(json(Solution81.solve(new int[]{8}, 15)));
System.out.println(json(Solution82.solve(new int[]{8}, 2, 1)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 2, -2, 1}, 4)));
System.out.println(json(Solution84.solve(new int[]{8})));
System.out.println(json(Solution85.solve(new int[]{8}, 31)));
System.out.println(json(Solution86.solve(new int[]{8}, 3)));
System.out.println(json(Solution87.solve(new int[]{8}, 3)));
System.out.println(json(Solution88.solve(new int[]{7}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, 9)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -3}, new int[]{-4, -1}, new int[]{-3, -1}}, 9)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 3)));
System.out.println(json(Solution92.solve(4, 7, 6)));
System.out.println(json(Solution93.solve(new int[]{-5, -5, -1, 1, 3, 6, 6}, new int[]{-3})));
System.out.println(json(Solution71.solve(new int[]{-1, 4, 5}, 7)));
System.out.println(json(Solution72.solve(new int[]{-1, 4, 5}, 7, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-1, 4, 5}, 7, "upper")));
System.out.println(json(Solution73.solve(new int[]{-1, 4, 5}, 7)));
System.out.println(json(Solution74.solve(new int[]{-1, 4, 5}, 7)));
System.out.println(json(Solution75.solve(new int[]{-1, 4, 5}, 7)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 15, 13, 11, 9, 7, 5})));
System.out.println(json(Solution77.solve(new int[]{1})));
System.out.println(json(Solution78.solve(new int[]{5, -1, 4})));
System.out.println(json(Solution79.solve(new int[]{5, -1, 4})));
System.out.println(json(Solution80.solve(new int[]{5, -1, 4}, 7)));
System.out.println(json(Solution81.solve(new int[]{7, 14, 13, 12, 3, 14, 3}, 6)));
System.out.println(json(Solution82.solve(new int[]{7, 14, 13, 12, 3, 14, 3}, 5, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 2, 8}, 4)));
System.out.println(json(Solution84.solve(new int[]{3, 3, 7, 12, 13, 14, 14})));
System.out.println(json(Solution85.solve(new int[]{7, 14, 13, 12, 3, 14, 3}, 8)));
System.out.println(json(Solution86.solve(new int[]{7, 14, 13, 12, 3, 14, 3}, 9)));
System.out.println(json(Solution87.solve(new int[]{7, 14, 13, 12, 3, 14, 3}, 9)));
System.out.println(json(Solution88.solve(new int[]{6, 13, 12, 11, 2, 13, 2}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, 7)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -4}, new int[]{-5, -4}, new int[]{-4, -2}}, 7)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 7)));
System.out.println(json(Solution92.solve(8, 3, 2)));
System.out.println(json(Solution93.solve(new int[]{-1, 4, 5}, new int[]{7})));
System.out.println(json(Solution71.solve(new int[]{-6, 4, 8}, 4)));
System.out.println(json(Solution72.solve(new int[]{-6, -6, 4, 8}, 4, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, -6, 4, 8}, 4, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, -6, 4, 8}, 4)));
System.out.println(json(Solution74.solve(new int[]{-6, -6, 4, 8}, 4)));
System.out.println(json(Solution75.solve(new int[]{-6, 4, 8}, 4)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 15, 13, 11, 9, 7, 5})));
System.out.println(json(Solution77.solve(new int[]{7, 8, -6, -4, 8, 0})));
System.out.println(json(Solution78.solve(new int[]{8, -6, 4})));
System.out.println(json(Solution79.solve(new int[]{8, -6, 4})));
System.out.println(json(Solution80.solve(new int[]{8, -6, 4}, 4)));
System.out.println(json(Solution81.solve(new int[]{13}, 19)));
System.out.println(json(Solution82.solve(new int[]{13}, 2, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -2, 2, 8, -5, -1}, 6)));
System.out.println(json(Solution84.solve(new int[]{13})));
System.out.println(json(Solution85.solve(new int[]{13}, 29)));
System.out.println(json(Solution86.solve(new int[]{13}, 1)));
System.out.println(json(Solution87.solve(new int[]{13}, 1)));
System.out.println(json(Solution88.solve(new int[]{12}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, 4)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -2}, new int[]{-3, -1}, new int[]{-1, 0}}, 4)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 7)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{-6, -6, 4, 8}, new int[]{-6, -4, -1, -1})));
System.out.println(json(Solution71.solve(new int[]{-3, -2, 1, 3, 5, 8}, -2)));
System.out.println(json(Solution72.solve(new int[]{-3, -2, 1, 1, 3, 5, 8}, -2, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-3, -2, 1, 1, 3, 5, 8}, -2, "upper")));
System.out.println(json(Solution73.solve(new int[]{-3, -2, 1, 1, 3, 5, 8}, -2)));
System.out.println(json(Solution74.solve(new int[]{-3, -2, 1, 1, 3, 5, 8}, -2)));
System.out.println(json(Solution75.solve(new int[]{-3, -2, 1, 3, 5, 8}, -2)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10, 8, 6})));
System.out.println(json(Solution77.solve(new int[]{1})));
System.out.println(json(Solution78.solve(new int[]{8, -3, -2, 1, 3, 5})));
}
static void batch7100() {
System.out.println(json(Solution79.solve(new int[]{8, -3, -2, 1, 3, 5})));
System.out.println(json(Solution80.solve(new int[]{8, -3, -2, 1, 3, 5}, -2)));
System.out.println(json(Solution81.solve(new int[]{6, 13, 7, 4}, 24)));
System.out.println(json(Solution82.solve(new int[]{6, 13, 7, 4}, 3, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 6, -2, 8, 2}, 5)));
System.out.println(json(Solution84.solve(new int[]{4, 6, 7, 13})));
System.out.println(json(Solution85.solve(new int[]{6, 13, 7, 4}, 31)));
System.out.println(json(Solution86.solve(new int[]{6, 13, 7, 4}, 4)));
System.out.println(json(Solution87.solve(new int[]{6, 13, 7, 4}, 4)));
System.out.println(json(Solution88.solve(new int[]{5, 12, 6, 3}, 3)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, -2)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3}, new int[]{-2}}, -2)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{-3, -2, 1, 1, 3, 5, 8}, new int[]{-1})));
System.out.println(json(Solution71.solve(new int[]{-6, -4, -1, 2, 3, 7}, 4)));
System.out.println(json(Solution72.solve(new int[]{-6, -4, -1, 2, 3, 3, 7, 7}, 4, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, -4, -1, 2, 3, 3, 7, 7}, 4, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, -4, -1, 2, 3, 3, 7, 7}, 4)));
System.out.println(json(Solution74.solve(new int[]{-6, -4, -1, 2, 3, 3, 7, 7}, 4)));
System.out.println(json(Solution75.solve(new int[]{-6, -4, -1, 2, 3, 7}, 4)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 7, 5})));
System.out.println(json(Solution77.solve(new int[]{-1, 4, -5, -4, 4, -4})));
System.out.println(json(Solution78.solve(new int[]{7, -6, -4, -1, 2, 3})));
System.out.println(json(Solution79.solve(new int[]{7, -6, -4, -1, 2, 3})));
System.out.println(json(Solution80.solve(new int[]{7, -6, -4, -1, 2, 3}, 4)));
System.out.println(json(Solution81.solve(new int[]{5, 10, 2, 8, 13, 2}, 3)));
System.out.println(json(Solution82.solve(new int[]{5, 10, 2, 8, 13, 2}, 5, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 5, -2, 4, 1, 8}, 2)));
System.out.println(json(Solution84.solve(new int[]{2, 2, 5, 8, 10, 13})));
System.out.println(json(Solution85.solve(new int[]{5, 10, 2, 8, 13, 2}, 15)));
System.out.println(json(Solution86.solve(new int[]{5, 10, 2, 8, 13, 2}, 5)));
System.out.println(json(Solution87.solve(new int[]{5, 10, 2, 8, 13, 2}, 5)));
System.out.println(json(Solution88.solve(new int[]{4, 9, 1, 7, 12, 1}, 4)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, 4)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -1}, new int[]{-2, -1}, new int[]{-1, 0}}, 4)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 6)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{-6, -4, -1, 2, 3, 3, 7, 7}, new int[]{0, 6})));
System.out.println(json(Solution71.solve(new int[]{}, -6)));
System.out.println(json(Solution72.solve(new int[]{}, -6, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{}, -6, "upper")));
System.out.println(json(Solution73.solve(new int[]{}, -6)));
System.out.println(json(Solution74.solve(new int[]{}, -6)));
System.out.println(json(Solution75.solve(new int[]{}, -6)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1, -3})));
System.out.println(json(Solution77.solve(new int[]{-6, 1, -6, 7, 2, 1, 2, 8})));
System.out.println(json(Solution78.solve(new int[]{0})));
System.out.println(json(Solution79.solve(new int[]{0})));
System.out.println(json(Solution80.solve(new int[]{0}, -6)));
System.out.println(json(Solution81.solve(new int[]{11}, 7)));
System.out.println(json(Solution82.solve(new int[]{11}, 5, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 5, -1, -2, 8}, 4)));
System.out.println(json(Solution84.solve(new int[]{11})));
System.out.println(json(Solution85.solve(new int[]{11}, 1)));
System.out.println(json(Solution86.solve(new int[]{11}, 2)));
System.out.println(json(Solution87.solve(new int[]{11}, 2)));
System.out.println(json(Solution88.solve(new int[]{10}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6, -4}}, -6)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -3, -2, -2}}, -6)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 3)));
System.out.println(json(Solution92.solve(4, 7, 10)));
System.out.println(json(Solution93.solve(new int[]{}, new int[]{-2, 1, 2, 4, 6, 7})));
System.out.println(json(Solution71.solve(new int[]{-3, -2, 3, 4, 6}, -4)));
System.out.println(json(Solution72.solve(new int[]{-3, -2, 3, 4, 6}, -4, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-3, -2, 3, 4, 6}, -4, "upper")));
System.out.println(json(Solution73.solve(new int[]{-3, -2, 3, 4, 6}, -4)));
System.out.println(json(Solution74.solve(new int[]{-3, -2, 3, 4, 6}, -4)));
System.out.println(json(Solution75.solve(new int[]{-3, -2, 3, 4, 6}, -4)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1, -3, -5})));
System.out.println(json(Solution77.solve(new int[]{1})));
System.out.println(json(Solution78.solve(new int[]{4, 6, -3, -2, 3})));
System.out.println(json(Solution79.solve(new int[]{4, 6, -3, -2, 3})));
System.out.println(json(Solution80.solve(new int[]{4, 6, -3, -2, 3}, -4)));
System.out.println(json(Solution81.solve(new int[]{10, 12, 2, 4, 15, 11}, 22)));
System.out.println(json(Solution82.solve(new int[]{10, 12, 2, 4, 15, 11}, 3, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -4, 7, -1, 3, -5, -2, 5}, 5)));
System.out.println(json(Solution84.solve(new int[]{2, 4, 10, 11, 12, 15})));
System.out.println(json(Solution85.solve(new int[]{10, 12, 2, 4, 15, 11}, 14)));
System.out.println(json(Solution86.solve(new int[]{10, 12, 2, 4, 15, 11}, 6)));
System.out.println(json(Solution87.solve(new int[]{10, 12, 2, 4, 15, 11}, 6)));
System.out.println(json(Solution88.solve(new int[]{9, 11, 1, 3, 14, 10}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, -4)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -4, -3}, new int[]{-5, -4, -3}, new int[]{-4, -4, -1}, new int[]{-4, -4, 0}}, -4)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 14)));
System.out.println(json(Solution93.solve(new int[]{-3, -2, 3, 4, 6}, new int[]{-6, -3, -1, 3, 3, 4, 5, 6})));
System.out.println(json(Solution71.solve(new int[]{}, 13)));
System.out.println(json(Solution72.solve(new int[]{}, 13, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{}, 13, "upper")));
System.out.println(json(Solution73.solve(new int[]{}, 13)));
System.out.println(json(Solution74.solve(new int[]{}, 13)));
System.out.println(json(Solution75.solve(new int[]{}, 13)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 4})));
System.out.println(json(Solution77.solve(new int[]{4, 7})));
System.out.println(json(Solution78.solve(new int[]{0})));
System.out.println(json(Solution79.solve(new int[]{0})));
System.out.println(json(Solution80.solve(new int[]{0}, 13)));
System.out.println(json(Solution81.solve(new int[]{9, 4, 5, 6}, 14)));
System.out.println(json(Solution82.solve(new int[]{9, 4, 5, 6}, 3, 1)));
}
static void batch7200() {
System.out.println(json(Solution83.solve(new int[]{0, 20, -4, -5, 8}, 5)));
System.out.println(json(Solution84.solve(new int[]{4, 5, 6, 9})));
System.out.println(json(Solution85.solve(new int[]{9, 4, 5, 6}, 4)));
System.out.println(json(Solution86.solve(new int[]{9, 4, 5, 6}, 2)));
System.out.println(json(Solution87.solve(new int[]{9, 4, 5, 6}, 2)));
System.out.println(json(Solution88.solve(new int[]{8, 3, 4, 5}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, 13)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -2, 0}, new int[]{-3, -1, 0}, new int[]{-3, -1, 1}, new int[]{-1, -1, 1}}, 13)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 6)));
System.out.println(json(Solution93.solve(new int[]{}, new int[]{-6, -2, -1, 3, 4})));
System.out.println(json(Solution71.solve(new int[]{0, 6}, 14)));
System.out.println(json(Solution72.solve(new int[]{0, 6}, 14, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{0, 6}, 14, "upper")));
System.out.println(json(Solution73.solve(new int[]{0, 6}, 14)));
System.out.println(json(Solution74.solve(new int[]{0, 6}, 14)));
System.out.println(json(Solution75.solve(new int[]{0, 6}, 14)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10, 8})));
System.out.println(json(Solution77.solve(new int[]{-2, 6, 0, -2})));
System.out.println(json(Solution78.solve(new int[]{6, 0})));
System.out.println(json(Solution79.solve(new int[]{6, 0})));
System.out.println(json(Solution80.solve(new int[]{6, 0}, 14)));
System.out.println(json(Solution81.solve(new int[]{8, 2, 15, 8, 8, 1}, 15)));
System.out.println(json(Solution82.solve(new int[]{8, 2, 15, 8, 8, 1}, 4, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20}, 2)));
System.out.println(json(Solution84.solve(new int[]{1, 2, 8, 8, 8, 15})));
System.out.println(json(Solution85.solve(new int[]{8, 2, 15, 8, 8, 1}, 30)));
System.out.println(json(Solution86.solve(new int[]{8, 2, 15, 8, 8, 1}, 6)));
System.out.println(json(Solution87.solve(new int[]{8, 2, 15, 8, 8, 1}, 6)));
System.out.println(json(Solution88.solve(new int[]{7, 1, 14, 7, 7, 0}, 3)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, 14)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -5, -3}, new int[]{-4, -3, -2}, new int[]{-4, -1, -1}, new int[]{-3, 1, 3}}, 14)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 26)));
System.out.println(json(Solution93.solve(new int[]{0, 6}, new int[]{-6, -5, 4, 4, 6})));
System.out.println(json(Solution71.solve(new int[]{-3, 2, 3}, -1)));
System.out.println(json(Solution72.solve(new int[]{-3, 2, 3}, -1, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-3, 2, 3}, -1, "upper")));
System.out.println(json(Solution73.solve(new int[]{-3, 2, 3}, -1)));
System.out.println(json(Solution74.solve(new int[]{-3, 2, 3}, -1)));
System.out.println(json(Solution75.solve(new int[]{-3, 2, 3}, -1)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 15, 13, 11, 9, 7, 5})));
System.out.println(json(Solution77.solve(new int[]{-1, 4, 0})));
System.out.println(json(Solution78.solve(new int[]{3, -3, 2})));
System.out.println(json(Solution79.solve(new int[]{3, -3, 2})));
System.out.println(json(Solution80.solve(new int[]{3, -3, 2}, -1)));
System.out.println(json(Solution81.solve(new int[]{10, 4, 2}, 4)));
System.out.println(json(Solution82.solve(new int[]{10, 4, 2}, 1, 1)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -3, -2, -4, 3}, 3)));
System.out.println(json(Solution84.solve(new int[]{2, 4, 10})));
System.out.println(json(Solution85.solve(new int[]{10, 4, 2}, 3)));
System.out.println(json(Solution86.solve(new int[]{10, 4, 2}, 5)));
System.out.println(json(Solution87.solve(new int[]{10, 4, 2}, 5)));
System.out.println(json(Solution88.solve(new int[]{9, 3, 1}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6, -4}}, -1)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -1, 1, 3}}, -1)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 8)));
System.out.println(json(Solution92.solve(4, 7, 18)));
System.out.println(json(Solution93.solve(new int[]{-3, 2, 3}, new int[]{-6, -5, -4, -4, 0, 1, 7, 7})));
System.out.println(json(Solution71.solve(new int[]{-6, -4, -2, 2, 5, 8}, 3)));
System.out.println(json(Solution72.solve(new int[]{-6, -4, -2, 2, 5, 8}, 3, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, -4, -2, 2, 5, 8}, 3, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, -4, -2, 2, 5, 8}, 3)));
System.out.println(json(Solution74.solve(new int[]{-6, -4, -2, 2, 5, 8}, 3)));
System.out.println(json(Solution75.solve(new int[]{-6, -4, -2, 2, 5, 8}, 3)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 4, 2})));
System.out.println(json(Solution77.solve(new int[]{-5, 3, 2, 3})));
System.out.println(json(Solution78.solve(new int[]{5, 8, -6, -4, -2, 2})));
System.out.println(json(Solution79.solve(new int[]{5, 8, -6, -4, -2, 2})));
System.out.println(json(Solution80.solve(new int[]{5, 8, -6, -4, -2, 2}, 3)));
System.out.println(json(Solution81.solve(new int[]{13, 5}, 23)));
System.out.println(json(Solution82.solve(new int[]{13, 5}, 1, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -2, 1, -5, 8, 7}, 6)));
System.out.println(json(Solution84.solve(new int[]{5, 13})));
System.out.println(json(Solution85.solve(new int[]{13, 5}, 24)));
System.out.println(json(Solution86.solve(new int[]{13, 5}, 1)));
System.out.println(json(Solution87.solve(new int[]{13, 5}, 1)));
System.out.println(json(Solution88.solve(new int[]{12, 4}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, 3)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -3}, new int[]{-3, -2}, new int[]{-2, 0}}, 3)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 2)));
System.out.println(json(Solution92.solve(4, 7, 10)));
System.out.println(json(Solution93.solve(new int[]{-6, -4, -2, 2, 5, 8}, new int[]{-5, 2, 6, 7})));
System.out.println(json(Solution71.solve(new int[]{-5, -1, 0, 7, 8}, 12)));
System.out.println(json(Solution72.solve(new int[]{-5, -1, 0, 7, 8}, 12, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-5, -1, 0, 7, 8}, 12, "upper")));
System.out.println(json(Solution73.solve(new int[]{-5, -1, 0, 7, 8}, 12)));
System.out.println(json(Solution74.solve(new int[]{-5, -1, 0, 7, 8}, 12)));
System.out.println(json(Solution75.solve(new int[]{-5, -1, 0, 7, 8}, 12)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 7})));
System.out.println(json(Solution77.solve(new int[]{-5, 3})));
System.out.println(json(Solution78.solve(new int[]{-1, 0, 7, 8, -5})));
System.out.println(json(Solution79.solve(new int[]{-1, 0, 7, 8, -5})));
System.out.println(json(Solution80.solve(new int[]{-1, 0, 7, 8, -5}, 12)));
System.out.println(json(Solution81.solve(new int[]{15, 5, 4}, 16)));
System.out.println(json(Solution82.solve(new int[]{15, 5, 4}, 2, 1)));
System.out.println(json(Solution83.solve(new int[]{0, 20}, 2)));
System.out.println(json(Solution84.solve(new int[]{4, 5, 15})));
System.out.println(json(Solution85.solve(new int[]{15, 5, 4}, 27)));
System.out.println(json(Solution86.solve(new int[]{15, 5, 4}, 1)));
}
static void batch7300() {
System.out.println(json(Solution87.solve(new int[]{15, 5, 4}, 1)));
System.out.println(json(Solution88.solve(new int[]{14, 4, 3}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, 12)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -1}, new int[]{-2, 0}, new int[]{0, 1}}, 12)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 9)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{-5, -1, 0, 7, 8}, new int[]{-4, -1, -1, 0, 0, 3})));
System.out.println(json(Solution71.solve(new int[]{-5, -2, 8}, 13)));
System.out.println(json(Solution72.solve(new int[]{-5, -2, 8}, 13, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-5, -2, 8}, 13, "upper")));
System.out.println(json(Solution73.solve(new int[]{-5, -2, 8}, 13)));
System.out.println(json(Solution74.solve(new int[]{-5, -2, 8}, 13)));
System.out.println(json(Solution75.solve(new int[]{-5, -2, 8}, 13)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10, 8, 6, 4})));
System.out.println(json(Solution77.solve(new int[]{-2, -1, 0, 3, -5, 3, -5, -1})));
System.out.println(json(Solution78.solve(new int[]{-2, 8, -5})));
System.out.println(json(Solution79.solve(new int[]{-2, 8, -5})));
System.out.println(json(Solution80.solve(new int[]{-2, 8, -5}, 13)));
System.out.println(json(Solution81.solve(new int[]{9, 12, 2, 3, 7, 13, 15}, 16)));
System.out.println(json(Solution82.solve(new int[]{9, 12, 2, 3, 7, 13, 15}, 5, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -1, 6, 7, 8, 2, -4}, 3)));
System.out.println(json(Solution84.solve(new int[]{2, 3, 7, 9, 12, 13, 15})));
System.out.println(json(Solution85.solve(new int[]{9, 12, 2, 3, 7, 13, 15}, 22)));
System.out.println(json(Solution86.solve(new int[]{9, 12, 2, 3, 7, 13, 15}, 7)));
System.out.println(json(Solution87.solve(new int[]{9, 12, 2, 3, 7, 13, 15}, 7)));
System.out.println(json(Solution88.solve(new int[]{8, 11, 1, 2, 6, 12, 14}, 7)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, 13)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4}, new int[]{-4}}, 13)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 22)));
System.out.println(json(Solution93.solve(new int[]{-5, -2, 8}, new int[]{-6, -5, -5, -4, -4, -3, -1, 5})));
System.out.println(json(Solution71.solve(new int[]{-3, -1, 6, 7}, -2)));
System.out.println(json(Solution72.solve(new int[]{-3, -1, 6, 7}, -2, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-3, -1, 6, 7}, -2, "upper")));
System.out.println(json(Solution73.solve(new int[]{-3, -1, 6, 7}, -2)));
System.out.println(json(Solution74.solve(new int[]{-3, -1, 6, 7}, -2)));
System.out.println(json(Solution75.solve(new int[]{-3, -1, 6, 7}, -2)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 7, 5, 3, 1, -1})));
System.out.println(json(Solution77.solve(new int[]{4})));
System.out.println(json(Solution78.solve(new int[]{-1, 6, 7, -3})));
System.out.println(json(Solution79.solve(new int[]{-1, 6, 7, -3})));
System.out.println(json(Solution80.solve(new int[]{-1, 6, 7, -3}, -2)));
System.out.println(json(Solution81.solve(new int[]{11, 7, 14}, 4)));
System.out.println(json(Solution82.solve(new int[]{11, 7, 14}, 2, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 4, 8, 3, -6, -3}, 4)));
System.out.println(json(Solution84.solve(new int[]{7, 11, 14})));
System.out.println(json(Solution85.solve(new int[]{11, 7, 14}, 23)));
System.out.println(json(Solution86.solve(new int[]{11, 7, 14}, 2)));
System.out.println(json(Solution87.solve(new int[]{11, 7, 14}, 2)));
System.out.println(json(Solution88.solve(new int[]{10, 6, 13}, 3)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, -2)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -4}, new int[]{-4, -3}, new int[]{-3, -2}}, -2)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 7)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{-3, -1, 6, 7}, new int[]{-2, 4})));
System.out.println(json(Solution71.solve(new int[]{-5, -3, -2, -1, 2, 3}, 6)));
System.out.println(json(Solution72.solve(new int[]{-5, -3, -2, -1, 2, 3}, 6, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-5, -3, -2, -1, 2, 3}, 6, "upper")));
System.out.println(json(Solution73.solve(new int[]{-5, -3, -2, -1, 2, 3}, 6)));
System.out.println(json(Solution74.solve(new int[]{-5, -3, -2, -1, 2, 3}, 6)));
System.out.println(json(Solution75.solve(new int[]{-5, -3, -2, -1, 2, 3}, 6)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 4, 2, 0})));
System.out.println(json(Solution77.solve(new int[]{-2, -6, -5, -6})));
System.out.println(json(Solution78.solve(new int[]{2, 3, -5, -3, -2, -1})));
System.out.println(json(Solution79.solve(new int[]{2, 3, -5, -3, -2, -1})));
System.out.println(json(Solution80.solve(new int[]{2, 3, -5, -3, -2, -1}, 6)));
System.out.println(json(Solution81.solve(new int[]{8, 15, 10}, 13)));
System.out.println(json(Solution82.solve(new int[]{8, 15, 10}, 5, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -6, 2, 8}, 4)));
System.out.println(json(Solution84.solve(new int[]{8, 10, 15})));
System.out.println(json(Solution85.solve(new int[]{8, 15, 10}, 32)));
System.out.println(json(Solution86.solve(new int[]{8, 15, 10}, 4)));
System.out.println(json(Solution87.solve(new int[]{8, 15, 10}, 4)));
System.out.println(json(Solution88.solve(new int[]{7, 14, 9}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, 6)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -2}, new int[]{-3, -2}, new int[]{-1, 1}}, 6)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 5)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{-5, -3, -2, -1, 2, 3}, new int[]{8})));
System.out.println(json(Solution71.solve(new int[]{}, 0)));
System.out.println(json(Solution72.solve(new int[]{}, 0, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{}, 0, "upper")));
System.out.println(json(Solution73.solve(new int[]{}, 0)));
System.out.println(json(Solution74.solve(new int[]{}, 0)));
System.out.println(json(Solution75.solve(new int[]{}, 0)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10})));
System.out.println(json(Solution77.solve(new int[]{-5, 2, 4, -3})));
System.out.println(json(Solution78.solve(new int[]{0})));
System.out.println(json(Solution79.solve(new int[]{0})));
System.out.println(json(Solution80.solve(new int[]{0}, 0)));
System.out.println(json(Solution81.solve(new int[]{11, 7, 8}, 15)));
System.out.println(json(Solution82.solve(new int[]{11, 7, 8}, 4, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -3, 8}, 3)));
System.out.println(json(Solution84.solve(new int[]{7, 8, 11})));
System.out.println(json(Solution85.solve(new int[]{11, 7, 8}, 15)));
System.out.println(json(Solution86.solve(new int[]{11, 7, 8}, 5)));
System.out.println(json(Solution87.solve(new int[]{11, 7, 8}, 5)));
System.out.println(json(Solution88.solve(new int[]{10, 6, 7}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, 0)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -4}, new int[]{-3, -2}, new int[]{-1, 0}}, 0)));
}
static void batch7400() {
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 6)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{}, new int[]{-4, -4, -1, -1, -1, 5, 7, 8})));
System.out.println(json(Solution71.solve(new int[]{-4, -1, 2}, 13)));
System.out.println(json(Solution72.solve(new int[]{-4, -1, 2, 2}, 13, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-4, -1, 2, 2}, 13, "upper")));
System.out.println(json(Solution73.solve(new int[]{-4, -1, 2, 2}, 13)));
System.out.println(json(Solution74.solve(new int[]{-4, -1, 2, 2}, 13)));
System.out.println(json(Solution75.solve(new int[]{-4, -1, 2}, 13)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1, -3, -5})));
System.out.println(json(Solution77.solve(new int[]{-5, 0, -2, -5, -1})));
System.out.println(json(Solution78.solve(new int[]{2, -4, -1})));
System.out.println(json(Solution79.solve(new int[]{2, -4, -1})));
System.out.println(json(Solution80.solve(new int[]{2, -4, -1}, 13)));
System.out.println(json(Solution81.solve(new int[]{15, 8}, 19)));
System.out.println(json(Solution82.solve(new int[]{15, 8}, 5, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 2, 3}, 3)));
System.out.println(json(Solution84.solve(new int[]{8, 15})));
System.out.println(json(Solution85.solve(new int[]{15, 8}, 9)));
System.out.println(json(Solution86.solve(new int[]{15, 8}, 2)));
System.out.println(json(Solution87.solve(new int[]{15, 8}, 2)));
System.out.println(json(Solution88.solve(new int[]{14, 7}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, 13)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -3, -1}, new int[]{-1, 0, 1}, new int[]{0, 0, 1}, new int[]{2, 3, 3}}, 13)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 14)));
System.out.println(json(Solution93.solve(new int[]{-4, -1, 2, 2}, new int[]{-4, -2, 1, 5, 7, 8})));
System.out.println(json(Solution71.solve(new int[]{-5, 3, 5}, 12)));
System.out.println(json(Solution72.solve(new int[]{-5, 3, 5}, 12, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-5, 3, 5}, 12, "upper")));
System.out.println(json(Solution73.solve(new int[]{-5, 3, 5}, 12)));
System.out.println(json(Solution74.solve(new int[]{-5, 3, 5}, 12)));
System.out.println(json(Solution75.solve(new int[]{-5, 3, 5}, 12)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 4})));
System.out.println(json(Solution77.solve(new int[]{2, 7, 5, 0})));
System.out.println(json(Solution78.solve(new int[]{5, -5, 3})));
System.out.println(json(Solution79.solve(new int[]{5, -5, 3})));
System.out.println(json(Solution80.solve(new int[]{5, -5, 3}, 12)));
System.out.println(json(Solution81.solve(new int[]{2, 8, 7, 12, 14}, 6)));
System.out.println(json(Solution82.solve(new int[]{2, 8, 7, 12, 14}, 1, 1)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 1, 8, -5, 4}, 4)));
System.out.println(json(Solution84.solve(new int[]{2, 7, 8, 12, 14})));
System.out.println(json(Solution85.solve(new int[]{2, 8, 7, 12, 14}, 6)));
System.out.println(json(Solution86.solve(new int[]{2, 8, 7, 12, 14}, 2)));
System.out.println(json(Solution87.solve(new int[]{2, 8, 7, 12, 14}, 2)));
System.out.println(json(Solution88.solve(new int[]{1, 7, 6, 11, 13}, 4)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, 12)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -2}, new int[]{-2, -1}, new int[]{0, 2}}, 12)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 8)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{-5, 3, 5}, new int[]{})));
System.out.println(json(Solution71.solve(new int[]{6, 8}, 8)));
System.out.println(json(Solution72.solve(new int[]{6, 8}, 8, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{6, 8}, 8, "upper")));
System.out.println(json(Solution73.solve(new int[]{6, 8}, 8)));
System.out.println(json(Solution74.solve(new int[]{6, 8}, 8)));
System.out.println(json(Solution75.solve(new int[]{6, 8}, 8)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1, -3})));
System.out.println(json(Solution77.solve(new int[]{0, 7})));
System.out.println(json(Solution78.solve(new int[]{6, 8})));
System.out.println(json(Solution79.solve(new int[]{6, 8})));
System.out.println(json(Solution80.solve(new int[]{6, 8}, 8)));
System.out.println(json(Solution81.solve(new int[]{7}, 21)));
System.out.println(json(Solution82.solve(new int[]{7}, 4, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 6, -4, 7, -5, -6}, 5)));
System.out.println(json(Solution84.solve(new int[]{7})));
System.out.println(json(Solution85.solve(new int[]{7}, 14)));
System.out.println(json(Solution86.solve(new int[]{7}, 3)));
System.out.println(json(Solution87.solve(new int[]{7}, 3)));
System.out.println(json(Solution88.solve(new int[]{6}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, 8)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3}, new int[]{-3}}, 8)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{6, 8}, new int[]{-1, 3, 4, 8})));
System.out.println(json(Solution71.solve(new int[]{1, 5}, -4)));
System.out.println(json(Solution72.solve(new int[]{1, 5}, -4, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{1, 5}, -4, "upper")));
System.out.println(json(Solution73.solve(new int[]{1, 5}, -4)));
System.out.println(json(Solution74.solve(new int[]{1, 5}, -4)));
System.out.println(json(Solution75.solve(new int[]{1, 5}, -4)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 7, 5, 3})));
System.out.println(json(Solution77.solve(new int[]{1})));
System.out.println(json(Solution78.solve(new int[]{1, 5})));
System.out.println(json(Solution79.solve(new int[]{1, 5})));
System.out.println(json(Solution80.solve(new int[]{1, 5}, -4)));
System.out.println(json(Solution81.solve(new int[]{4, 11, 2, 11, 4}, 19)));
System.out.println(json(Solution82.solve(new int[]{4, 11, 2, 11, 4}, 3, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 3}, 2)));
System.out.println(json(Solution84.solve(new int[]{2, 4, 4, 11, 11})));
System.out.println(json(Solution85.solve(new int[]{4, 11, 2, 11, 4}, 27)));
System.out.println(json(Solution86.solve(new int[]{4, 11, 2, 11, 4}, 2)));
System.out.println(json(Solution87.solve(new int[]{4, 11, 2, 11, 4}, 2)));
System.out.println(json(Solution88.solve(new int[]{3, 10, 1, 10, 3}, 4)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6, -4}}, -4)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -3, -3, -3}}, -4)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 8)));
System.out.println(json(Solution92.solve(8, 3, 2)));
System.out.println(json(Solution93.solve(new int[]{1, 5}, new int[]{-6, -4, -1, 1, 2, 4, 7, 7})));
System.out.println(json(Solution71.solve(new int[]{-6, -1, 0}, 9)));
}
static void batch7500() {
System.out.println(json(Solution72.solve(new int[]{-6, -1, -1, 0}, 9, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, -1, -1, 0}, 9, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, -1, -1, 0}, 9)));
System.out.println(json(Solution74.solve(new int[]{-6, -1, -1, 0}, 9)));
System.out.println(json(Solution75.solve(new int[]{-6, -1, 0}, 9)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10, 8, 6, 4})));
System.out.println(json(Solution77.solve(new int[]{-3, -5, -1, -6, -2, 0, -6})));
System.out.println(json(Solution78.solve(new int[]{0, -6, -1})));
System.out.println(json(Solution79.solve(new int[]{0, -6, -1})));
System.out.println(json(Solution80.solve(new int[]{0, -6, -1}, 9)));
System.out.println(json(Solution81.solve(new int[]{14, 15, 11, 14, 9, 9, 10}, 18)));
System.out.println(json(Solution82.solve(new int[]{14, 15, 11, 14, 9, 9, 10}, 2, 1)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 5, 7, -4, -3}, 3)));
System.out.println(json(Solution84.solve(new int[]{9, 9, 10, 11, 14, 14, 15})));
System.out.println(json(Solution85.solve(new int[]{14, 15, 11, 14, 9, 9, 10}, 30)));
System.out.println(json(Solution86.solve(new int[]{14, 15, 11, 14, 9, 9, 10}, 8)));
System.out.println(json(Solution87.solve(new int[]{14, 15, 11, 14, 9, 9, 10}, 8)));
System.out.println(json(Solution88.solve(new int[]{13, 14, 10, 13, 8, 8, 9}, 7)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, 9)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -3}, new int[]{-5, -1}, new int[]{-4, 0}}, 9)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 5)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{-6, -1, -1, 0}, new int[]{-1, 0, 1, 2, 3, 4, 8})));
System.out.println(json(Solution71.solve(new int[]{-3, 0, 3, 4, 8}, -2)));
System.out.println(json(Solution72.solve(new int[]{-3, 0, 0, 3, 3, 4, 8}, -2, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-3, 0, 0, 3, 3, 4, 8}, -2, "upper")));
System.out.println(json(Solution73.solve(new int[]{-3, 0, 0, 3, 3, 4, 8}, -2)));
System.out.println(json(Solution74.solve(new int[]{-3, 0, 0, 3, 3, 4, 8}, -2)));
System.out.println(json(Solution75.solve(new int[]{-3, 0, 3, 4, 8}, -2)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1, -3, -5, -7})));
System.out.println(json(Solution77.solve(new int[]{1, -6, -1, -3, 6, 7, 5, 6})));
System.out.println(json(Solution78.solve(new int[]{4, 8, -3, 0, 3})));
System.out.println(json(Solution79.solve(new int[]{4, 8, -3, 0, 3})));
System.out.println(json(Solution80.solve(new int[]{4, 8, -3, 0, 3}, -2)));
System.out.println(json(Solution81.solve(new int[]{9, 12, 2, 11, 4, 12, 15}, 15)));
System.out.println(json(Solution82.solve(new int[]{9, 12, 2, 11, 4, 12, 15}, 2, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -4, 8, 4, 7}, 5)));
System.out.println(json(Solution84.solve(new int[]{2, 4, 9, 11, 12, 12, 15})));
System.out.println(json(Solution85.solve(new int[]{9, 12, 2, 11, 4, 12, 15}, 26)));
System.out.println(json(Solution86.solve(new int[]{9, 12, 2, 11, 4, 12, 15}, 3)));
System.out.println(json(Solution87.solve(new int[]{9, 12, 2, 11, 4, 12, 15}, 3)));
System.out.println(json(Solution88.solve(new int[]{8, 11, 1, 10, 3, 11, 14}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, -2)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5}, new int[]{-5}}, -2)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 2)));
System.out.println(json(Solution93.solve(new int[]{-3, 0, 0, 3, 3, 4, 8}, new int[]{-6, -3, 4, 8})));
System.out.println(json(Solution71.solve(new int[]{-3, -2, 3, 5, 6, 7}, -8)));
System.out.println(json(Solution72.solve(new int[]{-3, -3, -2, 3, 5, 6, 7}, -8, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-3, -3, -2, 3, 5, 6, 7}, -8, "upper")));
System.out.println(json(Solution73.solve(new int[]{-3, -3, -2, 3, 5, 6, 7}, -8)));
System.out.println(json(Solution74.solve(new int[]{-3, -3, -2, 3, 5, 6, 7}, -8)));
System.out.println(json(Solution75.solve(new int[]{-3, -2, 3, 5, 6, 7}, -8)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 15, 13, 11, 9, 7})));
System.out.println(json(Solution77.solve(new int[]{-4})));
System.out.println(json(Solution78.solve(new int[]{6, 7, -3, -2, 3, 5})));
System.out.println(json(Solution79.solve(new int[]{6, 7, -3, -2, 3, 5})));
System.out.println(json(Solution80.solve(new int[]{6, 7, -3, -2, 3, 5}, -8)));
System.out.println(json(Solution81.solve(new int[]{7, 6, 8, 6, 14, 1, 7}, 1)));
System.out.println(json(Solution82.solve(new int[]{7, 6, 8, 6, 14, 1, 7}, 2, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 3, -2}, 4)));
System.out.println(json(Solution84.solve(new int[]{1, 6, 6, 7, 7, 8, 14})));
System.out.println(json(Solution85.solve(new int[]{7, 6, 8, 6, 14, 1, 7}, 32)));
System.out.println(json(Solution86.solve(new int[]{7, 6, 8, 6, 14, 1, 7}, 8)));
System.out.println(json(Solution87.solve(new int[]{7, 6, 8, 6, 14, 1, 7}, 8)));
System.out.println(json(Solution88.solve(new int[]{6, 5, 7, 5, 13, 0, 6}, 6)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, -8)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -5}, new int[]{-3, -3}, new int[]{-1, 0}}, -8)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 22)));
System.out.println(json(Solution93.solve(new int[]{-3, -3, -2, 3, 5, 6, 7}, new int[]{-1, 2})));
System.out.println(json(Solution71.solve(new int[]{-6, -1, 1, 3, 4}, -3)));
System.out.println(json(Solution72.solve(new int[]{-6, -1, 1, 3, 4}, -3, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, -1, 1, 3, 4}, -3, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, -1, 1, 3, 4}, -3)));
System.out.println(json(Solution74.solve(new int[]{-6, -1, 1, 3, 4}, -3)));
System.out.println(json(Solution75.solve(new int[]{-6, -1, 1, 3, 4}, -3)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1, -3, -5, -7})));
System.out.println(json(Solution77.solve(new int[]{1})));
System.out.println(json(Solution78.solve(new int[]{-1, 1, 3, 4, -6})));
System.out.println(json(Solution79.solve(new int[]{-1, 1, 3, 4, -6})));
System.out.println(json(Solution80.solve(new int[]{-1, 1, 3, 4, -6}, -3)));
System.out.println(json(Solution81.solve(new int[]{11, 11}, 23)));
System.out.println(json(Solution82.solve(new int[]{11, 11}, 3, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -2, 6, 4}, 5)));
System.out.println(json(Solution84.solve(new int[]{11, 11})));
System.out.println(json(Solution85.solve(new int[]{11, 11}, 34)));
System.out.println(json(Solution86.solve(new int[]{11, 11}, 2)));
System.out.println(json(Solution87.solve(new int[]{11, 11}, 2)));
System.out.println(json(Solution88.solve(new int[]{10, 10}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, -3)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -1, 0}, new int[]{-2, -1, 2}, new int[]{-1, 0, 2}, new int[]{0, 2, 4}}, -3)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{-6, -1, 1, 3, 4}, new int[]{-2})));
System.out.println(json(Solution71.solve(new int[]{-4, -3, 0, 2, 7}, -7)));
System.out.println(json(Solution72.solve(new int[]{-4, -4, -3, -3, -3, 0, 2, 7}, -7, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-4, -4, -3, -3, -3, 0, 2, 7}, -7, "upper")));
System.out.println(json(Solution73.solve(new int[]{-4, -4, -3, -3, -3, 0, 2, 7}, -7)));
System.out.println(json(Solution74.solve(new int[]{-4, -4, -3, -3, -3, 0, 2, 7}, -7)));
}
static void batch7600() {
System.out.println(json(Solution75.solve(new int[]{-4, -3, 0, 2, 7}, -7)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1, -3, -5, -7})));
System.out.println(json(Solution77.solve(new int[]{7, -6, 7})));
System.out.println(json(Solution78.solve(new int[]{-4, -3, 0, 2, 7})));
System.out.println(json(Solution79.solve(new int[]{-4, -3, 0, 2, 7})));
System.out.println(json(Solution80.solve(new int[]{-4, -3, 0, 2, 7}, -7)));
System.out.println(json(Solution81.solve(new int[]{9, 10, 9, 15, 5, 15}, 7)));
System.out.println(json(Solution82.solve(new int[]{9, 10, 9, 15, 5, 15}, 1, 1)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -6, 6, 7}, 4)));
System.out.println(json(Solution84.solve(new int[]{5, 9, 9, 10, 15, 15})));
System.out.println(json(Solution85.solve(new int[]{9, 10, 9, 15, 5, 15}, 9)));
System.out.println(json(Solution86.solve(new int[]{9, 10, 9, 15, 5, 15}, 1)));
System.out.println(json(Solution87.solve(new int[]{9, 10, 9, 15, 5, 15}, 1)));
System.out.println(json(Solution88.solve(new int[]{8, 9, 8, 14, 4, 14}, 4)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, -7)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -3}, new int[]{-3, -2}, new int[]{-1, 0}}, -7)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 7)));
System.out.println(json(Solution92.solve(4, 7, 14)));
System.out.println(json(Solution93.solve(new int[]{-4, -4, -3, -3, -3, 0, 2, 7}, new int[]{0, 0, 1, 5, 8})));
System.out.println(json(Solution71.solve(new int[]{-3}, 6)));
System.out.println(json(Solution72.solve(new int[]{-3}, 6, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-3}, 6, "upper")));
System.out.println(json(Solution73.solve(new int[]{-3}, 6)));
System.out.println(json(Solution74.solve(new int[]{-3}, 6)));
System.out.println(json(Solution75.solve(new int[]{-3}, 6)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 4, 2, 0, -2})));
System.out.println(json(Solution77.solve(new int[]{-3, -2, -1})));
System.out.println(json(Solution78.solve(new int[]{-3})));
System.out.println(json(Solution79.solve(new int[]{-3})));
System.out.println(json(Solution80.solve(new int[]{-3}, 6)));
System.out.println(json(Solution81.solve(new int[]{15, 10, 1, 12, 5, 11}, 1)));
System.out.println(json(Solution82.solve(new int[]{15, 10, 1, 12, 5, 11}, 2, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 2, 4, 8, -4, -2}, 2)));
System.out.println(json(Solution84.solve(new int[]{1, 5, 10, 11, 12, 15})));
System.out.println(json(Solution85.solve(new int[]{15, 10, 1, 12, 5, 11}, 22)));
System.out.println(json(Solution86.solve(new int[]{15, 10, 1, 12, 5, 11}, 5)));
System.out.println(json(Solution87.solve(new int[]{15, 10, 1, 12, 5, 11}, 5)));
System.out.println(json(Solution88.solve(new int[]{14, 9, 0, 11, 4, 10}, 4)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, 6)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -4}, new int[]{-4, -4}, new int[]{-3, -1}}, 6)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 4)));
System.out.println(json(Solution92.solve(8, 3, 2)));
System.out.println(json(Solution93.solve(new int[]{-3}, new int[]{-6, 3, 4, 6, 7, 8})));
System.out.println(json(Solution71.solve(new int[]{}, 4)));
System.out.println(json(Solution72.solve(new int[]{}, 4, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{}, 4, "upper")));
System.out.println(json(Solution73.solve(new int[]{}, 4)));
System.out.println(json(Solution74.solve(new int[]{}, 4)));
System.out.println(json(Solution75.solve(new int[]{}, 4)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 7, 5, 3})));
System.out.println(json(Solution77.solve(new int[]{1})));
System.out.println(json(Solution78.solve(new int[]{0})));
System.out.println(json(Solution79.solve(new int[]{0})));
System.out.println(json(Solution80.solve(new int[]{0}, 4)));
System.out.println(json(Solution81.solve(new int[]{9, 5, 5, 13}, 20)));
System.out.println(json(Solution82.solve(new int[]{9, 5, 5, 13}, 3, 1)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -5, -1, -2}, 5)));
System.out.println(json(Solution84.solve(new int[]{5, 5, 9, 13})));
System.out.println(json(Solution85.solve(new int[]{9, 5, 5, 13}, 28)));
System.out.println(json(Solution86.solve(new int[]{9, 5, 5, 13}, 2)));
System.out.println(json(Solution87.solve(new int[]{9, 5, 5, 13}, 2)));
System.out.println(json(Solution88.solve(new int[]{8, 4, 4, 12}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, 4)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -5, -3}, new int[]{-5, -5, -2}, new int[]{-4, -3, -2}, new int[]{-2, -2, 0}}, 4)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 26)));
System.out.println(json(Solution93.solve(new int[]{}, new int[]{-3, 4})));
System.out.println(json(Solution71.solve(new int[]{-3, 2, 6, 7}, 0)));
System.out.println(json(Solution72.solve(new int[]{-3, -3, 2, 6, 6, 7}, 0, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-3, -3, 2, 6, 6, 7}, 0, "upper")));
System.out.println(json(Solution73.solve(new int[]{-3, -3, 2, 6, 6, 7}, 0)));
System.out.println(json(Solution74.solve(new int[]{-3, -3, 2, 6, 6, 7}, 0)));
System.out.println(json(Solution75.solve(new int[]{-3, 2, 6, 7}, 0)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 7})));
System.out.println(json(Solution77.solve(new int[]{-3, -4})));
System.out.println(json(Solution78.solve(new int[]{-3, 2, 6, 7})));
System.out.println(json(Solution79.solve(new int[]{-3, 2, 6, 7})));
System.out.println(json(Solution80.solve(new int[]{-3, 2, 6, 7}, 0)));
System.out.println(json(Solution81.solve(new int[]{5, 10, 3, 6, 3, 3}, 11)));
System.out.println(json(Solution82.solve(new int[]{5, 10, 3, 6, 3, 3}, 2, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 7, 4, 3, 5}, 5)));
System.out.println(json(Solution84.solve(new int[]{3, 3, 3, 5, 6, 10})));
System.out.println(json(Solution85.solve(new int[]{5, 10, 3, 6, 3, 3}, 18)));
System.out.println(json(Solution86.solve(new int[]{5, 10, 3, 6, 3, 3}, 4)));
System.out.println(json(Solution87.solve(new int[]{5, 10, 3, 6, 3, 3}, 4)));
System.out.println(json(Solution88.solve(new int[]{4, 9, 2, 5, 2, 2}, 3)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, 0)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4}, new int[]{-3}}, 0)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 2)));
System.out.println(json(Solution93.solve(new int[]{-3, -3, 2, 6, 6, 7}, new int[]{})));
System.out.println(json(Solution71.solve(new int[]{-6, 2, 4, 5}, -3)));
System.out.println(json(Solution72.solve(new int[]{-6, 2, 4, 5}, -3, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, 2, 4, 5}, -3, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, 2, 4, 5}, -3)));
System.out.println(json(Solution74.solve(new int[]{-6, 2, 4, 5}, -3)));
System.out.println(json(Solution75.solve(new int[]{-6, 2, 4, 5}, -3)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 4, 2})));
System.out.println(json(Solution77.solve(new int[]{1})));
System.out.println(json(Solution78.solve(new int[]{4, 5, -6, 2})));
}
static void batch7700() {
System.out.println(json(Solution79.solve(new int[]{4, 5, -6, 2})));
System.out.println(json(Solution80.solve(new int[]{4, 5, -6, 2}, -3)));
System.out.println(json(Solution81.solve(new int[]{12, 9, 9}, 16)));
System.out.println(json(Solution82.solve(new int[]{12, 9, 9}, 5, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 6, -6}, 2)));
System.out.println(json(Solution84.solve(new int[]{9, 9, 12})));
System.out.println(json(Solution85.solve(new int[]{12, 9, 9}, 23)));
System.out.println(json(Solution86.solve(new int[]{12, 9, 9}, 5)));
System.out.println(json(Solution87.solve(new int[]{12, 9, 9}, 5)));
System.out.println(json(Solution88.solve(new int[]{11, 8, 8}, 3)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, -3)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -2}, new int[]{-4, -1}, new int[]{-2, 0}}, -3)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 9)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{-6, 2, 4, 5}, new int[]{-5, -4, 0, 0, 1, 2, 5, 8})));
System.out.println(json(Solution71.solve(new int[]{-5, 2, 3}, -2)));
System.out.println(json(Solution72.solve(new int[]{-5, 2, 3}, -2, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-5, 2, 3}, -2, "upper")));
System.out.println(json(Solution73.solve(new int[]{-5, 2, 3}, -2)));
System.out.println(json(Solution74.solve(new int[]{-5, 2, 3}, -2)));
System.out.println(json(Solution75.solve(new int[]{-5, 2, 3}, -2)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10})));
System.out.println(json(Solution77.solve(new int[]{-3})));
System.out.println(json(Solution78.solve(new int[]{2, 3, -5})));
System.out.println(json(Solution79.solve(new int[]{2, 3, -5})));
System.out.println(json(Solution80.solve(new int[]{2, 3, -5}, -2)));
System.out.println(json(Solution81.solve(new int[]{7, 13}, 4)));
System.out.println(json(Solution82.solve(new int[]{7, 13}, 1, 1)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -6, -2, 8}, 5)));
System.out.println(json(Solution84.solve(new int[]{7, 13})));
System.out.println(json(Solution85.solve(new int[]{7, 13}, 32)));
System.out.println(json(Solution86.solve(new int[]{7, 13}, 2)));
System.out.println(json(Solution87.solve(new int[]{7, 13}, 2)));
System.out.println(json(Solution88.solve(new int[]{6, 12}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, -2)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -4, -4}, new int[]{-4, -3, -1}, new int[]{-4, -1, 1}, new int[]{-4, 0, 2}}, -2)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{-5, 2, 3}, new int[]{-6, -4, -1, -1, 1})));
System.out.println(json(Solution71.solve(new int[]{6}, -3)));
System.out.println(json(Solution72.solve(new int[]{6}, -3, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{6}, -3, "upper")));
System.out.println(json(Solution73.solve(new int[]{6}, -3)));
System.out.println(json(Solution74.solve(new int[]{6}, -3)));
System.out.println(json(Solution75.solve(new int[]{6}, -3)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1, -3, -5, -7})));
System.out.println(json(Solution77.solve(new int[]{2})));
System.out.println(json(Solution78.solve(new int[]{6})));
System.out.println(json(Solution79.solve(new int[]{6})));
System.out.println(json(Solution80.solve(new int[]{6}, -3)));
System.out.println(json(Solution81.solve(new int[]{10, 12, 6, 3, 3, 12, 10}, 21)));
System.out.println(json(Solution82.solve(new int[]{10, 12, 6, 3, 3, 12, 10}, 4, 1)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -6, 4, 2, 5}, 6)));
System.out.println(json(Solution84.solve(new int[]{3, 3, 6, 10, 10, 12, 12})));
System.out.println(json(Solution85.solve(new int[]{10, 12, 6, 3, 3, 12, 10}, 16)));
System.out.println(json(Solution86.solve(new int[]{10, 12, 6, 3, 3, 12, 10}, 2)));
System.out.println(json(Solution87.solve(new int[]{10, 12, 6, 3, 3, 12, 10}, 2)));
System.out.println(json(Solution88.solve(new int[]{9, 11, 5, 2, 2, 11, 9}, 7)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, -3)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -3}, new int[]{-1, 0}, new int[]{-1, 2}}, -3)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 7)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{6}, new int[]{-5, -2, 7})));
System.out.println(json(Solution71.solve(new int[]{-3, -1, 0, 1, 8}, -2)));
System.out.println(json(Solution72.solve(new int[]{-3, -1, -1, 0, 1, 8}, -2, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-3, -1, -1, 0, 1, 8}, -2, "upper")));
System.out.println(json(Solution73.solve(new int[]{-3, -1, -1, 0, 1, 8}, -2)));
System.out.println(json(Solution74.solve(new int[]{-3, -1, -1, 0, 1, 8}, -2)));
System.out.println(json(Solution75.solve(new int[]{-3, -1, 0, 1, 8}, -2)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1, -3})));
System.out.println(json(Solution77.solve(new int[]{-3, -1, 7, 5})));
System.out.println(json(Solution78.solve(new int[]{0, 1, 8, -3, -1})));
System.out.println(json(Solution79.solve(new int[]{0, 1, 8, -3, -1})));
System.out.println(json(Solution80.solve(new int[]{0, 1, 8, -3, -1}, -2)));
System.out.println(json(Solution81.solve(new int[]{10, 2}, 9)));
System.out.println(json(Solution82.solve(new int[]{10, 2}, 4, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 4, -1, -6, -4}, 6)));
System.out.println(json(Solution84.solve(new int[]{2, 10})));
System.out.println(json(Solution85.solve(new int[]{10, 2}, 32)));
System.out.println(json(Solution86.solve(new int[]{10, 2}, 3)));
System.out.println(json(Solution87.solve(new int[]{10, 2}, 3)));
System.out.println(json(Solution88.solve(new int[]{9, 1}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6, -4}}, -2)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -3, -2, -2}}, -2)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 3)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{-3, -1, -1, 0, 1, 8}, new int[]{7})));
System.out.println(json(Solution71.solve(new int[]{3, 4, 6}, -6)));
System.out.println(json(Solution72.solve(new int[]{3, 4, 6}, -6, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{3, 4, 6}, -6, "upper")));
System.out.println(json(Solution73.solve(new int[]{3, 4, 6}, -6)));
System.out.println(json(Solution74.solve(new int[]{3, 4, 6}, -6)));
System.out.println(json(Solution75.solve(new int[]{3, 4, 6}, -6)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1})));
System.out.println(json(Solution77.solve(new int[]{6, -6, 8, 1, 4, 6, -6, -5})));
System.out.println(json(Solution78.solve(new int[]{4, 6, 3})));
System.out.println(json(Solution79.solve(new int[]{4, 6, 3})));
System.out.println(json(Solution80.solve(new int[]{4, 6, 3}, -6)));
System.out.println(json(Solution81.solve(new int[]{8, 7, 10, 4}, 6)));
System.out.println(json(Solution82.solve(new int[]{8, 7, 10, 4}, 5, 3)));
}
static void batch7800() {
System.out.println(json(Solution83.solve(new int[]{0, 20, -4}, 3)));
System.out.println(json(Solution84.solve(new int[]{4, 7, 8, 10})));
System.out.println(json(Solution85.solve(new int[]{8, 7, 10, 4}, 32)));
System.out.println(json(Solution86.solve(new int[]{8, 7, 10, 4}, 4)));
System.out.println(json(Solution87.solve(new int[]{8, 7, 10, 4}, 4)));
System.out.println(json(Solution88.solve(new int[]{7, 6, 9, 3}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, -6)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -3, -1}, new int[]{-3, -1, 1}, new int[]{-1, 0, 3}, new int[]{-1, 0, 5}}, -6)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 2)));
System.out.println(json(Solution93.solve(new int[]{3, 4, 6}, new int[]{-6})));
System.out.println(json(Solution71.solve(new int[]{-4}, 5)));
System.out.println(json(Solution72.solve(new int[]{-4}, 5, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-4}, 5, "upper")));
System.out.println(json(Solution73.solve(new int[]{-4}, 5)));
System.out.println(json(Solution74.solve(new int[]{-4}, 5)));
System.out.println(json(Solution75.solve(new int[]{-4}, 5)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 4, 2})));
System.out.println(json(Solution77.solve(new int[]{6, 7, 1, 5, 1})));
System.out.println(json(Solution78.solve(new int[]{-4})));
System.out.println(json(Solution79.solve(new int[]{-4})));
System.out.println(json(Solution80.solve(new int[]{-4}, 5)));
System.out.println(json(Solution81.solve(new int[]{6, 14, 15, 11}, 19)));
System.out.println(json(Solution82.solve(new int[]{6, 14, 15, 11}, 4, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 4}, 2)));
System.out.println(json(Solution84.solve(new int[]{6, 11, 14, 15})));
System.out.println(json(Solution85.solve(new int[]{6, 14, 15, 11}, 12)));
System.out.println(json(Solution86.solve(new int[]{6, 14, 15, 11}, 3)));
System.out.println(json(Solution87.solve(new int[]{6, 14, 15, 11}, 3)));
System.out.println(json(Solution88.solve(new int[]{5, 13, 14, 10}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6, -4}}, 5)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -3, -2, -1}}, 5)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 7)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{-4}, new int[]{-6, -1, 2, 2, 5, 6, 8, 8})));
System.out.println(json(Solution71.solve(new int[]{-4, -2, -1, 5, 7}, 5)));
System.out.println(json(Solution72.solve(new int[]{-4, -2, -1, 5, 7}, 5, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-4, -2, -1, 5, 7}, 5, "upper")));
System.out.println(json(Solution73.solve(new int[]{-4, -2, -1, 5, 7}, 5)));
System.out.println(json(Solution74.solve(new int[]{-4, -2, -1, 5, 7}, 5)));
System.out.println(json(Solution75.solve(new int[]{-4, -2, -1, 5, 7}, 5)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 4})));
System.out.println(json(Solution77.solve(new int[]{0, 1, 6, 0, 5, 4, 1})));
System.out.println(json(Solution78.solve(new int[]{-4, -2, -1, 5, 7})));
System.out.println(json(Solution79.solve(new int[]{-4, -2, -1, 5, 7})));
System.out.println(json(Solution80.solve(new int[]{-4, -2, -1, 5, 7}, 5)));
System.out.println(json(Solution81.solve(new int[]{15, 3, 8, 4, 15}, 16)));
System.out.println(json(Solution82.solve(new int[]{15, 3, 8, 4, 15}, 4, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20}, 2)));
System.out.println(json(Solution84.solve(new int[]{3, 4, 8, 15, 15})));
System.out.println(json(Solution85.solve(new int[]{15, 3, 8, 4, 15}, 8)));
System.out.println(json(Solution86.solve(new int[]{15, 3, 8, 4, 15}, 5)));
System.out.println(json(Solution87.solve(new int[]{15, 3, 8, 4, 15}, 5)));
System.out.println(json(Solution88.solve(new int[]{14, 2, 7, 3, 14}, 4)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, 5)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -4, -3}, new int[]{-4, -4, -2}, new int[]{-3, -2, -2}, new int[]{-2, -2, -1}}, 5)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{-4, -2, -1, 5, 7}, new int[]{})));
System.out.println(json(Solution71.solve(new int[]{-6, 1, 6}, 9)));
System.out.println(json(Solution72.solve(new int[]{-6, 1, 6}, 9, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, 1, 6}, 9, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, 1, 6}, 9)));
System.out.println(json(Solution74.solve(new int[]{-6, 1, 6}, 9)));
System.out.println(json(Solution75.solve(new int[]{-6, 1, 6}, 9)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1})));
System.out.println(json(Solution77.solve(new int[]{-4, 1, 2, 4, 0})));
System.out.println(json(Solution78.solve(new int[]{-6, 1, 6})));
System.out.println(json(Solution79.solve(new int[]{-6, 1, 6})));
System.out.println(json(Solution80.solve(new int[]{-6, 1, 6}, 9)));
System.out.println(json(Solution81.solve(new int[]{15, 5, 2, 14, 14}, 23)));
System.out.println(json(Solution82.solve(new int[]{15, 5, 2, 14, 14}, 3, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20}, 2)));
System.out.println(json(Solution84.solve(new int[]{2, 5, 14, 14, 15})));
System.out.println(json(Solution85.solve(new int[]{15, 5, 2, 14, 14}, 15)));
System.out.println(json(Solution86.solve(new int[]{15, 5, 2, 14, 14}, 5)));
System.out.println(json(Solution87.solve(new int[]{15, 5, 2, 14, 14}, 5)));
System.out.println(json(Solution88.solve(new int[]{14, 4, 1, 13, 13}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, 9)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -5, -4}, new int[]{-3, -1, 0}, new int[]{-1, 1, 2}, new int[]{-1, 1, 3}}, 9)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 2)));
System.out.println(json(Solution93.solve(new int[]{-6, 1, 6}, new int[]{-3, 1, 1, 1, 2, 4, 6, 6})));
System.out.println(json(Solution71.solve(new int[]{-6, 0}, 0)));
System.out.println(json(Solution72.solve(new int[]{-6, 0}, 0, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, 0}, 0, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, 0}, 0)));
System.out.println(json(Solution74.solve(new int[]{-6, 0}, 0)));
System.out.println(json(Solution75.solve(new int[]{-6, 0}, 0)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10, 8, 6, 4, 2})));
System.out.println(json(Solution77.solve(new int[]{7, 4, 0, 6, 2, 8, 0})));
System.out.println(json(Solution78.solve(new int[]{0, -6})));
System.out.println(json(Solution79.solve(new int[]{0, -6})));
System.out.println(json(Solution80.solve(new int[]{0, -6}, 0)));
System.out.println(json(Solution81.solve(new int[]{5}, 12)));
System.out.println(json(Solution82.solve(new int[]{5}, 4, 1)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -2, 3, -4}, 5)));
System.out.println(json(Solution84.solve(new int[]{5})));
System.out.println(json(Solution85.solve(new int[]{5}, 7)));
System.out.println(json(Solution86.solve(new int[]{5}, 1)));
}
static void batch7900() {
System.out.println(json(Solution87.solve(new int[]{5}, 1)));
System.out.println(json(Solution88.solve(new int[]{4}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, 0)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -2, -2}, new int[]{-2, -2, -1}, new int[]{-1, 1, 1}, new int[]{1, 2, 2}}, 0)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{-6, 0}, new int[]{-5})));
System.out.println(json(Solution71.solve(new int[]{-1, 3, 7}, -6)));
System.out.println(json(Solution72.solve(new int[]{-1, -1, 3, 7}, -6, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-1, -1, 3, 7}, -6, "upper")));
System.out.println(json(Solution73.solve(new int[]{-1, -1, 3, 7}, -6)));
System.out.println(json(Solution74.solve(new int[]{-1, -1, 3, 7}, -6)));
System.out.println(json(Solution75.solve(new int[]{-1, 3, 7}, -6)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10, 8, 6, 4})));
System.out.println(json(Solution77.solve(new int[]{3})));
System.out.println(json(Solution78.solve(new int[]{-1, 3, 7})));
System.out.println(json(Solution79.solve(new int[]{-1, 3, 7})));
System.out.println(json(Solution80.solve(new int[]{-1, 3, 7}, -6)));
System.out.println(json(Solution81.solve(new int[]{7, 13, 7, 8, 15}, 16)));
System.out.println(json(Solution82.solve(new int[]{7, 13, 7, 8, 15}, 4, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20}, 2)));
System.out.println(json(Solution84.solve(new int[]{7, 7, 8, 13, 15})));
System.out.println(json(Solution85.solve(new int[]{7, 13, 7, 8, 15}, 13)));
System.out.println(json(Solution86.solve(new int[]{7, 13, 7, 8, 15}, 1)));
System.out.println(json(Solution87.solve(new int[]{7, 13, 7, 8, 15}, 1)));
System.out.println(json(Solution88.solve(new int[]{6, 12, 6, 7, 14}, 3)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, -6)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4}, new int[]{-4}}, -6)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{-1, -1, 3, 7}, new int[]{-4, -2, -1, 4, 6, 6, 7, 7})));
System.out.println(json(Solution71.solve(new int[]{}, 4)));
System.out.println(json(Solution72.solve(new int[]{}, 4, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{}, 4, "upper")));
System.out.println(json(Solution73.solve(new int[]{}, 4)));
System.out.println(json(Solution74.solve(new int[]{}, 4)));
System.out.println(json(Solution75.solve(new int[]{}, 4)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1, -3})));
System.out.println(json(Solution77.solve(new int[]{4, -6, -4, 7})));
System.out.println(json(Solution78.solve(new int[]{0})));
System.out.println(json(Solution79.solve(new int[]{0})));
System.out.println(json(Solution80.solve(new int[]{0}, 4)));
System.out.println(json(Solution81.solve(new int[]{3, 15}, 19)));
System.out.println(json(Solution82.solve(new int[]{3, 15}, 2, 1)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -3}, 3)));
System.out.println(json(Solution84.solve(new int[]{3, 15})));
System.out.println(json(Solution85.solve(new int[]{3, 15}, 13)));
System.out.println(json(Solution86.solve(new int[]{3, 15}, 4)));
System.out.println(json(Solution87.solve(new int[]{3, 15}, 4)));
System.out.println(json(Solution88.solve(new int[]{2, 14}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, 4)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4}, new int[]{-3}}, 4)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 2)));
System.out.println(json(Solution93.solve(new int[]{}, new int[]{-5, -3, -2, 4, 5})));
System.out.println(json(Solution71.solve(new int[]{-6, -2, 3, 7}, -8)));
System.out.println(json(Solution72.solve(new int[]{-6, -2, 3, 7}, -8, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, -2, 3, 7}, -8, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, -2, 3, 7}, -8)));
System.out.println(json(Solution74.solve(new int[]{-6, -2, 3, 7}, -8)));
System.out.println(json(Solution75.solve(new int[]{-6, -2, 3, 7}, -8)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10})));
System.out.println(json(Solution77.solve(new int[]{-6})));
System.out.println(json(Solution78.solve(new int[]{-6, -2, 3, 7})));
System.out.println(json(Solution79.solve(new int[]{-6, -2, 3, 7})));
System.out.println(json(Solution80.solve(new int[]{-6, -2, 3, 7}, -8)));
System.out.println(json(Solution81.solve(new int[]{2, 6, 4, 9, 6}, 7)));
System.out.println(json(Solution82.solve(new int[]{2, 6, 4, 9, 6}, 2, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 3, 2, -2, -4}, 4)));
System.out.println(json(Solution84.solve(new int[]{2, 4, 6, 6, 9})));
System.out.println(json(Solution85.solve(new int[]{2, 6, 4, 9, 6}, 33)));
System.out.println(json(Solution86.solve(new int[]{2, 6, 4, 9, 6}, 6)));
System.out.println(json(Solution87.solve(new int[]{2, 6, 4, 9, 6}, 6)));
System.out.println(json(Solution88.solve(new int[]{1, 5, 3, 8, 5}, 3)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, -8)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4}, new int[]{-4}}, -8)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 18)));
System.out.println(json(Solution93.solve(new int[]{-6, -2, 3, 7}, new int[]{-6, -3, -3, 1, 3, 4})));
System.out.println(json(Solution71.solve(new int[]{-5, -3, -2, 1, 2}, -4)));
System.out.println(json(Solution72.solve(new int[]{-5, -3, -2, 1, 2}, -4, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-5, -3, -2, 1, 2}, -4, "upper")));
System.out.println(json(Solution73.solve(new int[]{-5, -3, -2, 1, 2}, -4)));
System.out.println(json(Solution74.solve(new int[]{-5, -3, -2, 1, 2}, -4)));
System.out.println(json(Solution75.solve(new int[]{-5, -3, -2, 1, 2}, -4)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 15, 13})));
System.out.println(json(Solution77.solve(new int[]{-2, 1, -2, 7, 1})));
System.out.println(json(Solution78.solve(new int[]{-5, -3, -2, 1, 2})));
System.out.println(json(Solution79.solve(new int[]{-5, -3, -2, 1, 2})));
System.out.println(json(Solution80.solve(new int[]{-5, -3, -2, 1, 2}, -4)));
System.out.println(json(Solution81.solve(new int[]{14, 2, 3, 5, 9}, 15)));
System.out.println(json(Solution82.solve(new int[]{14, 2, 3, 5, 9}, 5, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20}, 2)));
System.out.println(json(Solution84.solve(new int[]{2, 3, 5, 9, 14})));
System.out.println(json(Solution85.solve(new int[]{14, 2, 3, 5, 9}, 19)));
System.out.println(json(Solution86.solve(new int[]{14, 2, 3, 5, 9}, 4)));
System.out.println(json(Solution87.solve(new int[]{14, 2, 3, 5, 9}, 4)));
System.out.println(json(Solution88.solve(new int[]{13, 1, 2, 4, 8}, 4)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, -4)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -2, -2}, new int[]{-3, -1, 1}, new int[]{-2, -1, 2}, new int[]{-1, 0, 4}}, -4)));
}
static void batch8000() {
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{-5, -3, -2, 1, 2}, new int[]{-6})));
System.out.println(json(Solution71.solve(new int[]{2, 4, 8}, 11)));
System.out.println(json(Solution72.solve(new int[]{2, 4, 8}, 11, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{2, 4, 8}, 11, "upper")));
System.out.println(json(Solution73.solve(new int[]{2, 4, 8}, 11)));
System.out.println(json(Solution74.solve(new int[]{2, 4, 8}, 11)));
System.out.println(json(Solution75.solve(new int[]{2, 4, 8}, 11)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10, 8, 6, 4, 2})));
System.out.println(json(Solution77.solve(new int[]{-2, 1, 4, -4, 8, 9, 4})));
System.out.println(json(Solution78.solve(new int[]{8, 2, 4})));
System.out.println(json(Solution79.solve(new int[]{8, 2, 4})));
System.out.println(json(Solution80.solve(new int[]{8, 2, 4}, 11)));
System.out.println(json(Solution81.solve(new int[]{10, 7, 12, 6, 7}, 25)));
System.out.println(json(Solution82.solve(new int[]{10, 7, 12, 6, 7}, 2, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -3}, 3)));
System.out.println(json(Solution84.solve(new int[]{6, 7, 7, 10, 12})));
System.out.println(json(Solution85.solve(new int[]{10, 7, 12, 6, 7}, 35)));
System.out.println(json(Solution86.solve(new int[]{10, 7, 12, 6, 7}, 4)));
System.out.println(json(Solution87.solve(new int[]{10, 7, 12, 6, 7}, 4)));
System.out.println(json(Solution88.solve(new int[]{9, 6, 11, 5, 6}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, 11)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -4, -2}, new int[]{-2, -2, -2}, new int[]{-2, -2, -1}, new int[]{0, 1, 3}}, 11)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{2, 4, 8}, new int[]{-2, 0, 0, 1, 5, 8})));
System.out.println(json(Solution71.solve(new int[]{}, -1)));
System.out.println(json(Solution72.solve(new int[]{}, -1, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{}, -1, "upper")));
System.out.println(json(Solution73.solve(new int[]{}, -1)));
System.out.println(json(Solution74.solve(new int[]{}, -1)));
System.out.println(json(Solution75.solve(new int[]{}, -1)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 7, 5, 3, 1})));
System.out.println(json(Solution77.solve(new int[]{-4, 1, -3, -2, 7, 5, 0, 6})));
System.out.println(json(Solution78.solve(new int[]{0})));
System.out.println(json(Solution79.solve(new int[]{0})));
System.out.println(json(Solution80.solve(new int[]{0}, -1)));
System.out.println(json(Solution81.solve(new int[]{15, 13}, 4)));
System.out.println(json(Solution82.solve(new int[]{15, 13}, 1, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 6}, 2)));
System.out.println(json(Solution84.solve(new int[]{13, 15})));
System.out.println(json(Solution85.solve(new int[]{15, 13}, 20)));
System.out.println(json(Solution86.solve(new int[]{15, 13}, 1)));
System.out.println(json(Solution87.solve(new int[]{15, 13}, 1)));
System.out.println(json(Solution88.solve(new int[]{14, 12}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, -1)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -3}, new int[]{-3, -1}, new int[]{-2, -1}}, -1)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 7)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{}, new int[]{-3, 0, 0})));
System.out.println(json(Solution71.solve(new int[]{-5, -4, -3, 0, 1, 4, 6}, -2)));
System.out.println(json(Solution72.solve(new int[]{-5, -4, -3, 0, 1, 4, 4, 6}, -2, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-5, -4, -3, 0, 1, 4, 4, 6}, -2, "upper")));
System.out.println(json(Solution73.solve(new int[]{-5, -4, -3, 0, 1, 4, 4, 6}, -2)));
System.out.println(json(Solution74.solve(new int[]{-5, -4, -3, 0, 1, 4, 4, 6}, -2)));
System.out.println(json(Solution75.solve(new int[]{-5, -4, -3, 0, 1, 4, 6}, -2)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 7, 5, 3})));
System.out.println(json(Solution77.solve(new int[]{8, -5, -3, -2, 5, 3, 2, 3})));
System.out.println(json(Solution78.solve(new int[]{0, 1, 4, 6, -5, -4, -3})));
System.out.println(json(Solution79.solve(new int[]{0, 1, 4, 6, -5, -4, -3})));
System.out.println(json(Solution80.solve(new int[]{0, 1, 4, 6, -5, -4, -3}, -2)));
System.out.println(json(Solution81.solve(new int[]{7, 6, 6, 15, 10, 13}, 10)));
System.out.println(json(Solution82.solve(new int[]{7, 6, 6, 15, 10, 13}, 5, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20}, 2)));
System.out.println(json(Solution84.solve(new int[]{6, 6, 7, 10, 13, 15})));
System.out.println(json(Solution85.solve(new int[]{7, 6, 6, 15, 10, 13}, 13)));
System.out.println(json(Solution86.solve(new int[]{7, 6, 6, 15, 10, 13}, 2)));
System.out.println(json(Solution87.solve(new int[]{7, 6, 6, 15, 10, 13}, 2)));
System.out.println(json(Solution88.solve(new int[]{6, 5, 5, 14, 9, 12}, 5)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, -2)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -5, -4}, new int[]{-5, -5, -4}, new int[]{-4, -4, -3}, new int[]{-3, -3, -1}}, -2)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{-5, -4, -3, 0, 1, 4, 4, 6}, new int[]{-5, -5, -2, -1, 4, 4, 6})));
System.out.println(json(Solution71.solve(new int[]{-5, -3, -2, -1, 4, 5}, -7)));
System.out.println(json(Solution72.solve(new int[]{-5, -3, -2, -1, 4, 4, 5}, -7, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-5, -3, -2, -1, 4, 4, 5}, -7, "upper")));
System.out.println(json(Solution73.solve(new int[]{-5, -3, -2, -1, 4, 4, 5}, -7)));
System.out.println(json(Solution74.solve(new int[]{-5, -3, -2, -1, 4, 4, 5}, -7)));
System.out.println(json(Solution75.solve(new int[]{-5, -3, -2, -1, 4, 5}, -7)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 4, 2})));
System.out.println(json(Solution77.solve(new int[]{3, -3})));
System.out.println(json(Solution78.solve(new int[]{-5, -3, -2, -1, 4, 5})));
System.out.println(json(Solution79.solve(new int[]{-5, -3, -2, -1, 4, 5})));
System.out.println(json(Solution80.solve(new int[]{-5, -3, -2, -1, 4, 5}, -7)));
System.out.println(json(Solution81.solve(new int[]{1, 13, 15}, 16)));
System.out.println(json(Solution82.solve(new int[]{1, 13, 15}, 5, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20}, 2)));
System.out.println(json(Solution84.solve(new int[]{1, 13, 15})));
System.out.println(json(Solution85.solve(new int[]{1, 13, 15}, 7)));
System.out.println(json(Solution86.solve(new int[]{1, 13, 15}, 5)));
System.out.println(json(Solution87.solve(new int[]{1, 13, 15}, 5)));
System.out.println(json(Solution88.solve(new int[]{0, 12, 14}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, -7)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5}, new int[]{-3}}, -7)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{-5, -3, -2, -1, 4, 4, 5}, new int[]{1, 4})));
System.out.println(json(Solution71.solve(new int[]{-6, 0, 4}, 4)));
}
static void batch8100() {
System.out.println(json(Solution72.solve(new int[]{-6, -6, 0, 4}, 4, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, -6, 0, 4}, 4, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, -6, 0, 4}, 4)));
System.out.println(json(Solution74.solve(new int[]{-6, -6, 0, 4}, 4)));
System.out.println(json(Solution75.solve(new int[]{-6, 0, 4}, 4)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 4, 2, 0, -2, -4})));
System.out.println(json(Solution77.solve(new int[]{-1, 0, -4, 6, 7, -5, 8, 9})));
System.out.println(json(Solution78.solve(new int[]{0, 4, -6})));
System.out.println(json(Solution79.solve(new int[]{0, 4, -6})));
System.out.println(json(Solution80.solve(new int[]{0, 4, -6}, 4)));
System.out.println(json(Solution81.solve(new int[]{4, 9, 1}, 20)));
System.out.println(json(Solution82.solve(new int[]{4, 9, 1}, 1, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 5, 7, 8}, 3)));
System.out.println(json(Solution84.solve(new int[]{1, 4, 9})));
System.out.println(json(Solution85.solve(new int[]{4, 9, 1}, 19)));
System.out.println(json(Solution86.solve(new int[]{4, 9, 1}, 5)));
System.out.println(json(Solution87.solve(new int[]{4, 9, 1}, 5)));
System.out.println(json(Solution88.solve(new int[]{3, 8, 0}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, 4)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4}, new int[]{-2}}, 4)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 22)));
System.out.println(json(Solution93.solve(new int[]{-6, -6, 0, 4}, new int[]{-5, 8})));
System.out.println(json(Solution71.solve(new int[]{2, 3, 6}, 6)));
System.out.println(json(Solution72.solve(new int[]{2, 3, 6}, 6, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{2, 3, 6}, 6, "upper")));
System.out.println(json(Solution73.solve(new int[]{2, 3, 6}, 6)));
System.out.println(json(Solution74.solve(new int[]{2, 3, 6}, 6)));
System.out.println(json(Solution75.solve(new int[]{2, 3, 6}, 6)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10})));
System.out.println(json(Solution77.solve(new int[]{1, 2, -1, 2, 3, -6, 0})));
System.out.println(json(Solution78.solve(new int[]{3, 6, 2})));
System.out.println(json(Solution79.solve(new int[]{3, 6, 2})));
System.out.println(json(Solution80.solve(new int[]{3, 6, 2}, 6)));
System.out.println(json(Solution81.solve(new int[]{13}, 10)));
System.out.println(json(Solution82.solve(new int[]{13}, 3, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -4, 7, -6, -3}, 5)));
System.out.println(json(Solution84.solve(new int[]{13})));
System.out.println(json(Solution85.solve(new int[]{13}, 17)));
System.out.println(json(Solution86.solve(new int[]{13}, 1)));
System.out.println(json(Solution87.solve(new int[]{13}, 1)));
System.out.println(json(Solution88.solve(new int[]{12}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, 6)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -5, -5}, new int[]{-3, -1, 1}, new int[]{-1, -1, 3}, new int[]{1, 3, 5}}, 6)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 6)));
System.out.println(json(Solution93.solve(new int[]{2, 3, 6}, new int[]{-6, -5, -3, -2, 1, 6})));
System.out.println(json(Solution71.solve(new int[]{-6, -5, -4, 5, 7}, 6)));
System.out.println(json(Solution72.solve(new int[]{-6, -5, -4, 5, 7}, 6, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, -5, -4, 5, 7}, 6, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, -5, -4, 5, 7}, 6)));
System.out.println(json(Solution74.solve(new int[]{-6, -5, -4, 5, 7}, 6)));
System.out.println(json(Solution75.solve(new int[]{-6, -5, -4, 5, 7}, 6)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 15, 13})));
System.out.println(json(Solution77.solve(new int[]{-5, 6, -3, 1, -1})));
System.out.println(json(Solution78.solve(new int[]{-5, -4, 5, 7, -6})));
System.out.println(json(Solution79.solve(new int[]{-5, -4, 5, 7, -6})));
System.out.println(json(Solution80.solve(new int[]{-5, -4, 5, 7, -6}, 6)));
System.out.println(json(Solution81.solve(new int[]{10, 12, 8, 14, 8, 4, 1}, 16)));
System.out.println(json(Solution82.solve(new int[]{10, 12, 8, 14, 8, 4, 1}, 5, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -4, 5, -3, -2, -5, 1, -1}, 3)));
System.out.println(json(Solution84.solve(new int[]{1, 4, 8, 8, 10, 12, 14})));
System.out.println(json(Solution85.solve(new int[]{10, 12, 8, 14, 8, 4, 1}, 17)));
System.out.println(json(Solution86.solve(new int[]{10, 12, 8, 14, 8, 4, 1}, 3)));
System.out.println(json(Solution87.solve(new int[]{10, 12, 8, 14, 8, 4, 1}, 3)));
System.out.println(json(Solution88.solve(new int[]{9, 11, 7, 13, 7, 3, 0}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, 6)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3}, new int[]{-1}}, 6)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{-6, -5, -4, 5, 7}, new int[]{-6, -5, 0, 2, 3, 4, 8})));
System.out.println(json(Solution71.solve(new int[]{2, 4, 5}, 14)));
System.out.println(json(Solution72.solve(new int[]{2, 4, 4, 5}, 14, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{2, 4, 4, 5}, 14, "upper")));
System.out.println(json(Solution73.solve(new int[]{2, 4, 4, 5}, 14)));
System.out.println(json(Solution74.solve(new int[]{2, 4, 4, 5}, 14)));
System.out.println(json(Solution75.solve(new int[]{2, 4, 5}, 14)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1, -3})));
System.out.println(json(Solution77.solve(new int[]{-2, 4, 6, -1, 8, 7, 5, 2})));
System.out.println(json(Solution78.solve(new int[]{5, 2, 4})));
System.out.println(json(Solution79.solve(new int[]{5, 2, 4})));
System.out.println(json(Solution80.solve(new int[]{5, 2, 4}, 14)));
System.out.println(json(Solution81.solve(new int[]{12, 6, 15, 10, 7}, 18)));
System.out.println(json(Solution82.solve(new int[]{12, 6, 15, 10, 7}, 3, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 1}, 3)));
System.out.println(json(Solution84.solve(new int[]{6, 7, 10, 12, 15})));
System.out.println(json(Solution85.solve(new int[]{12, 6, 15, 10, 7}, 25)));
System.out.println(json(Solution86.solve(new int[]{12, 6, 15, 10, 7}, 5)));
System.out.println(json(Solution87.solve(new int[]{12, 6, 15, 10, 7}, 5)));
System.out.println(json(Solution88.solve(new int[]{11, 5, 14, 9, 6}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, 14)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -4, -2}, new int[]{-3, -1, 0}, new int[]{-2, 1, 3}, new int[]{0, 1, 4}}, 14)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 2)));
System.out.println(json(Solution93.solve(new int[]{2, 4, 4, 5}, new int[]{-5, -5, -3, -2, -1, -1, 1, 7})));
System.out.println(json(Solution71.solve(new int[]{-4, -1, 1, 3, 8}, 0)));
System.out.println(json(Solution72.solve(new int[]{-4, -1, 1, 3, 8}, 0, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-4, -1, 1, 3, 8}, 0, "upper")));
System.out.println(json(Solution73.solve(new int[]{-4, -1, 1, 3, 8}, 0)));
System.out.println(json(Solution74.solve(new int[]{-4, -1, 1, 3, 8}, 0)));
}
static void batch8200() {
System.out.println(json(Solution75.solve(new int[]{-4, -1, 1, 3, 8}, 0)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10, 8})));
System.out.println(json(Solution77.solve(new int[]{6, -2, -5, 1, -4})));
System.out.println(json(Solution78.solve(new int[]{8, -4, -1, 1, 3})));
System.out.println(json(Solution79.solve(new int[]{8, -4, -1, 1, 3})));
System.out.println(json(Solution80.solve(new int[]{8, -4, -1, 1, 3}, 0)));
System.out.println(json(Solution81.solve(new int[]{9, 5, 1, 1, 12, 1}, 12)));
System.out.println(json(Solution82.solve(new int[]{9, 5, 1, 1, 12, 1}, 1, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 3, -4, -6, 8}, 2)));
System.out.println(json(Solution84.solve(new int[]{1, 1, 1, 5, 9, 12})));
System.out.println(json(Solution85.solve(new int[]{9, 5, 1, 1, 12, 1}, 20)));
System.out.println(json(Solution86.solve(new int[]{9, 5, 1, 1, 12, 1}, 1)));
System.out.println(json(Solution87.solve(new int[]{9, 5, 1, 1, 12, 1}, 1)));
System.out.println(json(Solution88.solve(new int[]{8, 4, 0, 0, 11, 0}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, 0)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -4}, new int[]{-4, -4}, new int[]{-3, -2}}, 0)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 6)));
System.out.println(json(Solution93.solve(new int[]{-4, -1, 1, 3, 8}, new int[]{-5, -2, 2, 4, 6, 6, 6, 8})));
System.out.println(json(Solution71.solve(new int[]{-4, 0, 2, 8}, 9)));
System.out.println(json(Solution72.solve(new int[]{-4, 0, 2, 8}, 9, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-4, 0, 2, 8}, 9, "upper")));
System.out.println(json(Solution73.solve(new int[]{-4, 0, 2, 8}, 9)));
System.out.println(json(Solution74.solve(new int[]{-4, 0, 2, 8}, 9)));
System.out.println(json(Solution75.solve(new int[]{-4, 0, 2, 8}, 9)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 15, 13, 11, 9})));
System.out.println(json(Solution77.solve(new int[]{0})));
System.out.println(json(Solution78.solve(new int[]{0, 2, 8, -4})));
System.out.println(json(Solution79.solve(new int[]{0, 2, 8, -4})));
System.out.println(json(Solution80.solve(new int[]{0, 2, 8, -4}, 9)));
System.out.println(json(Solution81.solve(new int[]{5, 9, 1, 11, 3, 8, 15}, 5)));
System.out.println(json(Solution82.solve(new int[]{5, 9, 1, 11, 3, 8, 15}, 3, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 1, 7, -1, 3, 2, 5}, 4)));
System.out.println(json(Solution84.solve(new int[]{1, 3, 5, 8, 9, 11, 15})));
System.out.println(json(Solution85.solve(new int[]{5, 9, 1, 11, 3, 8, 15}, 24)));
System.out.println(json(Solution86.solve(new int[]{5, 9, 1, 11, 3, 8, 15}, 9)));
System.out.println(json(Solution87.solve(new int[]{5, 9, 1, 11, 3, 8, 15}, 9)));
System.out.println(json(Solution88.solve(new int[]{4, 8, 0, 10, 2, 7, 14}, 7)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6, -4}}, 9)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -2, -2, 0}}, 9)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 3)));
System.out.println(json(Solution92.solve(4, 7, 2)));
System.out.println(json(Solution93.solve(new int[]{-4, 0, 2, 8}, new int[]{-4})));
System.out.println(json(Solution71.solve(new int[]{-1, 5}, -1)));
System.out.println(json(Solution72.solve(new int[]{-1, 5}, -1, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-1, 5}, -1, "upper")));
System.out.println(json(Solution73.solve(new int[]{-1, 5}, -1)));
System.out.println(json(Solution74.solve(new int[]{-1, 5}, -1)));
System.out.println(json(Solution75.solve(new int[]{-1, 5}, -1)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 7, 5, 3, 1, -1})));
System.out.println(json(Solution77.solve(new int[]{1, 4})));
System.out.println(json(Solution78.solve(new int[]{5, -1})));
System.out.println(json(Solution79.solve(new int[]{5, -1})));
System.out.println(json(Solution80.solve(new int[]{5, -1}, -1)));
System.out.println(json(Solution81.solve(new int[]{10, 5, 5, 11, 13, 14, 13}, 2)));
System.out.println(json(Solution82.solve(new int[]{10, 5, 5, 11, 13, 14, 13}, 4, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 3, 5}, 2)));
System.out.println(json(Solution84.solve(new int[]{5, 5, 10, 11, 13, 13, 14})));
System.out.println(json(Solution85.solve(new int[]{10, 5, 5, 11, 13, 14, 13}, 26)));
System.out.println(json(Solution86.solve(new int[]{10, 5, 5, 11, 13, 14, 13}, 3)));
System.out.println(json(Solution87.solve(new int[]{10, 5, 5, 11, 13, 14, 13}, 3)));
System.out.println(json(Solution88.solve(new int[]{9, 4, 4, 10, 12, 13, 12}, 6)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, -1)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -1}, new int[]{-3, 0}, new int[]{-2, 2}}, -1)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 2)));
System.out.println(json(Solution93.solve(new int[]{-1, 5}, new int[]{-6, 0, 2, 4, 6, 7, 8})));
System.out.println(json(Solution71.solve(new int[]{-2, 0, 2, 3, 6}, -7)));
System.out.println(json(Solution72.solve(new int[]{-2, 0, 2, 2, 3, 6}, -7, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-2, 0, 2, 2, 3, 6}, -7, "upper")));
System.out.println(json(Solution73.solve(new int[]{-2, 0, 2, 2, 3, 6}, -7)));
System.out.println(json(Solution74.solve(new int[]{-2, 0, 2, 2, 3, 6}, -7)));
System.out.println(json(Solution75.solve(new int[]{-2, 0, 2, 3, 6}, -7)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 15, 13})));
System.out.println(json(Solution77.solve(new int[]{2, 3, -1, 5})));
System.out.println(json(Solution78.solve(new int[]{2, 3, 6, -2, 0})));
System.out.println(json(Solution79.solve(new int[]{2, 3, 6, -2, 0})));
System.out.println(json(Solution80.solve(new int[]{2, 3, 6, -2, 0}, -7)));
System.out.println(json(Solution81.solve(new int[]{5, 1, 5}, 11)));
System.out.println(json(Solution82.solve(new int[]{5, 1, 5}, 3, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 1, 4, 5, -6, -1}, 6)));
System.out.println(json(Solution84.solve(new int[]{1, 5, 5})));
System.out.println(json(Solution85.solve(new int[]{5, 1, 5}, 24)));
System.out.println(json(Solution86.solve(new int[]{5, 1, 5}, 4)));
System.out.println(json(Solution87.solve(new int[]{5, 1, 5}, 4)));
System.out.println(json(Solution88.solve(new int[]{4, 0, 4}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, -7)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -2}, new int[]{-1, 1}, new int[]{-1, 1}}, -7)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 5)));
System.out.println(json(Solution92.solve(8, 3, 2)));
System.out.println(json(Solution93.solve(new int[]{-2, 0, 2, 2, 3, 6}, new int[]{-2, 8})));
System.out.println(json(Solution71.solve(new int[]{-6, -4, -2, 2, 3, 5}, 6)));
System.out.println(json(Solution72.solve(new int[]{-6, -4, -2, 2, 3, 5}, 6, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, -4, -2, 2, 3, 5}, 6, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, -4, -2, 2, 3, 5}, 6)));
System.out.println(json(Solution74.solve(new int[]{-6, -4, -2, 2, 3, 5}, 6)));
System.out.println(json(Solution75.solve(new int[]{-6, -4, -2, 2, 3, 5}, 6)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10})));
System.out.println(json(Solution77.solve(new int[]{7, 3, -3})));
System.out.println(json(Solution78.solve(new int[]{-4, -2, 2, 3, 5, -6})));
}
static void batch8300() {
System.out.println(json(Solution79.solve(new int[]{-4, -2, 2, 3, 5, -6})));
System.out.println(json(Solution80.solve(new int[]{-4, -2, 2, 3, 5, -6}, 6)));
System.out.println(json(Solution81.solve(new int[]{12, 3, 12, 5, 14}, 4)));
System.out.println(json(Solution82.solve(new int[]{12, 3, 12, 5, 14}, 3, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20}, 2)));
System.out.println(json(Solution84.solve(new int[]{3, 5, 12, 12, 14})));
System.out.println(json(Solution85.solve(new int[]{12, 3, 12, 5, 14}, 23)));
System.out.println(json(Solution86.solve(new int[]{12, 3, 12, 5, 14}, 1)));
System.out.println(json(Solution87.solve(new int[]{12, 3, 12, 5, 14}, 1)));
System.out.println(json(Solution88.solve(new int[]{11, 2, 11, 4, 13}, 5)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6, -4}}, 6)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -2, 0, 2}}, 6)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 5)));
System.out.println(json(Solution92.solve(4, 7, 10)));
System.out.println(json(Solution93.solve(new int[]{-6, -4, -2, 2, 3, 5}, new int[]{3, 8})));
System.out.println(json(Solution71.solve(new int[]{-5, -1}, -8)));
System.out.println(json(Solution72.solve(new int[]{-5, -1}, -8, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-5, -1}, -8, "upper")));
System.out.println(json(Solution73.solve(new int[]{-5, -1}, -8)));
System.out.println(json(Solution74.solve(new int[]{-5, -1}, -8)));
System.out.println(json(Solution75.solve(new int[]{-5, -1}, -8)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 4, 2, 0})));
System.out.println(json(Solution77.solve(new int[]{0, -5, 0, 4, -1})));
System.out.println(json(Solution78.solve(new int[]{-1, -5})));
System.out.println(json(Solution79.solve(new int[]{-1, -5})));
System.out.println(json(Solution80.solve(new int[]{-1, -5}, -8)));
System.out.println(json(Solution81.solve(new int[]{12, 1, 7, 4, 8, 5, 13}, 11)));
System.out.println(json(Solution82.solve(new int[]{12, 1, 7, 4, 8, 5, 13}, 5, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 6, -6, -1, -2, -3, -5}, 4)));
System.out.println(json(Solution84.solve(new int[]{1, 4, 5, 7, 8, 12, 13})));
System.out.println(json(Solution85.solve(new int[]{12, 1, 7, 4, 8, 5, 13}, 3)));
System.out.println(json(Solution86.solve(new int[]{12, 1, 7, 4, 8, 5, 13}, 1)));
System.out.println(json(Solution87.solve(new int[]{12, 1, 7, 4, 8, 5, 13}, 1)));
System.out.println(json(Solution88.solve(new int[]{11, 0, 6, 3, 7, 4, 12}, 3)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6, -4}}, -8)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -5, -5, -3}}, -8)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{-5, -1}, new int[]{-3, -3, -1, 0, 1, 3, 7})));
System.out.println(json(Solution71.solve(new int[]{-5, 1, 8}, -4)));
System.out.println(json(Solution72.solve(new int[]{-5, 1, 8}, -4, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-5, 1, 8}, -4, "upper")));
System.out.println(json(Solution73.solve(new int[]{-5, 1, 8}, -4)));
System.out.println(json(Solution74.solve(new int[]{-5, 1, 8}, -4)));
System.out.println(json(Solution75.solve(new int[]{-5, 1, 8}, -4)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 7, 5, 3, 1, -1})));
System.out.println(json(Solution77.solve(new int[]{1})));
System.out.println(json(Solution78.solve(new int[]{1, 8, -5})));
System.out.println(json(Solution79.solve(new int[]{1, 8, -5})));
System.out.println(json(Solution80.solve(new int[]{1, 8, -5}, -4)));
System.out.println(json(Solution81.solve(new int[]{15, 15, 13, 13, 3, 12}, 18)));
System.out.println(json(Solution82.solve(new int[]{15, 15, 13, 13, 3, 12}, 2, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 2, 6, -6, 5, 8, -2}, 8)));
System.out.println(json(Solution84.solve(new int[]{3, 12, 13, 13, 15, 15})));
System.out.println(json(Solution85.solve(new int[]{15, 15, 13, 13, 3, 12}, 7)));
System.out.println(json(Solution86.solve(new int[]{15, 15, 13, 13, 3, 12}, 1)));
System.out.println(json(Solution87.solve(new int[]{15, 15, 13, 13, 3, 12}, 1)));
System.out.println(json(Solution88.solve(new int[]{14, 14, 12, 12, 2, 11}, 6)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, -4)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -2}, new int[]{-2, -1}, new int[]{0, 1}}, -4)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 9)));
System.out.println(json(Solution92.solve(4, 7, 6)));
System.out.println(json(Solution93.solve(new int[]{-5, 1, 8}, new int[]{0, 3, 3, 5, 6, 6, 7, 8})));
System.out.println(json(Solution71.solve(new int[]{-3, 3, 7}, -8)));
System.out.println(json(Solution72.solve(new int[]{-3, -3, -3, -3, 3, 7, 7, 7}, -8, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-3, -3, -3, -3, 3, 7, 7, 7}, -8, "upper")));
System.out.println(json(Solution73.solve(new int[]{-3, -3, -3, -3, 3, 7, 7, 7}, -8)));
System.out.println(json(Solution74.solve(new int[]{-3, -3, -3, -3, 3, 7, 7, 7}, -8)));
System.out.println(json(Solution75.solve(new int[]{-3, 3, 7}, -8)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 4, 2})));
System.out.println(json(Solution77.solve(new int[]{1, 7, -4, 0, 4, 7})));
System.out.println(json(Solution78.solve(new int[]{-3, 3, 7})));
System.out.println(json(Solution79.solve(new int[]{-3, 3, 7})));
System.out.println(json(Solution80.solve(new int[]{-3, 3, 7}, -8)));
System.out.println(json(Solution81.solve(new int[]{2, 12, 3}, 23)));
System.out.println(json(Solution82.solve(new int[]{2, 12, 3}, 3, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 3, 5, -4, 1, -3}, 3)));
System.out.println(json(Solution84.solve(new int[]{2, 3, 12})));
System.out.println(json(Solution85.solve(new int[]{2, 12, 3}, 29)));
System.out.println(json(Solution86.solve(new int[]{2, 12, 3}, 3)));
System.out.println(json(Solution87.solve(new int[]{2, 12, 3}, 3)));
System.out.println(json(Solution88.solve(new int[]{1, 11, 2}, 3)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, -8)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -2, -2}, new int[]{-3, -1, -1}, new int[]{-2, 1, 1}, new int[]{-1, 2, 3}}, -8)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 10)));
System.out.println(json(Solution93.solve(new int[]{-3, -3, -3, -3, 3, 7, 7, 7}, new int[]{-4, -4, -3, -3, 4, 8})));
System.out.println(json(Solution71.solve(new int[]{6}, 14)));
System.out.println(json(Solution72.solve(new int[]{6}, 14, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{6}, 14, "upper")));
System.out.println(json(Solution73.solve(new int[]{6}, 14)));
System.out.println(json(Solution74.solve(new int[]{6}, 14)));
System.out.println(json(Solution75.solve(new int[]{6}, 14)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 15, 13, 11})));
System.out.println(json(Solution77.solve(new int[]{7, 8, 3, -2})));
System.out.println(json(Solution78.solve(new int[]{6})));
System.out.println(json(Solution79.solve(new int[]{6})));
System.out.println(json(Solution80.solve(new int[]{6}, 14)));
System.out.println(json(Solution81.solve(new int[]{8, 12}, 24)));
System.out.println(json(Solution82.solve(new int[]{8, 12}, 4, 2)));
}
static void batch8400() {
System.out.println(json(Solution83.solve(new int[]{0, 20}, 2)));
System.out.println(json(Solution84.solve(new int[]{8, 12})));
System.out.println(json(Solution85.solve(new int[]{8, 12}, 24)));
System.out.println(json(Solution86.solve(new int[]{8, 12}, 2)));
System.out.println(json(Solution87.solve(new int[]{8, 12}, 2)));
System.out.println(json(Solution88.solve(new int[]{7, 11}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, 14)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -2, -1}, new int[]{-3, -2, 0}, new int[]{-3, -1, 1}, new int[]{-3, 0, 1}}, 14)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{6}, new int[]{})));
System.out.println(json(Solution71.solve(new int[]{-6, -5, 1, 3, 8}, 2)));
System.out.println(json(Solution72.solve(new int[]{-6, -5, 1, 3, 8}, 2, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, -5, 1, 3, 8}, 2, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, -5, 1, 3, 8}, 2)));
System.out.println(json(Solution74.solve(new int[]{-6, -5, 1, 3, 8}, 2)));
System.out.println(json(Solution75.solve(new int[]{-6, -5, 1, 3, 8}, 2)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 4, 2, 0, -2})));
System.out.println(json(Solution77.solve(new int[]{4, 3, 1, 7})));
System.out.println(json(Solution78.solve(new int[]{8, -6, -5, 1, 3})));
System.out.println(json(Solution79.solve(new int[]{8, -6, -5, 1, 3})));
System.out.println(json(Solution80.solve(new int[]{8, -6, -5, 1, 3}, 2)));
System.out.println(json(Solution81.solve(new int[]{6, 9, 8}, 16)));
System.out.println(json(Solution82.solve(new int[]{6, 9, 8}, 2, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -3, 3, -5, -4, -2, 1}, 3)));
System.out.println(json(Solution84.solve(new int[]{6, 8, 9})));
System.out.println(json(Solution85.solve(new int[]{6, 9, 8}, 8)));
System.out.println(json(Solution86.solve(new int[]{6, 9, 8}, 4)));
System.out.println(json(Solution87.solve(new int[]{6, 9, 8}, 4)));
System.out.println(json(Solution88.solve(new int[]{5, 8, 7}, 3)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, 2)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4}, new int[]{-2}}, 2)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{-6, -5, 1, 3, 8}, new int[]{-5, -3, -1, 0, 5, 6, 7, 8})));
System.out.println(json(Solution71.solve(new int[]{}, 11)));
System.out.println(json(Solution72.solve(new int[]{}, 11, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{}, 11, "upper")));
System.out.println(json(Solution73.solve(new int[]{}, 11)));
System.out.println(json(Solution74.solve(new int[]{}, 11)));
System.out.println(json(Solution75.solve(new int[]{}, 11)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 4, 2, 0, -2, -4})));
System.out.println(json(Solution77.solve(new int[]{5})));
System.out.println(json(Solution78.solve(new int[]{0})));
System.out.println(json(Solution79.solve(new int[]{0})));
System.out.println(json(Solution80.solve(new int[]{0}, 11)));
System.out.println(json(Solution81.solve(new int[]{14, 14, 2, 2, 12, 15, 8}, 21)));
System.out.println(json(Solution82.solve(new int[]{14, 14, 2, 2, 12, 15, 8}, 5, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 8, 1, -2, -3}, 5)));
System.out.println(json(Solution84.solve(new int[]{2, 2, 8, 12, 14, 14, 15})));
System.out.println(json(Solution85.solve(new int[]{14, 14, 2, 2, 12, 15, 8}, 33)));
System.out.println(json(Solution86.solve(new int[]{14, 14, 2, 2, 12, 15, 8}, 6)));
System.out.println(json(Solution87.solve(new int[]{14, 14, 2, 2, 12, 15, 8}, 6)));
System.out.println(json(Solution88.solve(new int[]{13, 13, 1, 1, 11, 14, 7}, 4)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, 11)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -4, -3}, new int[]{-4, -3, -3}, new int[]{-4, -2, 0}, new int[]{-4, 0, 1}}, 11)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 22)));
System.out.println(json(Solution93.solve(new int[]{}, new int[]{0})));
System.out.println(json(Solution71.solve(new int[]{-5, 1, 3}, 3)));
System.out.println(json(Solution72.solve(new int[]{-5, 1, 3, 3}, 3, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-5, 1, 3, 3}, 3, "upper")));
System.out.println(json(Solution73.solve(new int[]{-5, 1, 3, 3}, 3)));
System.out.println(json(Solution74.solve(new int[]{-5, 1, 3, 3}, 3)));
System.out.println(json(Solution75.solve(new int[]{-5, 1, 3}, 3)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1, -3, -5, -7})));
System.out.println(json(Solution77.solve(new int[]{0, -1, 7, 0})));
System.out.println(json(Solution78.solve(new int[]{-5, 1, 3})));
System.out.println(json(Solution79.solve(new int[]{-5, 1, 3})));
System.out.println(json(Solution80.solve(new int[]{-5, 1, 3}, 3)));
System.out.println(json(Solution81.solve(new int[]{15, 3, 14, 15, 15, 15}, 2)));
System.out.println(json(Solution82.solve(new int[]{15, 3, 14, 15, 15, 15}, 1, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 4, -4, 6}, 3)));
System.out.println(json(Solution84.solve(new int[]{3, 14, 15, 15, 15, 15})));
System.out.println(json(Solution85.solve(new int[]{15, 3, 14, 15, 15, 15}, 10)));
System.out.println(json(Solution86.solve(new int[]{15, 3, 14, 15, 15, 15}, 8)));
System.out.println(json(Solution87.solve(new int[]{15, 3, 14, 15, 15, 15}, 8)));
System.out.println(json(Solution88.solve(new int[]{14, 2, 13, 14, 14, 14}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, 3)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4}, new int[]{-2}}, 3)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(4, 7, 22)));
System.out.println(json(Solution93.solve(new int[]{-5, 1, 3, 3}, new int[]{-4, -1, 5, 6})));
System.out.println(json(Solution71.solve(new int[]{}, 8)));
System.out.println(json(Solution72.solve(new int[]{}, 8, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{}, 8, "upper")));
System.out.println(json(Solution73.solve(new int[]{}, 8)));
System.out.println(json(Solution74.solve(new int[]{}, 8)));
System.out.println(json(Solution75.solve(new int[]{}, 8)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10, 8})));
System.out.println(json(Solution77.solve(new int[]{1, -5})));
System.out.println(json(Solution78.solve(new int[]{0})));
System.out.println(json(Solution79.solve(new int[]{0})));
System.out.println(json(Solution80.solve(new int[]{0}, 8)));
System.out.println(json(Solution81.solve(new int[]{14}, 14)));
System.out.println(json(Solution82.solve(new int[]{14}, 5, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 3, -5, 7, -2}, 6)));
System.out.println(json(Solution84.solve(new int[]{14})));
System.out.println(json(Solution85.solve(new int[]{14}, 23)));
System.out.println(json(Solution86.solve(new int[]{14}, 1)));
}
static void batch8500() {
System.out.println(json(Solution87.solve(new int[]{14}, 1)));
System.out.println(json(Solution88.solve(new int[]{13}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8, -6}, new int[]{-4, -2, 0}, new int[]{2, 4, 6}, new int[]{8, 10, 12}}, 8)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-5, -3, -2}, new int[]{-4, -1, 0}, new int[]{-4, -1, 1}, new int[]{-4, 0, 3}}, 8)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 2)));
System.out.println(json(Solution93.solve(new int[]{}, new int[]{-3, -3, -1, 3, 6, 7})));
System.out.println(json(Solution71.solve(new int[]{-4, 1, 3, 4, 5, 8}, -3)));
System.out.println(json(Solution72.solve(new int[]{-4, 1, 1, 3, 4, 5, 8}, -3, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-4, 1, 1, 3, 4, 5, 8}, -3, "upper")));
System.out.println(json(Solution73.solve(new int[]{-4, 1, 1, 3, 4, 5, 8}, -3)));
System.out.println(json(Solution74.solve(new int[]{-4, 1, 1, 3, 4, 5, 8}, -3)));
System.out.println(json(Solution75.solve(new int[]{-4, 1, 3, 4, 5, 8}, -3)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 15, 13, 11})));
System.out.println(json(Solution77.solve(new int[]{2})));
System.out.println(json(Solution78.solve(new int[]{3, 4, 5, 8, -4, 1})));
System.out.println(json(Solution79.solve(new int[]{3, 4, 5, 8, -4, 1})));
System.out.println(json(Solution80.solve(new int[]{3, 4, 5, 8, -4, 1}, -3)));
System.out.println(json(Solution81.solve(new int[]{9, 11, 6, 12, 9, 12, 12}, 19)));
System.out.println(json(Solution82.solve(new int[]{9, 11, 6, 12, 9, 12, 12}, 5, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -4}, 2)));
System.out.println(json(Solution84.solve(new int[]{6, 9, 9, 11, 12, 12, 12})));
System.out.println(json(Solution85.solve(new int[]{9, 11, 6, 12, 9, 12, 12}, 28)));
System.out.println(json(Solution86.solve(new int[]{9, 11, 6, 12, 9, 12, 12}, 5)));
System.out.println(json(Solution87.solve(new int[]{9, 11, 6, 12, 9, 12, 12}, 5)));
System.out.println(json(Solution88.solve(new int[]{8, 10, 5, 11, 8, 11, 11}, 5)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, -3)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4, -3}, new int[]{-4, -1}, new int[]{-2, 0}}, -3)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 3)));
System.out.println(json(Solution92.solve(4, 7, 2)));
System.out.println(json(Solution93.solve(new int[]{-4, 1, 1, 3, 4, 5, 8}, new int[]{-3, -3, -2, 0, 8})));
System.out.println(json(Solution71.solve(new int[]{-5, -3, 0, 2}, 12)));
System.out.println(json(Solution72.solve(new int[]{-5, -3, 0, 2}, 12, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-5, -3, 0, 2}, 12, "upper")));
System.out.println(json(Solution73.solve(new int[]{-5, -3, 0, 2}, 12)));
System.out.println(json(Solution74.solve(new int[]{-5, -3, 0, 2}, 12)));
System.out.println(json(Solution75.solve(new int[]{-5, -3, 0, 2}, 12)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 1, -1})));
System.out.println(json(Solution77.solve(new int[]{-4, 0, 1, -1, 3, -3, 5})));
System.out.println(json(Solution78.solve(new int[]{0, 2, -5, -3})));
System.out.println(json(Solution79.solve(new int[]{0, 2, -5, -3})));
System.out.println(json(Solution80.solve(new int[]{0, 2, -5, -3}, 12)));
System.out.println(json(Solution81.solve(new int[]{4, 15, 13, 8}, 9)));
System.out.println(json(Solution82.solve(new int[]{4, 15, 13, 8}, 5, 3)));
System.out.println(json(Solution83.solve(new int[]{0, 20, -6, 6, -1}, 3)));
System.out.println(json(Solution84.solve(new int[]{4, 8, 13, 15})));
System.out.println(json(Solution85.solve(new int[]{4, 15, 13, 8}, 17)));
System.out.println(json(Solution86.solve(new int[]{4, 15, 13, 8}, 4)));
System.out.println(json(Solution87.solve(new int[]{4, 15, 13, 8}, 4)));
System.out.println(json(Solution88.solve(new int[]{3, 14, 12, 7}, 3)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, 12)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-4}, new int[]{-4}}, 12)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{-5, -3, 0, 2}, new int[]{-4, -4, 1, 2})));
System.out.println(json(Solution71.solve(new int[]{-6, -3, -2, -1, 4, 8}, 13)));
System.out.println(json(Solution72.solve(new int[]{-6, -3, -2, -1, 4, 4, 8, 8}, 13, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-6, -3, -2, -1, 4, 4, 8, 8}, 13, "upper")));
System.out.println(json(Solution73.solve(new int[]{-6, -3, -2, -1, 4, 4, 8, 8}, 13)));
System.out.println(json(Solution74.solve(new int[]{-6, -3, -2, -1, 4, 4, 8, 8}, 13)));
System.out.println(json(Solution75.solve(new int[]{-6, -3, -2, -1, 4, 8}, 13)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 10, 8, 6, 4})));
System.out.println(json(Solution77.solve(new int[]{1, -6, -4, 2, -3})));
System.out.println(json(Solution78.solve(new int[]{-1, 4, 8, -6, -3, -2})));
System.out.println(json(Solution79.solve(new int[]{-1, 4, 8, -6, -3, -2})));
System.out.println(json(Solution80.solve(new int[]{-1, 4, 8, -6, -3, -2}, 13)));
System.out.println(json(Solution81.solve(new int[]{4, 4, 13, 1, 8, 12}, 23)));
System.out.println(json(Solution82.solve(new int[]{4, 4, 13, 1, 8, 12}, 3, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 8, -3}, 4)));
System.out.println(json(Solution84.solve(new int[]{1, 4, 4, 8, 12, 13})));
System.out.println(json(Solution85.solve(new int[]{4, 4, 13, 1, 8, 12}, 20)));
System.out.println(json(Solution86.solve(new int[]{4, 4, 13, 1, 8, 12}, 5)));
System.out.println(json(Solution87.solve(new int[]{4, 4, 13, 1, 8, 12}, 5)));
System.out.println(json(Solution88.solve(new int[]{3, 3, 12, 0, 7, 11}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, 13)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -3}, new int[]{-2, -1}, new int[]{0, 2}}, 13)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 9)));
System.out.println(json(Solution92.solve(8, 3, 10)));
System.out.println(json(Solution93.solve(new int[]{-6, -3, -2, -1, 4, 4, 8, 8}, new int[]{3, 4, 5, 5, 5})));
System.out.println(json(Solution71.solve(new int[]{-5, 3, 4}, -2)));
System.out.println(json(Solution72.solve(new int[]{-5, 3, 4}, -2, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{-5, 3, 4}, -2, "upper")));
System.out.println(json(Solution73.solve(new int[]{-5, 3, 4}, -2)));
System.out.println(json(Solution74.solve(new int[]{-5, 3, 4}, -2)));
System.out.println(json(Solution75.solve(new int[]{-5, 3, 4}, -2)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 12, 15, 13, 11, 9, 7, 5})));
System.out.println(json(Solution77.solve(new int[]{6})));
System.out.println(json(Solution78.solve(new int[]{4, -5, 3})));
System.out.println(json(Solution79.solve(new int[]{4, -5, 3})));
System.out.println(json(Solution80.solve(new int[]{4, -5, 3}, -2)));
System.out.println(json(Solution81.solve(new int[]{3, 5}, 21)));
System.out.println(json(Solution82.solve(new int[]{3, 5}, 1, 4)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 5, -1, -2, 1, -6, 3}, 5)));
System.out.println(json(Solution84.solve(new int[]{3, 5})));
System.out.println(json(Solution85.solve(new int[]{3, 5}, 17)));
System.out.println(json(Solution86.solve(new int[]{3, 5}, 1)));
System.out.println(json(Solution87.solve(new int[]{3, 5}, 1)));
System.out.println(json(Solution88.solve(new int[]{2, 4}, 2)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10, -8}, new int[]{-6, -4}, new int[]{-2, 0}}, -2)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3, -3}, new int[]{-2, -2}, new int[]{-1, 0}}, -2)));
}
static void batch8600() {
System.out.println(json(Solution91.solve(new int[][]{new int[]{-8, -8, -5}, new int[]{-6, -5, -5}, new int[]{-3, -1, 0}}, 8)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{-5, 3, 4}, new int[]{-5, -3, -3, 1, 6, 8, 8, 8})));
System.out.println(json(Solution71.solve(new int[]{0, 3, 5, 7, 8}, 13)));
System.out.println(json(Solution72.solve(new int[]{0, 3, 3, 3, 5, 7, 8}, 13, "ceiling")));
System.out.println(json(Solution72.solve(new int[]{0, 3, 3, 3, 5, 7, 8}, 13, "upper")));
System.out.println(json(Solution73.solve(new int[]{0, 3, 3, 3, 5, 7, 8}, 13)));
System.out.println(json(Solution74.solve(new int[]{0, 3, 3, 3, 5, 7, 8}, 13)));
System.out.println(json(Solution75.solve(new int[]{0, 3, 5, 7, 8}, 13)));
System.out.println(json(Solution76.solve(new int[]{0, 3, 6, 9, 7, 5})));
System.out.println(json(Solution77.solve(new int[]{1})));
System.out.println(json(Solution78.solve(new int[]{0, 3, 5, 7, 8})));
System.out.println(json(Solution79.solve(new int[]{0, 3, 5, 7, 8})));
System.out.println(json(Solution80.solve(new int[]{0, 3, 5, 7, 8}, 13)));
System.out.println(json(Solution81.solve(new int[]{13, 4, 7, 10, 1, 5}, 9)));
System.out.println(json(Solution82.solve(new int[]{13, 4, 7, 10, 1, 5}, 1, 2)));
System.out.println(json(Solution83.solve(new int[]{0, 20, 4, 8, 2, 7, -1}, 5)));
System.out.println(json(Solution84.solve(new int[]{1, 4, 5, 7, 10, 13})));
System.out.println(json(Solution85.solve(new int[]{13, 4, 7, 10, 1, 5}, 17)));
System.out.println(json(Solution86.solve(new int[]{13, 4, 7, 10, 1, 5}, 4)));
System.out.println(json(Solution87.solve(new int[]{13, 4, 7, 10, 1, 5}, 4)));
System.out.println(json(Solution88.solve(new int[]{12, 3, 6, 9, 0, 4}, 1)));
System.out.println(json(Solution89.solve(new int[][]{new int[]{-10}, new int[]{-8}}, 13)));
System.out.println(json(Solution90.solve(new int[][]{new int[]{-3}, new int[]{-1}}, 13)));
System.out.println(json(Solution91.solve(new int[][]{new int[]{-9, -6}, new int[]{-7, -5}}, 1)));
System.out.println(json(Solution92.solve(8, 3, 18)));
System.out.println(json(Solution93.solve(new int[]{0, 3, 3, 3, 5, 7, 8}, new int[]{2})));
System.out.println(json(Solution85.solve(new int[]{0, 0}, 4)));
System.out.println(json(Solution88.solve(new int[]{0, 0, 0}, 2)));
System.out.println(json(Solution93.solve(new int[]{}, new int[]{-3, -2})));
System.out.println(json(Solution93.solve(new int[]{1}, new int[]{})));
System.out.println(json(Solution82.solve(new int[]{1, 2, 3}, 1000000000, 1000000000)));
System.out.println(json(Solution94.solve(new int[]{-5, -3, 0, 3, -5, -6, 5, 4, -2}, 9)));
System.out.println(json(Solution95.solve(new int[]{-5, -3, 0, 3, -5, -6, 5, 4, -2}, 9)));
System.out.println(json(Solution109.solve(new int[]{-5, -3, 0, 3, -5, -6, 5, 4, -2}, 1)));
System.out.println(json(Solution110.solve(new int[]{-5, -3, 0, 3, -5, -6, 5, 4, -2}, 9)));
System.out.println(json(Solution96.solve(new int[]{-5, -3, 0, 3, -5, -6, 5, 4, -2}, 2)));
System.out.println(json(Solution99.solve(new int[]{-6, -5, -5, -3, -2, 0, 3, 4, 5}, 9, -1)));
System.out.println(json(Solution103.solve(new int[]{6, 4, 1, 4, 6, 7, 6, 5, 3})));
System.out.println(json(Solution104.solve("ACBCAAAB", 2)));
System.out.println(json(Solution105.solve("acbcaaab")));
System.out.println(json(Solution113.solve(new int[]{-5, -3, 0, 3, -5, -6, 5, 4, -2})));
System.out.println(json(Solution112.solve("acbcaaab")));
System.out.println(json(Solution115.solve("acbcaaab", "a")));
System.out.println(json(Solution114.solve(80501)));
System.out.println(json(Solution94.solve(new int[]{5, -2, -6, -4, 2}, 1)));
System.out.println(json(Solution95.solve(new int[]{5, -2, -6, -4, 2}, 1)));
System.out.println(json(Solution109.solve(new int[]{5, -2, -6, -4, 2}, 1)));
System.out.println(json(Solution110.solve(new int[]{5, -2, -6, -4, 2}, 1)));
System.out.println(json(Solution96.solve(new int[]{5, -2, -6, -4, 2}, 1)));
System.out.println(json(Solution99.solve(new int[]{-6, -4, -2, 2, 5}, 1, 6)));
System.out.println(json(Solution103.solve(new int[]{6, 3, 7, 5, 3})));
System.out.println(json(Solution104.solve("BABACAAB", 2)));
System.out.println(json(Solution105.solve("babacaab")));
System.out.println(json(Solution113.solve(new int[]{5, -2, -6, -4, 2})));
System.out.println(json(Solution112.solve("babacaab")));
System.out.println(json(Solution115.solve("babacaab", "a")));
System.out.println(json(Solution114.solve(30025)));
System.out.println(json(Solution94.solve(new int[]{2, 6, -3, -1, -4, -2, 1}, 6)));
System.out.println(json(Solution95.solve(new int[]{2, 6, -3, -1, -4, -2, 1}, 6)));
System.out.println(json(Solution109.solve(new int[]{2, 6, -3, -1, -4, -2, 1}, 1)));
System.out.println(json(Solution110.solve(new int[]{2, 6, -3, -1, -4, -2, 1}, 6)));
System.out.println(json(Solution96.solve(new int[]{2, 6, -3, -1, -4, -2, 1}, 6)));
System.out.println(json(Solution99.solve(new int[]{-4, -3, -2, -1, 1, 2, 6}, 6, -3)));
System.out.println(json(Solution103.solve(new int[]{3, 7, 4, 2, 5, 3, 2})));
System.out.println(json(Solution104.solve("", 0)));
System.out.println(json(Solution105.solve("")));
System.out.println(json(Solution113.solve(new int[]{2, 6, -3, -1, -4, -2, 1})));
System.out.println(json(Solution112.solve("")));
System.out.println(json(Solution115.solve("", "a")));
System.out.println(json(Solution114.solve(33135)));
System.out.println(json(Solution94.solve(new int[]{0, -1, 2, -4, 3, -5, 4, 5}, 2)));
System.out.println(json(Solution95.solve(new int[]{0, -1, 2, -4, 3, -5, 4, 5}, 2)));
System.out.println(json(Solution109.solve(new int[]{0, -1, 2, -4, 3, -5, 4, 5}, 1)));
System.out.println(json(Solution110.solve(new int[]{0, -1, 2, -4, 3, -5, 4, 5}, 2)));
System.out.println(json(Solution96.solve(new int[]{0, -1, 2, -4, 3, -5, 4, 5}, 5)));
System.out.println(json(Solution99.solve(new int[]{-5, -4, -1, 0, 2, 3, 4, 5}, 2, 7)));
System.out.println(json(Solution103.solve(new int[]{1, 2, 3, 5, 4, 6, 5, 6})));
System.out.println(json(Solution104.solve("B", 0)));
System.out.println(json(Solution105.solve("b")));
System.out.println(json(Solution113.solve(new int[]{0, -1, 2, -4, 3, -5, 4, 5})));
System.out.println(json(Solution112.solve("b")));
System.out.println(json(Solution115.solve("b", "a")));
System.out.println(json(Solution114.solve(84159)));
System.out.println(json(Solution94.solve(new int[]{3}, 1)));
System.out.println(json(Solution95.solve(new int[]{3}, 1)));
System.out.println(json(Solution109.solve(new int[]{3}, 1)));
System.out.println(json(Solution110.solve(new int[]{3}, 1)));
System.out.println(json(Solution96.solve(new int[]{3}, 1)));
System.out.println(json(Solution99.solve(new int[]{3}, 1, 4)));
System.out.println(json(Solution103.solve(new int[]{4})));
System.out.println(json(Solution104.solve("", 0)));
System.out.println(json(Solution105.solve("")));
System.out.println(json(Solution113.solve(new int[]{3})));
System.out.println(json(Solution112.solve("")));
System.out.println(json(Solution115.solve("", "a")));
System.out.println(json(Solution114.solve(74503)));
System.out.println(json(Solution94.solve(new int[]{0, -1, 5, 5, -4, 3, -4}, 6)));
System.out.println(json(Solution95.solve(new int[]{0, -1, 5, 5, -4, 3, -4}, 6)));
System.out.println(json(Solution109.solve(new int[]{0, -1, 5, 5, -4, 3, -4}, 1)));
}
static void batch8700() {
System.out.println(json(Solution110.solve(new int[]{0, -1, 5, 5, -4, 3, -4}, 6)));
System.out.println(json(Solution96.solve(new int[]{0, -1, 5, 5, -4, 3, -4}, 5)));
System.out.println(json(Solution99.solve(new int[]{-4, -4, -1, 0, 3, 5, 5}, 6, -3)));
System.out.println(json(Solution103.solve(new int[]{1, 2, 6, 6, 5, 4, 5})));
System.out.println(json(Solution104.solve("BABBAAA", 3)));
System.out.println(json(Solution105.solve("babbaaa")));
System.out.println(json(Solution113.solve(new int[]{0, -1, 5, 5, -4, 3, -4})));
System.out.println(json(Solution112.solve("babbaaa")));
System.out.println(json(Solution115.solve("babbaaa", "a")));
System.out.println(json(Solution114.solve(87038)));
System.out.println(json(Solution94.solve(new int[]{7, 4, -2, -5, 4, 3, -6, 0, -6}, 9)));
System.out.println(json(Solution95.solve(new int[]{7, 4, -2, -5, 4, 3, -6, 0, -6}, 9)));
System.out.println(json(Solution109.solve(new int[]{7, 4, -2, -5, 4, 3, -6, 0, -6}, 1)));
System.out.println(json(Solution110.solve(new int[]{7, 4, -2, -5, 4, 3, -6, 0, -6}, 9)));
System.out.println(json(Solution96.solve(new int[]{7, 4, -2, -5, 4, 3, -6, 0, -6}, 3)));
System.out.println(json(Solution99.solve(new int[]{-6, -6, -5, -2, 0, 3, 4, 4, 7}, 9, 7)));
System.out.println(json(Solution103.solve(new int[]{8, 5, 3, 6, 5, 4, 7, 1, 7})));
System.out.println(json(Solution104.solve("AABBCC", 1)));
System.out.println(json(Solution105.solve("aabbcc")));
System.out.println(json(Solution113.solve(new int[]{7, 4, -2, -5, 4, 3, -6, 0, -6})));
System.out.println(json(Solution112.solve("aabbcc")));
System.out.println(json(Solution115.solve("aabbcc", "a")));
System.out.println(json(Solution114.solve(25252)));
System.out.println(json(Solution94.solve(new int[]{4, -2}, 1)));
System.out.println(json(Solution95.solve(new int[]{4, -2}, 1)));
System.out.println(json(Solution109.solve(new int[]{4, -2}, 1)));
System.out.println(json(Solution110.solve(new int[]{4, -2}, 1)));
System.out.println(json(Solution96.solve(new int[]{4, -2}, 2)));
System.out.println(json(Solution99.solve(new int[]{-2, 4}, 1, 2)));
System.out.println(json(Solution103.solve(new int[]{5, 3})));
System.out.println(json(Solution104.solve("BBCBCAC", 1)));
System.out.println(json(Solution105.solve("bbcbcac")));
System.out.println(json(Solution113.solve(new int[]{4, -2})));
System.out.println(json(Solution112.solve("bbcbcac")));
System.out.println(json(Solution115.solve("bbcbcac", "a")));
System.out.println(json(Solution114.solve(74324)));
System.out.println(json(Solution94.solve(new int[]{6, 4, -4, 1, -4, 6}, 5)));
System.out.println(json(Solution95.solve(new int[]{6, 4, -4, 1, -4, 6}, 5)));
System.out.println(json(Solution109.solve(new int[]{6, 4, -4, 1, -4, 6}, 1)));
System.out.println(json(Solution110.solve(new int[]{6, 4, -4, 1, -4, 6}, 5)));
System.out.println(json(Solution96.solve(new int[]{6, 4, -4, 1, -4, 6}, 4)));
System.out.println(json(Solution99.solve(new int[]{-4, -4, 1, 4, 6, 6}, 5, -7)));
System.out.println(json(Solution103.solve(new int[]{7, 5, 5, 2, 5, 7})));
System.out.println(json(Solution104.solve("ABCACCBA", 0)));
System.out.println(json(Solution105.solve("abcaccba")));
System.out.println(json(Solution113.solve(new int[]{6, 4, -4, 1, -4, 6})));
System.out.println(json(Solution112.solve("abcaccba")));
System.out.println(json(Solution115.solve("abcaccba", "a")));
System.out.println(json(Solution114.solve(85783)));
System.out.println(json(Solution94.solve(new int[]{-2}, 1)));
System.out.println(json(Solution95.solve(new int[]{-2}, 1)));
System.out.println(json(Solution109.solve(new int[]{-2}, 1)));
System.out.println(json(Solution110.solve(new int[]{-2}, 1)));
System.out.println(json(Solution96.solve(new int[]{-2}, 1)));
System.out.println(json(Solution99.solve(new int[]{-2}, 1, -1)));
System.out.println(json(Solution103.solve(new int[]{3})));
System.out.println(json(Solution104.solve("ACA", 1)));
System.out.println(json(Solution105.solve("aca")));
System.out.println(json(Solution113.solve(new int[]{-2})));
System.out.println(json(Solution112.solve("aca")));
System.out.println(json(Solution115.solve("aca", "a")));
System.out.println(json(Solution114.solve(27956)));
System.out.println(json(Solution94.solve(new int[]{0}, 1)));
System.out.println(json(Solution95.solve(new int[]{0}, 1)));
System.out.println(json(Solution109.solve(new int[]{0}, 1)));
System.out.println(json(Solution110.solve(new int[]{0}, 1)));
System.out.println(json(Solution96.solve(new int[]{0}, 1)));
System.out.println(json(Solution99.solve(new int[]{0}, 1, 6)));
System.out.println(json(Solution103.solve(new int[]{1})));
System.out.println(json(Solution104.solve("ACABACB", 2)));
System.out.println(json(Solution105.solve("acabacb")));
System.out.println(json(Solution113.solve(new int[]{0})));
System.out.println(json(Solution112.solve("acabacb")));
System.out.println(json(Solution115.solve("acabacb", "a")));
System.out.println(json(Solution114.solve(5593)));
System.out.println(json(Solution94.solve(new int[]{-7, 1, 1, 0, 7}, 3)));
System.out.println(json(Solution95.solve(new int[]{-7, 1, 1, 0, 7}, 3)));
System.out.println(json(Solution109.solve(new int[]{-7, 1, 1, 0, 7}, 1)));
System.out.println(json(Solution110.solve(new int[]{-7, 1, 1, 0, 7}, 3)));
System.out.println(json(Solution96.solve(new int[]{-7, 1, 1, 0, 7}, 2)));
System.out.println(json(Solution99.solve(new int[]{-7, 0, 1, 1, 7}, 3, 5)));
System.out.println(json(Solution103.solve(new int[]{8, 2, 2, 1, 8})));
System.out.println(json(Solution104.solve("CBCC", 2)));
System.out.println(json(Solution105.solve("cbcc")));
System.out.println(json(Solution113.solve(new int[]{-7, 1, 1, 0, 7})));
System.out.println(json(Solution112.solve("cbcc")));
System.out.println(json(Solution115.solve("cbcc", "a")));
System.out.println(json(Solution114.solve(95273)));
System.out.println(json(Solution94.solve(new int[]{4, 6, 0, -4, 6, 0, -6, 3}, 4)));
System.out.println(json(Solution95.solve(new int[]{4, 6, 0, -4, 6, 0, -6, 3}, 4)));
System.out.println(json(Solution109.solve(new int[]{4, 6, 0, -4, 6, 0, -6, 3}, 1)));
System.out.println(json(Solution110.solve(new int[]{4, 6, 0, -4, 6, 0, -6, 3}, 4)));
System.out.println(json(Solution96.solve(new int[]{4, 6, 0, -4, 6, 0, -6, 3}, 3)));
System.out.println(json(Solution99.solve(new int[]{-6, -4, 0, 0, 3, 4, 6, 6}, 4, 3)));
System.out.println(json(Solution103.solve(new int[]{5, 7, 1, 5, 7, 1, 7, 4})));
System.out.println(json(Solution104.solve("C", 2)));
System.out.println(json(Solution105.solve("c")));
System.out.println(json(Solution113.solve(new int[]{4, 6, 0, -4, 6, 0, -6, 3})));
System.out.println(json(Solution112.solve("c")));
System.out.println(json(Solution115.solve("c", "a")));
}
static void batch8800() {
System.out.println(json(Solution114.solve(29625)));
System.out.println(json(Solution94.solve(new int[]{1, -7, 1, -5, 4, 7, -1}, 4)));
System.out.println(json(Solution95.solve(new int[]{1, -7, 1, -5, 4, 7, -1}, 4)));
System.out.println(json(Solution109.solve(new int[]{1, -7, 1, -5, 4, 7, -1}, 1)));
System.out.println(json(Solution110.solve(new int[]{1, -7, 1, -5, 4, 7, -1}, 4)));
System.out.println(json(Solution96.solve(new int[]{1, -7, 1, -5, 4, 7, -1}, 2)));
System.out.println(json(Solution99.solve(new int[]{-7, -5, -1, 1, 1, 4, 7}, 4, -7)));
System.out.println(json(Solution103.solve(new int[]{2, 8, 2, 6, 5, 8, 2})));
System.out.println(json(Solution104.solve("", 0)));
System.out.println(json(Solution105.solve("")));
System.out.println(json(Solution113.solve(new int[]{1, -7, 1, -5, 4, 7, -1})));
System.out.println(json(Solution112.solve("")));
System.out.println(json(Solution115.solve("", "a")));
System.out.println(json(Solution114.solve(16479)));
System.out.println(json(Solution94.solve(new int[]{2, -1}, 2)));
System.out.println(json(Solution95.solve(new int[]{2, -1}, 2)));
System.out.println(json(Solution109.solve(new int[]{2, -1}, 1)));
System.out.println(json(Solution110.solve(new int[]{2, -1}, 2)));
System.out.println(json(Solution96.solve(new int[]{2, -1}, 1)));
System.out.println(json(Solution99.solve(new int[]{-1, 2}, 2, 1)));
System.out.println(json(Solution103.solve(new int[]{3, 2})));
System.out.println(json(Solution104.solve("BBA", 0)));
System.out.println(json(Solution105.solve("bba")));
System.out.println(json(Solution113.solve(new int[]{2, -1})));
System.out.println(json(Solution112.solve("bba")));
System.out.println(json(Solution115.solve("bba", "a")));
System.out.println(json(Solution114.solve(78315)));
System.out.println(json(Solution94.solve(new int[]{-1, 7, -2, -7, -2, 5, -5, 5, 0}, 1)));
System.out.println(json(Solution95.solve(new int[]{-1, 7, -2, -7, -2, 5, -5, 5, 0}, 1)));
System.out.println(json(Solution109.solve(new int[]{-1, 7, -2, -7, -2, 5, -5, 5, 0}, 1)));
System.out.println(json(Solution110.solve(new int[]{-1, 7, -2, -7, -2, 5, -5, 5, 0}, 1)));
System.out.println(json(Solution96.solve(new int[]{-1, 7, -2, -7, -2, 5, -5, 5, 0}, 3)));
System.out.println(json(Solution99.solve(new int[]{-7, -5, -2, -2, -1, 0, 5, 5, 7}, 1, 3)));
System.out.println(json(Solution103.solve(new int[]{2, 8, 3, 8, 3, 6, 6, 6, 1})));
System.out.println(json(Solution104.solve("BBC", 1)));
System.out.println(json(Solution105.solve("bbc")));
System.out.println(json(Solution113.solve(new int[]{-1, 7, -2, -7, -2, 5, -5, 5, 0})));
System.out.println(json(Solution112.solve("bbc")));
System.out.println(json(Solution115.solve("bbc", "a")));
System.out.println(json(Solution114.solve(19360)));
System.out.println(json(Solution94.solve(new int[]{5, -6, -1, 6, 3, 5, -4, -3}, 3)));
System.out.println(json(Solution95.solve(new int[]{5, -6, -1, 6, 3, 5, -4, -3}, 3)));
System.out.println(json(Solution109.solve(new int[]{5, -6, -1, 6, 3, 5, -4, -3}, 1)));
System.out.println(json(Solution110.solve(new int[]{5, -6, -1, 6, 3, 5, -4, -3}, 3)));
System.out.println(json(Solution96.solve(new int[]{5, -6, -1, 6, 3, 5, -4, -3}, 3)));
System.out.println(json(Solution99.solve(new int[]{-6, -4, -3, -1, 3, 5, 5, 6}, 3, 5)));
System.out.println(json(Solution103.solve(new int[]{6, 7, 2, 7, 4, 6, 5, 4})));
System.out.println(json(Solution104.solve("CBC", 3)));
System.out.println(json(Solution105.solve("cbc")));
System.out.println(json(Solution113.solve(new int[]{5, -6, -1, 6, 3, 5, -4, -3})));
System.out.println(json(Solution112.solve("cbc")));
System.out.println(json(Solution115.solve("cbc", "a")));
System.out.println(json(Solution114.solve(83074)));
System.out.println(json(Solution94.solve(new int[]{7, 5, 2, 2, 2, -7, -6}, 1)));
System.out.println(json(Solution95.solve(new int[]{7, 5, 2, 2, 2, -7, -6}, 1)));
System.out.println(json(Solution109.solve(new int[]{7, 5, 2, 2, 2, -7, -6}, 1)));
System.out.println(json(Solution110.solve(new int[]{7, 5, 2, 2, 2, -7, -6}, 1)));
System.out.println(json(Solution96.solve(new int[]{7, 5, 2, 2, 2, -7, -6}, 5)));
System.out.println(json(Solution99.solve(new int[]{-7, -6, 2, 2, 2, 5, 7}, 1, 5)));
System.out.println(json(Solution103.solve(new int[]{8, 6, 3, 3, 3, 8, 7})));
System.out.println(json(Solution104.solve("ACBAB", 0)));
System.out.println(json(Solution105.solve("acbab")));
System.out.println(json(Solution113.solve(new int[]{7, 5, 2, 2, 2, -7, -6})));
System.out.println(json(Solution112.solve("acbab")));
System.out.println(json(Solution115.solve("acbab", "a")));
System.out.println(json(Solution114.solve(27683)));
System.out.println(json(Solution94.solve(new int[]{4, 4, 5, 6, 0, -7, -5, 7, 6}, 1)));
System.out.println(json(Solution95.solve(new int[]{4, 4, 5, 6, 0, -7, -5, 7, 6}, 1)));
System.out.println(json(Solution109.solve(new int[]{4, 4, 5, 6, 0, -7, -5, 7, 6}, 1)));
System.out.println(json(Solution110.solve(new int[]{4, 4, 5, 6, 0, -7, -5, 7, 6}, 1)));
System.out.println(json(Solution96.solve(new int[]{4, 4, 5, 6, 0, -7, -5, 7, 6}, 6)));
System.out.println(json(Solution99.solve(new int[]{-7, -5, 0, 4, 4, 5, 6, 6, 7}, 1, -6)));
System.out.println(json(Solution103.solve(new int[]{5, 5, 6, 7, 1, 8, 6, 8, 7})));
System.out.println(json(Solution104.solve("A", 3)));
System.out.println(json(Solution105.solve("a")));
System.out.println(json(Solution113.solve(new int[]{4, 4, 5, 6, 0, -7, -5, 7, 6})));
System.out.println(json(Solution112.solve("a")));
System.out.println(json(Solution115.solve("a", "a")));
System.out.println(json(Solution114.solve(70550)));
System.out.println(json(Solution94.solve(new int[]{4, 2, -1}, 2)));
System.out.println(json(Solution95.solve(new int[]{4, 2, -1}, 2)));
System.out.println(json(Solution109.solve(new int[]{4, 2, -1}, 1)));
System.out.println(json(Solution110.solve(new int[]{4, 2, -1}, 2)));
System.out.println(json(Solution96.solve(new int[]{4, 2, -1}, 3)));
System.out.println(json(Solution99.solve(new int[]{-1, 2, 4}, 2, 3)));
System.out.println(json(Solution103.solve(new int[]{5, 3, 2})));
System.out.println(json(Solution104.solve("BA", 3)));
System.out.println(json(Solution105.solve("ba")));
System.out.println(json(Solution113.solve(new int[]{4, 2, -1})));
System.out.println(json(Solution112.solve("ba")));
System.out.println(json(Solution115.solve("ba", "a")));
System.out.println(json(Solution114.solve(89810)));
System.out.println(json(Solution94.solve(new int[]{6, 7, -2, 4, -5, -4, -2, 4}, 5)));
System.out.println(json(Solution95.solve(new int[]{6, 7, -2, 4, -5, -4, -2, 4}, 5)));
System.out.println(json(Solution109.solve(new int[]{6, 7, -2, 4, -5, -4, -2, 4}, 1)));
System.out.println(json(Solution110.solve(new int[]{6, 7, -2, 4, -5, -4, -2, 4}, 5)));
System.out.println(json(Solution96.solve(new int[]{6, 7, -2, 4, -5, -4, -2, 4}, 6)));
System.out.println(json(Solution99.solve(new int[]{-5, -4, -2, -2, 4, 4, 6, 7}, 5, -3)));
System.out.println(json(Solution103.solve(new int[]{7, 8, 3, 5, 6, 5, 3, 5})));
System.out.println(json(Solution104.solve("C", 3)));
}
static void batch8900() {
System.out.println(json(Solution105.solve("c")));
System.out.println(json(Solution113.solve(new int[]{6, 7, -2, 4, -5, -4, -2, 4})));
System.out.println(json(Solution112.solve("c")));
System.out.println(json(Solution115.solve("c", "a")));
System.out.println(json(Solution114.solve(30978)));
System.out.println(json(Solution94.solve(new int[]{-7, -1}, 1)));
System.out.println(json(Solution95.solve(new int[]{-7, -1}, 1)));
System.out.println(json(Solution109.solve(new int[]{-7, -1}, 1)));
System.out.println(json(Solution110.solve(new int[]{-7, -1}, 1)));
System.out.println(json(Solution96.solve(new int[]{-7, -1}, 2)));
System.out.println(json(Solution99.solve(new int[]{-7, -1}, 1, -3)));
System.out.println(json(Solution103.solve(new int[]{8, 2})));
System.out.println(json(Solution104.solve("BBACC", 1)));
System.out.println(json(Solution105.solve("bbacc")));
System.out.println(json(Solution113.solve(new int[]{-7, -1})));
System.out.println(json(Solution112.solve("bbacc")));
System.out.println(json(Solution115.solve("bbacc", "a")));
System.out.println(json(Solution114.solve(85440)));
System.out.println(json(Solution94.solve(new int[]{7, -4}, 1)));
System.out.println(json(Solution95.solve(new int[]{7, -4}, 1)));
System.out.println(json(Solution109.solve(new int[]{7, -4}, 1)));
System.out.println(json(Solution110.solve(new int[]{7, -4}, 1)));
System.out.println(json(Solution96.solve(new int[]{7, -4}, 2)));
System.out.println(json(Solution99.solve(new int[]{-4, 7}, 1, 1)));
System.out.println(json(Solution103.solve(new int[]{8, 5})));
System.out.println(json(Solution104.solve("BCCCCACA", 0)));
System.out.println(json(Solution105.solve("bccccaca")));
System.out.println(json(Solution113.solve(new int[]{7, -4})));
System.out.println(json(Solution112.solve("bccccaca")));
System.out.println(json(Solution115.solve("bccccaca", "a")));
System.out.println(json(Solution114.solve(80239)));
System.out.println(json(Solution94.solve(new int[]{-7, 4, 6, 5, 4, -2, 6, -1, 2}, 8)));
System.out.println(json(Solution95.solve(new int[]{-7, 4, 6, 5, 4, -2, 6, -1, 2}, 8)));
System.out.println(json(Solution109.solve(new int[]{-7, 4, 6, 5, 4, -2, 6, -1, 2}, 1)));
System.out.println(json(Solution110.solve(new int[]{-7, 4, 6, 5, 4, -2, 6, -1, 2}, 8)));
System.out.println(json(Solution96.solve(new int[]{-7, 4, 6, 5, 4, -2, 6, -1, 2}, 4)));
System.out.println(json(Solution99.solve(new int[]{-7, -2, -1, 2, 4, 4, 5, 6, 6}, 8, 2)));
System.out.println(json(Solution103.solve(new int[]{8, 5, 7, 6, 5, 3, 7, 2, 3})));
System.out.println(json(Solution104.solve("CAAA", 0)));
System.out.println(json(Solution105.solve("caaa")));
System.out.println(json(Solution113.solve(new int[]{-7, 4, 6, 5, 4, -2, 6, -1, 2})));
System.out.println(json(Solution112.solve("caaa")));
System.out.println(json(Solution115.solve("caaa", "a")));
System.out.println(json(Solution114.solve(92547)));
System.out.println(json(Solution94.solve(new int[]{-3, -2}, 2)));
System.out.println(json(Solution95.solve(new int[]{-3, -2}, 2)));
System.out.println(json(Solution109.solve(new int[]{-3, -2}, 1)));
System.out.println(json(Solution110.solve(new int[]{-3, -2}, 2)));
System.out.println(json(Solution96.solve(new int[]{-3, -2}, 1)));
System.out.println(json(Solution99.solve(new int[]{-3, -2}, 2, -4)));
System.out.println(json(Solution103.solve(new int[]{4, 3})));
System.out.println(json(Solution104.solve("CB", 1)));
System.out.println(json(Solution105.solve("cb")));
System.out.println(json(Solution113.solve(new int[]{-3, -2})));
System.out.println(json(Solution112.solve("cb")));
System.out.println(json(Solution115.solve("cb", "a")));
System.out.println(json(Solution114.solve(43920)));
System.out.println(json(Solution94.solve(new int[]{5, 4, 6}, 1)));
System.out.println(json(Solution95.solve(new int[]{5, 4, 6}, 1)));
System.out.println(json(Solution109.solve(new int[]{5, 4, 6}, 1)));
System.out.println(json(Solution110.solve(new int[]{5, 4, 6}, 1)));
System.out.println(json(Solution96.solve(new int[]{5, 4, 6}, 2)));
System.out.println(json(Solution99.solve(new int[]{4, 5, 6}, 1, -1)));
System.out.println(json(Solution103.solve(new int[]{6, 5, 7})));
System.out.println(json(Solution104.solve("CABB", 3)));
System.out.println(json(Solution105.solve("cabb")));
System.out.println(json(Solution113.solve(new int[]{5, 4, 6})));
System.out.println(json(Solution112.solve("cabb")));
System.out.println(json(Solution115.solve("cabb", "a")));
System.out.println(json(Solution114.solve(27038)));
System.out.println(json(Solution94.solve(new int[]{0, -6, 2, 0, -1, 0}, 1)));
System.out.println(json(Solution95.solve(new int[]{0, -6, 2, 0, -1, 0}, 1)));
System.out.println(json(Solution109.solve(new int[]{0, -6, 2, 0, -1, 0}, 1)));
System.out.println(json(Solution110.solve(new int[]{0, -6, 2, 0, -1, 0}, 1)));
System.out.println(json(Solution96.solve(new int[]{0, -6, 2, 0, -1, 0}, 2)));
System.out.println(json(Solution99.solve(new int[]{-6, -1, 0, 0, 0, 2}, 1, 7)));
System.out.println(json(Solution103.solve(new int[]{1, 7, 3, 1, 2, 1})));
System.out.println(json(Solution104.solve("ACCCCCCB", 2)));
System.out.println(json(Solution105.solve("accccccb")));
System.out.println(json(Solution113.solve(new int[]{0, -6, 2, 0, -1, 0})));
System.out.println(json(Solution112.solve("accccccb")));
System.out.println(json(Solution115.solve("accccccb", "a")));
System.out.println(json(Solution114.solve(57113)));
System.out.println(json(Solution94.solve(new int[]{-6, 6, 1}, 1)));
System.out.println(json(Solution95.solve(new int[]{-6, 6, 1}, 1)));
System.out.println(json(Solution109.solve(new int[]{-6, 6, 1}, 1)));
System.out.println(json(Solution110.solve(new int[]{-6, 6, 1}, 1)));
System.out.println(json(Solution96.solve(new int[]{-6, 6, 1}, 2)));
System.out.println(json(Solution99.solve(new int[]{-6, 1, 6}, 1, 1)));
System.out.println(json(Solution103.solve(new int[]{7, 7, 2})));
System.out.println(json(Solution104.solve("", 0)));
System.out.println(json(Solution105.solve("")));
System.out.println(json(Solution113.solve(new int[]{-6, 6, 1})));
System.out.println(json(Solution112.solve("")));
System.out.println(json(Solution115.solve("", "a")));
System.out.println(json(Solution114.solve(9019)));
System.out.println(json(Solution94.solve(new int[]{-7, -4, 4, -2, -6, -6, 7, -7, 7}, 9)));
System.out.println(json(Solution95.solve(new int[]{-7, -4, 4, -2, -6, -6, 7, -7, 7}, 9)));
System.out.println(json(Solution109.solve(new int[]{-7, -4, 4, -2, -6, -6, 7, -7, 7}, 1)));
System.out.println(json(Solution110.solve(new int[]{-7, -4, 4, -2, -6, -6, 7, -7, 7}, 9)));
}
static void batch9000() {
System.out.println(json(Solution96.solve(new int[]{-7, -4, 4, -2, -6, -6, 7, -7, 7}, 4)));
System.out.println(json(Solution99.solve(new int[]{-7, -7, -6, -6, -4, -2, 4, 7, 7}, 9, -6)));
System.out.println(json(Solution103.solve(new int[]{8, 5, 5, 3, 7, 7, 8, 8, 8})));
System.out.println(json(Solution104.solve("BBBCCC", 2)));
System.out.println(json(Solution105.solve("bbbccc")));
System.out.println(json(Solution113.solve(new int[]{-7, -4, 4, -2, -6, -6, 7, -7, 7})));
System.out.println(json(Solution112.solve("bbbccc")));
System.out.println(json(Solution115.solve("bbbccc", "a")));
System.out.println(json(Solution114.solve(66681)));
System.out.println(json(Solution94.solve(new int[]{7, 0, 5, 1, 5, 3, 1}, 3)));
System.out.println(json(Solution95.solve(new int[]{7, 0, 5, 1, 5, 3, 1}, 3)));
System.out.println(json(Solution109.solve(new int[]{7, 0, 5, 1, 5, 3, 1}, 1)));
System.out.println(json(Solution110.solve(new int[]{7, 0, 5, 1, 5, 3, 1}, 3)));
System.out.println(json(Solution96.solve(new int[]{7, 0, 5, 1, 5, 3, 1}, 5)));
System.out.println(json(Solution99.solve(new int[]{0, 1, 1, 3, 5, 5, 7}, 3, 1)));
System.out.println(json(Solution103.solve(new int[]{8, 1, 6, 2, 6, 4, 2})));
System.out.println(json(Solution104.solve("CAACCABC", 0)));
System.out.println(json(Solution105.solve("caaccabc")));
System.out.println(json(Solution113.solve(new int[]{7, 0, 5, 1, 5, 3, 1})));
System.out.println(json(Solution112.solve("caaccabc")));
System.out.println(json(Solution115.solve("caaccabc", "a")));
System.out.println(json(Solution114.solve(58727)));
System.out.println(json(Solution94.solve(new int[]{4, 5, -2, 4, 6, 7, 7}, 3)));
System.out.println(json(Solution95.solve(new int[]{4, 5, -2, 4, 6, 7, 7}, 3)));
System.out.println(json(Solution109.solve(new int[]{4, 5, -2, 4, 6, 7, 7}, 1)));
System.out.println(json(Solution110.solve(new int[]{4, 5, -2, 4, 6, 7, 7}, 3)));
System.out.println(json(Solution96.solve(new int[]{4, 5, -2, 4, 6, 7, 7}, 1)));
System.out.println(json(Solution99.solve(new int[]{-2, 4, 4, 5, 6, 7, 7}, 3, -1)));
System.out.println(json(Solution103.solve(new int[]{5, 6, 3, 5, 7, 8, 8})));
System.out.println(json(Solution104.solve("", 2)));
System.out.println(json(Solution105.solve("")));
System.out.println(json(Solution113.solve(new int[]{4, 5, -2, 4, 6, 7, 7})));
System.out.println(json(Solution112.solve("")));
System.out.println(json(Solution115.solve("", "a")));
System.out.println(json(Solution114.solve(92797)));
System.out.println(json(Solution94.solve(new int[]{-3, 6, -4, 2, 5, 2}, 2)));
System.out.println(json(Solution95.solve(new int[]{-3, 6, -4, 2, 5, 2}, 2)));
System.out.println(json(Solution109.solve(new int[]{-3, 6, -4, 2, 5, 2}, 1)));
System.out.println(json(Solution110.solve(new int[]{-3, 6, -4, 2, 5, 2}, 2)));
System.out.println(json(Solution96.solve(new int[]{-3, 6, -4, 2, 5, 2}, 5)));
System.out.println(json(Solution99.solve(new int[]{-4, -3, 2, 2, 5, 6}, 2, -2)));
System.out.println(json(Solution103.solve(new int[]{4, 7, 5, 3, 6, 3})));
System.out.println(json(Solution104.solve("B", 0)));
System.out.println(json(Solution105.solve("b")));
System.out.println(json(Solution113.solve(new int[]{-3, 6, -4, 2, 5, 2})));
System.out.println(json(Solution112.solve("b")));
System.out.println(json(Solution115.solve("b", "a")));
System.out.println(json(Solution114.solve(71491)));
System.out.println(json(Solution94.solve(new int[]{-4, 0}, 2)));
System.out.println(json(Solution95.solve(new int[]{-4, 0}, 2)));
System.out.println(json(Solution109.solve(new int[]{-4, 0}, 1)));
System.out.println(json(Solution110.solve(new int[]{-4, 0}, 2)));
System.out.println(json(Solution96.solve(new int[]{-4, 0}, 1)));
System.out.println(json(Solution99.solve(new int[]{-4, 0}, 2, 0)));
System.out.println(json(Solution103.solve(new int[]{5, 1})));
System.out.println(json(Solution104.solve("BABAABAA", 3)));
System.out.println(json(Solution105.solve("babaabaa")));
System.out.println(json(Solution113.solve(new int[]{-4, 0})));
System.out.println(json(Solution112.solve("babaabaa")));
System.out.println(json(Solution115.solve("babaabaa", "a")));
System.out.println(json(Solution114.solve(30518)));
System.out.println(json(Solution94.solve(new int[]{7, -1, -3, -4}, 1)));
System.out.println(json(Solution95.solve(new int[]{7, -1, -3, -4}, 1)));
System.out.println(json(Solution109.solve(new int[]{7, -1, -3, -4}, 1)));
System.out.println(json(Solution110.solve(new int[]{7, -1, -3, -4}, 1)));
System.out.println(json(Solution96.solve(new int[]{7, -1, -3, -4}, 4)));
System.out.println(json(Solution99.solve(new int[]{-4, -3, -1, 7}, 1, 4)));
System.out.println(json(Solution103.solve(new int[]{8, 2, 4, 5})));
System.out.println(json(Solution104.solve("BC", 2)));
System.out.println(json(Solution105.solve("bc")));
System.out.println(json(Solution113.solve(new int[]{7, -1, -3, -4})));
System.out.println(json(Solution112.solve("bc")));
System.out.println(json(Solution115.solve("bc", "a")));
System.out.println(json(Solution114.solve(96393)));
System.out.println(json(Solution94.solve(new int[]{3, 4, -2}, 1)));
System.out.println(json(Solution95.solve(new int[]{3, 4, -2}, 1)));
System.out.println(json(Solution109.solve(new int[]{3, 4, -2}, 1)));
System.out.println(json(Solution110.solve(new int[]{3, 4, -2}, 1)));
System.out.println(json(Solution96.solve(new int[]{3, 4, -2}, 2)));
System.out.println(json(Solution99.solve(new int[]{-2, 3, 4}, 1, 5)));
System.out.println(json(Solution103.solve(new int[]{4, 5, 3})));
System.out.println(json(Solution104.solve("BCB", 1)));
System.out.println(json(Solution105.solve("bcb")));
System.out.println(json(Solution113.solve(new int[]{3, 4, -2})));
System.out.println(json(Solution112.solve("bcb")));
System.out.println(json(Solution115.solve("bcb", "a")));
System.out.println(json(Solution114.solve(58096)));
System.out.println(json(Solution94.solve(new int[]{-4, 7, -5}, 2)));
System.out.println(json(Solution95.solve(new int[]{-4, 7, -5}, 2)));
System.out.println(json(Solution109.solve(new int[]{-4, 7, -5}, 1)));
System.out.println(json(Solution110.solve(new int[]{-4, 7, -5}, 2)));
System.out.println(json(Solution96.solve(new int[]{-4, 7, -5}, 1)));
System.out.println(json(Solution99.solve(new int[]{-5, -4, 7}, 2, 5)));
System.out.println(json(Solution103.solve(new int[]{5, 8, 6})));
System.out.println(json(Solution104.solve("CABACCA", 0)));
System.out.println(json(Solution105.solve("cabacca")));
System.out.println(json(Solution113.solve(new int[]{-4, 7, -5})));
System.out.println(json(Solution112.solve("cabacca")));
System.out.println(json(Solution115.solve("cabacca", "a")));
System.out.println(json(Solution114.solve(44415)));
}
static void batch9100() {
System.out.println(json(Solution94.solve(new int[]{-7, 4, 0, 4, -5, -5, -5}, 2)));
System.out.println(json(Solution95.solve(new int[]{-7, 4, 0, 4, -5, -5, -5}, 2)));
System.out.println(json(Solution109.solve(new int[]{-7, 4, 0, 4, -5, -5, -5}, 1)));
System.out.println(json(Solution110.solve(new int[]{-7, 4, 0, 4, -5, -5, -5}, 2)));
System.out.println(json(Solution96.solve(new int[]{-7, 4, 0, 4, -5, -5, -5}, 2)));
System.out.println(json(Solution99.solve(new int[]{-7, -5, -5, -5, 0, 4, 4}, 2, -4)));
System.out.println(json(Solution103.solve(new int[]{8, 5, 1, 5, 6, 6, 6})));
System.out.println(json(Solution104.solve("", 2)));
System.out.println(json(Solution105.solve("")));
System.out.println(json(Solution113.solve(new int[]{-7, 4, 0, 4, -5, -5, -5})));
System.out.println(json(Solution112.solve("")));
System.out.println(json(Solution115.solve("", "a")));
System.out.println(json(Solution114.solve(56757)));
System.out.println(json(Solution94.solve(new int[]{3}, 1)));
System.out.println(json(Solution95.solve(new int[]{3}, 1)));
System.out.println(json(Solution109.solve(new int[]{3}, 1)));
System.out.println(json(Solution110.solve(new int[]{3}, 1)));
System.out.println(json(Solution96.solve(new int[]{3}, 1)));
System.out.println(json(Solution99.solve(new int[]{3}, 1, -2)));
System.out.println(json(Solution103.solve(new int[]{4})));
System.out.println(json(Solution104.solve("ABBBC", 1)));
System.out.println(json(Solution105.solve("abbbc")));
System.out.println(json(Solution113.solve(new int[]{3})));
System.out.println(json(Solution112.solve("abbbc")));
System.out.println(json(Solution115.solve("abbbc", "a")));
System.out.println(json(Solution114.solve(71628)));
System.out.println(json(Solution94.solve(new int[]{5, -4, -3, 6, -7, -4, -7, -6, -5}, 9)));
System.out.println(json(Solution95.solve(new int[]{5, -4, -3, 6, -7, -4, -7, -6, -5}, 9)));
System.out.println(json(Solution109.solve(new int[]{5, -4, -3, 6, -7, -4, -7, -6, -5}, 1)));
System.out.println(json(Solution110.solve(new int[]{5, -4, -3, 6, -7, -4, -7, -6, -5}, 9)));
System.out.println(json(Solution96.solve(new int[]{5, -4, -3, 6, -7, -4, -7, -6, -5}, 6)));
System.out.println(json(Solution99.solve(new int[]{-7, -7, -6, -5, -4, -4, -3, 5, 6}, 9, -5)));
System.out.println(json(Solution103.solve(new int[]{6, 5, 4, 7, 8, 5, 8, 7, 6})));
System.out.println(json(Solution104.solve("ACAC", 1)));
System.out.println(json(Solution105.solve("acac")));
System.out.println(json(Solution113.solve(new int[]{5, -4, -3, 6, -7, -4, -7, -6, -5})));
System.out.println(json(Solution112.solve("acac")));
System.out.println(json(Solution115.solve("acac", "a")));
System.out.println(json(Solution114.solve(70256)));
System.out.println(json(Solution94.solve(new int[]{-1}, 1)));
System.out.println(json(Solution95.solve(new int[]{-1}, 1)));
System.out.println(json(Solution109.solve(new int[]{-1}, 1)));
System.out.println(json(Solution110.solve(new int[]{-1}, 1)));
System.out.println(json(Solution96.solve(new int[]{-1}, 1)));
System.out.println(json(Solution99.solve(new int[]{-1}, 1, -1)));
System.out.println(json(Solution103.solve(new int[]{2})));
System.out.println(json(Solution104.solve("CBACAA", 3)));
System.out.println(json(Solution105.solve("cbacaa")));
System.out.println(json(Solution113.solve(new int[]{-1})));
System.out.println(json(Solution112.solve("cbacaa")));
System.out.println(json(Solution115.solve("cbacaa", "a")));
System.out.println(json(Solution114.solve(88894)));
System.out.println(json(Solution94.solve(new int[]{-4, 7, -7, -1, 5, -1, -5, 4, 1}, 2)));
System.out.println(json(Solution95.solve(new int[]{-4, 7, -7, -1, 5, -1, -5, 4, 1}, 2)));
System.out.println(json(Solution109.solve(new int[]{-4, 7, -7, -1, 5, -1, -5, 4, 1}, 1)));
System.out.println(json(Solution110.solve(new int[]{-4, 7, -7, -1, 5, -1, -5, 4, 1}, 2)));
System.out.println(json(Solution96.solve(new int[]{-4, 7, -7, -1, 5, -1, -5, 4, 1}, 3)));
System.out.println(json(Solution99.solve(new int[]{-7, -5, -4, -1, -1, 1, 4, 5, 7}, 2, 4)));
System.out.println(json(Solution103.solve(new int[]{5, 8, 8, 2, 6, 2, 6, 5, 2})));
System.out.println(json(Solution104.solve("B", 2)));
System.out.println(json(Solution105.solve("b")));
System.out.println(json(Solution113.solve(new int[]{-4, 7, -7, -1, 5, -1, -5, 4, 1})));
System.out.println(json(Solution112.solve("b")));
System.out.println(json(Solution115.solve("b", "a")));
System.out.println(json(Solution114.solve(36917)));
System.out.println(json(Solution94.solve(new int[]{-5, 2, 7, 2, 5, -7}, 2)));
System.out.println(json(Solution95.solve(new int[]{-5, 2, 7, 2, 5, -7}, 2)));
System.out.println(json(Solution109.solve(new int[]{-5, 2, 7, 2, 5, -7}, 1)));
System.out.println(json(Solution110.solve(new int[]{-5, 2, 7, 2, 5, -7}, 2)));
System.out.println(json(Solution96.solve(new int[]{-5, 2, 7, 2, 5, -7}, 1)));
System.out.println(json(Solution99.solve(new int[]{-7, -5, 2, 2, 5, 7}, 2, -1)));
System.out.println(json(Solution103.solve(new int[]{6, 3, 8, 3, 6, 8})));
System.out.println(json(Solution104.solve("", 1)));
System.out.println(json(Solution105.solve("")));
System.out.println(json(Solution113.solve(new int[]{-5, 2, 7, 2, 5, -7})));
System.out.println(json(Solution112.solve("")));
System.out.println(json(Solution115.solve("", "a")));
System.out.println(json(Solution114.solve(68812)));
System.out.println(json(Solution94.solve(new int[]{-1, 6}, 1)));
System.out.println(json(Solution95.solve(new int[]{-1, 6}, 1)));
System.out.println(json(Solution109.solve(new int[]{-1, 6}, 1)));
System.out.println(json(Solution110.solve(new int[]{-1, 6}, 1)));
System.out.println(json(Solution96.solve(new int[]{-1, 6}, 2)));
System.out.println(json(Solution99.solve(new int[]{-1, 6}, 1, -3)));
System.out.println(json(Solution103.solve(new int[]{2, 7})));
System.out.println(json(Solution104.solve("CACBBB", 2)));
System.out.println(json(Solution105.solve("cacbbb")));
System.out.println(json(Solution113.solve(new int[]{-1, 6})));
System.out.println(json(Solution112.solve("cacbbb")));
System.out.println(json(Solution115.solve("cacbbb", "a")));
System.out.println(json(Solution114.solve(82577)));
System.out.println(json(Solution94.solve(new int[]{5, 2, 4, 0, 1}, 5)));
System.out.println(json(Solution95.solve(new int[]{5, 2, 4, 0, 1}, 5)));
System.out.println(json(Solution109.solve(new int[]{5, 2, 4, 0, 1}, 1)));
System.out.println(json(Solution110.solve(new int[]{5, 2, 4, 0, 1}, 5)));
System.out.println(json(Solution96.solve(new int[]{5, 2, 4, 0, 1}, 5)));
System.out.println(json(Solution99.solve(new int[]{0, 1, 2, 4, 5}, 5, -5)));
System.out.println(json(Solution103.solve(new int[]{6, 3, 5, 1, 2})));
System.out.println(json(Solution104.solve("CBC", 3)));
System.out.println(json(Solution105.solve("cbc")));
}
static void batch9200() {
System.out.println(json(Solution113.solve(new int[]{5, 2, 4, 0, 1})));
System.out.println(json(Solution112.solve("cbc")));
System.out.println(json(Solution115.solve("cbc", "a")));
System.out.println(json(Solution114.solve(76618)));
System.out.println(json(Solution94.solve(new int[]{-6, 0}, 1)));
System.out.println(json(Solution95.solve(new int[]{-6, 0}, 1)));
System.out.println(json(Solution109.solve(new int[]{-6, 0}, 1)));
System.out.println(json(Solution110.solve(new int[]{-6, 0}, 1)));
System.out.println(json(Solution96.solve(new int[]{-6, 0}, 2)));
System.out.println(json(Solution99.solve(new int[]{-6, 0}, 1, 0)));
System.out.println(json(Solution103.solve(new int[]{7, 1})));
System.out.println(json(Solution104.solve("ABA", 3)));
System.out.println(json(Solution105.solve("aba")));
System.out.println(json(Solution113.solve(new int[]{-6, 0})));
System.out.println(json(Solution112.solve("aba")));
System.out.println(json(Solution115.solve("aba", "a")));
System.out.println(json(Solution114.solve(4870)));
System.out.println(json(Solution94.solve(new int[]{-6, 2, 7, 3, 0, -1, -3}, 1)));
System.out.println(json(Solution95.solve(new int[]{-6, 2, 7, 3, 0, -1, -3}, 1)));
System.out.println(json(Solution109.solve(new int[]{-6, 2, 7, 3, 0, -1, -3}, 1)));
System.out.println(json(Solution110.solve(new int[]{-6, 2, 7, 3, 0, -1, -3}, 1)));
System.out.println(json(Solution96.solve(new int[]{-6, 2, 7, 3, 0, -1, -3}, 4)));
System.out.println(json(Solution99.solve(new int[]{-6, -3, -1, 0, 2, 3, 7}, 1, -2)));
System.out.println(json(Solution103.solve(new int[]{7, 3, 8, 4, 1, 2, 4})));
System.out.println(json(Solution104.solve("BBAAACAB", 1)));
System.out.println(json(Solution105.solve("bbaaacab")));
System.out.println(json(Solution113.solve(new int[]{-6, 2, 7, 3, 0, -1, -3})));
System.out.println(json(Solution112.solve("bbaaacab")));
System.out.println(json(Solution115.solve("bbaaacab", "a")));
System.out.println(json(Solution114.solve(46284)));
System.out.println(json(Solution94.solve(new int[]{-2}, 1)));
System.out.println(json(Solution95.solve(new int[]{-2}, 1)));
System.out.println(json(Solution109.solve(new int[]{-2}, 1)));
System.out.println(json(Solution110.solve(new int[]{-2}, 1)));
System.out.println(json(Solution96.solve(new int[]{-2}, 1)));
System.out.println(json(Solution99.solve(new int[]{-2}, 1, 3)));
System.out.println(json(Solution103.solve(new int[]{3})));
System.out.println(json(Solution104.solve("AAA", 2)));
System.out.println(json(Solution105.solve("aaa")));
System.out.println(json(Solution113.solve(new int[]{-2})));
System.out.println(json(Solution112.solve("aaa")));
System.out.println(json(Solution115.solve("aaa", "a")));
System.out.println(json(Solution114.solve(54765)));
System.out.println(json(Solution94.solve(new int[]{7, -5, 7, 3, -4}, 5)));
System.out.println(json(Solution95.solve(new int[]{7, -5, 7, 3, -4}, 5)));
System.out.println(json(Solution109.solve(new int[]{7, -5, 7, 3, -4}, 1)));
System.out.println(json(Solution110.solve(new int[]{7, -5, 7, 3, -4}, 5)));
System.out.println(json(Solution96.solve(new int[]{7, -5, 7, 3, -4}, 2)));
System.out.println(json(Solution99.solve(new int[]{-5, -4, 3, 7, 7}, 5, -1)));
System.out.println(json(Solution103.solve(new int[]{8, 6, 8, 4, 5})));
System.out.println(json(Solution104.solve("BCB", 3)));
System.out.println(json(Solution105.solve("bcb")));
System.out.println(json(Solution113.solve(new int[]{7, -5, 7, 3, -4})));
System.out.println(json(Solution112.solve("bcb")));
System.out.println(json(Solution115.solve("bcb", "a")));
System.out.println(json(Solution114.solve(80630)));
System.out.println(json(Solution94.solve(new int[]{-3, 0, 4, 5, 1, -5, -6}, 6)));
System.out.println(json(Solution95.solve(new int[]{-3, 0, 4, 5, 1, -5, -6}, 6)));
System.out.println(json(Solution109.solve(new int[]{-3, 0, 4, 5, 1, -5, -6}, 1)));
System.out.println(json(Solution110.solve(new int[]{-3, 0, 4, 5, 1, -5, -6}, 6)));
System.out.println(json(Solution96.solve(new int[]{-3, 0, 4, 5, 1, -5, -6}, 1)));
System.out.println(json(Solution99.solve(new int[]{-6, -5, -3, 0, 1, 4, 5}, 6, -3)));
System.out.println(json(Solution103.solve(new int[]{4, 1, 5, 6, 2, 6, 7})));
System.out.println(json(Solution104.solve("BCBAABC", 2)));
System.out.println(json(Solution105.solve("bcbaabc")));
System.out.println(json(Solution113.solve(new int[]{-3, 0, 4, 5, 1, -5, -6})));
System.out.println(json(Solution112.solve("bcbaabc")));
System.out.println(json(Solution115.solve("bcbaabc", "a")));
System.out.println(json(Solution114.solve(63793)));
System.out.println(json(Solution94.solve(new int[]{4}, 1)));
System.out.println(json(Solution95.solve(new int[]{4}, 1)));
System.out.println(json(Solution109.solve(new int[]{4}, 1)));
System.out.println(json(Solution110.solve(new int[]{4}, 1)));
System.out.println(json(Solution96.solve(new int[]{4}, 1)));
System.out.println(json(Solution99.solve(new int[]{4}, 1, 7)));
System.out.println(json(Solution103.solve(new int[]{5})));
System.out.println(json(Solution104.solve("C", 1)));
System.out.println(json(Solution105.solve("c")));
System.out.println(json(Solution113.solve(new int[]{4})));
System.out.println(json(Solution112.solve("c")));
System.out.println(json(Solution115.solve("c", "a")));
System.out.println(json(Solution114.solve(26980)));
System.out.println(json(Solution94.solve(new int[]{-3, 3, -5, -5, 3, 0, 7, -5, 2}, 5)));
System.out.println(json(Solution95.solve(new int[]{-3, 3, -5, -5, 3, 0, 7, -5, 2}, 5)));
System.out.println(json(Solution109.solve(new int[]{-3, 3, -5, -5, 3, 0, 7, -5, 2}, 1)));
System.out.println(json(Solution110.solve(new int[]{-3, 3, -5, -5, 3, 0, 7, -5, 2}, 5)));
System.out.println(json(Solution96.solve(new int[]{-3, 3, -5, -5, 3, 0, 7, -5, 2}, 5)));
System.out.println(json(Solution99.solve(new int[]{-5, -5, -5, -3, 0, 2, 3, 3, 7}, 5, 3)));
System.out.println(json(Solution103.solve(new int[]{4, 4, 6, 6, 4, 1, 8, 6, 3})));
System.out.println(json(Solution104.solve("CBB", 2)));
System.out.println(json(Solution105.solve("cbb")));
System.out.println(json(Solution113.solve(new int[]{-3, 3, -5, -5, 3, 0, 7, -5, 2})));
System.out.println(json(Solution112.solve("cbb")));
System.out.println(json(Solution115.solve("cbb", "a")));
System.out.println(json(Solution114.solve(92429)));
System.out.println(json(Solution94.solve(new int[]{7, -7, -2, -7, 0}, 1)));
System.out.println(json(Solution95.solve(new int[]{7, -7, -2, -7, 0}, 1)));
System.out.println(json(Solution109.solve(new int[]{7, -7, -2, -7, 0}, 1)));
System.out.println(json(Solution110.solve(new int[]{7, -7, -2, -7, 0}, 1)));
System.out.println(json(Solution96.solve(new int[]{7, -7, -2, -7, 0}, 2)));
}
static void batch9300() {
System.out.println(json(Solution99.solve(new int[]{-7, -7, -2, 0, 7}, 1, -4)));
System.out.println(json(Solution103.solve(new int[]{8, 8, 3, 8, 1})));
System.out.println(json(Solution104.solve("ABCCBBAA", 2)));
System.out.println(json(Solution105.solve("abccbbaa")));
System.out.println(json(Solution113.solve(new int[]{7, -7, -2, -7, 0})));
System.out.println(json(Solution112.solve("abccbbaa")));
System.out.println(json(Solution115.solve("abccbbaa", "a")));
System.out.println(json(Solution114.solve(32865)));
System.out.println(json(Solution94.solve(new int[]{-6}, 1)));
System.out.println(json(Solution95.solve(new int[]{-6}, 1)));
System.out.println(json(Solution109.solve(new int[]{-6}, 1)));
System.out.println(json(Solution110.solve(new int[]{-6}, 1)));
System.out.println(json(Solution96.solve(new int[]{-6}, 1)));
System.out.println(json(Solution99.solve(new int[]{-6}, 1, -6)));
System.out.println(json(Solution103.solve(new int[]{7})));
System.out.println(json(Solution104.solve("BABC", 2)));
System.out.println(json(Solution105.solve("babc")));
System.out.println(json(Solution113.solve(new int[]{-6})));
System.out.println(json(Solution112.solve("babc")));
System.out.println(json(Solution115.solve("babc", "a")));
System.out.println(json(Solution114.solve(43117)));
System.out.println(json(Solution94.solve(new int[]{-4, -6, -3, -4}, 4)));
System.out.println(json(Solution95.solve(new int[]{-4, -6, -3, -4}, 4)));
System.out.println(json(Solution109.solve(new int[]{-4, -6, -3, -4}, 1)));
System.out.println(json(Solution110.solve(new int[]{-4, -6, -3, -4}, 4)));
System.out.println(json(Solution96.solve(new int[]{-4, -6, -3, -4}, 2)));
System.out.println(json(Solution99.solve(new int[]{-6, -4, -4, -3}, 4, -4)));
System.out.println(json(Solution103.solve(new int[]{5, 7, 4, 5})));
System.out.println(json(Solution104.solve("", 3)));
System.out.println(json(Solution105.solve("")));
System.out.println(json(Solution113.solve(new int[]{-4, -6, -3, -4})));
System.out.println(json(Solution112.solve("")));
System.out.println(json(Solution115.solve("", "a")));
System.out.println(json(Solution114.solve(67554)));
System.out.println(json(Solution94.solve(new int[]{2, -7, 4, 3, 7, -5}, 5)));
System.out.println(json(Solution95.solve(new int[]{2, -7, 4, 3, 7, -5}, 5)));
System.out.println(json(Solution109.solve(new int[]{2, -7, 4, 3, 7, -5}, 1)));
System.out.println(json(Solution110.solve(new int[]{2, -7, 4, 3, 7, -5}, 5)));
System.out.println(json(Solution96.solve(new int[]{2, -7, 4, 3, 7, -5}, 6)));
System.out.println(json(Solution99.solve(new int[]{-7, -5, 2, 3, 4, 7}, 5, -4)));
System.out.println(json(Solution103.solve(new int[]{3, 8, 5, 4, 8, 6})));
System.out.println(json(Solution104.solve("CACABAA", 3)));
System.out.println(json(Solution105.solve("cacabaa")));
System.out.println(json(Solution113.solve(new int[]{2, -7, 4, 3, 7, -5})));
System.out.println(json(Solution112.solve("cacabaa")));
System.out.println(json(Solution115.solve("cacabaa", "a")));
System.out.println(json(Solution114.solve(73830)));
System.out.println(json(Solution94.solve(new int[]{4, 1, 5, -5}, 1)));
System.out.println(json(Solution95.solve(new int[]{4, 1, 5, -5}, 1)));
System.out.println(json(Solution109.solve(new int[]{4, 1, 5, -5}, 1)));
System.out.println(json(Solution110.solve(new int[]{4, 1, 5, -5}, 1)));
System.out.println(json(Solution96.solve(new int[]{4, 1, 5, -5}, 4)));
System.out.println(json(Solution99.solve(new int[]{-5, 1, 4, 5}, 1, -6)));
System.out.println(json(Solution103.solve(new int[]{5, 2, 6, 6})));
System.out.println(json(Solution104.solve("CAB", 1)));
System.out.println(json(Solution105.solve("cab")));
System.out.println(json(Solution113.solve(new int[]{4, 1, 5, -5})));
System.out.println(json(Solution112.solve("cab")));
System.out.println(json(Solution115.solve("cab", "a")));
System.out.println(json(Solution114.solve(37380)));
System.out.println(json(Solution94.solve(new int[]{-2, -6, -7, -6, -6}, 4)));
System.out.println(json(Solution95.solve(new int[]{-2, -6, -7, -6, -6}, 4)));
System.out.println(json(Solution109.solve(new int[]{-2, -6, -7, -6, -6}, 1)));
System.out.println(json(Solution110.solve(new int[]{-2, -6, -7, -6, -6}, 4)));
System.out.println(json(Solution96.solve(new int[]{-2, -6, -7, -6, -6}, 2)));
System.out.println(json(Solution99.solve(new int[]{-7, -6, -6, -6, -2}, 4, 0)));
System.out.println(json(Solution103.solve(new int[]{3, 7, 8, 7, 7})));
System.out.println(json(Solution104.solve("CBCC", 1)));
System.out.println(json(Solution105.solve("cbcc")));
System.out.println(json(Solution113.solve(new int[]{-2, -6, -7, -6, -6})));
System.out.println(json(Solution112.solve("cbcc")));
System.out.println(json(Solution115.solve("cbcc", "a")));
System.out.println(json(Solution114.solve(29524)));
System.out.println(json(Solution94.solve(new int[]{-1, 2, -4}, 1)));
System.out.println(json(Solution95.solve(new int[]{-1, 2, -4}, 1)));
System.out.println(json(Solution109.solve(new int[]{-1, 2, -4}, 1)));
System.out.println(json(Solution110.solve(new int[]{-1, 2, -4}, 1)));
System.out.println(json(Solution96.solve(new int[]{-1, 2, -4}, 3)));
System.out.println(json(Solution99.solve(new int[]{-4, -1, 2}, 1, -6)));
System.out.println(json(Solution103.solve(new int[]{2, 3, 5})));
System.out.println(json(Solution104.solve("B", 2)));
System.out.println(json(Solution105.solve("b")));
System.out.println(json(Solution113.solve(new int[]{-1, 2, -4})));
System.out.println(json(Solution112.solve("b")));
System.out.println(json(Solution115.solve("b", "a")));
System.out.println(json(Solution114.solve(93717)));
System.out.println(json(Solution94.solve(new int[]{7, 2, -3, 5, -4, -2, 7, 6}, 8)));
System.out.println(json(Solution95.solve(new int[]{7, 2, -3, 5, -4, -2, 7, 6}, 8)));
System.out.println(json(Solution109.solve(new int[]{7, 2, -3, 5, -4, -2, 7, 6}, 1)));
System.out.println(json(Solution110.solve(new int[]{7, 2, -3, 5, -4, -2, 7, 6}, 8)));
System.out.println(json(Solution96.solve(new int[]{7, 2, -3, 5, -4, -2, 7, 6}, 4)));
System.out.println(json(Solution99.solve(new int[]{-4, -3, -2, 2, 5, 6, 7, 7}, 8, 4)));
System.out.println(json(Solution103.solve(new int[]{8, 3, 4, 6, 5, 3, 8, 7})));
System.out.println(json(Solution104.solve("CABA", 3)));
System.out.println(json(Solution105.solve("caba")));
System.out.println(json(Solution113.solve(new int[]{7, 2, -3, 5, -4, -2, 7, 6})));
System.out.println(json(Solution112.solve("caba")));
System.out.println(json(Solution115.solve("caba", "a")));
System.out.println(json(Solution114.solve(81714)));
System.out.println(json(Solution94.solve(new int[]{3, 5, -5, -7, 3, 0}, 3)));
}
static void batch9400() {
System.out.println(json(Solution95.solve(new int[]{3, 5, -5, -7, 3, 0}, 3)));
System.out.println(json(Solution109.solve(new int[]{3, 5, -5, -7, 3, 0}, 1)));
System.out.println(json(Solution110.solve(new int[]{3, 5, -5, -7, 3, 0}, 3)));
System.out.println(json(Solution96.solve(new int[]{3, 5, -5, -7, 3, 0}, 3)));
System.out.println(json(Solution99.solve(new int[]{-7, -5, 0, 3, 3, 5}, 3, 6)));
System.out.println(json(Solution103.solve(new int[]{4, 6, 6, 8, 4, 1})));
System.out.println(json(Solution104.solve("", 2)));
System.out.println(json(Solution105.solve("")));
System.out.println(json(Solution113.solve(new int[]{3, 5, -5, -7, 3, 0})));
System.out.println(json(Solution112.solve("")));
System.out.println(json(Solution115.solve("", "a")));
System.out.println(json(Solution114.solve(56565)));
System.out.println(json(Solution94.solve(new int[]{-4, 2, -1, 0, -7, -5}, 4)));
System.out.println(json(Solution95.solve(new int[]{-4, 2, -1, 0, -7, -5}, 4)));
System.out.println(json(Solution109.solve(new int[]{-4, 2, -1, 0, -7, -5}, 1)));
System.out.println(json(Solution110.solve(new int[]{-4, 2, -1, 0, -7, -5}, 4)));
System.out.println(json(Solution96.solve(new int[]{-4, 2, -1, 0, -7, -5}, 5)));
System.out.println(json(Solution99.solve(new int[]{-7, -5, -4, -1, 0, 2}, 4, 0)));
System.out.println(json(Solution103.solve(new int[]{5, 3, 2, 1, 8, 6})));
System.out.println(json(Solution104.solve("", 1)));
System.out.println(json(Solution105.solve("")));
System.out.println(json(Solution113.solve(new int[]{-4, 2, -1, 0, -7, -5})));
System.out.println(json(Solution112.solve("")));
System.out.println(json(Solution115.solve("", "a")));
System.out.println(json(Solution114.solve(5036)));
System.out.println(json(Solution94.solve(new int[]{-3, 7, 0}, 1)));
System.out.println(json(Solution95.solve(new int[]{-3, 7, 0}, 1)));
System.out.println(json(Solution109.solve(new int[]{-3, 7, 0}, 1)));
System.out.println(json(Solution110.solve(new int[]{-3, 7, 0}, 1)));
System.out.println(json(Solution96.solve(new int[]{-3, 7, 0}, 2)));
System.out.println(json(Solution99.solve(new int[]{-3, 0, 7}, 1, -6)));
System.out.println(json(Solution103.solve(new int[]{4, 8, 1})));
System.out.println(json(Solution104.solve("BCBAAABC", 3)));
System.out.println(json(Solution105.solve("bcbaaabc")));
System.out.println(json(Solution113.solve(new int[]{-3, 7, 0})));
System.out.println(json(Solution112.solve("bcbaaabc")));
System.out.println(json(Solution115.solve("bcbaaabc", "a")));
System.out.println(json(Solution114.solve(7694)));
System.out.println(json(Solution94.solve(new int[]{-1, -7, 7}, 2)));
System.out.println(json(Solution95.solve(new int[]{-1, -7, 7}, 2)));
System.out.println(json(Solution109.solve(new int[]{-1, -7, 7}, 1)));
System.out.println(json(Solution110.solve(new int[]{-1, -7, 7}, 2)));
System.out.println(json(Solution96.solve(new int[]{-1, -7, 7}, 3)));
System.out.println(json(Solution99.solve(new int[]{-7, -1, 7}, 2, 4)));
System.out.println(json(Solution103.solve(new int[]{2, 8, 8})));
System.out.println(json(Solution104.solve("ABB", 2)));
System.out.println(json(Solution105.solve("abb")));
System.out.println(json(Solution113.solve(new int[]{-1, -7, 7})));
System.out.println(json(Solution112.solve("abb")));
System.out.println(json(Solution115.solve("abb", "a")));
System.out.println(json(Solution114.solve(55841)));
System.out.println(json(Solution94.solve(new int[]{-6, -4, 1, 1, 2, -5}, 6)));
System.out.println(json(Solution95.solve(new int[]{-6, -4, 1, 1, 2, -5}, 6)));
System.out.println(json(Solution109.solve(new int[]{-6, -4, 1, 1, 2, -5}, 1)));
System.out.println(json(Solution110.solve(new int[]{-6, -4, 1, 1, 2, -5}, 6)));
System.out.println(json(Solution96.solve(new int[]{-6, -4, 1, 1, 2, -5}, 1)));
System.out.println(json(Solution99.solve(new int[]{-6, -5, -4, 1, 1, 2}, 6, -1)));
System.out.println(json(Solution103.solve(new int[]{7, 5, 2, 2, 3, 6})));
System.out.println(json(Solution104.solve("CB", 3)));
System.out.println(json(Solution105.solve("cb")));
System.out.println(json(Solution113.solve(new int[]{-6, -4, 1, 1, 2, -5})));
System.out.println(json(Solution112.solve("cb")));
System.out.println(json(Solution115.solve("cb", "a")));
System.out.println(json(Solution114.solve(67290)));
System.out.println(json(Solution94.solve(new int[]{7, -7, 7, 5, 4, -5}, 5)));
System.out.println(json(Solution95.solve(new int[]{7, -7, 7, 5, 4, -5}, 5)));
System.out.println(json(Solution109.solve(new int[]{7, -7, 7, 5, 4, -5}, 1)));
System.out.println(json(Solution110.solve(new int[]{7, -7, 7, 5, 4, -5}, 5)));
System.out.println(json(Solution96.solve(new int[]{7, -7, 7, 5, 4, -5}, 5)));
System.out.println(json(Solution99.solve(new int[]{-7, -5, 4, 5, 7, 7}, 5, -5)));
System.out.println(json(Solution103.solve(new int[]{8, 8, 8, 6, 5, 6})));
System.out.println(json(Solution104.solve("CC", 0)));
System.out.println(json(Solution105.solve("cc")));
System.out.println(json(Solution113.solve(new int[]{7, -7, 7, 5, 4, -5})));
System.out.println(json(Solution112.solve("cc")));
System.out.println(json(Solution115.solve("cc", "a")));
System.out.println(json(Solution114.solve(44663)));
System.out.println(json(Solution94.solve(new int[]{7, -3, 1, 3, 2, 4, 3}, 6)));
System.out.println(json(Solution95.solve(new int[]{7, -3, 1, 3, 2, 4, 3}, 6)));
System.out.println(json(Solution109.solve(new int[]{7, -3, 1, 3, 2, 4, 3}, 1)));
System.out.println(json(Solution110.solve(new int[]{7, -3, 1, 3, 2, 4, 3}, 6)));
System.out.println(json(Solution96.solve(new int[]{7, -3, 1, 3, 2, 4, 3}, 4)));
System.out.println(json(Solution99.solve(new int[]{-3, 1, 2, 3, 3, 4, 7}, 6, 0)));
System.out.println(json(Solution103.solve(new int[]{8, 4, 2, 4, 3, 5, 4})));
System.out.println(json(Solution104.solve("ABACCBB", 3)));
System.out.println(json(Solution105.solve("abaccbb")));
System.out.println(json(Solution113.solve(new int[]{7, -3, 1, 3, 2, 4, 3})));
System.out.println(json(Solution112.solve("abaccbb")));
System.out.println(json(Solution115.solve("abaccbb", "a")));
System.out.println(json(Solution114.solve(14)));
System.out.println(json(Solution94.solve(new int[]{3, 1, -5, -7, 6, 0, 2}, 6)));
System.out.println(json(Solution95.solve(new int[]{3, 1, -5, -7, 6, 0, 2}, 6)));
System.out.println(json(Solution109.solve(new int[]{3, 1, -5, -7, 6, 0, 2}, 1)));
System.out.println(json(Solution110.solve(new int[]{3, 1, -5, -7, 6, 0, 2}, 6)));
System.out.println(json(Solution96.solve(new int[]{3, 1, -5, -7, 6, 0, 2}, 5)));
System.out.println(json(Solution99.solve(new int[]{-7, -5, 0, 1, 2, 3, 6}, 6, -2)));
System.out.println(json(Solution103.solve(new int[]{4, 2, 6, 8, 7, 1, 3})));
System.out.println(json(Solution104.solve("BBACABAB", 1)));
System.out.println(json(Solution105.solve("bbacabab")));
System.out.println(json(Solution113.solve(new int[]{3, 1, -5, -7, 6, 0, 2})));
}
static void batch9500() {
System.out.println(json(Solution112.solve("bbacabab")));
System.out.println(json(Solution115.solve("bbacabab", "a")));
System.out.println(json(Solution114.solve(63124)));
System.out.println(json(Solution94.solve(new int[]{4, -4, 0, 1, -4, -2, 7, 1}, 7)));
System.out.println(json(Solution95.solve(new int[]{4, -4, 0, 1, -4, -2, 7, 1}, 7)));
System.out.println(json(Solution109.solve(new int[]{4, -4, 0, 1, -4, -2, 7, 1}, 1)));
System.out.println(json(Solution110.solve(new int[]{4, -4, 0, 1, -4, -2, 7, 1}, 7)));
System.out.println(json(Solution96.solve(new int[]{4, -4, 0, 1, -4, -2, 7, 1}, 6)));
System.out.println(json(Solution99.solve(new int[]{-4, -4, -2, 0, 1, 1, 4, 7}, 7, -4)));
System.out.println(json(Solution103.solve(new int[]{5, 5, 1, 2, 5, 3, 8, 2})));
System.out.println(json(Solution104.solve("CABCB", 1)));
System.out.println(json(Solution105.solve("cabcb")));
System.out.println(json(Solution113.solve(new int[]{4, -4, 0, 1, -4, -2, 7, 1})));
System.out.println(json(Solution112.solve("cabcb")));
System.out.println(json(Solution115.solve("cabcb", "a")));
System.out.println(json(Solution114.solve(39512)));
System.out.println(json(Solution94.solve(new int[]{-5, 7, 3, 6, 0, 2, -1}, 1)));
System.out.println(json(Solution95.solve(new int[]{-5, 7, 3, 6, 0, 2, -1}, 1)));
System.out.println(json(Solution109.solve(new int[]{-5, 7, 3, 6, 0, 2, -1}, 1)));
System.out.println(json(Solution110.solve(new int[]{-5, 7, 3, 6, 0, 2, -1}, 1)));
System.out.println(json(Solution96.solve(new int[]{-5, 7, 3, 6, 0, 2, -1}, 3)));
System.out.println(json(Solution99.solve(new int[]{-5, -1, 0, 2, 3, 6, 7}, 1, 4)));
System.out.println(json(Solution103.solve(new int[]{6, 8, 4, 7, 1, 3, 2})));
System.out.println(json(Solution104.solve("CAACCB", 1)));
System.out.println(json(Solution105.solve("caaccb")));
System.out.println(json(Solution113.solve(new int[]{-5, 7, 3, 6, 0, 2, -1})));
System.out.println(json(Solution112.solve("caaccb")));
System.out.println(json(Solution115.solve("caaccb", "a")));
System.out.println(json(Solution114.solve(45180)));
System.out.println(json(Solution94.solve(new int[]{-7, -1}, 1)));
System.out.println(json(Solution95.solve(new int[]{-7, -1}, 1)));
System.out.println(json(Solution109.solve(new int[]{-7, -1}, 1)));
System.out.println(json(Solution110.solve(new int[]{-7, -1}, 1)));
System.out.println(json(Solution96.solve(new int[]{-7, -1}, 2)));
System.out.println(json(Solution99.solve(new int[]{-7, -1}, 1, -3)));
System.out.println(json(Solution103.solve(new int[]{8, 2})));
System.out.println(json(Solution104.solve("CBB", 1)));
System.out.println(json(Solution105.solve("cbb")));
System.out.println(json(Solution113.solve(new int[]{-7, -1})));
System.out.println(json(Solution112.solve("cbb")));
System.out.println(json(Solution115.solve("cbb", "a")));
System.out.println(json(Solution114.solve(1784)));
System.out.println(json(Solution94.solve(new int[]{1, -2, 1}, 1)));
System.out.println(json(Solution95.solve(new int[]{1, -2, 1}, 1)));
System.out.println(json(Solution109.solve(new int[]{1, -2, 1}, 1)));
System.out.println(json(Solution110.solve(new int[]{1, -2, 1}, 1)));
System.out.println(json(Solution96.solve(new int[]{1, -2, 1}, 1)));
System.out.println(json(Solution99.solve(new int[]{-2, 1, 1}, 1, -4)));
System.out.println(json(Solution103.solve(new int[]{2, 3, 2})));
System.out.println(json(Solution104.solve("ACBCBABA", 3)));
System.out.println(json(Solution105.solve("acbcbaba")));
System.out.println(json(Solution113.solve(new int[]{1, -2, 1})));
System.out.println(json(Solution112.solve("acbcbaba")));
System.out.println(json(Solution115.solve("acbcbaba", "a")));
System.out.println(json(Solution114.solve(85082)));
System.out.println(json(Solution94.solve(new int[]{6, -6, 0, -5, -1, 2, 0, 3}, 5)));
System.out.println(json(Solution95.solve(new int[]{6, -6, 0, -5, -1, 2, 0, 3}, 5)));
System.out.println(json(Solution109.solve(new int[]{6, -6, 0, -5, -1, 2, 0, 3}, 1)));
System.out.println(json(Solution110.solve(new int[]{6, -6, 0, -5, -1, 2, 0, 3}, 5)));
System.out.println(json(Solution96.solve(new int[]{6, -6, 0, -5, -1, 2, 0, 3}, 4)));
System.out.println(json(Solution99.solve(new int[]{-6, -5, -1, 0, 0, 2, 3, 6}, 5, -1)));
System.out.println(json(Solution103.solve(new int[]{7, 7, 1, 6, 2, 3, 1, 4})));
System.out.println(json(Solution104.solve("A", 3)));
System.out.println(json(Solution105.solve("a")));
System.out.println(json(Solution113.solve(new int[]{6, -6, 0, -5, -1, 2, 0, 3})));
System.out.println(json(Solution112.solve("a")));
System.out.println(json(Solution115.solve("a", "a")));
System.out.println(json(Solution114.solve(88778)));
System.out.println(json(Solution94.solve(new int[]{0, 7, 2, 4, -6, 7}, 1)));
System.out.println(json(Solution95.solve(new int[]{0, 7, 2, 4, -6, 7}, 1)));
System.out.println(json(Solution109.solve(new int[]{0, 7, 2, 4, -6, 7}, 1)));
System.out.println(json(Solution110.solve(new int[]{0, 7, 2, 4, -6, 7}, 1)));
System.out.println(json(Solution96.solve(new int[]{0, 7, 2, 4, -6, 7}, 3)));
System.out.println(json(Solution99.solve(new int[]{-6, 0, 2, 4, 7, 7}, 1, -7)));
System.out.println(json(Solution103.solve(new int[]{1, 8, 3, 5, 7, 8})));
System.out.println(json(Solution104.solve("CAAA", 2)));
System.out.println(json(Solution105.solve("caaa")));
System.out.println(json(Solution113.solve(new int[]{0, 7, 2, 4, -6, 7})));
System.out.println(json(Solution112.solve("caaa")));
System.out.println(json(Solution115.solve("caaa", "a")));
System.out.println(json(Solution114.solve(93105)));
System.out.println(json(Solution94.solve(new int[]{5, 4, 2, -1, 3, 2, -3}, 2)));
System.out.println(json(Solution95.solve(new int[]{5, 4, 2, -1, 3, 2, -3}, 2)));
System.out.println(json(Solution109.solve(new int[]{5, 4, 2, -1, 3, 2, -3}, 1)));
System.out.println(json(Solution110.solve(new int[]{5, 4, 2, -1, 3, 2, -3}, 2)));
System.out.println(json(Solution96.solve(new int[]{5, 4, 2, -1, 3, 2, -3}, 2)));
System.out.println(json(Solution99.solve(new int[]{-3, -1, 2, 2, 3, 4, 5}, 2, -1)));
System.out.println(json(Solution103.solve(new int[]{6, 5, 3, 2, 4, 3, 4})));
System.out.println(json(Solution104.solve("CBCACACA", 0)));
System.out.println(json(Solution105.solve("cbcacaca")));
System.out.println(json(Solution113.solve(new int[]{5, 4, 2, -1, 3, 2, -3})));
System.out.println(json(Solution112.solve("cbcacaca")));
System.out.println(json(Solution115.solve("cbcacaca", "a")));
System.out.println(json(Solution114.solve(8927)));
System.out.println(json(Solution94.solve(new int[]{0, -5, -7, -3}, 2)));
System.out.println(json(Solution95.solve(new int[]{0, -5, -7, -3}, 2)));
System.out.println(json(Solution109.solve(new int[]{0, -5, -7, -3}, 1)));
System.out.println(json(Solution110.solve(new int[]{0, -5, -7, -3}, 2)));
System.out.println(json(Solution96.solve(new int[]{0, -5, -7, -3}, 1)));
System.out.println(json(Solution99.solve(new int[]{-7, -5, -3, 0}, 2, -6)));
}
static void batch9600() {
System.out.println(json(Solution103.solve(new int[]{1, 6, 8, 4})));
System.out.println(json(Solution104.solve("ABCC", 1)));
System.out.println(json(Solution105.solve("abcc")));
System.out.println(json(Solution113.solve(new int[]{0, -5, -7, -3})));
System.out.println(json(Solution112.solve("abcc")));
System.out.println(json(Solution115.solve("abcc", "a")));
System.out.println(json(Solution114.solve(52592)));
System.out.println(json(Solution94.solve(new int[]{7, 5, -7, 7, 2, -1, -2, 6}, 3)));
System.out.println(json(Solution95.solve(new int[]{7, 5, -7, 7, 2, -1, -2, 6}, 3)));
System.out.println(json(Solution109.solve(new int[]{7, 5, -7, 7, 2, -1, -2, 6}, 1)));
System.out.println(json(Solution110.solve(new int[]{7, 5, -7, 7, 2, -1, -2, 6}, 3)));
System.out.println(json(Solution96.solve(new int[]{7, 5, -7, 7, 2, -1, -2, 6}, 5)));
System.out.println(json(Solution99.solve(new int[]{-7, -2, -1, 2, 5, 6, 7, 7}, 3, -7)));
System.out.println(json(Solution103.solve(new int[]{8, 6, 8, 8, 3, 2, 3, 7})));
System.out.println(json(Solution104.solve("ACBA", 2)));
System.out.println(json(Solution105.solve("acba")));
System.out.println(json(Solution113.solve(new int[]{7, 5, -7, 7, 2, -1, -2, 6})));
System.out.println(json(Solution112.solve("acba")));
System.out.println(json(Solution115.solve("acba", "a")));
System.out.println(json(Solution114.solve(32329)));
System.out.println(json(Solution94.solve(new int[]{4, 7, 3, -7, 3}, 4)));
System.out.println(json(Solution95.solve(new int[]{4, 7, 3, -7, 3}, 4)));
System.out.println(json(Solution109.solve(new int[]{4, 7, 3, -7, 3}, 1)));
System.out.println(json(Solution110.solve(new int[]{4, 7, 3, -7, 3}, 4)));
System.out.println(json(Solution96.solve(new int[]{4, 7, 3, -7, 3}, 2)));
System.out.println(json(Solution99.solve(new int[]{-7, 3, 3, 4, 7}, 4, -1)));
System.out.println(json(Solution103.solve(new int[]{5, 8, 4, 8, 4})));
System.out.println(json(Solution104.solve("CC", 0)));
System.out.println(json(Solution105.solve("cc")));
System.out.println(json(Solution113.solve(new int[]{4, 7, 3, -7, 3})));
System.out.println(json(Solution112.solve("cc")));
System.out.println(json(Solution115.solve("cc", "a")));
System.out.println(json(Solution114.solve(77103)));
System.out.println(json(Solution94.solve(new int[]{-5, -6, -2, 1, 6, -5, 2, 7}, 2)));
System.out.println(json(Solution95.solve(new int[]{-5, -6, -2, 1, 6, -5, 2, 7}, 2)));
System.out.println(json(Solution109.solve(new int[]{-5, -6, -2, 1, 6, -5, 2, 7}, 1)));
System.out.println(json(Solution110.solve(new int[]{-5, -6, -2, 1, 6, -5, 2, 7}, 2)));
System.out.println(json(Solution96.solve(new int[]{-5, -6, -2, 1, 6, -5, 2, 7}, 6)));
System.out.println(json(Solution99.solve(new int[]{-6, -5, -5, -2, 1, 2, 6, 7}, 2, -2)));
System.out.println(json(Solution103.solve(new int[]{6, 7, 3, 2, 7, 6, 3, 8})));
System.out.println(json(Solution104.solve("CB", 3)));
System.out.println(json(Solution105.solve("cb")));
System.out.println(json(Solution113.solve(new int[]{-5, -6, -2, 1, 6, -5, 2, 7})));
System.out.println(json(Solution112.solve("cb")));
System.out.println(json(Solution115.solve("cb", "a")));
System.out.println(json(Solution114.solve(98354)));
System.out.println(json(Solution94.solve(new int[]{6, -3}, 1)));
System.out.println(json(Solution95.solve(new int[]{6, -3}, 1)));
System.out.println(json(Solution109.solve(new int[]{6, -3}, 1)));
System.out.println(json(Solution110.solve(new int[]{6, -3}, 1)));
System.out.println(json(Solution96.solve(new int[]{6, -3}, 2)));
System.out.println(json(Solution99.solve(new int[]{-3, 6}, 1, 6)));
System.out.println(json(Solution103.solve(new int[]{7, 4})));
System.out.println(json(Solution104.solve("CAABA", 1)));
System.out.println(json(Solution105.solve("caaba")));
System.out.println(json(Solution113.solve(new int[]{6, -3})));
System.out.println(json(Solution112.solve("caaba")));
System.out.println(json(Solution115.solve("caaba", "a")));
System.out.println(json(Solution114.solve(81328)));
System.out.println(json(Solution94.solve(new int[]{-4, -7, 7, -2, 7, 1, -4, 1, 2}, 2)));
System.out.println(json(Solution95.solve(new int[]{-4, -7, 7, -2, 7, 1, -4, 1, 2}, 2)));
System.out.println(json(Solution109.solve(new int[]{-4, -7, 7, -2, 7, 1, -4, 1, 2}, 1)));
System.out.println(json(Solution110.solve(new int[]{-4, -7, 7, -2, 7, 1, -4, 1, 2}, 2)));
System.out.println(json(Solution96.solve(new int[]{-4, -7, 7, -2, 7, 1, -4, 1, 2}, 1)));
System.out.println(json(Solution99.solve(new int[]{-7, -4, -4, -2, 1, 1, 2, 7, 7}, 2, -6)));
System.out.println(json(Solution103.solve(new int[]{5, 8, 8, 3, 8, 2, 5, 2, 3})));
System.out.println(json(Solution104.solve("AAB", 2)));
System.out.println(json(Solution105.solve("aab")));
System.out.println(json(Solution113.solve(new int[]{-4, -7, 7, -2, 7, 1, -4, 1, 2})));
System.out.println(json(Solution112.solve("aab")));
System.out.println(json(Solution115.solve("aab", "a")));
System.out.println(json(Solution114.solve(35849)));
System.out.println(json(Solution94.solve(new int[]{6, 2, 3}, 1)));
System.out.println(json(Solution95.solve(new int[]{6, 2, 3}, 1)));
System.out.println(json(Solution109.solve(new int[]{6, 2, 3}, 1)));
System.out.println(json(Solution110.solve(new int[]{6, 2, 3}, 1)));
System.out.println(json(Solution96.solve(new int[]{6, 2, 3}, 3)));
System.out.println(json(Solution99.solve(new int[]{2, 3, 6}, 1, 5)));
System.out.println(json(Solution103.solve(new int[]{7, 3, 4})));
System.out.println(json(Solution104.solve("A", 3)));
System.out.println(json(Solution105.solve("a")));
System.out.println(json(Solution113.solve(new int[]{6, 2, 3})));
System.out.println(json(Solution112.solve("a")));
System.out.println(json(Solution115.solve("a", "a")));
System.out.println(json(Solution114.solve(20654)));
System.out.println(json(Solution94.solve(new int[]{-3, 1, 5}, 2)));
System.out.println(json(Solution95.solve(new int[]{-3, 1, 5}, 2)));
System.out.println(json(Solution109.solve(new int[]{-3, 1, 5}, 1)));
System.out.println(json(Solution110.solve(new int[]{-3, 1, 5}, 2)));
System.out.println(json(Solution96.solve(new int[]{-3, 1, 5}, 3)));
System.out.println(json(Solution99.solve(new int[]{-3, 1, 5}, 2, 2)));
System.out.println(json(Solution103.solve(new int[]{4, 2, 6})));
System.out.println(json(Solution104.solve("BAC", 2)));
System.out.println(json(Solution105.solve("bac")));
System.out.println(json(Solution113.solve(new int[]{-3, 1, 5})));
System.out.println(json(Solution112.solve("bac")));
System.out.println(json(Solution115.solve("bac", "a")));
System.out.println(json(Solution114.solve(32801)));
System.out.println(json(Solution94.solve(new int[]{6}, 1)));
System.out.println(json(Solution95.solve(new int[]{6}, 1)));
}
static void batch9700() {
System.out.println(json(Solution109.solve(new int[]{6}, 1)));
System.out.println(json(Solution110.solve(new int[]{6}, 1)));
System.out.println(json(Solution96.solve(new int[]{6}, 1)));
System.out.println(json(Solution99.solve(new int[]{6}, 1, 5)));
System.out.println(json(Solution103.solve(new int[]{7})));
System.out.println(json(Solution104.solve("C", 1)));
System.out.println(json(Solution105.solve("c")));
System.out.println(json(Solution113.solve(new int[]{6})));
System.out.println(json(Solution112.solve("c")));
System.out.println(json(Solution115.solve("c", "a")));
System.out.println(json(Solution114.solve(54100)));
System.out.println(json(Solution94.solve(new int[]{2, 3, -3}, 3)));
System.out.println(json(Solution95.solve(new int[]{2, 3, -3}, 3)));
System.out.println(json(Solution109.solve(new int[]{2, 3, -3}, 1)));
System.out.println(json(Solution110.solve(new int[]{2, 3, -3}, 3)));
System.out.println(json(Solution96.solve(new int[]{2, 3, -3}, 2)));
System.out.println(json(Solution99.solve(new int[]{-3, 2, 3}, 3, -5)));
System.out.println(json(Solution103.solve(new int[]{3, 4, 4})));
System.out.println(json(Solution104.solve("B", 2)));
System.out.println(json(Solution105.solve("b")));
System.out.println(json(Solution113.solve(new int[]{2, 3, -3})));
System.out.println(json(Solution112.solve("b")));
System.out.println(json(Solution115.solve("b", "a")));
System.out.println(json(Solution114.solve(38037)));
System.out.println(json(Solution94.solve(new int[]{5, 5, 7, 6, -7}, 5)));
System.out.println(json(Solution95.solve(new int[]{5, 5, 7, 6, -7}, 5)));
System.out.println(json(Solution109.solve(new int[]{5, 5, 7, 6, -7}, 1)));
System.out.println(json(Solution110.solve(new int[]{5, 5, 7, 6, -7}, 5)));
System.out.println(json(Solution96.solve(new int[]{5, 5, 7, 6, -7}, 2)));
System.out.println(json(Solution99.solve(new int[]{-7, 5, 5, 6, 7}, 5, -4)));
System.out.println(json(Solution103.solve(new int[]{6, 6, 8, 7, 8})));
System.out.println(json(Solution104.solve("CAABAB", 0)));
System.out.println(json(Solution105.solve("caabab")));
System.out.println(json(Solution113.solve(new int[]{5, 5, 7, 6, -7})));
System.out.println(json(Solution112.solve("caabab")));
System.out.println(json(Solution115.solve("caabab", "a")));
System.out.println(json(Solution114.solve(19615)));
System.out.println(json(Solution94.solve(new int[]{3}, 1)));
System.out.println(json(Solution95.solve(new int[]{3}, 1)));
System.out.println(json(Solution109.solve(new int[]{3}, 1)));
System.out.println(json(Solution110.solve(new int[]{3}, 1)));
System.out.println(json(Solution96.solve(new int[]{3}, 1)));
System.out.println(json(Solution99.solve(new int[]{3}, 1, 6)));
System.out.println(json(Solution103.solve(new int[]{4})));
System.out.println(json(Solution104.solve("CACABCBC", 0)));
System.out.println(json(Solution105.solve("cacabcbc")));
System.out.println(json(Solution113.solve(new int[]{3})));
System.out.println(json(Solution112.solve("cacabcbc")));
System.out.println(json(Solution115.solve("cacabcbc", "a")));
System.out.println(json(Solution114.solve(24495)));
System.out.println(json(Solution94.solve(new int[]{0, -4, 3, 4, 4, 3, 5, 0, -5}, 1)));
System.out.println(json(Solution95.solve(new int[]{0, -4, 3, 4, 4, 3, 5, 0, -5}, 1)));
System.out.println(json(Solution109.solve(new int[]{0, -4, 3, 4, 4, 3, 5, 0, -5}, 1)));
System.out.println(json(Solution110.solve(new int[]{0, -4, 3, 4, 4, 3, 5, 0, -5}, 1)));
System.out.println(json(Solution96.solve(new int[]{0, -4, 3, 4, 4, 3, 5, 0, -5}, 4)));
System.out.println(json(Solution99.solve(new int[]{-5, -4, 0, 0, 3, 3, 4, 4, 5}, 1, -3)));
System.out.println(json(Solution103.solve(new int[]{1, 5, 4, 5, 5, 4, 6, 1, 6})));
System.out.println(json(Solution104.solve("CAA", 1)));
System.out.println(json(Solution105.solve("caa")));
System.out.println(json(Solution113.solve(new int[]{0, -4, 3, 4, 4, 3, 5, 0, -5})));
System.out.println(json(Solution112.solve("caa")));
System.out.println(json(Solution115.solve("caa", "a")));
System.out.println(json(Solution114.solve(61492)));
System.out.println(json(Solution94.solve(new int[]{7, 0, -1, 6}, 3)));
System.out.println(json(Solution95.solve(new int[]{7, 0, -1, 6}, 3)));
System.out.println(json(Solution109.solve(new int[]{7, 0, -1, 6}, 1)));
System.out.println(json(Solution110.solve(new int[]{7, 0, -1, 6}, 3)));
System.out.println(json(Solution96.solve(new int[]{7, 0, -1, 6}, 2)));
System.out.println(json(Solution99.solve(new int[]{-1, 0, 6, 7}, 3, -4)));
System.out.println(json(Solution103.solve(new int[]{8, 1, 2, 7})));
System.out.println(json(Solution104.solve("A", 1)));
System.out.println(json(Solution105.solve("a")));
System.out.println(json(Solution113.solve(new int[]{7, 0, -1, 6})));
System.out.println(json(Solution112.solve("a")));
System.out.println(json(Solution115.solve("a", "a")));
System.out.println(json(Solution114.solve(49296)));
System.out.println(json(Solution94.solve(new int[]{1, -2, 5}, 2)));
System.out.println(json(Solution95.solve(new int[]{1, -2, 5}, 2)));
System.out.println(json(Solution109.solve(new int[]{1, -2, 5}, 1)));
System.out.println(json(Solution110.solve(new int[]{1, -2, 5}, 2)));
System.out.println(json(Solution96.solve(new int[]{1, -2, 5}, 1)));
System.out.println(json(Solution99.solve(new int[]{-2, 1, 5}, 2, 4)));
System.out.println(json(Solution103.solve(new int[]{2, 3, 6})));
System.out.println(json(Solution104.solve("CC", 1)));
System.out.println(json(Solution105.solve("cc")));
System.out.println(json(Solution113.solve(new int[]{1, -2, 5})));
System.out.println(json(Solution112.solve("cc")));
System.out.println(json(Solution115.solve("cc", "a")));
System.out.println(json(Solution114.solve(2348)));
System.out.println(json(Solution94.solve(new int[]{-7, 6, 5, 7, -6, -1, -3}, 7)));
System.out.println(json(Solution95.solve(new int[]{-7, 6, 5, 7, -6, -1, -3}, 7)));
System.out.println(json(Solution109.solve(new int[]{-7, 6, 5, 7, -6, -1, -3}, 1)));
System.out.println(json(Solution110.solve(new int[]{-7, 6, 5, 7, -6, -1, -3}, 7)));
System.out.println(json(Solution96.solve(new int[]{-7, 6, 5, 7, -6, -1, -3}, 7)));
System.out.println(json(Solution99.solve(new int[]{-7, -6, -3, -1, 5, 6, 7}, 7, 6)));
System.out.println(json(Solution103.solve(new int[]{8, 7, 6, 8, 7, 2, 4})));
System.out.println(json(Solution104.solve("CAAAABCA", 3)));
System.out.println(json(Solution105.solve("caaaabca")));
System.out.println(json(Solution113.solve(new int[]{-7, 6, 5, 7, -6, -1, -3})));
System.out.println(json(Solution112.solve("caaaabca")));
}
static void batch9800() {
System.out.println(json(Solution115.solve("caaaabca", "a")));
System.out.println(json(Solution114.solve(90786)));
System.out.println(json(Solution94.solve(new int[]{-6, 6, 7, -6, 0, -7}, 5)));
System.out.println(json(Solution95.solve(new int[]{-6, 6, 7, -6, 0, -7}, 5)));
System.out.println(json(Solution109.solve(new int[]{-6, 6, 7, -6, 0, -7}, 1)));
System.out.println(json(Solution110.solve(new int[]{-6, 6, 7, -6, 0, -7}, 5)));
System.out.println(json(Solution96.solve(new int[]{-6, 6, 7, -6, 0, -7}, 3)));
System.out.println(json(Solution99.solve(new int[]{-7, -6, -6, 0, 6, 7}, 5, 2)));
System.out.println(json(Solution103.solve(new int[]{7, 7, 8, 7, 1, 8})));
System.out.println(json(Solution104.solve("BCCBCAC", 3)));
System.out.println(json(Solution105.solve("bccbcac")));
System.out.println(json(Solution113.solve(new int[]{-6, 6, 7, -6, 0, -7})));
System.out.println(json(Solution112.solve("bccbcac")));
System.out.println(json(Solution115.solve("bccbcac", "a")));
System.out.println(json(Solution114.solve(95270)));
System.out.println(json(Solution94.solve(new int[]{-5, -7, 5, 0, -1, 2, -7}, 2)));
System.out.println(json(Solution95.solve(new int[]{-5, -7, 5, 0, -1, 2, -7}, 2)));
System.out.println(json(Solution109.solve(new int[]{-5, -7, 5, 0, -1, 2, -7}, 1)));
System.out.println(json(Solution110.solve(new int[]{-5, -7, 5, 0, -1, 2, -7}, 2)));
System.out.println(json(Solution96.solve(new int[]{-5, -7, 5, 0, -1, 2, -7}, 3)));
System.out.println(json(Solution99.solve(new int[]{-7, -7, -5, -1, 0, 2, 5}, 2, -4)));
System.out.println(json(Solution103.solve(new int[]{6, 8, 6, 1, 2, 3, 8})));
System.out.println(json(Solution104.solve("CCB", 2)));
System.out.println(json(Solution105.solve("ccb")));
System.out.println(json(Solution113.solve(new int[]{-5, -7, 5, 0, -1, 2, -7})));
System.out.println(json(Solution112.solve("ccb")));
System.out.println(json(Solution115.solve("ccb", "a")));
System.out.println(json(Solution114.solve(10877)));
System.out.println(json(Solution94.solve(new int[]{-5, 2, -1, -3, 3, -5, -1, 5, -1}, 7)));
System.out.println(json(Solution95.solve(new int[]{-5, 2, -1, -3, 3, -5, -1, 5, -1}, 7)));
System.out.println(json(Solution109.solve(new int[]{-5, 2, -1, -3, 3, -5, -1, 5, -1}, 1)));
System.out.println(json(Solution110.solve(new int[]{-5, 2, -1, -3, 3, -5, -1, 5, -1}, 7)));
System.out.println(json(Solution96.solve(new int[]{-5, 2, -1, -3, 3, -5, -1, 5, -1}, 4)));
System.out.println(json(Solution99.solve(new int[]{-5, -5, -3, -1, -1, -1, 2, 3, 5}, 7, -7)));
System.out.println(json(Solution103.solve(new int[]{6, 3, 2, 4, 4, 6, 2, 6, 2})));
System.out.println(json(Solution104.solve("AC", 0)));
System.out.println(json(Solution105.solve("ac")));
System.out.println(json(Solution113.solve(new int[]{-5, 2, -1, -3, 3, -5, -1, 5, -1})));
System.out.println(json(Solution112.solve("ac")));
System.out.println(json(Solution115.solve("ac", "a")));
System.out.println(json(Solution114.solve(72743)));
System.out.println(json(Solution94.solve(new int[]{-6, 3, -6, -3, 2, -4, 7, -7, -7}, 5)));
System.out.println(json(Solution95.solve(new int[]{-6, 3, -6, -3, 2, -4, 7, -7, -7}, 5)));
System.out.println(json(Solution109.solve(new int[]{-6, 3, -6, -3, 2, -4, 7, -7, -7}, 1)));
System.out.println(json(Solution110.solve(new int[]{-6, 3, -6, -3, 2, -4, 7, -7, -7}, 5)));
System.out.println(json(Solution96.solve(new int[]{-6, 3, -6, -3, 2, -4, 7, -7, -7}, 4)));
System.out.println(json(Solution99.solve(new int[]{-7, -7, -6, -6, -4, -3, 2, 3, 7}, 5, 6)));
System.out.println(json(Solution103.solve(new int[]{7, 4, 7, 4, 3, 5, 8, 8, 8})));
System.out.println(json(Solution104.solve("CCC", 1)));
System.out.println(json(Solution105.solve("ccc")));
System.out.println(json(Solution113.solve(new int[]{-6, 3, -6, -3, 2, -4, 7, -7, -7})));
System.out.println(json(Solution112.solve("ccc")));
System.out.println(json(Solution115.solve("ccc", "a")));
System.out.println(json(Solution114.solve(45836)));
System.out.println(json(Solution94.solve(new int[]{-1, 2, -2, 0, 5, -1, -2}, 3)));
System.out.println(json(Solution95.solve(new int[]{-1, 2, -2, 0, 5, -1, -2}, 3)));
System.out.println(json(Solution109.solve(new int[]{-1, 2, -2, 0, 5, -1, -2}, 1)));
System.out.println(json(Solution110.solve(new int[]{-1, 2, -2, 0, 5, -1, -2}, 3)));
System.out.println(json(Solution96.solve(new int[]{-1, 2, -2, 0, 5, -1, -2}, 5)));
System.out.println(json(Solution99.solve(new int[]{-2, -2, -1, -1, 0, 2, 5}, 3, 5)));
System.out.println(json(Solution103.solve(new int[]{2, 3, 3, 1, 6, 2, 3})));
System.out.println(json(Solution104.solve("AAB", 0)));
System.out.println(json(Solution105.solve("aab")));
System.out.println(json(Solution113.solve(new int[]{-1, 2, -2, 0, 5, -1, -2})));
System.out.println(json(Solution112.solve("aab")));
System.out.println(json(Solution115.solve("aab", "a")));
System.out.println(json(Solution114.solve(32747)));
System.out.println(json(Solution94.solve(new int[]{4, -5, 0, -5}, 2)));
System.out.println(json(Solution95.solve(new int[]{4, -5, 0, -5}, 2)));
System.out.println(json(Solution109.solve(new int[]{4, -5, 0, -5}, 1)));
System.out.println(json(Solution110.solve(new int[]{4, -5, 0, -5}, 2)));
System.out.println(json(Solution96.solve(new int[]{4, -5, 0, -5}, 2)));
System.out.println(json(Solution99.solve(new int[]{-5, -5, 0, 4}, 2, 1)));
System.out.println(json(Solution103.solve(new int[]{5, 6, 1, 6})));
System.out.println(json(Solution104.solve("ABCAACBA", 1)));
System.out.println(json(Solution105.solve("abcaacba")));
System.out.println(json(Solution113.solve(new int[]{4, -5, 0, -5})));
System.out.println(json(Solution112.solve("abcaacba")));
System.out.println(json(Solution115.solve("abcaacba", "a")));
System.out.println(json(Solution114.solve(7744)));
System.out.println(json(Solution94.solve(new int[]{5, 4}, 1)));
System.out.println(json(Solution95.solve(new int[]{5, 4}, 1)));
System.out.println(json(Solution109.solve(new int[]{5, 4}, 1)));
System.out.println(json(Solution110.solve(new int[]{5, 4}, 1)));
System.out.println(json(Solution96.solve(new int[]{5, 4}, 2)));
System.out.println(json(Solution99.solve(new int[]{4, 5}, 1, 7)));
System.out.println(json(Solution103.solve(new int[]{6, 5})));
System.out.println(json(Solution104.solve("BACB", 0)));
System.out.println(json(Solution105.solve("bacb")));
System.out.println(json(Solution113.solve(new int[]{5, 4})));
System.out.println(json(Solution112.solve("bacb")));
System.out.println(json(Solution115.solve("bacb", "a")));
System.out.println(json(Solution114.solve(16587)));
System.out.println(json(Solution94.solve(new int[]{3, 7, -6, -4, -2, 6, -6}, 4)));
System.out.println(json(Solution95.solve(new int[]{3, 7, -6, -4, -2, 6, -6}, 4)));
System.out.println(json(Solution109.solve(new int[]{3, 7, -6, -4, -2, 6, -6}, 1)));
System.out.println(json(Solution110.solve(new int[]{3, 7, -6, -4, -2, 6, -6}, 4)));
System.out.println(json(Solution96.solve(new int[]{3, 7, -6, -4, -2, 6, -6}, 2)));
System.out.println(json(Solution99.solve(new int[]{-6, -6, -4, -2, 3, 6, 7}, 4, -6)));
System.out.println(json(Solution103.solve(new int[]{4, 8, 7, 5, 3, 7, 7})));
}
static void batch9900() {
System.out.println(json(Solution104.solve("CABB", 2)));
System.out.println(json(Solution105.solve("cabb")));
System.out.println(json(Solution113.solve(new int[]{3, 7, -6, -4, -2, 6, -6})));
System.out.println(json(Solution112.solve("cabb")));
System.out.println(json(Solution115.solve("cabb", "a")));
System.out.println(json(Solution114.solve(49317)));
System.out.println(json(Solution94.solve(new int[]{-1, -5, -1, 1, 1, -4, 1, -4}, 8)));
System.out.println(json(Solution95.solve(new int[]{-1, -5, -1, 1, 1, -4, 1, -4}, 8)));
System.out.println(json(Solution109.solve(new int[]{-1, -5, -1, 1, 1, -4, 1, -4}, 1)));
System.out.println(json(Solution110.solve(new int[]{-1, -5, -1, 1, 1, -4, 1, -4}, 8)));
System.out.println(json(Solution96.solve(new int[]{-1, -5, -1, 1, 1, -4, 1, -4}, 3)));
System.out.println(json(Solution99.solve(new int[]{-5, -4, -4, -1, -1, 1, 1, 1}, 8, -2)));
System.out.println(json(Solution103.solve(new int[]{2, 6, 2, 2, 2, 5, 2, 5})));
System.out.println(json(Solution104.solve("", 3)));
System.out.println(json(Solution105.solve("")));
System.out.println(json(Solution113.solve(new int[]{-1, -5, -1, 1, 1, -4, 1, -4})));
System.out.println(json(Solution112.solve("")));
System.out.println(json(Solution115.solve("", "a")));
System.out.println(json(Solution114.solve(48302)));
System.out.println(json(Solution94.solve(new int[]{6, 5, 5}, 1)));
System.out.println(json(Solution95.solve(new int[]{6, 5, 5}, 1)));
System.out.println(json(Solution109.solve(new int[]{6, 5, 5}, 1)));
System.out.println(json(Solution110.solve(new int[]{6, 5, 5}, 1)));
System.out.println(json(Solution96.solve(new int[]{6, 5, 5}, 1)));
System.out.println(json(Solution99.solve(new int[]{5, 5, 6}, 1, -1)));
System.out.println(json(Solution103.solve(new int[]{7, 6, 6})));
System.out.println(json(Solution104.solve("CCCA", 1)));
System.out.println(json(Solution105.solve("ccca")));
System.out.println(json(Solution113.solve(new int[]{6, 5, 5})));
System.out.println(json(Solution112.solve("ccca")));
System.out.println(json(Solution115.solve("ccca", "a")));
System.out.println(json(Solution114.solve(35468)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{2, 1}}, 0, 0)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{2, 2}, new int[]{4, 10}, new int[]{6, 8}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 1}, new int[]{5, 4}, new int[]{8, 9}}, 5, 3)));
System.out.println(json(Solution97.solve(new String[]{"bb", "cc", "a", "cc"}, 3)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-5, -4, 4}, new int[]{-4, -2, -1}, new int[]{-2, -2, 1}, new int[]{-4, -4, -1}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-5, -4, 4}, new int[]{-4, -2, -1}, new int[]{-2, -2, 1}, new int[]{-4, -4, -1}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{5, 5}}, 4, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 4}, new int[]{6, 9}, new int[]{8, 15}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 7}}, 16, 3)));
System.out.println(json(Solution97.solve(new String[]{"a", "a", "cc", "bb", "a", "bb", "a", "bb", "bb", "cc"}, 3)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-4}, new int[]{-5, -4, -2}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-4}, new int[]{-5, -4, -2}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{4, 1}, new int[]{6, 5}, new int[]{6, 5}, new int[]{0, 1}, new int[]{4, 5}}, 4, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 8}, new int[]{2, 15}, new int[]{4, 6}, new int[]{6, 8}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 7}, new int[]{5, 6}, new int[]{8, 3}, new int[]{11, 6}, new int[]{14, 9}}, 26, 5)));
System.out.println(json(Solution97.solve(new String[]{"cc", "cc", "bb", "a", "cc", "a"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-1, 4, 4}, new int[]{-4, -3, 4}, new int[]{-3, -2, 4}, new int[]{-3, 0, 2}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-1, 4, 4}, new int[]{-4, -3, 4}, new int[]{-3, -2, 4}, new int[]{-3, 0, 2}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{2, 5}}, 0, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 13}, new int[]{2, 3}, new int[]{4, 8}, new int[]{6, 10}, new int[]{8, 5}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 9}}, 14, 3)));
System.out.println(json(Solution97.solve(new String[]{"bb", "cc", "cc", "bb", "a", "cc", "cc", "bb", "cc", "a"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{4}, new int[]{-5, -4, -4}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{4}, new int[]{-5, -4, -4}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{4, 1}, new int[]{5, 1}, new int[]{4, 3}}, 4, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 2}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 7}, new int[]{5, 4}, new int[]{8, 7}}, 8, 1)));
System.out.println(json(Solution97.solve(new String[]{"a", "bb", "bb", "a", "a", "a", "a", "bb", "bb", "bb"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-5, -4, 2}, new int[]{-4, 1, 2}, new int[]{-2, -1, 4}, new int[]{-2, -1, 2}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-5, -4, 2}, new int[]{-4, 1, 2}, new int[]{-2, -1, 4}, new int[]{-2, -1, 2}}, 11)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{4, 3}}, 0, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 10}, new int[]{2, 8}, new int[]{4, 6}, new int[]{6, 10}, new int[]{8, 4}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 9}, new int[]{5, 4}, new int[]{8, 3}}, 23, 5)));
System.out.println(json(Solution97.solve(new String[]{"a", "a", "a", "a"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{2}, new int[]{-5, -4, -2}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{2}, new int[]{-5, -4, -2}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{1, 5}, new int[]{0, 3}, new int[]{2, 3}}, 2, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 5}, new int[]{2, 15}, new int[]{4, 12}, new int[]{6, 4}, new int[]{8, 8}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 1}, new int[]{5, 4}, new int[]{8, 9}, new int[]{11, 6}, new int[]{14, 5}}, 14, 7)));
System.out.println(json(Solution97.solve(new String[]{"cc", "a"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{4}, new int[]{0, 1, 2}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{4}, new int[]{0, 1, 2}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{5, 1}, new int[]{3, 5}, new int[]{5, 3}}, 4, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 9}, new int[]{2, 9}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 5}, new int[]{5, 4}, new int[]{8, 9}, new int[]{11, 6}, new int[]{14, 3}}, 23, 1)));
System.out.println(json(Solution97.solve(new String[]{"a", "bb", "cc", "bb", "cc", "a"}, 2)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-2, -1, 4}, new int[]{-4, -4, 1}, new int[]{-1, 0, 2}, new int[]{-2, 2, 3}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-2, -1, 4}, new int[]{-4, -4, 1}, new int[]{-1, 0, 2}, new int[]{-2, 2, 3}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{1, 1}, new int[]{2, 5}, new int[]{4, 1}, new int[]{2, 5}, new int[]{2, 1}}, 3, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 10}, new int[]{6, 13}, new int[]{8, 4}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 7}}, 23, 3)));
System.out.println(json(Solution97.solve(new String[]{"cc", "cc", "a", "a", "cc", "cc", "cc", "cc", "cc", "a"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{0}, new int[]{0, 1, 4}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{0}, new int[]{0, 1, 4}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{1, 1}, new int[]{6, 1}, new int[]{6, 3}}, 4, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 14}, new int[]{6, 7}, new int[]{8, 7}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 9}, new int[]{5, 8}, new int[]{8, 3}}, 19, 5)));
System.out.println(json(Solution97.solve(new String[]{"cc", "bb"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-2, -1, 0}, new int[]{-4, -3, 2}, new int[]{-4, -1, 0}, new int[]{-3, -2, 2}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-2, -1, 0}, new int[]{-4, -3, 2}, new int[]{-4, -1, 0}, new int[]{-3, -2, 2}}, 7)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{5, 3}, new int[]{5, 1}, new int[]{1, 5}, new int[]{3, 3}, new int[]{4, 5}}, 3, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 1}, new int[]{6, 6}, new int[]{8, 12}, new int[]{2, 2}, new int[]{4, 15}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 3}, new int[]{5, 2}, new int[]{8, 5}}, 16, 1)));
System.out.println(json(Solution97.solve(new String[]{"cc", "cc", "cc", "cc", "cc", "bb"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-3, 2, 4}, new int[]{-5, 0, 0}, new int[]{-4, -3, -2}, new int[]{-4, -1, 0}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-3, 2, 4}, new int[]{-5, 0, 0}, new int[]{-4, -3, -2}, new int[]{-4, -1, 0}}, 7)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{0, 5}, new int[]{6, 3}, new int[]{5, 3}, new int[]{1, 3}, new int[]{5, 1}}, 1, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 7}, new int[]{6, 2}})));
}
static void batch10000() {
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 1}, new int[]{5, 8}, new int[]{8, 3}}, 15, 3)));
System.out.println(json(Solution97.solve(new String[]{"a", "a", "a", "a", "a", "a", "cc", "a", "cc", "a"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{0}, new int[]{-4, 3, 4}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{0}, new int[]{-4, 3, 4}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{0, 3}}, 4, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 9}, new int[]{2, 14}, new int[]{4, 11}, new int[]{6, 9}, new int[]{8, 4}, new int[]{2, 14}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 9}}, 19, 5)));
System.out.println(json(Solution97.solve(new String[]{"a", "a", "cc", "bb"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-2}, new int[]{-3, -2, 0}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-2}, new int[]{-3, -2, 0}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{3, 1}, new int[]{6, 3}, new int[]{6, 3}, new int[]{5, 5}, new int[]{6, 3}}, 2, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 13}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 9}}, 19, 7)));
System.out.println(json(Solution97.solve(new String[]{"bb", "cc", "bb", "a", "cc", "cc", "a", "bb", "cc", "a"}, 2)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{2}, new int[]{-5, 2, 2}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{2}, new int[]{-5, 2, 2}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{6, 1}, new int[]{3, 5}, new int[]{3, 1}}, 2, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 5}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 5}}, 9, 3)));
System.out.println(json(Solution97.solve(new String[]{"a", "a", "bb", "a"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{1, 2, 4}, new int[]{-4, -2, 3}, new int[]{-4, -4, -1}, new int[]{-4, 0, 3}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{1, 2, 4}, new int[]{-4, -2, 3}, new int[]{-4, -4, -1}, new int[]{-4, 0, 3}}, 7)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{0, 1}, new int[]{3, 3}, new int[]{3, 3}}, 4, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 4}, new int[]{2, 8}, new int[]{4, 6}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 9}}, 17, 7)));
System.out.println(json(Solution97.solve(new String[]{"cc", "a", "bb", "bb", "bb", "a", "cc", "cc"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-2, 0, 3}, new int[]{0, 1, 4}, new int[]{-2, 0, 1}, new int[]{-2, 0, 3}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-2, 0, 3}, new int[]{0, 1, 4}, new int[]{-2, 0, 1}, new int[]{-2, 0, 3}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{6, 3}}, 0, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 11}, new int[]{6, 14}, new int[]{8, 4}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 7}, new int[]{5, 6}, new int[]{8, 9}}, 7, 5)));
System.out.println(json(Solution97.solve(new String[]{"bb", "a", "cc", "cc", "bb", "a", "bb", "bb", "cc", "bb"}, 2)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-4, 2, 3}, new int[]{0, 3, 4}, new int[]{-3, 0, 4}, new int[]{-4, -3, -2}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-4, 2, 3}, new int[]{0, 3, 4}, new int[]{-3, 0, 4}, new int[]{-4, -3, -2}}, 7)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{2, 5}, new int[]{4, 3}, new int[]{2, 1}}, 1, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 10}, new int[]{2, 9}, new int[]{4, 11}, new int[]{6, 5}, new int[]{8, 10}, new int[]{2, 1}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 9}}, 21, 5)));
System.out.println(json(Solution97.solve(new String[]{"bb", "bb", "cc", "a", "cc", "a", "bb", "bb", "cc", "cc"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-2, -1, 2}, new int[]{-3, 2, 4}, new int[]{-1, 0, 0}, new int[]{-5, -2, 0}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-2, -1, 2}, new int[]{-3, 2, 4}, new int[]{-1, 0, 0}, new int[]{-5, -2, 0}}, 11)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{0, 5}, new int[]{4, 1}, new int[]{1, 5}, new int[]{1, 3}, new int[]{6, 1}}, 3, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 8}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 7}, new int[]{5, 10}, new int[]{8, 5}, new int[]{11, 6}, new int[]{14, 3}}, 9, 3)));
System.out.println(json(Solution97.solve(new String[]{"a", "bb", "a", "cc", "cc", "a"}, 2)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{0}, new int[]{-5, 2, 2}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{0}, new int[]{-5, 2, 2}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{2, 3}}, 0, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 12}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 7}, new int[]{5, 4}, new int[]{8, 5}}, 20, 1)));
System.out.println(json(Solution97.solve(new String[]{"cc", "a", "cc", "cc", "bb", "a", "cc", "bb", "a", "cc"}, 2)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-2, 3, 4}, new int[]{-3, -2, 4}, new int[]{-5, -4, 0}, new int[]{-2, 3, 4}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-2, 3, 4}, new int[]{-3, -2, 4}, new int[]{-5, -4, 0}, new int[]{-2, 3, 4}}, 11)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{3, 3}, new int[]{0, 3}, new int[]{2, 3}}, 3, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 5}, new int[]{6, 12}, new int[]{8, 10}, new int[]{2, 3}, new int[]{4, 1}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 5}, new int[]{5, 6}, new int[]{8, 3}, new int[]{11, 10}, new int[]{14, 3}}, 16, 3)));
System.out.println(json(Solution97.solve(new String[]{"bb", "a", "cc", "a", "cc", "bb", "a", "bb"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-2, 2, 3}, new int[]{-5, 0, 4}, new int[]{-4, 1, 4}, new int[]{-5, -2, 0}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-2, 2, 3}, new int[]{-5, 0, 4}, new int[]{-4, 1, 4}, new int[]{-5, -2, 0}}, 7)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{1, 5}, new int[]{6, 3}, new int[]{3, 1}, new int[]{6, 5}, new int[]{1, 3}}, 2, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 12}, new int[]{2, 8}, new int[]{4, 6}, new int[]{6, 7}, new int[]{8, 6}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 7}, new int[]{5, 6}, new int[]{8, 7}}, 7, 5)));
System.out.println(json(Solution97.solve(new String[]{"bb", "cc", "a", "cc", "a", "cc", "cc", "bb"}, 3)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-2}, new int[]{-1, 0, 2}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-2}, new int[]{-1, 0, 2}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{6, 1}, new int[]{6, 3}, new int[]{3, 3}}, 1, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 1}, new int[]{6, 12}, new int[]{8, 13}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 9}}, 6, 3)));
System.out.println(json(Solution97.solve(new String[]{"bb", "bb", "cc", "cc"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-3, -2, 4}, new int[]{-4, -1, 2}, new int[]{-3, 0, 4}, new int[]{-3, 0, 2}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-3, -2, 4}, new int[]{-4, -1, 2}, new int[]{-3, 0, 4}, new int[]{-3, 0, 2}}, 7)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{3, 5}, new int[]{0, 5}, new int[]{4, 3}, new int[]{4, 3}, new int[]{1, 5}}, 2, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 11}, new int[]{6, 10}, new int[]{8, 15}, new int[]{2, 12}, new int[]{4, 10}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 1}, new int[]{5, 2}, new int[]{8, 1}, new int[]{11, 2}, new int[]{14, 9}}, 26, 3)));
System.out.println(json(Solution97.solve(new String[]{"a", "bb", "a", "bb"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{0, 1, 4}, new int[]{-1, 2, 4}, new int[]{0, 1, 4}, new int[]{0, 2, 3}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{0, 1, 4}, new int[]{-1, 2, 4}, new int[]{0, 1, 4}, new int[]{0, 2, 3}}, 7)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{1, 5}}, 1, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 5}, new int[]{6, 12}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 9}, new int[]{5, 6}, new int[]{8, 3}}, 29, 3)));
System.out.println(json(Solution97.solve(new String[]{"bb", "a", "a", "bb", "bb", "bb"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{2}, new int[]{-5, -4, 4}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{2}, new int[]{-5, -4, 4}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{4, 3}}, 3, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 11}, new int[]{6, 12}, new int[]{8, 5}, new int[]{2, 4}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 1}, new int[]{5, 8}, new int[]{8, 1}}, 26, 7)));
System.out.println(json(Solution97.solve(new String[]{"cc", "cc", "cc", "cc"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{0, 1, 2}, new int[]{-2, -2, 1}, new int[]{-3, 0, 2}, new int[]{-3, 2, 4}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{0, 1, 2}, new int[]{-2, -2, 1}, new int[]{-3, 0, 2}, new int[]{-3, 2, 4}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{1, 5}, new int[]{5, 1}, new int[]{1, 3}}, 0, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 3}, new int[]{6, 7}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 9}, new int[]{5, 2}, new int[]{8, 1}}, 23, 3)));
System.out.println(json(Solution97.solve(new String[]{"a", "bb", "a", "cc", "bb", "cc", "bb", "cc"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-5, 2, 4}, new int[]{-2, 1, 2}, new int[]{-5, 0, 2}, new int[]{-4, -2, -1}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-5, 2, 4}, new int[]{-2, 1, 2}, new int[]{-5, 0, 2}, new int[]{-4, -2, -1}}, 7)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{0, 3}}, 4, 2)));
System.out.println(json(Solution108.solve(new int[][]{})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 7}, new int[]{5, 10}, new int[]{8, 1}}, 21, 3)));
System.out.println(json(Solution97.solve(new String[]{"a", "a", "bb", "cc", "bb", "bb"}, 2)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-2}, new int[]{-2, 1, 2}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-2}, new int[]{-2, 1, 2}}, 3)));
}
static void batch10100() {
System.out.println(json(Solution107.solve(new int[][]{new int[]{1, 1}}, 1, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 11}, new int[]{6, 1}, new int[]{8, 13}, new int[]{2, 1}, new int[]{4, 6}, new int[]{6, 2}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 7}}, 28, 1)));
System.out.println(json(Solution97.solve(new String[]{"bb", "bb", "cc", "cc", "a", "cc", "bb", "cc"}, 3)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{4}, new int[]{-3, -2, 4}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{4}, new int[]{-3, -2, 4}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{5, 5}, new int[]{0, 1}, new int[]{0, 1}, new int[]{3, 3}, new int[]{5, 3}}, 2, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 5}, new int[]{6, 12}, new int[]{8, 6}, new int[]{2, 2}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 7}, new int[]{5, 10}, new int[]{8, 9}, new int[]{11, 8}, new int[]{14, 7}}, 26, 1)));
System.out.println(json(Solution97.solve(new String[]{"cc", "a", "cc", "cc", "cc", "a"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-4, -2, 1}, new int[]{-5, -4, -2}, new int[]{-3, 0, 4}, new int[]{-2, 1, 4}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-4, -2, 1}, new int[]{-5, -4, -2}, new int[]{-3, 0, 4}, new int[]{-2, 1, 4}}, 11)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{2, 3}}, 3, 2)));
System.out.println(json(Solution108.solve(new int[][]{})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 1}, new int[]{5, 2}, new int[]{8, 9}, new int[]{11, 4}, new int[]{14, 9}}, 11, 1)));
System.out.println(json(Solution97.solve(new String[]{"cc", "bb", "bb", "bb", "bb", "cc"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-5, -4, 2}, new int[]{-1, 0, 2}, new int[]{-3, -2, 4}, new int[]{-5, -2, 0}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-5, -4, 2}, new int[]{-1, 0, 2}, new int[]{-3, -2, 4}, new int[]{-5, -2, 0}}, 7)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{3, 5}}, 2, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 15}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 1}}, 7, 7)));
System.out.println(json(Solution97.solve(new String[]{"a", "cc", "a", "bb"}, 3)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-4, -2, 1}, new int[]{-1, 0, 2}, new int[]{-4, -4, -1}, new int[]{-1, 0, 0}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-4, -2, 1}, new int[]{-1, 0, 2}, new int[]{-4, -4, -1}, new int[]{-1, 0, 0}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{3, 1}, new int[]{3, 3}, new int[]{2, 5}, new int[]{4, 1}, new int[]{1, 5}}, 3, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 15}, new int[]{2, 7}, new int[]{4, 13}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 1}, new int[]{5, 8}, new int[]{8, 7}}, 18, 1)));
System.out.println(json(Solution97.solve(new String[]{"bb", "a", "cc", "a", "a", "a", "bb", "a", "bb", "a"}, 3)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-2, 0, 3}, new int[]{-4, -2, -1}, new int[]{1, 4, 4}, new int[]{0, 3, 4}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-2, 0, 3}, new int[]{-4, -2, -1}, new int[]{1, 4, 4}, new int[]{0, 3, 4}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{1, 5}, new int[]{2, 5}, new int[]{2, 1}}, 0, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 15}, new int[]{6, 1}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 7}, new int[]{5, 10}, new int[]{8, 7}}, 25, 3)));
System.out.println(json(Solution97.solve(new String[]{"a", "cc", "a", "bb", "a", "cc", "a", "bb"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-4, -4, 3}, new int[]{0, 2, 3}, new int[]{-5, -4, 4}, new int[]{-4, -3, 2}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-4, -4, 3}, new int[]{0, 2, 3}, new int[]{-5, -4, 4}, new int[]{-4, -3, 2}}, 11)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{2, 3}, new int[]{3, 5}, new int[]{6, 3}}, 2, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 11}, new int[]{6, 1}, new int[]{8, 5}, new int[]{2, 2}, new int[]{4, 10}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 5}, new int[]{5, 10}, new int[]{8, 3}, new int[]{11, 2}, new int[]{14, 5}}, 17, 3)));
System.out.println(json(Solution97.solve(new String[]{"cc", "bb", "bb", "bb", "bb", "bb"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{4}, new int[]{-2, 0, 3}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{4}, new int[]{-2, 0, 3}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{4, 3}}, 4, 2)));
System.out.println(json(Solution108.solve(new int[][]{})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 9}, new int[]{5, 8}, new int[]{8, 5}}, 15, 7)));
System.out.println(json(Solution97.solve(new String[]{"a", "cc"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{2}, new int[]{-2, 0, 1}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{2}, new int[]{-2, 0, 1}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{0, 5}}, 4, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 15}, new int[]{6, 8}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 3}}, 20, 1)));
System.out.println(json(Solution97.solve(new String[]{"bb", "a", "a", "bb", "cc", "cc"}, 2)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-5, -4, -2}, new int[]{-4, -2, 1}, new int[]{-4, -2, -1}, new int[]{2, 3, 4}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-5, -4, -2}, new int[]{-4, -2, 1}, new int[]{-4, -2, -1}, new int[]{2, 3, 4}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{6, 5}}, 1, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 10}, new int[]{6, 7}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 9}}, 14, 1)));
System.out.println(json(Solution97.solve(new String[]{"a", "bb", "cc", "bb", "a", "a"}, 2)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-1, 0, 4}, new int[]{1, 4, 4}, new int[]{-2, -2, -1}, new int[]{-4, -2, -1}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-1, 0, 4}, new int[]{1, 4, 4}, new int[]{-2, -2, -1}, new int[]{-4, -2, -1}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{3, 1}, new int[]{2, 3}, new int[]{3, 1}}, 3, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 2}, new int[]{2, 10}, new int[]{4, 9}, new int[]{6, 9}, new int[]{8, 11}, new int[]{2, 7}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 1}, new int[]{5, 6}, new int[]{8, 7}, new int[]{11, 6}, new int[]{14, 9}}, 11, 1)));
System.out.println(json(Solution97.solve(new String[]{"cc", "a", "a", "a"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-2}, new int[]{-2, 0, 3}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-2}, new int[]{-2, 0, 3}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{0, 5}}, 1, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 14}, new int[]{2, 11}, new int[]{4, 13}, new int[]{6, 14}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 7}, new int[]{5, 8}, new int[]{8, 1}, new int[]{11, 6}, new int[]{14, 9}}, 28, 5)));
System.out.println(json(Solution97.solve(new String[]{"bb", "a", "cc", "cc", "cc", "cc", "a", "bb"}, 2)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{4}, new int[]{-4, -1, 2}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{4}, new int[]{-4, -1, 2}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{3, 5}}, 1, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 4}, new int[]{2, 9}, new int[]{4, 8}, new int[]{6, 11}, new int[]{8, 3}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 7}}, 23, 3)));
System.out.println(json(Solution97.solve(new String[]{"a", "bb", "a", "bb"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-1, 4, 4}, new int[]{-5, -4, 0}, new int[]{-5, -2, 4}, new int[]{-1, 0, 4}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-1, 4, 4}, new int[]{-5, -4, 0}, new int[]{-5, -2, 4}, new int[]{-1, 0, 4}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{1, 5}, new int[]{2, 5}, new int[]{3, 1}, new int[]{4, 5}, new int[]{0, 3}}, 0, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 3}, new int[]{6, 4}, new int[]{8, 4}, new int[]{2, 12}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 9}, new int[]{5, 4}, new int[]{8, 3}}, 14, 7)));
System.out.println(json(Solution97.solve(new String[]{"bb", "cc", "bb", "a", "cc", "a"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{4}, new int[]{-1, 0, 2}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{4}, new int[]{-1, 0, 2}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{5, 1}, new int[]{3, 3}, new int[]{5, 5}, new int[]{1, 3}, new int[]{1, 1}}, 0, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 12}, new int[]{2, 1}, new int[]{4, 11}, new int[]{6, 7}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 7}}, 10, 1)));
System.out.println(json(Solution97.solve(new String[]{"a", "bb", "a", "bb", "a", "a", "a", "cc", "cc", "bb"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-3, 2, 2}, new int[]{0, 2, 3}, new int[]{-4, 0, 1}, new int[]{-1, 0, 2}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-3, 2, 2}, new int[]{0, 2, 3}, new int[]{-4, 0, 1}, new int[]{-1, 0, 2}}, 7)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{1, 1}}, 1, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 1}, new int[]{2, 9}, new int[]{4, 2}, new int[]{6, 6}, new int[]{8, 7}, new int[]{2, 7}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 9}, new int[]{5, 4}, new int[]{8, 3}, new int[]{11, 4}, new int[]{14, 5}}, 16, 1)));
System.out.println(json(Solution97.solve(new String[]{"cc", "a", "bb", "cc", "a", "a", "cc", "cc"}, 2)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{0}, new int[]{3, 4, 4}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{0}, new int[]{3, 4, 4}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{4, 5}, new int[]{0, 1}, new int[]{6, 1}, new int[]{4, 1}, new int[]{0, 1}}, 3, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 10}, new int[]{6, 10}, new int[]{8, 2}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 1}}, 14, 3)));
System.out.println(json(Solution97.solve(new String[]{"a", "a", "a", "cc"}, 1)));
}
static void batch10200() {
System.out.println(json(Solution101.solve(new int[][]{new int[]{-2, 2, 3}, new int[]{-2, 0, 1}, new int[]{-5, -4, -2}, new int[]{-5, 0, 4}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-2, 2, 3}, new int[]{-2, 0, 1}, new int[]{-5, -4, -2}, new int[]{-5, 0, 4}}, 11)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{4, 5}, new int[]{4, 1}, new int[]{0, 3}}, 4, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 10}, new int[]{2, 8}, new int[]{4, 9}, new int[]{6, 3}, new int[]{8, 8}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 7}, new int[]{5, 10}, new int[]{8, 5}, new int[]{11, 10}, new int[]{14, 7}}, 22, 7)));
System.out.println(json(Solution97.solve(new String[]{"a", "bb", "a", "bb"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-2, 1, 4}, new int[]{-4, 3, 4}, new int[]{-4, -2, 1}, new int[]{-5, -2, 0}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-2, 1, 4}, new int[]{-4, 3, 4}, new int[]{-4, -2, 1}, new int[]{-5, -2, 0}}, 7)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{3, 5}, new int[]{6, 5}, new int[]{3, 5}, new int[]{4, 1}, new int[]{3, 1}}, 1, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 10}, new int[]{2, 15}, new int[]{4, 3}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 1}, new int[]{5, 8}, new int[]{8, 1}}, 11, 1)));
System.out.println(json(Solution97.solve(new String[]{"a", "bb", "cc", "bb", "bb", "bb", "a", "bb", "a", "bb"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-2, -2, -1}, new int[]{-4, -1, 2}, new int[]{-3, -2, 2}, new int[]{-3, -2, -2}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-2, -2, -1}, new int[]{-4, -1, 2}, new int[]{-3, -2, 2}, new int[]{-3, -2, -2}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{4, 5}, new int[]{4, 3}, new int[]{5, 1}, new int[]{5, 3}, new int[]{6, 3}}, 2, 2)));
System.out.println(json(Solution108.solve(new int[][]{})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 7}, new int[]{5, 2}, new int[]{8, 1}, new int[]{11, 2}, new int[]{14, 3}}, 24, 5)));
System.out.println(json(Solution97.solve(new String[]{"cc", "cc", "cc", "cc"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{0}, new int[]{-4, 1, 4}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{0}, new int[]{-4, 1, 4}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{2, 5}, new int[]{2, 5}, new int[]{1, 1}, new int[]{3, 1}, new int[]{1, 1}}, 4, 2)));
System.out.println(json(Solution108.solve(new int[][]{})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 1}}, 6, 5)));
System.out.println(json(Solution97.solve(new String[]{"cc", "cc"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{2, 3, 4}, new int[]{-5, 0, 4}, new int[]{1, 4, 4}, new int[]{-1, 0, 2}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{2, 3, 4}, new int[]{-5, 0, 4}, new int[]{1, 4, 4}, new int[]{-1, 0, 2}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{1, 5}, new int[]{6, 5}, new int[]{0, 5}, new int[]{1, 5}, new int[]{6, 5}}, 3, 2)));
System.out.println(json(Solution108.solve(new int[][]{})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 5}, new int[]{5, 6}, new int[]{8, 9}, new int[]{11, 6}, new int[]{14, 1}}, 18, 1)));
System.out.println(json(Solution97.solve(new String[]{"a", "bb", "a", "bb", "bb", "cc"}, 2)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-4, -1, 4}, new int[]{-5, -4, 4}, new int[]{-4, 1, 2}, new int[]{-5, -4, 4}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-4, -1, 4}, new int[]{-5, -4, 4}, new int[]{-4, 1, 2}, new int[]{-5, -4, 4}}, 7)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{2, 1}}, 3, 2)));
System.out.println(json(Solution108.solve(new int[][]{})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 3}, new int[]{5, 4}, new int[]{8, 5}}, 19, 7)));
System.out.println(json(Solution97.solve(new String[]{"bb", "a", "bb", "bb", "a", "bb", "a", "cc", "cc", "bb"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{4}, new int[]{-3, 4, 4}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{4}, new int[]{-3, 4, 4}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{0, 1}, new int[]{6, 3}, new int[]{4, 3}, new int[]{1, 1}, new int[]{5, 3}}, 3, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 6}, new int[]{6, 8}, new int[]{8, 9}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 1}, new int[]{5, 10}, new int[]{8, 3}, new int[]{11, 10}, new int[]{14, 1}}, 9, 7)));
System.out.println(json(Solution97.solve(new String[]{"bb", "a", "a", "a", "bb", "cc", "a", "cc"}, 3)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-5, -4, -4}, new int[]{1, 2, 2}, new int[]{-5, -4, 2}, new int[]{1, 4, 4}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-5, -4, -4}, new int[]{1, 2, 2}, new int[]{-5, -4, 2}, new int[]{1, 4, 4}}, 7)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{1, 1}, new int[]{3, 5}, new int[]{1, 5}, new int[]{1, 1}, new int[]{4, 5}}, 1, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 11}, new int[]{6, 7}, new int[]{8, 13}, new int[]{2, 15}, new int[]{4, 6}, new int[]{6, 11}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 9}, new int[]{5, 8}, new int[]{8, 9}}, 16, 3)));
System.out.println(json(Solution97.solve(new String[]{"cc", "cc", "cc", "cc", "bb", "a", "a", "cc", "bb", "bb"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{4}, new int[]{-4, 0, 3}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{4}, new int[]{-4, 0, 3}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{3, 3}, new int[]{0, 1}, new int[]{5, 1}, new int[]{5, 5}, new int[]{4, 3}}, 4, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 13}, new int[]{2, 12}, new int[]{4, 3}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 7}}, 29, 7)));
System.out.println(json(Solution97.solve(new String[]{"bb", "bb", "a", "cc", "bb", "a"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-2}, new int[]{-4, 2, 3}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-2}, new int[]{-4, 2, 3}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{2, 3}, new int[]{6, 3}, new int[]{6, 5}}, 0, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 1}, new int[]{6, 8}, new int[]{8, 9}, new int[]{2, 3}, new int[]{4, 10}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 1}, new int[]{5, 4}, new int[]{8, 5}, new int[]{11, 2}, new int[]{14, 7}}, 8, 3)));
System.out.println(json(Solution97.solve(new String[]{"cc", "cc", "a", "cc", "a", "bb", "cc", "bb", "bb", "bb"}, 2)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{2}, new int[]{-4, 1, 2}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{2}, new int[]{-4, 1, 2}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{6, 3}, new int[]{0, 3}, new int[]{5, 3}}, 1, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 6}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 7}}, 17, 7)));
System.out.println(json(Solution97.solve(new String[]{"cc", "cc"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-2}, new int[]{-4, 1, 4}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-2}, new int[]{-4, 1, 4}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{1, 1}, new int[]{0, 5}, new int[]{4, 5}, new int[]{0, 3}, new int[]{2, 5}}, 3, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 12}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 5}, new int[]{5, 6}, new int[]{8, 3}}, 21, 1)));
System.out.println(json(Solution97.solve(new String[]{"bb", "cc"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-5, 0, 0}, new int[]{-2, -2, -1}, new int[]{-3, 0, 0}, new int[]{-2, -1, 4}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-5, 0, 0}, new int[]{-2, -2, -1}, new int[]{-3, 0, 0}, new int[]{-2, -1, 4}}, 7)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{1, 3}, new int[]{5, 3}, new int[]{2, 1}}, 2, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{4, 11}, new int[]{6, 7}, new int[]{8, 7}, new int[]{2, 15}, new int[]{4, 1}, new int[]{6, 8}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 1}, new int[]{5, 10}, new int[]{8, 7}, new int[]{11, 4}, new int[]{14, 3}}, 18, 5)));
System.out.println(json(Solution97.solve(new String[]{"cc", "a", "cc", "bb", "bb", "a", "a", "bb"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{2}, new int[]{-5, 0, 4}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{2}, new int[]{-5, 0, 4}}, 3)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{4, 1}}, 0, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 1}, new int[]{2, 15}, new int[]{4, 10}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 9}, new int[]{5, 2}, new int[]{8, 1}}, 8, 1)));
System.out.println(json(Solution97.solve(new String[]{"bb", "a", "cc", "a", "cc", "bb", "cc", "bb", "a", "a"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-4, -4, -3}, new int[]{-3, 0, 2}, new int[]{-4, 0, 1}, new int[]{-1, 2, 4}}, 12)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-4, -4, -3}, new int[]{-3, 0, 2}, new int[]{-4, 0, 1}, new int[]{-1, 2, 4}}, 11)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{4, 3}, new int[]{6, 5}, new int[]{0, 3}, new int[]{1, 5}, new int[]{1, 5}}, 0, 2)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{8, 6}, new int[]{2, 5}, new int[]{4, 8}, new int[]{6, 1}, new int[]{8, 7}})));
System.out.println(json(Solution106.solve(new int[][]{new int[]{2, 1}}, 5, 3)));
System.out.println(json(Solution97.solve(new String[]{"cc", "bb"}, 1)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{-4}, new int[]{-4, -4, 1}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{-4}, new int[]{-4, -4, 1}}, 3)));
System.out.println(json(Solution111.solve(0)));
System.out.println(json(Solution111.solve(1)));
System.out.println(json(Solution111.solve(2)));
System.out.println(json(Solution111.solve(3)));
System.out.println(json(Solution111.solve(4)));
System.out.println(json(Solution111.solve(5)));
System.out.println(json(Solution111.solve(6)));
System.out.println(json(Solution111.solve(7)));
}
static void batch10300() {
System.out.println(json(Solution111.solve(8)));
System.out.println(json(Solution111.solve(9)));
System.out.println(json(Solution111.solve(10)));
System.out.println(json(Solution116.solve(0)));
System.out.println(json(Solution116.solve(1)));
System.out.println(json(Solution116.solve(2)));
System.out.println(json(Solution116.solve(3)));
System.out.println(json(Solution116.solve(4)));
System.out.println(json(Solution118.solve(new int[]{})));
System.out.println(json(Solution118.solve(new int[]{1})));
System.out.println(json(Solution118.solve(new int[]{1, 2, 3})));
System.out.println(json(Solution118.solve(new int[]{1, 2, 3, 4})));
System.out.println(json(Solution117.solve("")));
System.out.println(json(Solution117.solve("23")));
System.out.println(json(Solution119.solve(new int[]{2, 3, 6, 7}, 7)));
System.out.println(json(Solution119.solve(new int[]{2}, 1)));
System.out.println(json(Solution120.solve("aab")));
System.out.println(json(Solution120.solve("")));
System.out.println(json(Solution97.solve(new String[]{"b", "a", "b", "a", "c"}, 2)));
System.out.println(json(Solution98.solve(new int[][]{new int[]{1, 3}, new int[]{-2, 2}, new int[]{2, -2}}, 2)));
System.out.println(json(Solution100.solve(new int[][]{new int[]{1, 0}, new int[]{0, 0}, new int[]{1, 0}}, 3)));
System.out.println(json(Solution101.solve(new int[][]{new int[]{1, 5}, new int[]{2, 3}}, 4)));
System.out.println(json(Solution102.solve(new int[][]{new int[]{1, 2}, new int[]{2, 4}}, 3)));
System.out.println(json(Solution106.solve(new int[][]{}, 2, 1)));
System.out.println(json(Solution106.solve(new int[][]{new int[]{10, 60}, new int[]{20, 30}, new int[]{30, 30}, new int[]{60, 40}}, 100, 10)));
System.out.println(json(Solution107.solve(new int[][]{new int[]{0, 1}, new int[]{1, 2}, new int[]{1, 3}}, 2, 0)));
System.out.println(json(Solution107.solve(new int[][]{}, 2, 5)));
System.out.println(json(Solution108.solve(new int[][]{new int[]{100, 200}, new int[]{200, 1300}, new int[]{1000, 1250}, new int[]{2000, 3200}})));
System.out.println(json(Solution108.solve(new int[][]{})));
}
public static void main(String[] args) {batch0();batch100();batch200();batch300();batch400();batch500();batch600();batch700();batch800();batch900();batch1000();batch1100();batch1200();batch1300();batch1400();batch1500();batch1600();batch1700();batch1800();batch1900();batch2000();batch2100();batch2200();batch2300();batch2400();batch2500();batch2600();batch2700();batch2800();batch2900();batch3000();batch3100();batch3200();batch3300();batch3400();batch3500();batch3600();batch3700();batch3800();batch3900();batch4000();batch4100();batch4200();batch4300();batch4400();batch4500();batch4600();batch4700();batch4800();batch4900();batch5000();batch5100();batch5200();batch5300();batch5400();batch5500();batch5600();batch5700();batch5800();batch5900();batch6000();batch6100();batch6200();batch6300();batch6400();batch6500();batch6600();batch6700();batch6800();batch6900();batch7000();batch7100();batch7200();batch7300();batch7400();batch7500();batch7600();batch7700();batch7800();batch7900();batch8000();batch8100();batch8200();batch8300();batch8400();batch8500();batch8600();batch8700();batch8800();batch8900();batch9000();batch9100();batch9200();batch9300();batch9400();batch9500();batch9600();batch9700();batch9800();batch9900();batch10000();batch10100();batch10200();batch10300();}
}