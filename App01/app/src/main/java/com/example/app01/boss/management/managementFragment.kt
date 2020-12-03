package com.example.app01.boss.management

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.app01.MainActivity
import com.example.app01.R
import com.example.app01.dataObject
import com.example.app01.databinding.DialogBranchCreationBinding
import com.example.app01.databinding.DialogDeleteConfirmBinding
import com.example.app01.databinding.DialogWorkerCreationBinding
import com.example.app01.databinding.FragmentManagementBinding
import com.example.app01.dto.branch.Branch
import com.example.app01.dto.branch.branchRAdapter
import com.example.app01.dto.worker.WorkerInfo
import com.example.app01.dto.worker.workerAdapter

class managementFragment : Fragment() {
    private lateinit var binding : FragmentManagementBinding
    private lateinit var bindingDialogBranch : DialogBranchCreationBinding
    private lateinit var bindingDialogWorker : DialogWorkerCreationBinding
    private lateinit var bindingDialogDelete : DialogDeleteConfirmBinding
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
        mWorkerAdapter = workerAdapter(dataObject.listWorkerView, requireContext())
        binding.RvWorkers.adapter = mWorkerAdapter
        var snapHelper1 : PagerSnapHelper = PagerSnapHelper()
        snapHelper1.attachToRecyclerView(binding.RvWorkers)
        mWorkerAdapter.setItemClickListener(object : workerAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
            }

            override fun onLongClick(view: View, position: Int): Boolean {
                if (dataObject.listWorkerView[position].id != dataObject.selectUser.id) {
                    val dialog = AlertDialog.Builder(requireContext()).create()
                    bindingDialogDelete = DataBindingUtil.inflate(inflater,
                        R.layout.dialog_delete_confirm, container, false)
                    dialog.setView(bindingDialogDelete.root)
                    dialog.show()
                    bindingDialogDelete.title = "Are you sure to delete this worker?"
                    bindingDialogDelete.textWarn = "It can't be recovered once you delete."
                    bindingDialogDelete.buttonCancel.setOnClickListener {
                        dialog.cancel()
                    }
                    bindingDialogDelete.buttonConfirm.setOnClickListener {
                        (activity as MainActivity).deleteWorkerInfo(dataObject.listWorkerView[position].id, dataObject.selectBranch.id)
                        dataObject.listWorkerView.remove(dataObject.listWorkerView[position])
                        mWorkerAdapter = workerAdapter(dataObject.listWorkerView, requireContext())
                        binding.RvWorkers.adapter = mWorkerAdapter
                        dialog.dismiss()
                    }
                } else {
                    (activity as MainActivity).alertToast("It is yourself.")
                }

                return true
            }

        })

        // RecyclerView for Branches
        mBranchAdapter = branchRAdapter(dataObject.listBranch, requireContext())
        binding.RvBranches.adapter = mBranchAdapter
        var snapHelper2 : PagerSnapHelper = PagerSnapHelper()
        snapHelper2.attachToRecyclerView(binding.RvBranches)
        // Click event
        mBranchAdapter.setItemClickListener( object : branchRAdapter.ItemClickListener {
            // Click
            override fun onClick(view: View, position: Int) {
                var select = dataObject.listBranch[position]
                dataObject.selectBranch = select
                (activity as MainActivity).getWorkerViewesByIdBranch(select.id)
                mWorkerAdapter = workerAdapter(dataObject.listWorkerView, requireContext())
                binding.RvWorkers.adapter = mWorkerAdapter
            }

            // Long Click
            override fun onLongClick(view: View, position: Int): Boolean {
                var target = dataObject.listBranch[position]
                // Modifying selected Branch
                val dialog = AlertDialog.Builder(requireContext()).create()
                bindingDialogBranch = DataBindingUtil.inflate(inflater,
                    R.layout.dialog_branch_creation, container, false)
                dialog.setView(bindingDialogBranch.root)
                dialog.show()
                bindingDialogBranch.inputTitle = "Title of Branch"
                bindingDialogBranch.title = target.title
                bindingDialogBranch.buttonCancel.setOnClickListener {
                    dialog.cancel()
                }
                bindingDialogBranch.buttonConfirm.setOnClickListener {
                    dataObject.listBranch[position].title = bindingDialogBranch.title.toString()
                    (activity as MainActivity).modifyBranch(target)
                    mBranchAdapter.notifyItemChanged(position)
                    dialog.dismiss()
                }
                return true
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
                val newBranch = Branch()
                newBranch.title = bindingDialogBranch.title.toString()
                newBranch.id_boss = dataObject.selectUser.id
                (activity as MainActivity).createBranch(newBranch)
                dialog.dismiss()
            }
        }

        // Add new Worker to timetable
        binding.addWorker.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext()).create()
            bindingDialogWorker = DataBindingUtil.inflate(inflater,
                R.layout.dialog_worker_creation, container, false)
            dialog.setView(bindingDialogWorker.root)
            dialog.show()
            bindingDialogWorker.inputName = "Account of new worker"
            bindingDialogWorker.buttonCancel.setOnClickListener {
                dialog.cancel()
            }
            bindingDialogWorker.buttonConfirm.setOnClickListener {
                val newWorker =
                    (activity as MainActivity).searchUserByAccount(bindingDialogWorker.account.toString())
                // If account correct
                if (newWorker.account != "allowed") {
                    var newWorkerInfo = WorkerInfo()
                    newWorkerInfo.id_worker = newWorker.id
                    (activity as MainActivity).createWorker(newWorkerInfo, dataObject.selectBranch.id)
                    (activity as MainActivity).getWorkerViewesByIdBranch(dataObject.selectBranch.id)
                    mWorkerAdapter = workerAdapter(dataObject.listWorkerView, requireContext())
                    binding.RvWorkers.adapter = mWorkerAdapter
                    dialog.dismiss()
                } else {
                    (activity as MainActivity).alertToast("Invalid account. Please check again.")
                }
            }
        }

        return binding.root
    }

}
