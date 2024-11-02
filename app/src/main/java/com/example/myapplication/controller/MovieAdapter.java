package com.example.myapplication.controller;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.myapplication.DetailActivity;
import com.example.myapplication.R;
import com.example.myapplication.ui.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movieList;
    private Context context;

    public MovieAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
//        LayoutInflater 用于将 XML 布局文件转换成代码中的 View 对象。
//        .from(parent.getContext())：从 parent（RecyclerView） 中获取上下文（Context），用于访问资源和创建视图。
//        parent：这是 RecyclerView 本身，告诉 LayoutInflater 加载的视图将添加到 RecyclerView 中。
//        false：暂时不将加载的视图添加到父布局中，因为 RecyclerView 会自行管理视图的添加。

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvYear.setText(movie.getYear());
        Glide.with(context).load(movie.getPosterUrl()).into(holder.imgPoster);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("id", movie.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvYear;
        ImageView imgPoster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView); // 传给父类 (因为RecyclerView.ViewHolder只有含参构造方法)
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvYear = itemView.findViewById(R.id.tvYear);
            imgPoster = itemView.findViewById(R.id.imgPoster);
        }
    }

}
