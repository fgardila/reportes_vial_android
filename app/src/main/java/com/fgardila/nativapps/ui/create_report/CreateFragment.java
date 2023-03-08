package com.fgardila.nativapps.ui.create_report;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fgardila.nativapps.R;
import com.fgardila.nativapps.SharedViewModel;
import com.fgardila.nativapps.databinding.FragmentCreateBinding;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CreateFragment extends Fragment {

    FragmentCreateBinding mBinding;
    SharedViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCreateBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.setStatus(false);
        observers();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.btnAddImage.setOnClickListener(click -> {
            if (!validateDescription()) {
                viewModel.setDescription(Objects.requireNonNull(mBinding.tieDescription.getText()).toString());
                dispatchTakePictureIntent();
            } else {
                mBinding.tilDescription.setError("Completa la descripción");
            }
        });
    }

    private boolean validateDescription() {
        return mBinding.tieDescription.getText() == null || mBinding.tieDescription.getText().length() < 1;
    }

    private void observers() {
        viewModel.getStatus().observe(getViewLifecycleOwner(), status -> {
            if (status) {
                Toast.makeText(requireActivity(), "Creado correctamente", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(mBinding.getRoot()).navigate(R.id.action_createFragment_to_listReportsFragment);
            }

        });
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}