package ca.dal.bartertrader.handlers;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;

public class CompletableTaskHandler implements OnSuccessListener, OnFailureListener {

    private final CompletableEmitter emitter;

    private CompletableTaskHandler(CompletableEmitter emitter) {
        this.emitter = emitter;
    }

    public static <T>  void assign(CompletableEmitter emitter, Task<T> task) {
        CompletableTaskHandler handler = new CompletableTaskHandler(emitter);
        task.addOnFailureListener(handler);
        task.addOnFailureListener(handler);
    }

    @Override
    public void onFailure(@NonNull Exception e) {

    }

    @Override
    public void onSuccess(Object o) {

    }
}
