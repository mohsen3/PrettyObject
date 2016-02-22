# PrettyObject

PrettyObject is a Java library that converts Java objects into human readable strings.
PrettyObject heavily uses Java reflection API to dive deep into Java objects and extract every bit of information out of them.
PrettyObject is inspired by [Awesome Print](https://github.com/michaeldv/awesome_print).

# Build
Run the following command

```
./gradlew build
```
on Unix like machines or

```
gradle.bat build
```
on Windows.
This will generate a jar file inside `build/libs` directory.
You should have JDK (>=1.5) installed.
The script downloads all the requirements, including `gradle`, and build the library.

# Example

An instance of the following class:

```java
class Example {
    private String str = "string-field";
    Object nil = null;
    int i = 10;
    List<Object> list;
    Map<Object, Object> map;

    Example() {
        ArrayList<Object> arrayList = new ArrayList<Object>();
        arrayList.add("string-list-item");
        arrayList.add(123);
        arrayList.add(new int[]{10, 20, 30});
        this.list = arrayList;

        HashMap<Object, Object> map = new HashMap<Object, Object>();
        map.put(new Point(3, 4), 5);
        this.map = map;
    }
}
```

is printed as:

```
m3.prettyobject.examples.Example {
  str => "string-field",
  nil => null,
  i => 10,
  list => ArrayList {
    "string-list-item",
    123,
    [
      10,
      20,
      30
    ]
  },
  map => class java.util.HashMap {
    java.awt.Point {
      x => 3,
      y => 4
    } => 5
  }
}
```

See `src/examples` directory for more examples.

# Warning

The generated output can be huge for some ordinary objects.
For instance,
pretty printing a `new java.swing.JButton()`
results in about 173MB of output string!
See the `CanBeHuge` example in the examples directory.





