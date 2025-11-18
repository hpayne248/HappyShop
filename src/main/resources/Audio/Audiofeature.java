import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShopAudioControls extends JPanel {

    private final JSlider volumeSlider = new JSlider(0, 100, 80); // start at 80 %
    private final JCheckBox muteCheck    = new JCheckBox("Mute");

    public ShopAudioControls() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(new JLabel("Background Music:"));
        add(volumeSlider);
        add(muteCheck);

        // ---- volume slider ----
        volumeSlider.setToolTipText("Adjust music volume");
        volumeSlider.addChangeListener(e -> {
            if (!muteCheck.isSelected()) {
                float vol = volumeSlider.getValue() / 100f; // 0.0‑1.0
                AudioFeature.getInstance().setVolume(vol);
            }
        });

        // ---- mute checkbox ----
        muteCheck.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                AudioFeature.getInstance().stopMusic();
            } else {
                // restore previous volume
                float vol = volumeSlider.getValue() / 100f;
                AudioFeature.getInstance().setVolume(vol);
                AudioFeature.getInstance().startMusic();
            }
        });
    }
}