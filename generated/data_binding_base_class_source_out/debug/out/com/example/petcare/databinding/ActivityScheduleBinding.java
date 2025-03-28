// Generated by view binder compiler. Do not edit!
package com.example.petcare.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.petcare.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityScheduleBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final Button btnSave;

  @NonNull
  public final EditText etDate;

  @NonNull
  public final EditText etDescription;

  @NonNull
  public final EditText etTitle;

  private ActivityScheduleBinding(@NonNull FrameLayout rootView, @NonNull Button btnSave,
      @NonNull EditText etDate, @NonNull EditText etDescription, @NonNull EditText etTitle) {
    this.rootView = rootView;
    this.btnSave = btnSave;
    this.etDate = etDate;
    this.etDescription = etDescription;
    this.etTitle = etTitle;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityScheduleBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityScheduleBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_schedule, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityScheduleBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnSave;
      Button btnSave = ViewBindings.findChildViewById(rootView, id);
      if (btnSave == null) {
        break missingId;
      }

      id = R.id.etDate;
      EditText etDate = ViewBindings.findChildViewById(rootView, id);
      if (etDate == null) {
        break missingId;
      }

      id = R.id.etDescription;
      EditText etDescription = ViewBindings.findChildViewById(rootView, id);
      if (etDescription == null) {
        break missingId;
      }

      id = R.id.etTitle;
      EditText etTitle = ViewBindings.findChildViewById(rootView, id);
      if (etTitle == null) {
        break missingId;
      }

      return new ActivityScheduleBinding((FrameLayout) rootView, btnSave, etDate, etDescription,
          etTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
