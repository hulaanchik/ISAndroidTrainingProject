package com.example.isandroidtraining

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager

private val Num_pages = 2
class ViewPagerFragment : Fragment() {

    private lateinit var mPager: ViewPager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Instantiate a ViewPager and a PagerAdapter
        mPager = view.findViewById(R.id.pager)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //The pager adapter, which provides the pages to the view pager widget
        val pagerAdapter = SlideAdapter(childFragmentManager)
        mPager.adapter = pagerAdapter
    }
    //A simple pager adapter that represents 2 ScreenSlidePageFragment objects, in
    //sequence
    private inner class SlideAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override  fun getCount(): Int {
            return Num_pages
        }
        override  fun getItem(position: Int) : Fragment {
            var fragment: Fragment? = null
            when (position) {
                0 -> fragment = RecyclerViewFragment()
                1 -> fragment = SearchViewFragment()
            }
            return fragment!!
        }
    }
}