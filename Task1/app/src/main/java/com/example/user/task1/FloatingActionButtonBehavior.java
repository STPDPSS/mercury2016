package com.example.user.task1;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by nika on 11/18/16.
 */

public class FloatingActionButtonBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {
  public FloatingActionButtonBehavior(Context context, AttributeSet attrs) {
    super();
  }

  @Override
  public boolean layoutDependsOn(
      CoordinatorLayout parent, FloatingActionButton child, View dependency) {
    return dependency instanceof Snackbar.SnackbarLayout || dependency instanceof RecyclerView;
  }

  @Override
  public void onDependentViewRemoved(
      CoordinatorLayout parent, FloatingActionButton child, View dependency) {
    child.show();
  }

  @Override
  public boolean onDependentViewChanged(
      CoordinatorLayout parent, FloatingActionButton child, View dependency) {
    if (dependency instanceof Snackbar.SnackbarLayout) {
      child.hide();
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean onStartNestedScroll(
      final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
      final View directTargetChild, final View target, final int nestedScrollAxes) {
    return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
        || super.onStartNestedScroll(coordinatorLayout, child,
        directTargetChild, target, nestedScrollAxes);
  }

  @Override
  public void onNestedScroll(
      final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
      final View target, final int dxConsumed, final int dyConsumed, final int dxUnconsumed,
      final int dyUnconsumed) {
    super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
        dxUnconsumed, dyUnconsumed);
    if (dyConsumed > 0) {
      child.hide();
    } else if (dyConsumed < 0) {
      child.show();
    }
  }
}
