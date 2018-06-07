package activity.user.example.detectuseractivity;

import android.app.IntentService;
import android.app.LoaderManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.ActivityTransition;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class ActivityRecognizeService extends IntentService {

    private static final String TAG = "RecognizeService";
    List<ActivityTransition> transitions = new ArrayList<>();
    public ActivityRecognizeService(){
        super("ActivityRecognizeService");
    }

    public ActivityRecognizeService(String name){
        super(name);
    }

    // input
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(ActivityRecognitionResult.hasResult(intent)){
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivity(result.getProbableActivities());
//            ActivityTransitionRequest request = new ActivityTransitionRequest(transitions);
//            Task<Void> task = ActivityRecognition.getClient();
        }
    }

    private void handleDetectedActivity(List<DetectedActivity> probableActivity){

        //Old version to detect movement
        for(DetectedActivity activity : probableActivity){
            switch (activity.getType()){
                case DetectedActivity.IN_VEHICLE:
                    Log.d(TAG, "Handle Detected Activity: IN_VEHICLE: " + activity.getConfidence());
                    break;

                case DetectedActivity.ON_BICYCLE:
                    Log.d(TAG, "Handle Detected Activity: ON_BICYCLE: " + activity.getConfidence());
                    break;
                case DetectedActivity.ON_FOOT:
                    Log.d(TAG, "Handle Detected Activity: ON_FOOT: " + activity.getConfidence());
                    break;
                case DetectedActivity.RUNNING:
                    Log.d(TAG, "Handle Detected Activity: RUNNING: " + activity.getConfidence());
                    break;
                case DetectedActivity.STILL:
                    Log.d(TAG, "Handle Detected Activity: STILL: " + activity.getConfidence());
                    break;
                case DetectedActivity.WALKING:
                    Log.d(TAG, "Handle Detected Activity: WALKING: " + activity.getConfidence());
                    break;
                case DetectedActivity.TILTING:
                    Log.d(TAG, "Handle Detected Activity: TILTING: " + activity.getConfidence());
                    break;
                case DetectedActivity.UNKNOWN:
                    Log.d(TAG, "Handle Detected Activity: Unknown: " + activity.getConfidence());
                    break;
            }
        }

        //New version: using ActivityTransitionRequest

    }
    public void StartRecording(){
        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.WALKING)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                        .build());
        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.RUNNING)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                        .build());
        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.ON_BICYCLE)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                        .build());
        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.IN_VEHICLE)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                        .build());
        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.UNKNOWN)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                        .build());
        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.STILL)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                        .build());
        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.TILTING)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                        .build());
        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.ON_FOOT)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                        .build());
    }
    public void EndRecording(){
        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.WALKING)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                        .build());
        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.RUNNING)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                        .build());
        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.ON_BICYCLE)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                        .build());
        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.IN_VEHICLE)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                        .build());
        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.UNKNOWN)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                        .build());
        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.STILL)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                        .build());
        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.TILTING)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                        .build());
        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.ON_FOOT)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                        .build());
    }
    private static String toTransitionType(int transitionType) {
        switch (transitionType) {
            case ActivityTransition.ACTIVITY_TRANSITION_ENTER:
                return "ENTER";
            case ActivityTransition.ACTIVITY_TRANSITION_EXIT:
                return "EXIT";
            default:
                return "UNKNOWN";
        }
    }
}
