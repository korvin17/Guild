package com.korvin.guild.game.ui.event.story;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.korvin.guild.R;
import com.korvin.guild.event.story.StoryItem;

import static com.korvin.guild.game.GameApplication.EVENT;

public class StoryChoiceFragment extends Fragment implements AbsListView.OnItemClickListener {

    private StoryItem itemChoice;
    private OnFragmentInteractionListener mListener;


    private AbsListView mListView;
    private ListAdapter mAdapter;

    public StoryChoiceFragment() {

    }

    public static StoryChoiceFragment newInstance(StoryItem itemChoice) {
        StoryChoiceFragment fragment = new StoryChoiceFragment();
        Bundle args = new Bundle();
        args.putSerializable(EVENT, itemChoice);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            itemChoice = (StoryItem) getArguments().getSerializable(EVENT);
        }
        mAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, itemChoice.getOptions());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story_choice, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        imageView.setImageResource(itemChoice.getImage());

        TextView textView = (TextView) view.findViewById(R.id.description);
        textView.setText(itemChoice.getDescription());
        textView.setMovementMethod(new ScrollingMovementMethod());

        final GestureDetector gestureDetector = new GestureDetector(getActivity().getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
            public boolean onDoubleTap(MotionEvent e) {
                mListener.onNextPage();
                return true;
            }
        });
        textView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });


        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            mListener.onOptionSelected(itemChoice.getOptions().get(position));
        }
    }

    public interface OnFragmentInteractionListener {
        public void onOptionSelected(Object way);

        public void onNextPage();
    }

}
