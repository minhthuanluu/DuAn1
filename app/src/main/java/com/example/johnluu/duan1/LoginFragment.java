package com.example.johnluu.duan1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for t>his fragment
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Đăng Nhập");
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        final EditText et_username = v.findViewById(R.id.et_username);
        final EditText et_matkhau = v.findViewById(R.id.et_matkhau);
        Button bt_dangnhap = v.findViewById(R.id.bt_dangnhap);
        Button bt_huy = v.findViewById(R.id.bt_huy);

        bt_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(et_username.getText().length()==0){
                        Toast.makeText(getContext(), "Tên đăng nhập không được trống!", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                try {
                    if( et_matkhau.getText().length()==0){
                        Toast.makeText(getContext(), "Mật khẩu không được trống!", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                if(et_username.getText().toString().equals("admin")&&et_matkhau.getText().toString().equals("admin")){
                    Toast.makeText(getContext(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                    AdminMenuFragment adminmenuFragment = new AdminMenuFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction()
                            .replace(R.id.content,adminmenuFragment,adminmenuFragment.getTag())
                            .commit();
                }
            }
        });




        return v;
    }

}
