package com.antosik.benchproject.app.movie.details.view

import com.antosik.benchproject.app.common.navigator.FragmentNavigator
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
// TODO Not needed, do not inherit from NavigableFragment when navigation is not needed
class MovieDetailsFragmentNavigator @Inject constructor() : FragmentNavigator()
