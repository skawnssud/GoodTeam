package com.example.app01.boss.management

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.app01.R
import com.example.app01.dataObject
import com.example.app01.databinding.DialogBranchCreationBinding
import com.example.app01.databinding.DialogWorkerCreationBinding
import com.example.app01.databinding.FragmentManagementBinding
import com.example.app01.dto.branch.Branch
import com.example.app01.dto.branch.branchRAdapter
import com.example.app01.dto.worker.Worker
import com.example.app01.dto.worker.workerAdapter

class managementFragment : Fragment() {
    private lateinit var binding : FragmentManagementBinding
    private lateinit var bindingDialogBranch : DialogBranchCreationBinding
    private lateinit var bindingDialogWorker : DialogWorkerCreationBinding
    private lateinit var mWorkerAdapter: workerAdapter
    private lateinit var mBranchAdapter : branchRAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // For bottom bar + Navigation
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_management, container, false)
        binding.layoutSchedule.setOnClickListener {
            it.findNavController().navigate(R.id.action_managementFragment_to_scheduleFragment)
        }
        binding.layoutHistory.setOnClickListener {
            it.findNavController().navigate(R.id.action_managementFragment_to_historyFragment)
        }
        binding.layoutMyPage.setOnClickListener {
            it.findNavController().navigate(R.id.action_managementFragment_to_infoFragment)
        }

        // RecyclerView for workers
        mWorkerAdapter = workerAdapter(dataObject.listWorker, requireContext())
        binding.RvWorkers.adapter = mWorkerAdapter
        var snapHelper1 : PagerSnapHelper = PagerSnapHelper()
        snapHelper1.attachToRecyclerView(binding.RvWorkers)
        mWorkerAdapter.setItemClickListener(object : workerAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                var target = dataObject.listWorker[position]
                val dialog = AlertDialog.Builder(requireContext()).create()
                bindingDialogWorker = DataBindingUtil.inflate(inflater,
                    R.layout.dialog_worker_creation, container, false)
                dialog.setView(bindingDialogWorker.root)
                dialog.show()
                bindingDialogWorker.inputName = "Name of worker"
                bindingDialogWorker.name = target.name
                bindingDialogWorker.age = target.age.toString()
                bindingDialogWorker.buttonCancel.setOnClickListener {
                    dialog.cancel()
                }
                bindingDialogWorker.buttonConfirm.setOnClickListener {
                    dataObject.listWorker[position].name = bindingDialogWorker.name.toString()
                    dataObject.listWorker[position].age = bindingDialogWorker.age!!.toInt()
                    mWorkerAdapter.notifyItemChanged(position)
                    dialog.dismiss()
                }
            }

        })

        // RecyclerView for Branches
        mBranchAdapter = branchRAdapter(dataObject.listBranch, requireContext())
        binding.RvBranches.adapter = mBranchAdapter
        var snapHelper2 : PagerSnapHelper = PagerSnapHelper()
        snapHelper2.attachToRecyclerView(binding.RvBranches)
        // Click event
        mBranchAdapter.setItemClickListener( object : branchRAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                var target = dataObject.listBranch[position]
                // Modifying selected Branch
                val dialog = AlertDialog.Builder(requireContext()).create()
                bindingDialogBranch = DataBindingUtil.inflate(inflater,
                    R.layout.dialog_branch_creation, container, false)
                dialog.setView(bindingDialogBranch.root)
                dialog.show()
                bindingDialogBranch.inputTitle = "Title of Branch"
                bindingDialogBranch.title = target.title
                bindingDialogBranch.numberOfWorkers = target.numberOfWorker.toString()
                bindingDialogBranch.buttonCancel.setOnClickListener {
                    dialog.cancel()
                }
                bindingDialogBranch.buttonConfirm.setOnClickListener {
                    dataObject.listBranch[position].title = bindingDialogBranch.title.toString()
                    dataObject.listBranch[position].numberOfWorker = bindingDialogBranch.numberOfWorkers!!.toInt()
                    mBranchAdapter.notifyItemChanged(position)
                    dialog.dismiss()
                }
            }
        })

        // Add new Branch
        binding.addBranch.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext()).create()
            bindingDialogBranch = DataBindingUtil.inflate(inflater,
                R.layout.dialog_branch_creation, container, false)
            dialog.setView(bindingDialogBranch.root)
            dialog.show()
            bindingDialogBranch.inputTitle = "Title of new Branch"
            bindingDialogBranch.buttonCancel.setOnClickListener {
                dialog.cancel()
            }
            bindingDialogBranch.buttonConfirm.setOnClickListener {
                val newBranch = Branch(bindingDialogBranch.title.toString())
                newBranch.numberOfWorker = bindingDialogBranch.numberOfWorkers!!.toInt()
                dataObject.listBranch.add(newBranch)
                dialog.dismiss()
            }
        }
        // Add new Worker
        binding.addWorker.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext()).create()
            bindingDialogWorker = DataBindingUtil.inflate(inflater,
                R.layout.dialog_worker_creation, container, false)
            dialog.setView(bindingDialogWorker.root)
            dialog.show()
            bindingDialogWorker.inputName = "Name of new worker"
            bindingDialogWorker.buttonCancel.setOnClickListener {
                dialog.cancel()
            }
            bindingDialogWorker.buttonConfirm.setOnClickListener {
                val newWorker = Worker(bindingDialogWorker.name.toString())
                newWorker.age = bindingDialogWorker.age!!.toInt()
                dataObject.listWorker.add(newWorker)
                dialog.dismiss()
            }
        }

        return binding.root
    }

}
