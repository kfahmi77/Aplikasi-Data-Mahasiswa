package com.fahmi.assesmen2.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahmi.assesmen2.R
import com.fahmi.assesmen2.model.UIViewModel
import com.fahmi.assesmen2.model.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private lateinit var mMahasiswaViewModel: UserViewModel
    private val viewModel: UIViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mMahasiswaViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        mMahasiswaViewModel.readAllData.observe(viewLifecycleOwner, Observer { mahasiswa ->
            adapter.setData(mahasiswa)
        })


        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        inflater.inflate(R.menu.menu_setting, menu)
        inflater.inflate(R.menu.dark_mode,menu)
        lifecycleScope.launch {
            val isChecked = viewModel.getUIMode.first()
            val item = menu.findItem(R.id.action_night_mode)
            item.isChecked = isChecked
            setUIMode(item, isChecked)
        }
    }

    private fun setUIMode(item: MenuItem, isChecked: Boolean) {
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            viewModel.saveToDataStore(true)
            item.setIcon(R.drawable.ic_night)

        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            viewModel.saveToDataStore(false)
            item.setIcon(R.drawable.ic_day)

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllUsers()
        }
       if (item.itemId == R.id.action_settings){
           findNavController().navigate(R.id.action_listFragment_to_settingFragment)
       }
        if(item.itemId == R.id.action_night_mode){
            item.isChecked = !item.isChecked
            setUIMode(item, item.isChecked)
            true
        }

        return super.onOptionsItemSelected(item)
    }


    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Ya") { _, _ ->
            mMahasiswaViewModel.deleteALlMahasiswa()
            Toast.makeText(
                requireContext(),
                "Berhasil menghapus semua data",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("Tidak") { _, _ -> }
        builder.setTitle("Hapus semua?")
        builder.setMessage("Yakin menghapus semua?")
        builder.create().show()
    }

}