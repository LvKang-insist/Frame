package com.admin.work.detail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.ui.recycler.ItemType;
import com.admin.core.ui.recycler.MultipleFields;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.work.R;
import com.admin.work.R2;

import java.util.ArrayList;

import butterknife.BindView;

public class ImageDeleagate extends LatteDelegate {

    @BindView(R2.id.rv_image_container)
    RecyclerView mRecyclerView = null;

    private static final String ARG_PTITURES = "ARG_PICTURES";

    @Override
    public Object setLayout() {
        return R.layout.delegate_image;
    }

    private void initImages(){
        final ArrayList<String> pictures = getArguments().getStringArrayList(ARG_PTITURES);
        final ArrayList<MultipleItemEntity> entities = new ArrayList<>();
        final int size ;
        if (pictures != null){
            size = pictures.size();
            for (int i = 0; i < size; i++) {
                final String imageUrl = pictures.get(i);
                final MultipleItemEntity entity = MultipleItemEntity
                        .builder()
                        .setItemType(ItemType.SINGLE_BIG_IAMGE)
                        .setField(MultipleFields.IMAGE_URL,imageUrl)
                        .build();
                entities.add(entity);
            }
        }
        final RecyclerImageAdapter adapter = new RecyclerImageAdapter(entities);
        mRecyclerView.setAdapter(adapter);
    }

    public static ImageDeleagate create(ArrayList<String> pictures) {
        final Bundle args = new Bundle();
        args.putStringArrayList(ARG_PTITURES, pictures);
        final ImageDeleagate delegate = new ImageDeleagate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        initImages();
    }
}
