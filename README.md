# Lox-Interpreter
A lox interpreter based on Java

# Basic Lox Grammar
```
  # 定义变量
  var a; | var a = 1;
  
  # print 函数
  print  a;
  
  # while 语句
  var a = 1;
  while (a <= 10) {
    print a;
    a = a + 1;
  }
  
  # if 语句
  # This should print "a >= 20"
  a = 30;
  
  if ( a < 20 ) {
      print "a" + " < 20.";
  } else {
      print "a" + " >= 20";
  }
  
  # function
  fun add(a, b) {
    print a + b;
  }
  
  # class
  class A {
    method() {
        print "A method";
    }
  }
  
  class B < A {
    method() {
        print "B method";
    }
    
    test() {
        super.method();
    }
  }
  
  class C < B {}
  
  C().test();
  
  // 给一个类添加成员变量
  var a = A();
  a.name = "A class";   // 直接 class.成员变量 = xxx即可
  print a.name;
  
  
```

