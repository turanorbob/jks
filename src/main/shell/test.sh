#!/bin/sh

# echo 输出内容

echo "hello world!";

# 变量,变量定义不能有空格

your_name="liaojian";

echo $your_name;

# 通过语句变量赋值

ls_ret=`ls -l`
echo $ls_ret

# for使用
for skill in Ada Coffe Action Java; do
    echo "I am good at ${skill}Script"
done

# 只读变量
my_url="http://www.baidu.com"
readonly my_url

echo $my_url

# 删除变量(只读变量不能unset)
unset your_name

# 单双引号的使用
d_str='AA'
s_str="${d_str} BB"
echo $s_str

# 拼接字符串
con_str=${s_str}" CC"
echo $con_str

# 获取字符串长度
echo ${#con_str}

# 提取子字符串
echo ${con_str:0:5}

# 查找子字符串
#echo `expr index "$con_str" A`

# 数组 ([@]获取数组所有元素)
array_name=(value0 value1 value2 value3)
for array in ${array_name[@]}; do
    echo $array
done

# 获得数组的个数
echo ${#array_name[*]}

# 两个数相加
val=`expr 2 + 2`
echo "2 + 2 = "$val

# 两个数相减
val=`expr 4 - 2`
echo "4 - 2 = "$val

# 两个数相乘, *需要转义
val=`expr 2 \* 2`
echo "2 * 2 = "$val

# 两个数相除
val=`expr 4 / 2`
echo "4 / 2 = "$val

# 取余
val=`expr 4 % 2`
echo "4 % 2 = "$val

# 赋值
val2=0
echo $val2

# 相等比较,[]必须有空格
if [ $val == $val2 ]
then
    echo "相等"
fi

# 不等
val2=0
if [ $val != $val2 ]
then
    echo "不相等"
else
    echo "相等"
fi

# 关系运算符
a=10
b=20
if [ $a -eq $b ]
then
    echo "a 等于 b"
else
    echo "a 不等于 b"
fi


if [ $a -ne $b ]
then
    echo "a 不等于 b"
else
    echo "a 等于 b"
fi

if [ $a -gt $b ]
then
    echo "a 大于 b"
else
    echo "a 不大于 b"
fi

if [ $a -lt $b ]
then
    echo "a 小于 b"
else
    echo "a 不小于 b"
fi

if [ $a -ge $b ]
then
    echo "a 大于等于 b"
else
    echo "a 不大于等于 b"
fi

if [ $a -le $b ]
then
    echo "a 小于等于 b"
else
    echo "a 不小于等于 b"
fi

# 布尔运算符
if [ !false ]
then
    echo "!false"
fi

if [ $a == $b -o $a -lt $b ]
then
    echo "a==b or a<b"
fi

if [ $a == $b -a $a -lt $b ]
then
    echo "a==b and a<b:true"
else
    echo "a==b and a<b:false"
fi

a=0
b=20
# 逻辑运算符
if [[ $a -gt 10 && $b ]]
then
    echo "$a -gt 10 && $b : true"
else
    echo "$a -gt 10 && $b : false"
fi

if [[ $a -gt 10 || $b ]]
then
    echo "$a -gt 10 || $b : true"
else
    echo "$a -gt 10 || $b : false"
fi

# 字符串运算符
a="abc"
b="efg"
if [ $a = $b ]
then
    echo "$a = $b : true"
else
    echo "$a = $b : false"
fi

if [ $a != $b ]
then
    echo "$a != $b : true"
else
    echo "$a != $b : false"
fi

if [ -z $a ]
then
    echo "-z $a : true"
else
    echo "-z $a : false"
fi

if [ -n $a ]
then
    echo "-n $a : true"
else
    echo "-n $a : false"
fi

if [ $a ]
then
    echo "$a : true"
else
    echo "$a : false"
fi

# 文件测试运算符
file="/Users/liaojian/work/liaojian/jks/src/main/shell/test.sh"
if [ -r $file ]
then
    echo "文件可读"
else
    echo "文件不可读"
fi

if [ -w $file ]
then
    echo "文件可写"
else
    echo "文件不可写"
fi

if [ -x $file ]
then
    echo "文件可执行"
else
    echo "文件不可执行"
fi

if [ -f $file ]
then
    echo "文件为普通文件"
else
    echo "文件不为普通文件"
fi

if [ -d $file ]
then
    echo "文件是个目录"
else
    echo "文件不是个目录"
fi

if [ -s $file ]
then
    echo "文件不为空"
else
    echo "文件为空"
fi

if [ -e $file ]
then
    echo "文件存在"
else
    echo "文件不存在"
fi

# 读入字符串
#read name
#echo "$name test"

# 开启转义 echo -e
echo "hell \n"
echo "world"
# 显示不换行 -e
echo "OK! \c"
echo "test"
# 显示结果重定向 >
echo "hello world" > /Users/liaojian/work/liaojian/jks/src/main/shell/log

echo `date`

# printf format-string [arguments...]
printf "hello \n world\n"

# - 左对齐 没有-则右对齐, 10个字符宽,不足的显示空格,超过的显示出来
printf "%-10s %-8s %-4s\n" 姓名 性别 体重kg
printf "%-10s %-8s %-4.2f\n" 郭靖 男 66.12345

printf "%s and %d\n"
printf "\a"

# test 用于检测字符串、数字、文件
num1=100
num2=200
if test $num1 -eq $num2
then
    echo "等于"
else
    echo "不等于"
fi

demoFun(){
    echo "这是我的第一个demo函数"
}

demoFun

echo $?





