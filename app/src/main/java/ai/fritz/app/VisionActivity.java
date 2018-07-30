package ai.fritz.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import ai.fritz.fritzvisionobjectmodel.FritzVisionObjectPredictor;
import ai.fritz.vision.FritzVisionObject;
import ai.fritz.vision.inputs.FritzVisionImage;
import butterknife.BindView;
import butterknife.ButterKnife;

public class VisionActivity extends AppCompatActivity {

    private static final String TAG = VisionActivity.class.getSimpleName();

    private static final int PICK_IMAGE_REQUEST = 1;

    @BindView(R.id.app_toolbar)
    Toolbar appBar;

    @BindView(R.id.image_preview)
    ImageView imagePreview;

    @BindView(R.id.vision_result)
    TextView resultView;

    @BindView(R.id.select_photo)
    Button selectPhotoBtn;

    FritzVisionObjectPredictor predictor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fritz_vision);
        setTitle(R.string.fritz_vision_title);
        ButterKnife.bind(this);
        predictor = FritzVisionObjectPredictor.getInstance(this);

        setSupportActionBar(appBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        selectPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageGallery();
            }
        });
    }

    private void openImageGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK && reqCode == PICK_IMAGE_REQUEST) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                FritzVisionImage visionImage = FritzVisionImage.fromBitmap(selectedImage);
                List<FritzVisionObject> labels = predictor.predict(visionImage);
                imagePreview.setImageBitmap(visionImage.getBitmap());

                resultView.setText(createTextFromList(labels));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private String createTextFromList(List<FritzVisionObject> objects) {
        String textToShow = "";
        for (FritzVisionObject object : objects) {
            textToShow = textToShow + String.format("%s: %4.2f\n", object.getVisionLabel().getText(), object.getVisionLabel().getConfidence());
        }

        Log.d(TAG, textToShow);
        return textToShow;
    }
}

