package me.bendik.sample;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import me.bendik.simplerangeview.SimpleRangeView;


public class BasicExampleFragment extends Fragment implements SimpleRangeView.OnRangeLabelsListener, SimpleRangeView.OnTrackRangeListener {

    LinearLayout rangeViewsContainer;
    SimpleRangeView rangeView;
    SimpleRangeView fixedRangeView;
    EditText editStart;
    EditText editEnd;
    private String[] labels = new String[] {"L1","L2","L3","L4","L5","L6","L7","L8","L9","L10"};

    public BasicExampleFragment() {
        // Required empty public constructor
    }

    public static BasicExampleFragment newInstance() {
        BasicExampleFragment fragment = new BasicExampleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basic_example, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rangeViewsContainer = (LinearLayout) view.findViewById(R.id.range_views_container);
        editStart = (EditText) view.findViewById(R.id.edit_start);
        editEnd = (EditText) view.findViewById(R.id.edit_end);

        rangeView = (SimpleRangeView) view.findViewById(R.id.rangeview);
        fixedRangeView = (SimpleRangeView) view.findViewById(R.id.fixed_rangeview);
        rangeView.setOnRangeLabelsListener(this);
        rangeView.setOnTrackRangeListener(this);

        rangeView.setActiveLineColor(getResources().getColor(R.color.colorAccent));
        rangeView.setActiveThumbColor(getResources().getColor(R.color.colorAccent));
        rangeView.setActiveLabelColor(getResources().getColor(R.color.colorAccent));
        rangeView.setActiveThumbLabelColor(getResources().getColor(R.color.colorAccent));
        rangeView.setActiveFocusThumbColor(getResources().getColor(R.color.colorAccent));
        rangeView.setActiveFocusThumbAlpha(0.26f);

        SimpleRangeView movable = (SimpleRangeView) view.findViewById(R.id.rangeview_movable);
        movable.setOnChangeRangeListener(new SimpleRangeView.OnChangeRangeListener() {
            @Override
            public void onRangeChanged(@NotNull SimpleRangeView rangeView, int start, int end) {
                Toast.makeText(BasicExampleFragment.this.getContext(),"start => "+start+", end => "+end,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable @Override
    public String getLabelTextForPosition(@NotNull SimpleRangeView rangeView, int pos, @NotNull SimpleRangeView.State state) {
        return labels[pos];
    }

    @Override
    public void onStartRangeChanged(@NotNull SimpleRangeView rangeView, int start) {
        editStart.setText(String.valueOf(start));
    }

    @Override
    public void onEndRangeChanged(@NotNull SimpleRangeView rangeView, int end) {
        editEnd.setText(String.valueOf(end));
    }
}
