package ca.dal.bartertrader.presentation.view.provider_home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ca.dal.bartertrader.R;
import ca.dal.bartertrader.data.model.Post;
import ca.dal.bartertrader.databinding.CardItemBinding;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder>  {
    private ArrayList<Post> posts;
    private PostListener postListener;

    public PostAdapter(PostListener postListener) {
        this.posts = new ArrayList<>();
        this.postListener = postListener;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardItemBinding cardItemBinding = CardItemBinding.inflate(layoutInflater, parent, false);
        return new PostHolder(cardItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        final Post post = posts.get(position);
        holder.cardItemBinding.setPost(post);
        holder.cardItemBinding.executePendingBindings();

        boolean isExpanded = posts.get(position).isExpanded();
        holder.cardItemBinding.expandableView.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        if(post.isExpanded()){
            holder.cardItemBinding.arrowButton.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
        }else{
            holder.cardItemBinding.arrowButton.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
        }

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    class PostHolder extends RecyclerView.ViewHolder {
        CardItemBinding cardItemBinding;

        public PostHolder(@NonNull CardItemBinding cardItemBinding) {
            super(cardItemBinding.getRoot());
            this.cardItemBinding = cardItemBinding;

            cardItemBinding.arrowButton.setOnClickListener(v -> {
                getPost().setExpanded(!getPost().isExpanded());
                notifyItemChanged(getAdapterPosition());
            });

            cardItemBinding.editButton.setOnClickListener(v -> {
                postListener.onEditClick(getPost());
                notifyItemChanged(getAdapterPosition());
            });

            cardItemBinding.deleteButton.setOnClickListener(v ->{
                postListener.onDeleteClick(getPost());
                notifyItemRemoved(getAdapterPosition());
                notifyItemRangeChanged(getAdapterPosition(), posts.size());
            });
        }

        public Post getPost() {
            return posts.get(getAdapterPosition());
        }
    }
}

