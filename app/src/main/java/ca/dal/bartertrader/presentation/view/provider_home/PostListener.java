package ca.dal.bartertrader.presentation.view.provider_home;

import ca.dal.bartertrader.data.model.Post;

public interface PostListener {
    void onEditClick(Post post);
    void onDeleteClick(Post post);
}
