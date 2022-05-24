package com.fahmi.assesmen2.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fahmi.assesmen2.R
import com.fahmi.assesmen2.db.Mahasiswa
import com.fahmi.assesmen2.model.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_row_list.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        view.updateFirstName_et.setText(args.currentMahasiswa.namaDepan)
        view.updateLastName_et.setText(args.currentMahasiswa.namaBelakang)
        view.updateAge_et.setText(args.currentMahasiswa.usia.toString())
        view.updateNim_et.setText(args.currentMahasiswa.nim)

        view.update_btn.setOnClickListener {
            updateItem()
        }
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        val firstName = updateFirstName_et.text.toString()
        val lastName = updateLastName_et.text.toString()
        val age = Integer.parseInt(updateAge_et.text.toString())
        val nim = updateNim_et.text.toString()

        if (inputCheck(firstName, lastName, updateAge_et.text)) {
            // Create User Object
            val updatedUser = Mahasiswa(args.currentMahasiswa.id, nim, firstName, lastName, age)
            // Update Current User
            mUserViewModel.updateMahasiswa(updatedUser)
            Toast.makeText(requireContext(), "Update berhasil!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment2_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Isi semua dtaa", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Ya") { _, _ ->
            mUserViewModel.deleteMahasiswa(args.currentMahasiswa)
            Toast.makeText(
                requireContext(),
                "Berhasil dihapus: ${args.currentMahasiswa.nim}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment2_to_listFragment)
        }
        builder.setNegativeButton("Tidak") { _, _ -> }
        builder.setTitle("Hapus ${args.currentMahasiswa.nim}?")
        builder.setMessage("Yakin menghapus ${args.currentMahasiswa.nim}?")
        builder.create().show()
    }

}