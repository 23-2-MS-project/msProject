package com.example.ms_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ms_project.dto.FoodDetail;
import com.example.ms_project.dto.FoodSimple;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<FoodSimple> foodList;
    private FoodRepository foodRepository;
    private Context context;  // Context 필드 추가

    public FoodAdapter(Context context, List<FoodSimple> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_list_item, parent, false);
        return new FoodViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        FoodSimple data = foodList.get(position);
        if (data.getDate().equals("날짜를 선택해주세요")) holder.emptyBind(data);
        else holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public void setData(List<FoodSimple> foodSimples) {
        this.foodList = foodSimples;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        private TextView time, category, calorie;
        private Button button;

        public FoodViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.Type);
            category = itemView.findViewById(R.id.Category);
            calorie = itemView.findViewById(R.id.Calorie);
            button = itemView.findViewById(R.id.button);

            // 버튼 클릭 이벤트 처리
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    foodRepository = new FoodRepository(context);
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // 클릭된 아이템의 위치(position)을 사용하여 원하는 작업 수행
                        // 버튼 클릭에 대한 동작을 정의
                        FoodSimple selectedFood = foodList.get(position);
                        FoodDetail foodDetail = foodRepository.getFoodDetail(selectedFood.getDate(), selectedFood.getType());

                        Intent intent = new Intent(view.getContext(), ShowFoodDetail.class);

                        // FoodDetail 객체를 인텐트에 추가
                        intent.putExtra("foodDetail", foodDetail);

                        view.getContext().startActivity(intent);
                    }
                }
            });
        }

        public void bind(FoodSimple data) {
            time.setText(data.getDate());
            category.setText(data.getType());
            calorie.setText(String.valueOf(data.getCalorie()));
            // 이미지 로딩 (이미지가 byte[] 형태일 경우에 따라서 처리)
            // Glide.with(itemView.getContext()).load(data.getImage()).into(image);
        }
        public void emptyBind(FoodSimple data){
            time.setText(data.getDate());
        }
    }
}

