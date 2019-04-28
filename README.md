#### lint 检测代码

##### android studio自带的检测工具

    此处不再过多介绍
    

#### 自定义lint

    1、创建一个java项目，里面包含lint检测的规则（参考lintrules module），后面需要打成jar包
    
    2、由于项目中不能直接识别jar包，因此需要创建一个android module，也就是需要打成aar包（参考lintaar）
    
    3、将生产的jar包放入aar中的libs下并add as a module操作，将lintaar引入到项目中即可，就会自动生效
    
    
    
    
#### 相关API

    Detector.UastScanner对应于以前的Detector.JavaPsiScanner和更久以前的JavaScanner
    用于扫描Java代码，必须成对出现（满足条件 -> visitor）
    
    UastScanner内部包含的方法：
    1.getApplicableUastTypes
    此方法返回需要检查的AST节点的类型，类型匹配的UElement将会被createUastHandler(createJavaVisitor)创建的UElementHandler(Visitor)检查。
    
    2.createUastHandler
    创建一个UastHandler来检查需要检查的UElement，对应于getApplicableUastTypes
    
    3.getApplicableMethodNames
    返回你所需要检查的方法名称列表，或者返回null，相匹配的方法将通过visitMethod方法被检查
    
    4.visitMethod
    检查与getApplicableMethodNames相匹配的方法
    
    5.getApplicableConstructorTypes
    返回需要检查的构造函数类型列表，类型匹配的方法将通过visitConstructor被检查
    
    6.visitConstructor
    检查与getApplicableConstructorTypes相匹配的构造方法
    
    7.getApplicableReferenceNames
    返回需要检查的引用路径名，匹配的引用将通过visitReference被检查
    
    8.visitReference
    检查与getApplicableReferenceNames匹配的引用
    
    9.appliesToResourceRefs
    返回需要检查的资源引用，匹配的引用将通过visitResourceReference被检查
    
    10.visitResourceReference
    检查与appliesToResourceRefs匹配的资源引用
    
    11.applicableSuperClasses
    返回需要检查的父类名列表，此处需要类的全路径名
    
    11.visitClass
    检查applicableSuperClasses返回的类
