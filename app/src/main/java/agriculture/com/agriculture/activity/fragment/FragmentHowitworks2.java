package agriculture.com.agriculture.activity.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import agriculture.com.agriculture.R;
import agriculture.com.agriculture.activity.Testing;
import agriculture.com.agriculture.activity.adapters.CustomExpandableListAdapter;
import agriculture.com.agriculture.activity.model.ModelQuestionAnswer;
import agriculture.com.agriculture.activity.restclint.JRestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHowitworks2 extends Fragment {

    List<String> expandableListTitle;
    HashMap<String, List<ModelQuestionAnswer>> expandableListDetail;

    List<ModelQuestionAnswer> modelQuestionAnswers;

    ExpandableListView expandableListView;
    private CustomExpandableListAdapter expandableListAdapter;
    TextView tvheading;
    String id ;

    public FragmentHowitworks2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate ( R.layout.fragment_fragment_howitworks2, container, false );

        final DrawerLayout drawerLayout =getActivity ().findViewById(R.id.drawer);
        drawerLayout.closeDrawer ( Gravity.START );

        ImageView imageView = getActivity ().findViewById ( R.id.imgCloseDrawer2 );
        imageView.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer ( Gravity.START );
            }
        } );


        ImageView imageView1  = view .findViewById ( R.id. imgHamburger);
        imageView1.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer ( Gravity.START );

            }
        } );

       /* getActivity ().findViewById(R.id.imgHamburger).setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer ( Gravity.START );


            }
        } );*/



        /*getActivity ().findViewById(R.id.imgCloseDrawer2).setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.START);

            }
        } );*/









        id = getArguments ().getString ( "id" );


        expandableListView = view.findViewById ( R.id.expendablelistvew );
        tvheading =  view.findViewById ( R.id.tvheading );

        expandableListView.setChildDivider(getResources().getDrawable(R.color.white));
        expandableListView.setDivider(getResources().getDrawable(R.color.white));
        expandableListView.setDividerHeight(2);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // Doing nothing
                return true;
            }
        });
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated ( savedInstanceState );

        expandableListTitle = new ArrayList <> (  );
        expandableListDetail = new HashMap <> (  );


        JRestClient.GitApiInterface restClient = JRestClient.getClient();

        restClient.getHowitWorks ( id ).enqueue ( new Callback <Testing> ( ) {
            @Override
            public void onResponse(Call <Testing> call, Response <Testing> response) {

                if(response.body ().isSuccess ()) {
                    if(response.body ().getPayLoad ()!=null)
                    tvheading.setText ( response.body ().getPayLoad ().getPage ().getTitle () );
                    for(int i =0;i<response.body ().getPayLoad ().getHeading ().size ();i++)
                    {
                        modelQuestionAnswers = new ArrayList <> (  );
                        expandableListTitle.add ( response.body ().getPayLoad ().getHeading ().get ( i ).getTitle () );
                        for(int j=0;j<response.body ().getPayLoad ().getHeading ().get ( i ).getContent ().size ();j++)
                        {

                            ModelQuestionAnswer modelQuestionAnswer = new ModelQuestionAnswer (response.body ().getPayLoad ().getHeading ().get ( i ).getContent ().get ( j ).getQuestion (),response.body ().getPayLoad ().getHeading ().get ( i ).getContent ().get ( j ).getAnswer ()  );
                            modelQuestionAnswers.add ( modelQuestionAnswer );
                        }
                        expandableListDetail.put ( response.body ().getPayLoad ().getHeading ().get ( i ).getTitle (),modelQuestionAnswers );
                        Log.i ( "", "onResponse: " +expandableListDetail.toString ());
                    }
                    Log.i ( "", "onResponse: " );


                    expandableListAdapter = new CustomExpandableListAdapter (getActivity (), expandableListTitle, expandableListDetail);

                    expandableListView.setAdapter ( expandableListAdapter );
                    for(int i=0; i < expandableListAdapter.getGroupCount(); i++)
                        expandableListView.expandGroup(i);
                }

                else
                {
                    Toast.makeText ( getActivity ( ), response.body ().getMessage (), Toast.LENGTH_SHORT ).show ( );

                }

            }

            @Override
            public void onFailure(Call <Testing> call, Throwable t) {
                Toast.makeText ( getActivity (), t.toString (), Toast.LENGTH_SHORT ).show ( );
            }
        } );
    }







}
