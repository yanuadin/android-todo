package com.todoapk.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.todoapk.R;
import com.todoapk.model.Task;

import java.util.List;

public class RecyclerViewAdapterTodoList extends RecyclerView.Adapter<RecyclerViewAdapterTodoList.ViewHolder> {

    private List<Task> taskList;
    private TodoListClickListener todoListClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.task = taskList.get(position);
        holder.tvTitle.setText(holder.task.getTitle());
        holder.tvRangeTime.setText(holder.task.getTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todoListClickListener.onTaskClick(holder.task);
            }
        });
        holder.cbTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todoListClickListener.onTaskCheckBoxClick(holder.task);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (taskList == null) ? 0 : taskList.size();
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    public void setTodoListClickListener(TodoListClickListener todoListClickListener) {
        this.todoListClickListener = todoListClickListener;
    }

    public interface TodoListClickListener {
        void onTaskClick(Task task);

        void onTaskCheckBoxClick(Task task);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final View view;
        final TextView tvTitle;
        final TextView tvRangeTime;
        final TextView tvTime;
        final CheckBox cbTask;
        Task task;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvRangeTime = itemView.findViewById(R.id.tv_rangeTime);
            tvTime = itemView.findViewById(R.id.tv_time);
            cbTask = itemView.findViewById(R.id.cb_checklist);
        }
    }
}
