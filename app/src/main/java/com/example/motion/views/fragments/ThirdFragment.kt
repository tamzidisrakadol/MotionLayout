package com.example.motion.views.fragments

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.motion.R
import com.example.motion.adapter.NotificationAdapter
import com.example.motion.databinding.FragmentThirdBinding
import com.example.motion.model.NotificationModel


class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null
    private var channelID = "Ad_tech"
    private var channelName = "Ad_tech"
    private var channelDesc = "Ad_tech_Notify"
    private var counter = 0
    private val notificationList = mutableListOf<NotificationModel>()
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(channelID,channelName,NotificationManager.IMPORTANCE_DEFAULT)
            notificationChannel.description = channelDesc
            val manager:NotificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(notificationChannel)

        }

        binding.notifyBtn.setOnClickListener {
            counter++
            displayNotification()
            showListOfNotification()
        }

    }


    private fun displayNotification() {
        val mBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(requireContext(), channelID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("New notification no.")
                .setContentText(counter.toString())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManagerCompat = NotificationManagerCompat.from(requireContext())
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManagerCompat.notify(1,mBuilder.build())
    }

    private fun showListOfNotification() {
        val notificationModel = NotificationModel("List Notification no.", counter)
        notificationList.add(notificationModel)
        binding.notificationRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val adapter = NotificationAdapter(notificationList)
        binding.notificationRecyclerView.adapter = adapter
        binding.notificationRecyclerView.adapter?.notifyDataSetChanged()
    }
}