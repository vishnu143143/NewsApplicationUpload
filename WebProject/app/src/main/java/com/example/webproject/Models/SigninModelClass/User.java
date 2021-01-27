package com.example.webproject.Models.SigninModelClass;

public class User {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthentication_token() {
        return authentication_token;
    }

    public void setAuthentication_token(String authentication_token) {
        this.authentication_token = authentication_token;
    }

    String email,authentication_token;

    public User(String email, String authentication_token) {
        this.email = email;
        this.authentication_token = authentication_token;
    }
}



//
//    <?xml version="1.0" encoding="utf-8"?>
//<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
//        xmlns:app="http://schemas.android.com/apk/res-auto"
//        xmlns:tools="http://schemas.android.com/tools"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:padding="10dp"
//        android:orientation="vertical"
//
//        tools:context=".Activities.MainActivity"
//
//        >
//
//<de.hdodenhof.circleimageview.CircleImageView
//        android:layout_width="wrap_content"
//        android:layout_height="139dp"
//        android:paddingTop="40dp"
//        android:src="@drawable/user1"
//
//        >
//
//</de.hdodenhof.circleimageview.CircleImageView>
//
//
//<LinearLayout
//        android:layout_width="400dp"
//                android:layout_height="70dp"
//                >
//
//<ImageView
//            android:layout_width="97dp"
//                    android:layout_height="match_parent"
//                    android:src="@drawable/email">
//                    1</ImageView>
//
//<EditText
//            android:id="@+id/gmailText"
//                    android:layout_width="288dp"
//                    android:layout_height="50dp"
//                    android:layout_marginTop="10dp"
//                    android:background="#ffffff"
//                    android:hint="gmail"
//
//                    android:padding="10dp"
//                    android:paddingLeft="2dp" />
//</LinearLayout>
//
//<LinearLayout
//      android:layout_width="match_parent"
//              android:layout_height="wrap_content"
//              android:paddingTop="30dp">
//
//<ImageView
//       android:layout_width="97dp"
//               android:layout_height="match_parent"
//               android:src="@drawable/password">
//
//</ImageView>
//
//<EditText
//       android:id="@+id/pass"
//               android:layout_width="match_parent"
//               android:layout_height="40dp"
//               android:layout_marginTop="10dp"
//               android:background="#ffffff"
//               android:hint="password"
//
//               android:paddingLeft="2dp" />
//</LinearLayout>
//
//
//
//
//
//<Button
//     android:id="@+id/login"
//             android:layout_width="130dp"
//             android:layout_height="wrap_content"
//             android:layout_marginLeft="130dp"
//             android:layout_marginTop="20dp"
//             android:background="#2196F3"
//             android:text="login" />
//
//<LinearLayout
//     android:orientation="horizontal"
//             android:layout_height="wrap_content"
//             android:layout_width="wrap_content"
//             android:layout_marginLeft="120dp"
//             android:paddingTop="10dp">
//
//<TextView
//         android:layout_width="wrap_content"
//                 android:layout_height="35dp"
//                 android:gravity="center"
//
//                 android:text="not registered ?" />
//
//<TextView
//      android:id="@+id/register"
//              android:layout_width="wrap_content"
//              android:layout_height="35dp"
//              android:paddingLeft="10dp"
//              android:textStyle="bold"
//              android:textColor="#2196F3"
//              android:text="register"/>
//
//</LinearLayout>
//
//
//
//</LinearLayout>