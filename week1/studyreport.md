# 关于vi JAVA Ant Junit的学习报告

## vi/vim  编辑器的使用
**1. vi编辑器简介**
vi（Visual Interface），即可视化接口是所有Unix及Linux系统下标准的编辑器，相当于windows系统中的记事本  
vim（vi iMprove），vi的增强版，比vi更容易使用。vi的命令几乎全部都可以在vim上使用。

**2. vi工作模式**
vi一共有三种工作模式，即命令模式、文本输入模式、末行模式。  

·命令模式即为默认模式，进入vi便为命令模式，并且任何时候按esc也将进入命令模式，可以进行复制行、删除行等操作（输入的命令操作并不会显示）  
·输入模式，执行i a o s等操作即可进入输入模式，输入的字符将被当作文件内容保存显示在屏幕上  
·末行模式，执行：键进入末行模式，屏幕最后一行显示“：”等待输入命令。  

**3. 基本操作**

**3.1 进入编辑模式**  


命令 | 含义
:-- | :--:
i & I | i在光标前插入，I在行首插入
a & A | a在光标后插入，A在行末插入
o & O | o在光标所在行下一行插入，O在光标所在行上一行插入

**3.2 移动光标**  


命令|含义
:--:|:--:
h|光标向左移动
j|光标向下移动
k|光标向上移动
l|光标向右移动
H、M、L|光标移动到到可见屏幕第一行(H)、中间行(M)、最后一行(L)
^和$|^移动到行首，$移动到行末
G和gg|G文档最后一行，gg文档第一行
ctrl+f、ctrl+b|向前翻屏、向后翻屏
ctrl+d、ctrl+u|向前半屛、向后半屛
{ 和 }|{向上移动一段，}向后移动一段
w和b|向前移动一个单词，向后移动一个单词

**3.3 删除命令**

命令|含义
:--:|:--:
X和x|x删除光标后一个字符，X删除光标前一个字符,包含光标位置字符
dd和 n dd|dd删除所在行，5 dd删除指定行数
d0和D|d0删除光标前本行所有内容，D删除光标后本行所有内容，包含光标位置字符
dw|删除光标所在位置的字，包含光表所在位置字符

**3.4 撤销命令**
 

命令|含义
:--:|:--:
u|一步一步撤销
ctrl + r|反撤销

**3.5 重复命令**


命令|含义
:--:|:--:
.|重复执行上一次操作的命令

**3.6 复制粘贴**

命令|含义
:--:|:--:
yy|和 n yy 和y$ y^ yy复制当前行，5 yy复制5行
p|在光标所在位置向下新开一行粘贴

**3.7 选择文本**

命令|含义
:--:|:--:
v 和 V|v选择单个字符，V选择整行
<<和>>|选择文本之后，向左缩进，向右缩进

**3.8 查找替换**

命令|含义
:--:|:--:
命令模式下，r和R|换当前字符，R替换光标后的字符
末行模式下，/ + str|n查找下一个，N查找前一个
末行模式下,%s/abc/123/g|将文件中所有abc替换为123
末行模式下,1, 10s/abc/123/g|将第一行至第10行之间的abc替换成123


## JAVA语言

由于大二曾经选修Java的课程，对于Java也算是相对了解。这里便直接便直接运行helloworld   
  
hello world 代码:  

![hello world](https://github.com/saltydong/intermediate_training/blob/master/pics/helloworld.JPG)  

运行结果:  

![result](https://github.com/saltydong/intermediate_training/blob/master/pics/%E7%BB%93%E6%9E%9C.JPG)

  
## Ant

Apache Ant,是一个将软件编译、测试、部署等步骤联系在一起加以自动化的一个工具，大多用于Java环境中的软件开发。由Apache软件基金会所提供。  
进行的ant安装和环境配置 ，首先需要从官网下载压缩包  
解压到相关文件夹后进行环境变量配置  
需要添加的有三个：ANT_HOME,CLASSPATH,PATH  
![环境变量](https://github.com/saltydong/intermediate_training/blob/master/pics/%E7%8E%AF%E5%A2%83%E5%8F%98%E9%87%8F.JPG)  
   
![ant配置成功](https://github.com/saltydong/intermediate_training/blob/master/pics/ant%E9%85%8D%E7%BD%AE%E6%88%90%E5%8A%9F.JPG)  
   
接下来主要进行build.xml的编写和对hello world的自动编译  

编写build文件，将其放到源代码的目录下，然后通过cmd到相关目录，执行ant操作，即可自动编译运行helloworld文件  
![ant运行结果](https://github.com/saltydong/intermediate_training/blob/master/pics/ant%E8%87%AA%E5%8A%A8%E7%BC%96%E8%AF%91helloworld.JPG)  


## JUnit  
JUnit是一个Java语言的单元测试框架。它由Kent Beck和Erich Gamma建立，逐渐成为源于Kent Beck的sUnit的xUnit家族中最为成功的一个。 JUnit有它自己的JUnit扩展生态圈。多数Java的开发环境都已经集成了JUnit作为单元测试的工具。  
JUnit是由 Erich Gamma 和 Kent Beck 编写的一个回归测试框架（regression testing framework）。Junit测试是程序员测试，即所谓白盒测试，因为程序员知道被测试的软件如何（How）完成功能和完成什么样（What）的功能。Junit是一套框架，继承TestCase类，就可以用Junit进行自动测试了。  
   
这里需要对helloworld进行测试，首先需要建立一个testhelloworld的文件。  
然后进行JUnit的run junit

![junit](https://github.com/saltydong/intermediate_training/blob/master/pics/JUnit%E3%80%81.JPG)  


