https://www.jianshu.com/p/9841425259e7

问题定义
主元素(Majority Number)定义为数组中出现次数严格超过一半的数。找到这个数。要求使用O(1)的额外空间和O(n)的时间。

问题分类
- 数组中有N个数，其中有一个数严格超过1/2，求这个数
- 数组中有N个数，其中有两个数严格超过1/3，求这两个数
- 数组中有N个数，只有一个数严格超过1/3，求这个数
- 如果数组中存在且只存在一个出现次数严格超过1/k的数，找到这个数。要求使用O(k)的额外空间和O(n)的时间。
- 使用蒙特卡洛随机算法判断主元素是否存在。

### 问题1：
抵消法（O(n))：
一旦发现数组中存在两个不同的数，就都删除，直到剩下的数都一样。此时剩下的数就是主元素。
原理：任意删去两个不同的数之后，在剩余子数组中，主元素依旧是主元素。随着遍历，最后剩下的数，一定是主元素（因为最多删除n/2 - 1次）
实现方式：采用一个计数器count和一个候选值tmp，计数器记录当前候选值在数组中的存在次数，若count=0则更换候选值，否则与遍历值进行比较，如果相等则计数器加1，否则减一。

直接排序(O(nlgn)):
原理：直接对数组排序，因为主元素超过1/2，因此中位数一定是主元素。代码略。

### 问题二：数组中有N个数，其中有两个数严格超过1/3，求这两个数
与问题1相似，我们仍然使用抵消法。
抵消法：当出现三个元素不同时，相互抵消。

code:
```vector<int> majorityElement(vector<int>& nums) 
{
    vector<int>ans;
    int n = nums.size();
    if(n<1)return ans;
    int cnt1=0,cnt2=0,tmp1=nums[0],tmp2=nums[0];
    for (int i = 0; i < n; i++) {
        if (nums[i] == tmp1)
            cnt1++;
        else if (nums[i] == tmp2)
            cnt2++;
        else if (cnt1 == 0) {
            tmp1 = nums[i];
            cnt1 = 1;
        } else if (cnt2 == 0) {
            tmp2 = nums[i];
            cnt2 = 1;
        } else {
            cnt1--;
            cnt2--;
        }
    }
    ans.push_back(tmp1);
    ans.push_back(tmp2);
    return ans;
}
```
### 问题三：数组中有N个数，只有一个数严格超过1/3，求这个数
与问题二基本相同，在得到两个数之后，我们取出现次数更多的那个数。代码略

### 问题四：如果数组中存在且只存在一个出现次数严格超过1/k的数，找到这个数。要求使用O(k)的额外空间和O(n)的时间。

同样使用抵消法：当出现K个不同的数，则相互抵消，最后取出现次数最大的那个数。注意：超过1/3我们使用2个候选值，则超过1/K我们使用K-1个候选值。

code(假设K=4):
```
int majorityElement4(vector<int>& nums) 
{
    int n = nums.size();
    int cnt1=1,cnt2=1,cnt3=1;
    int tmp1=nums[0],tmp2=nums[0],tmp3=nums[0];
    for (int i = 1; i < n; i++) {
        if (nums[i] == tmp1)
            cnt1++;
        else if (nums[i] == tmp2)
            cnt2++;
        else if(nums[i]==tmp3)
            cnt3++;
        else if (cnt1 == 0) {
            tmp1 = nums[i];
            cnt1 = 1;
        } else if (cnt2 == 0) {
            tmp2 = nums[i];
            cnt2 = 1;
        }else if (cnt2 == 0) {
            tmp2 = nums[i];
            cnt2 = 1;
        }else {
            cnt1--;
            cnt2--;
            cnt3--;
        }
    }
    int count1=0,count2=0,count3=0;
    for(int i=0;i<n;i++){
        if(nums[i]==tmp1)
            count1++;
        else if(nums[i]==tmp2)
            count2++;
        else if(nums[i]==tmp3)
            count3++;
    }
    int c = max(count1,max(count2,count3));
    map<int, int> mymap;
    mymap.insert(pair<int, int>(count1, tmp1));
    mymap.insert(pair<int, int>(count2, tmp2));
    mymap.insert(pair<int, int>(count3, tmp3));
    map<int, int>::iterator  iter = mymap.find(c);
    return iter->second;
}
```
问题五：
判断主元素是否存在实际上可以使用随机算法，这里我们使用的是蒙特卡洛随机算法。
蒙特卡洛算法能够返回一个准确值，但是没有办法判断它正确与否，因此采用增加抽样次数的方式，来降低错误率，使得返回错误值的概率降至可见小。设p是一个实数，且1/2<p<1。如果一个蒙特卡罗算法对于问题的任一实例得到正确解的概率不小于p，则称该蒙特卡罗算法是p正确的，且称p-1/2是该算法的优势。
在本实例中，主元素出现的次数超过n/2，因此我们随意在数组中随机抽样（随机抽取一个数），如果主元素存在则抽到主元素的概率大于1/2，这就构成了蒙特卡洛优势。当我们随机抽样次数增加以后，判断失误的概率就会变得越来越小。

code
bool MajorityMC(vector<int>& nums, double p)
{
    int k = ceil(log(1/p) / log((float)2));
    for(int i=1; i<=k; i++){
        if(Majority(nums))
            return true
    }
    return false;
}

bool Majority(nums){
    srand((unsigned)time(NULL));
    int n = nums.size();
    int i = rand()%n;    //[0,n)
    int x = nums[i];
    int acount = 0;
    for(int j=0; j<n; j++){
        if(nums[j] == x)
            acount++;
    }
    return (acount>n/2);
}
注意：有K次判断的机会，最后判断错误的概率小于p。(p是我们提供的可以接收的错误概率）
算法majorityMC所需的计算时间显然是O(nlog(1/p))。

总结
在常见主元素问题中，我们主要使用的是抵消法，抵消法的实质其实是找到数组中出现次数最多的一个数。在判断主元素是否存在时，使用了随机算法，随机算法我们将会单独再次讲述。

