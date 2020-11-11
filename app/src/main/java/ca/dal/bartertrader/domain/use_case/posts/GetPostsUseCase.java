package ca.dal.bartertrader.domain.use_case.posts;

import com.google.firebase.firestore.QuerySnapshot;

import ca.dal.bartertrader.data.repository.FirebasePostsRepository;
import ca.dal.bartertrader.domain.use_case.abstracts.AbstractBaseUseCase;
import ca.dal.bartertrader.utils.handler.resource.Resource;
import io.reactivex.rxjava3.core.Flowable;

public class GetPostsUseCase extends AbstractBaseUseCase<Integer, Flowable<Resource<QuerySnapshot>>> {

    private final FirebasePostsRepository firebasePostsRepository;

    public GetPostsUseCase(FirebasePostsRepository firebasePostsRepository) {
        this.firebasePostsRepository = firebasePostsRepository;
    }

    @Override
    public Flowable<Resource<QuerySnapshot>> execute(Integer maxNumberOfPosts) {
        return firebasePostsRepository.getPosts(maxNumberOfPosts);
    }
}