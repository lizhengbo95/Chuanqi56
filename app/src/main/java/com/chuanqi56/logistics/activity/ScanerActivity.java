package com.chuanqi56.logistics.activity;

import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chuanqi56.logistics.BaseActivity;
import com.chuanqi56.logistics.R;
import com.chuanqi56.logistics.entity.BarcodeSku;
import com.chuanqi56.logistics.entity.JsonResult;
import com.chuanqi56.logistics.parameters.UserParameters;
import com.chuanqi56.logistics.rxjavaretrofit.http.HttpMethods;
import com.chuanqi56.logistics.utils.CommonUtils;
import com.chuanqi56.logistics.utils.DebugLog;
import com.chuanqi56.logistics.utils.ToastUtils;
import com.chuanqi56.logistics.view.CommonTitle;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * Created by ycw on 2016/8/24.
 */
public class ScanerActivity extends BaseActivity implements QRCodeView.Delegate {

    public static final int SUCCESS = 1;
    public static final int FAIL = 2;
    @BindView(R.id.layout_scaner_title)CommonTitle commonTitle;
    @BindView(R.id.layout_scaner_edit_input_line)LinearLayout edit_input_line;
    @BindView(R.id.layout_scaner_edit)EditText inputEdit;//远单号手动输入框
    @BindView(R.id.layout_scaner_content)ZXingView mQRCodeView;
//    private OrderHttpManager orderHttpManager;
//    private MaterialDialog materialDialog;
    private final static long DURATION_TIME =2000;//弹框的提示时间

    private SoundPool mSoundPool;
    private HashMap<Integer, Integer> soundPoolMap;
    private boolean isActivityCanVisiable = false;//扫描界面是否可见的标志（true 可见 false不可见）
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_scaner_activity);
        ButterKnife.bind(this);
        initData();
//        initSoundPool();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
        mQRCodeView.showScanRect();
        mQRCodeView.startSpot();
        mQRCodeView.changeToScanBarcodeStyle();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isActivityCanVisiable = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isActivityCanVisiable = false;
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
//        EventBus.getDefault().post(new EBRefeshFragment(EBRefeshFragment.FLAG_WAIT_DELIVERY_FRAGMENT));
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String rawResult) {
        DebugLog.e("扫描结果是："+rawResult);
//        ToastUtils.showToast(rawResult);
        Toast.makeText(mContext,rawResult,Toast.LENGTH_LONG).show();
        vibrate();
        doScan(rawResult);
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        DebugLog.e("打开相机出错");
    }

    @OnClick(R.id.layout_scaner_edit_input_confirm)
    public void inputComfirmClick(View view){
        DebugLog.e("输入框输入，添加运单");
        String mailNo = inputEdit.getText().toString().trim();
        if(TextUtils.isEmpty(mailNo)){
            ToastUtils.showToast("输入框为空");
            return;
        }
//        doScan(mailNo);
    }


//    private void initSoundPool(){
//        mSoundPool = new SoundPool(1, AudioManager.STREAM_ALARM, 0);
////        mSoundPool.setOnLoadCompleteListener((soundPool, sampleId, status) -> DebugLog.e("完成"));
//        soundPoolMap = new HashMap<Integer, Integer>();
//        soundPoolMap.put(SUCCESS, mSoundPool.load(this, R.raw.success, 1));
//        soundPoolMap.put(FAIL, mSoundPool.load(this, R.raw.fail, 1));
//    }

    /**
     * 初始化数据
     */
    private void initData(){
        setContentView(R.layout.layout_scaner_activity);
        ButterKnife.bind(this);
//        materialDialog = new MaterialDialog(this);
//        orderHttpManager = new OrderHttpManager();
        initTitle();
        initScanerView();
    }

    /**
     * 初始化扫描器
     */
    private void initScanerView(){
        mQRCodeView.setDelegate(this);
    }

    /**
     * 初始化title
     */
    private void initTitle(){
        commonTitle.setTitleMessage("扫描订单");
        commonTitle.setBtnRightText("手动输入");
        commonTitle.setBtnRightOnClickListener(ufreshTitleView -> {
            DebugLog.e("手动输入被点击");
            setInputLineVisable();
        });
    }


    /**
     * 设置手动输入栏是否可见的方法(如果可见则设置为不可见，反之可见)
     */
    private void setInputLineVisable(){
        if(edit_input_line.getVisibility()== View.INVISIBLE){
//            commonTitle.setBtnRightText("取消");
            mQRCodeView.stopCamera();
            mQRCodeView.stopSpot();
            edit_input_line.setVisibility(View.VISIBLE);
        }else{
//            commonTitle.setBtnRightText("手动输入");
            mQRCodeView.startCamera();
            mQRCodeView.showScanRect();
            mQRCodeView.startSpot();
            edit_input_line.setVisibility(View.INVISIBLE);
        }
    }

    private String mailNo;
    /**
     * 处理扫描结果
     */
    private void doScan(String mailNo){
        if (TextUtils.isEmpty(mailNo)){
            return;
        }
        this.mailNo = mailNo;
        Map<String, String> map = new UserParameters().getBarcodeSku("U00017",mailNo);
        HttpMethods.getInstance().postBarcode_sku(map, new Observer<JsonResult<BarcodeSku>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {}
            @Override
            public void onComplete() {}
            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, "添加地址失败", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNext(JsonResult<BarcodeSku> jsonResult) {
                if (jsonResult.ret == 0){
                    CommonUtils.showLongToast(mContext, jsonResult.data.title);
                }else {
                    CommonUtils.showLongToast(mContext, jsonResult.msg);
                }
            }
        });
    }
