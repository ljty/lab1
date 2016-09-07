package com.ifox.android.lab.video.Activity;
/**
 * 搜索结果
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.ifox.android.lab.R;

public class SearchVideoListActivity extends AppCompatActivity {
    private com.ifox.android.lab.video.Adapter.JsonAdapter JsonAdapter;
   // private ListView listview;
    //private View1 loadview;
  //  private List<Video> list2;
   // private Handler handler = new Handler();
   // ListItem2 listItem = new ListItem2();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchvideolist);
        //JsonAdapter = new JsonAdapter(this);
        //initView();//初始化组件；
        //sendContent();
       // itemClickEvent();//列表项单击事件
   // }

   // public void initView() {
       // listview = (ListView) findViewById(R.id.videolist1);
        //listview.addFooterView(loadview);

    }

    //public void sendContent() {
       // String url1 = "http://169.254.188.96:8080/Web/Jsontest";
       // Intent it = super.getIntent();
       // String searchContent = it.getStringExtra("searchContent");
       // new HttpThread1(url1, listItem, searchContent, listview, handler, JsonAdapter).start();
       // list2 = listItem.getList();

   // }

    //class ListItem2 {
       // private List<Video> list;

       // public List<Video> getList() {
           // return list;
      //  }

        //public void setList(List<Video> list) {
           // this.list = list;
       //}
   // }

    //public void itemClickEvent() {
        //listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           // @Override
          //  public void onItemClick(AdapterView<?> parent, View1 view, int position, long id) {
            //    Intent it = new Intent(SearchVideoListActivity.this, VideoShowActivity.class);
              //  it.putExtra("videocontent", list2.get(position).getVideoContent());
               // SearchVideoListActivity.this.startActivity(it);
           // }
      //  });
   // }

}



