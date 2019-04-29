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


#### 使用

    如果是一个项目的话，可以直接引用lintaar module
    如果是团队协作的话，可以将lintaar module上传到远程仓库中管理


#### 相关api（根据lint-checks-26.4.0.jar中已经实现的lint来分析出，全靠猜！！！）

      XmlScanner：xml扫描
      UastScanner：.java源文件扫描
      ClassScanner：.class文件扫描
      FileScanner：file扫描
      GradleScanner：gradle文件扫描
      ResourceFolderScanner：资源文件扫描
      SourceCodeScanner：源文件扫描
      OtherFileScanner：其他文件扫描

      // 一般情况下使用UastScanner和XmlScanner的居多
      // 对应的两个工具类
      UastUtils uastUtils;
      XmlUtils xmlUtils;

      // 扫描方法
      @Override
      public List<String> getApplicableMethodNames() {
          return Arrays.asList("setHostnameVerifier", "setDefaultHostnameVerifier");
      }
     // 对应于
     public void visitMethodCall(@NotNull JavaContext context, @NotNull UCallExpression node, @NotNull PsiMethod method) {
      JavaContext context:Java当前扫描的上下文
      JavaEvaluator evaluator = context.getEvaluator();
      evaluator.isMemberInClass(PsiMethod method, "类名"); // 判断当前方法是否是这个类里面的
      evaluator.getParameterCount(PsiMethod method); // 获取这个方法的参数个数

      UCallExpression node; // 返回的节点类型
      List<UExpression> valueArguments = node.getValueArguments(); // 返回方法参数的集合
      node.getValueArgumentCount(); // 获取方法参数的个数
      node.getReturnType(); // 获取此方法的返回类型
      Object evaluate = ConstantEvaluator.evaluate(context, valueArguments.get(1)); // 获取当前参数的类型
      if (evaluate instanceof Integer) { // 判断当前参数是否是int类型

      }
    }

    // 构造器
    @Nullable
      @SuppressWarnings("javadoc")
      public List<String> getApplicableConstructorTypes() { // 根据某个类来获取构造方法
          return Collections.singletonList("org.apache.http.conn.ssl.AllowAllHostnameVerifier");
      }
      // 对应于
      @Override
      public void visitConstructor(@NonNull JavaContext context, @NonNull UCallExpression node, @NonNull PsiMethod constructor) {
          // 一般情况下返回的已经是要扫描的类的构造方法使用的节点
      }

      // 引用（导包路径）
      @Nullable
      @Override
      public List<String> getApplicableReferenceNames() {
          return Arrays.asList("SHOW_AS_ACTION_IF_ROOM", "引用路径");
      }
      // 对应于
      @Override
      public void visitReference(JavaContext context, UReferenceExpression reference, PsiElement resolved) {

      }

      // 继承关系
      public List<String> applicableSuperClasses() {
          return Arrays.asList("类名和路径");
      }
      // 对应于
      @Override
      public void visitClass(@NonNull JavaContext context, @NonNull UClass declaration) {
          context.getEvaluator().isAbstract(declaration); // 判断是否是抽象方法

      }

      // 扫描Uast类型节点，根据设置的UElement列表来进行扫描
      @Override
      public List<Class<? extends UElement>> getApplicableUastTypes() {
          List<Class<? extends UElement>> types = new ArrayList<>(2);
          types.add(UAnnotation.class);
          types.add(USwitchExpression.class);
          return types;
      }
      // 对应于
      @Nullable
      @Override
      public UElementHandler createUastHandler(@NonNull JavaContext context) {
          return new MyUElementHandler(context); // 创建自己的UElementHandler来分析扫描的内容
      }
      // 自定义UElementHandler如下，内部包含的方法需要根据getApplicableUastTypes传入的节点进行匹配
      private static class MyUElementHandler extends UElementHandler {
          private JavaContext context;

          private MyUElementHandler(JavaContext context) {
              this.context = context;
          }

          @Override
          public void visitMethod(@NotNull UMethod node) {
              super.visitMethod(node);
          }

          @Override
          public void visitVariable(@NotNull UVariable node) {
              super.visitVariable(node);
          }

          @Override
          public void visitField(@NotNull UField node) {
              super.visitField(node);
          }

          @Override
          public void visitAnnotation(@NotNull UAnnotation node) {
              super.visitAnnotation(node);
          }

          @Override
          public void visitClass(@NotNull UClass node) {
              super.visitClass(node);
          }

          @Override
          public void visitElement(@NotNull UElement node) {
              super.visitElement(node);
          }

          @Override
          public void visitFile(@NotNull UFile node) {
              super.visitFile(node);
          }
          // ...
      }

    =====xml======
    @Override
      public Collection<String> getApplicableElements() {
          return Arrays.asList("IMAGE_BUTTON", "布局控件的名字");
      }
      // 对应于
      @Override
      public void visitElement(@NonNull XmlContext context, @NonNull Element element) {
          element.hasAttributeNS(ANDROID_URI, "属性名"); // 布局控件是否有某一个属性
          Attr attributeNode = element.getAttributeNS(ANDROID_URI, ATTR_IMPORTANT_FOR_ACCESSIBILITY); // 获取当前属性
          String attribute = attributeNode.getValue(); // 获取属性的值

      }

      @Override
      @Nullable
      public Collection<String> getApplicableAttributes() { // 根据传入的属性名来获取对应的属性
          return Collections.singletonList(ATTR_CONTENT_DESCRIPTION);
      }
      // 对应于
      @Override
      public void visitAttribute(@NonNull XmlContext context, @NonNull Attr attribute) {
          Element element = attribute.getOwnerElement(); // 获取当前属性的控件
          element.hasAttributeNS(ANDROID_URI, "属性名"); // 当前控件是否有某个属性

      }
