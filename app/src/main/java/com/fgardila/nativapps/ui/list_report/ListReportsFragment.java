package com.fgardila.nativapps.ui.list_report;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fgardila.nativapps.SharedViewModel;
import com.fgardila.nativapps.R;
import com.fgardila.nativapps.databinding.FragmentListReportsBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListReportsFragment extends Fragment {

    FragmentListReportsBinding mBinding;
    SharedViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentListReportsBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observers();
        mBinding.fab.setOnClickListener(click -> {
            Navigation.findNavController(click).navigate(R.id.action_listReportsFragment_to_createFragment);
        });
    }

    private void observers() {
        viewModel.getReports().observe(getViewLifecycleOwner(), reports -> {
            ListReportsAdapter adapter = new ListReportsAdapter(reports);
            mBinding.recyclerView.setAdapter(adapter);
            mBinding.recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        });
    }
}