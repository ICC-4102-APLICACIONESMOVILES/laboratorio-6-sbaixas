package none.sbaixas.laboratorio5;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormFragment extends Fragment {

    TextView nameEdit;
    TextView dateEdit;
    TextView commentEdit;
    Spinner category_spinner;

    public FormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        category_spinner = getView().findViewById(R.id.category_spinner);
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Encuesta");
        spinnerArray.add("Inspeccion");
        spinnerArray.add("Reporte");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerArray);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_spinner.setAdapter(spinnerAdapter);

        nameEdit = getView().findViewById(R.id.nameEdit);
        dateEdit = getView().findViewById(R.id.dateEdit);
        commentEdit = getView().findViewById(R.id.commentEdit);
    }

    public void onDoneClick(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Forms form = new Forms();
                form.setUserName(nameEdit.getText().toString());
                form.setDate(dateEdit.getText().toString());
                form.setCategory(category_spinner.getSelectedItem().toString());
                form.setComment(commentEdit.getText().toString());
                MainApplication.formDatabase.daoAccess().insertOnlySingleForm(form);
            }
        }).start();
        getActivity().onBackPressed();
    }


}
