package View.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import View.Fragments.Food;
import hcmute.phamvohonglam19110154.foodyapplication.R;

public class AngiFragment extends Fragment {
    private static final String TAG = AngiFragment.class.getSimpleName();
    private static final String URL = "https://www.foody.vn/__get/Place/HomeListPlace?t=1651839485847&page=1&lat=10.823099&lon=106.629664&count=12&districtId=&cateId=&cuisineId=&isReputation=&type=1";

    private RecyclerView recyclerView;
    private List<Food> foodList;
    private StoreAdapter mAdapter;
    public  AngiFragment(){

    }
    public static AngiFragment newInstance(String param1, String param2) {
        AngiFragment fragment = new AngiFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_angi, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_angi);
        foodList = new ArrayList<>();
        mAdapter = new StoreAdapter(getActivity(), foodList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        fetchStoreItems();
        return view;
    }


    private void fetchStoreItems() {
        JsonObjectRequest request = new JsonObjectRequest(URL,
                response -> {
                    if (response == null) {
                        Toast.makeText(getActivity(), "Couldn't fetch the " +
                                "store items! Please try again.", Toast.LENGTH_LONG).show();
                        return;
                    }

                    ArrayList<Food> items = new ArrayList<>();

                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());

                        JSONArray jSONArray = jsonObject.getJSONArray("Items");

                        for (int i = 0; i < jSONArray.length(); i++) {
                            JSONObject store = jSONArray.getJSONObject(i);
                            String storeId = store.getString("Id");
                            String name = store.getString("Name");
                            String address = store.getString("Address");
                            String photoUrl = store.getString("PhotoUrl");

                            items.add(new Food(storeId, "Sai Gon", name, address, photoUrl));
                        }

                        foodList.clear();
                        foodList.addAll(items);

                        mAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.getStackTrace();
                    }
                },
                error -> {
                    // error in getting json
                    Log.e(TAG, error.getMessage());
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("X-Requested-With", "XMLHttpRequest");
                params.put("Cookie", "gcat=food; fd.res.view.217=990502; " +
                        "__ondemand_sessionid=0plb3jqny0w21mwzcuj0ggxe; floc=217; flg=vn");

                return params;
            }
        };

        MyApplication.getInstance().addToRequestQueue(request);
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }



        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {
        private final List<Food> foodList;
        private Context context;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView name, idStore;
            public ImageView thumbnail;

            public MyViewHolder(View view) {
                super(view);
                name = view.findViewById(R.id.title);
                idStore = view.findViewById(R.id.idStore);
                thumbnail = view.findViewById(R.id.thumbnail);
            }
        }

        public StoreAdapter(Context context, List<Food> foodList) {
            this.context = context;
            this.foodList =foodList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.store_item_row, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            final Food food = foodList.get(position);
            holder.name.setText(food.getName());
            holder.idStore.setText(food.getAddress());

            Glide.with(context)
                    .load(food.getPhotoUrl())
                    .into(holder.thumbnail);
        }

        @Override
        public int getItemCount() {
            return foodList.size();
        }
    }
}
