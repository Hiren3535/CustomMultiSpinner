#Custom Multi Selection Spinner

1) Add this to Project Level Gradle (build.gradle(Module: app))
      ```java
        allprojects {
            repositories {
                  ...
                  maven { url 'https://jitpack.io' }
            }
        }
2)  In Same file

      ```java
        dependencies 
        {
                compile 'com.github.Hiren3535:CustomMultiSpinner:v1.0'
        }
    
3)  In your Activity.java

    ```java
    public class MainActivity extends AppCompatActivity {

    ArrayList<String> items = new ArrayList<String>();
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items.clear();
        items.add("Assamese");
        items.add("Bengali");
        items.add("English");
        items.add("Gujarati");
        items.add("Hindi");
        items.add("Kannada");
        items.add("Konkani");
        items.add("Malayalam");
        items.add("Marathi");
        items.add("Marwari");
        items.add("Odia");
        items.add("Punjabi");
        items.add("Sindhi");
        items.add("Tamil");
        items.add("Telugu");
        items.add("Urdu");
        items.add("Other");

        tv =(TextView) findViewById(R.id.text);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomAdapter adapter = new CustomAdapter(MainActivity.this,"Select State",items,tv,4);
                //CustomAdapter.ImageAdapter adapter= new CustomAdapter.ImageAdapter(MainActivity.this,items,"Select State",tv);
            }
        });
        }
