**aaaaaa**  
_bbb_  
ccccc
**asdfasd**
#aaaaa 
~~aaa~~    

```mermaid
graph
A[这是一个实例] ---> B[这是第二个实例]
B -- Yes --> C[TEST]
B -- No --> B
C -- 参见 -->A
```
```java
public class A{
    
}
```
```flow
st=>start: Start
i=>inputoutput: 输入年份n
cond1=>condition: n能否被4整除？
cond2=>condition: n能否被100整除？
cond3=>condition: n能否被400整除？
o1=>inputoutput: 输出非闰年
o2=>inputoutput: 输出非闰年
o3=>inputoutput: 输出闰年
o4=>inputoutput: 输出闰年
e=>end
st->i->cond1
cond1(no)->o1->e
cond1(yes)->cond2
cond2(no)->o3->e
cond2(yes)->cond3
cond3(yes)->o2->e
cond3(no)->o4->e
```
![这是我的学位证](E:\个人资料\学位证.jpg "学位证")