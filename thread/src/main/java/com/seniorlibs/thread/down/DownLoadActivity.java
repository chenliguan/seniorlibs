package com.seniorlibs.thread.down;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Author: 陈李冠
 * Time: 2021/4/15
 * Description:
 */
//public class DownLoadActivity extends AppCompatActivity {
//
//    @Bind(R.id.start_button)
//    Button startButton;
//    @Bind(R.id.pause_button)
//    Button pauseButton;
//    @Bind(R.id.continue_button)
//    Button continueButton;
//    @Bind(R.id.progress_bar)
//    ProgressBar progressBar;
//    @Bind(R.id.have_downloaded)
//    TextView haveDownloaded;
//    private DownLoadFile downLoadFile;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_down_load);
//        ButterKnife.bind(this);
//        final File file = new File(Environment.getExternalStorageDirectory(), "911Mothers.mp4");
//        downLoadFile = new DownLoadFile(this,
//                "http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4",
//                file.getAbsolutePath(),
//                4,
//                new DownLoadFile.DownLoadListener() {
//                    @Override
//                    public void getProgress(int progress) {
//                        progressBar.setProgress(progress);
//                        haveDownloaded.setText("已下载：" + progress + "%");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Toast.makeText(DownLoadActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        Uri data = Uri.parse(file.getAbsolutePath());
//                        intent.setDataAndType(data, "video/*");
//                        startActivity(intent);
//                    }
//
//                    @Override
//                    public void onFailure() {
//                        Toast.makeText(DownLoadActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
//                    }
//                });
//        progressBar.setMax(100);
//    }
//
//    @OnClick({R.id.start_button, R.id.pause_button, R.id.continue_button})
//    public void onControlButtonChild(View view) {
//        switch (view.getId()) {
//            case R.id.start_button:
//                downLoadFile.downLoad();
//                break;
//            case R.id.pause_button:
//                downLoadFile.onPause();
//                break;
//            case R.id.continue_button:
//                downLoadFile.onStart();
//                break;
//            default:
//                break;
//        }
//    }
//}
