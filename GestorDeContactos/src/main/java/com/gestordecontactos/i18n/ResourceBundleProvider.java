package com.gestordecontactos.i18n;

import com.google.inject.Provider;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleProvider implements Provider<ResourceBundle> {
    @Override
    public ResourceBundle get() {
        return ResourceBundle.getBundle("com.gestordecontactos.i18n.Messages", Locale.getDefault());
    }
}