//    /**
//     * 确认领取配送单
//     */
//    private void doConfirm(String mailNo){
//        if (TextUtils.isEmpty(mailNo)){
//            return;
//        }
//       showProgressDialog();
//        orderHttpManager.addOrderByScan(mailNo,true)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<JsonResult<AddOrderByScanData>>() {
//                    @Override
//                    public void onCompleted() {
//                        DebugLog.e("onCompleted");
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        DebugLog.e("onError()");
//                        doScanResult("添加失败");
//                        mSoundPool.play(soundPoolMap.get(FAIL), 1, 1, 0, 0, 1);
//                    }
//                    @Override
//                    public void onNext(JsonResult<AddOrderByScanData> addOrderByScanDataJsonResult) {
//                        DebugLog.e("onNext()");
//                        if(addOrderByScanDataJsonResult.result){
//                            mSoundPool.play(soundPoolMap.get(SUCCESS), 1, 1, 0, 0, 1);
//                            doScanResult("添加成功");
//                            if(edit_input_line.getVisibility()== View.VISIBLE){
//                                setInputLineVisable();
//                            }
//
////                            AddOrderByScanData addOrderByScanData = addOrderByScanDataJsonResult.content;
////                            goodsDialog(addOrderByScanData);
//
//
//                        }else{
//                            inputEdit.setText("");
//                            mSoundPool.play(soundPoolMap.get(FAIL), 1, 1, 0, 0, 1);
//                            doScanResult(addOrderByScanDataJsonResult.remark);
//                        }
//                    }
//
//                });
//    }
//
//    /**
//     * // （版本：V1.5.0；作者：lizhengbo95；时间：2017/5/18 16:10 ）说明：
//     * @param addOrderByScanData
//     */
//    private void goodsDialog(AddOrderByScanData addOrderByScanData) {
//
//        ArrayList<GoodData> goodDatas = new ArrayList<>();
////        //测试数据
////        for (int i =0; i < 3; i++) {
////            GoodData gd = new GoodData();
////            gd.count = 2+i;
////            gd.skuName = "茄子"+i;
////            goodDatas.add(gd);
////        }
//        //数据整合转换
//        if (addOrderByScanData.packageDetails != null ) {
//            goodDatas.addAll(addOrderByScanData.packageDetails);
//        }
//        if(addOrderByScanData.pickupDetails != null){
//            for (int i = 0; i < addOrderByScanData.pickupDetails.size(); i++) {
//                GoodData gd = new GoodData();
//                gd.skuName = addOrderByScanData.pickupDetails.get(i).skuName;
//                gd.count = addOrderByScanData.pickupDetails.get(i).planQty;
//                goodDatas.add(gd);
//            }
//        }
//
//        if (goodDatas != null && goodDatas.size() > 0) {
//            ScanGoodsListDialog dialog = new ScanGoodsListDialog(this,goodDatas);
//            dialog.setConfirmClickListener(new ScanGoodsListDialog.OnScanGoodsListDialogOkListener() {
//                @Override
//                public void onClick(ScanGoodsListDialog scanGoodsListDialog) {
////                    mSoundPool.play(soundPoolMap.get(SUCCESS), 1, 1, 0, 0, 1);
////                    doScanResult("添加成功");
////                    if(edit_input_line.getVisibility()== View.VISIBLE){
////                        setInputLineVisable();
////                    }
//
//                    doConfirm(mailNo);
//                    dialog.dismiss();
//                }
//            });
//            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                @Override
//                public void onDismiss(DialogInterface dialog) {
//                    mQRCodeView.startCamera();
//                    mQRCodeView.showScanRect();
//                    mQRCodeView.startSpot();
//                }
//            });
//            dialog.show();
//        }else {
//            ToastUtils.showToast("商品数量为0");
//        }
//    }
//
//    /**
//     * 同意处理扫描结果的方法
//     * @param msg
//     */
//    private void doScanResult(String msg){
//        if(!isActivityCanVisiable){//不可见时不做处理
//            return;
//        }
//        dismissProgressDialog();
//        materialDialog.setMessage(msg);
//        materialDialog.show();
//        Handler handler = new Handler();
//        handler.postDelayed(() ->{
//            if(!(edit_input_line.getVisibility()== View.VISIBLE)){
//                if(mQRCodeView!=null){
//                    mQRCodeView.startSpot();
//                }
//            }
//            materialDialog.dismiss();
//        },DURATION_TIME);
//    }

}
