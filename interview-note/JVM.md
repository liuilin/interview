# JVM

**栈帧（Stack Frame）：**

- 局部变量表

  定义为一个数字数组，主要用于存储方法参数和定义在方法体内的局部变量，局部变量里面包括了 8 种基本数据类型和对象的引用地址，由于局部变量表是建立在线程的栈上，是线程的私有数据，因此不存在数据安全问题。

  > 局部变量表的最大容量在编译期就确定下来了，用字节码看是保存在方法的 Code 属性的 Maximum local variables 中，在方法运行期间是不会改变局部变量表的大小的。
  >
  > 局部变量表中的变量只在当前方法调用中有效，当方法调用结束后会随着栈帧的销毁而销毁。

  ```java
  public static void main(String[] args) {
      LocalVariablesTest test = new LocalVariablesTest();
      int num = 10;
      test.test1();
  }
  private void test1() { }
  ```

  > IDEA 插件 Jclasslib 或 javap -c 命令都能查看：
  >
  > Ljava：L 是指引用类型变量

  ![局部变量表字节码](https://cdn.jsdelivr.net/gh/liuilin/interview@latest/interview_note/img/局部变量表字节码.jpg)

  **Slot 的理解**

  局部变量表的单位是一个个 Slot，JVM 会为局部变量中的每一个 Slot 都分配一个访问索引，通过索引即可访问到对应的局部变量值。

  如果当前帧是由 <font color='red'> 构造方法 </font> 或者 <font color='red'> 实例方法（非静态方法的普通方法）</font> 创建的，那么该对象引用 this 将会存放在 index 为 0 的 Slot 处，其余的参数按照参数表顺序继续排列。他们两个方法可以用是因为他们对应的局部变量表中是有变量声明的，而静态方法则会编译出错。

  > double、long 会占据两个 Slot，相当于字节码里面的两个 Index

  局部变量表中的变量也是重要的垃圾回收根节点，只要被局部变量表中直接或间接引用的对象都不会被回收。如果局部变量表的变量不存在了，那么指针也就不存在了，垃圾就会被回收

  ![JVM 栈的局部变量表 Slot 理解](https://cdn.jsdelivr.net/gh/liuilin/interview@latest/interview_note/img/JVM 栈的局部变量表 Slot 理解.jpg)

- 操作数栈 OperandStack

  主要用于保存计算过程的中间结果，同时作为计算过程中变量临时的存储空间

  > 操作数栈的最大容量在编译期就确定下来了，用字节码看是保存在方法的 Code 属性的 Maximum stack size 中（相当于类里面有几个方法）
  >
  > 如果被调用的方法带有返回值的话，其返回值将会被压入当前栈帧的操作数栈中，并更新 PC 寄存器中下一条需要执行的字节码指令。
  >
  > 代码追踪（执行流程）：[JVM/1_内存与垃圾回收篇/5_虚拟机栈 · 陌溪/LearningNotes - 码云 - 开源中国](https://gitee.com/moxi159753/LearningNotes/tree/master/JVM/1_内存与垃圾回收篇/5_虚拟机栈#代码追踪)

- 动态链接 Dynamic Linking（指向运行时常量池的方法引用）

  字节码中，所有的变量和方法引用都会作为符号引用保存在常量池，常量池运行以后就在方法区了（运行时加载进发放区的所以也叫运行时常量池）

  作用：将这些符号引用转换为调用方法的直接引用

  > 为什么需要动态链接？为什么需要指向运行时常量池的方法引用
  >
  > 因为这样就在不同的方法里，都可以共享的调用同一份常量或者方法引用。这样就只需要存储一份，节省了空间
  >
  > 为什么需要运行时常量池？
  >
  > 常量池提供了一些符号和常量，便于指令识别。在不同的方法里都有可能调用同一份数据（两个方法调用同一个 num -> int num = 1;），没必要各自拥有一份，只需要放入一份到运行时常量池大家去引用即可

  **方法的调用：**

  ![方法调用](https://cdn.jsdelivr.net/gh/liuilin/interview@latest/interview_note/img/方法调用.jpg)

  在 JVM 中，将符号引用转换为调用方法的直接引用与方法的绑定机制相关。methodB 调用 methodA 时，字节码体现就是符号引用 `#6` 会去对应的常量池找到直接引用 `CONSTANT_Methodref_info` ，找到对应的方法

  虚方法：编译期已经确定，运行期不可变的叫虚方法。有静态方法、私有方法、final 方法、实例构造器、父类方法

  非虚方法：除虚方法外都为非虚方法，比如多态，不确定运行哪个子类方法

  普通调用指令

  - invokestatic：调用静态方法，解析阶段确定唯一方法版本
  - invokespecial：调用 `<init>` 方法、私有及父类方法，解析阶段确定唯一方法版本
  - invokevirtual：调用所有虚方法
  - invokeinterface：调用接口方法

  动态调用指令：

  - invokedynamic：动态解析出需要调用的方法，然后执行

  > 对应的打印如下字节码

  ![方法调用的虚方法与非虚方法字节码](https://cdn.jsdelivr.net/gh/liuilin/interview@latest/interview_note/img/方法调用的虚方法与非虚方法字节码.jpg)

  ```java
  public class Son extends Father {
  
      public Son() {
          super();
      }
  
      public Son(int age) {
          this();
      }
  
      // 不是重写的父类的静态方法，因为静态方法不能被重写
      public static void showStatic(String str) {
          System.out.println("son " + str);
      }
  
      private void showPrivate(String str) {
          System.out.println("son private " + str);
      }
  
      public void show() {
          // =========================== 非虚方法 ===========================
          showStatic("good"); // invokestatic
          super.showStatic("nice"); // invokestatic
          showPrivate("Kimochi"); // invokestatic
          super.showNormalMethod(); // invokestatic
          showFinal(); // 虽然显示的是 invokevirtual，但因为被 final 修饰，不能被子类重写，所以也是非虚方法
          // =========================== 虚方法 ===========================
          showNormalMethod(); // invokevirtual，子类会重写父类方法，编译期确定不下来
          info(); // invokevirtual
  
          MethodInterface in = null;
          in.methodA(); // invokeinterface 要想运行成功，需要子类实现方法，重写时又不知子类是谁。所以表现为虚方法
      }
  
      private void info() { }
  
      public static void main(String[] args) {
          Son son = new Son();
          son.show();
      }
  }
  
  class Father {
  
      public Father() {
          System.out.println("father 的空参构造");
      }
  
      public static void showStatic(String str) {
          System.out.println("father " + str);
      }
  
      public final void showFinal() {
          System.out.println("father show final");
      }
  
      public void showNormalMethod() {
          System.out.println("father normal method");
      }
  }
  
  interface MethodInterface {
      void methodA();
  }
  ```

  函数式接口创建对象为 invokedynamic，创建的对象是谁的确定不了，是根据等号右边的值来确定的，有点类似于 Python 这样的动态语言，info = 13，根据 13 来确定是 int 类型

  ```java
  Func func = s -> { // invokedynamic
      return true;
  }
  ```

  **虚方法表：**

  JVM 为了提高性能呢，它在类的方法区建立一个虚方法表 virtual method table（非虚方法不会出现在表中）来实现使用索引表来代替查找。这样在子类重写之后，就不用向上一层层判断有没有改方法，而是直接用自己的方法

  虚方法表会在类加载的 `链接阶段` 被创建并开始初始化，类的变量初始值准备完成之后，JVM 会把该类的方法表也初始化完毕。

  如果类中重写了方法，那么调用时会在自己重写了的虚方法表中查找，如果没有才到 Object 的虚方法表中查找

  <img src="https://cdn.jsdelivr.net/gh/liuilin/interview@latest/interview_note/img/%E6%96%B9%E6%B3%95%E8%B0%83%E7%94%A8%E4%B9%8B%E8%99%9A%E6%96%B9%E6%B3%95%E8%A1%A8.jpg" alt="方法调用之虚方法表" style="zoom: 33%;" />

  ```java
  public class CockerSpaniel extends Dog implements Friendly{
      @Override
      protected void finalize() { }
      public void eat(){ }
      @Override
      public void sayHello() { }
      @Override
      public void sayGoodbye() { }
      public static void main(String[] args) {
          System.out.println(new CockerSpaniel().toString());
      }
  }
  class Dog {
      public void sayHello(){ }
  
      @Override
      public String toString() {
          return "Dog";
      }
  }
  interface Friendly {
      void sayHello();
      void sayGoodbye();
  }
  ```

  > 比如没有重写的指向 Object，重写了的指向自己，比如自定义对象 Son，它没重写 toString()，父对象 Father 也没有重写 toString()，那么调用 Son.toString() 时就会调用 Object 的 toString() 

- 方法返回地址 Return Address

  存放调用该方法的 PC 寄存器的值。一个方法的结束，有两种方式：1. 正常执行完成 2. 出现未处理的异常，非正常退出，无论通过哪种方式退出，在方法退出后都返回到该方法被调用的位置

  i. 方法正常退出时，<font color='red'>调用者的程序计数器的值会作为返回地址，即调用该方法指令的下一条指令地址</font>

  ii. 方法异常退出时，不会给调用者返回值，返回地址要通过异常表来确定，栈帧不会保存这部分信息