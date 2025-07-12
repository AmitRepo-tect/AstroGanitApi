package com.astroganit.lib.panchang.util;

public class EnumContainer {

    public enum LandingClass {
        SUVICHAR_ACTIVITY,
        PANCHANG_ACTIVITY,
        WEB_ACTIVITY
    }

    public enum KundliCategory {
        BASIC,
        SHODASVARGA,
        DASHA,
        KP_SYSTEM,
        VARSHFAL
    }

    public enum SuvicharFrags {
        SUVICHAR_FRAG,
        SUPRABHAT_FRAG,
        SUBH_SHANDYA_FRAG,
        SUBH_RATRI_FRAG
    }

    public enum SongDownloadStatus {
        DOWNLOADED,
        NOT_DOWNLOADED,
        DOWNLOADING
    }

    public enum SongPlayStatus {
        PLAYING,
        NOT_PLAYING,
        PAUSED,
        BUFFERING,
        LOADING
    }

    public enum SongType {
        AARTI,
        CHALISHA,
        BHAVAD_GEETA,
        SUNDER_KAAND,
        NAVRATRI_BHAJAN,
        BAJRANG_BAAN
    }

    public enum FestType {
        FESTIVALS,
        PURNIMA_VRAT,
        EKADASHI_VRAT,
        PRADOSH_VRAT,
        MASIK_SHIVRATRI,
        SANKSHTI_CHATURTHI,
        AMAVSYA,
        SANKRANTI,
        FEST_SANKRANTI,
        OTHER
    }

    public enum StotraType {
        SANKAT_NASHAK_GANESH_STOTRA,
        SHIV_TANDAV_STOTRA,
        SHREE_KANAKDHARA_STOTRA,
        DASHRATH_KRIT_SHANI_STOTRA,
        SHIV_MAHIMA_STOTRA,
        DRIDRA_DAHAN_SHIV_STOTRA,
        NARMADA_STOTRA,
        DHANVANTRI_STOTRA,
        SHIV_TANDAV_STUTI_STOTRA,
        SHRI_RUDRASHTAKAM,
        OTHER
    }
}
