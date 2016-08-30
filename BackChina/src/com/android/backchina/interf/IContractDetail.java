package com.android.backchina.interf;

public interface IContractDetail {
    
   Object getData();
   
   void hideLoading();
   
   // 收藏
   void toFavorite();

   // 分享
   void toShare();

   // 提交评价
   void toSendComment(String comment);
}