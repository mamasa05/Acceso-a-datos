package es.florida.Ad_ejercicios_T1;

import java.io.File;
import java.io.FilenameFilter;

public class FiltroExtension implements FilenameFilter {
    String extension;

    FiltroExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extension);
    }
}