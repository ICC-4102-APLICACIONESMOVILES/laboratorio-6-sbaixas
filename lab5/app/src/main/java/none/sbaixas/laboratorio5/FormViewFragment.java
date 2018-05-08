package none.sbaixas.laboratorio5;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.Normalizer;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormViewFragment extends Fragment {

    List<Forms> forms;
    ListView listView;
    Handler mainHandler;
    public FormViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainHandler = new Handler(getActivity().getMainLooper());

        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_form_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                forms = MainApplication.formDatabase.daoAccess().fetchAllForms();
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listView = getView().findViewById(R.id.form_list_view);
                        FormAdapter adapter = new FormAdapter(forms, getContext());
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position,
                                                    long id) {
                                Intent intent = new Intent(getContext(), AnswerActivity.class);
                                Forms item = (Forms)parent.getAdapter().getItem(position);
                                intent.putExtra("id", item.getFormId());
                                startActivity(intent);
                            }
                        });
                    }
                });
            }
        }).start();


    }

}
