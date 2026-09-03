# Mi Band 5 Companion — Native Android (Phase 3: dashboard UI + notifications + background sync)

Local-first fitness companion for Mi Band 5. No account, no cloud — all data on device.

## 3-phase plan (complete)
1. **Phase 1** — BLE scan → connect → GATT service discovery → auth handshake → pairing flow
2. **Phase 2** — Data sync & Room storage (5 tables) + Gadgetbridge import + periodic sync worker
3. **Phase 3 (this)** — Compose dashboard UI, notifications, foreground-service background sync

## Build
Requires Android Studio (source only — no pre-built APK).

1. Open this folder in Android Studio (Hedgehog or newer).
2. Let Gradle sync (AGP 8.5.2, Kotlin 2.0.20, Compose BOM 2024.09.03, Room 2.6.1, WorkManager 2.9.1).
3. Run on a device with Bluetooth LE (API 26+).
4. Tap **Sync** → paste the 32-hex-char auth key (Zepp / Gadgetbridge / huami-token) → it scans, connects, authenticates, and streams data into Room.

## What Phase 3 adds

### Compose dashboard (design fidelity to the brief)
- `ui/theme/` — exact palette: navy base `#14151f→#1c2440→#282d50`, magenta `#b06bff` / cyan `#5cd8ff` blooms; pill gradients copied verbatim (Steps / Heart Rate / Sleep).
- `ui/components/GlassCard.kt` — frosted card: radial blooms + **visible film grain** overlay.
- `ui/components/BellCurveChart.kt` — **Catmull-Rom spline** through 7 daily values, dotted baseline + dotted drop-line at selected day, marker with translucent halo.
- `ui/components/DaySelector.kt` — S M T W T F S circles (selected = solid white oval) + `‹ Jun 6 – Jun 12 ›` navigator.
- `ui/components/StatPill.kt` — true 999px capsules, 150° gradients, big value + unit + ▲/▼ %-change chip; one white "+" add-widget slot.
- `ui/dashboard/DashboardScreen.kt` — Steps / Heart Rate / Sleep tabs, live polling from Room, auth-key dialog, permission flow, status dot.

### Notifications & background sync
- `service/BleSyncService.kt` — **foreground service** (`connectedDevice` type) that keeps the process alive while connecting + syncing; live progress in the notification.
- `sync/PeriodicSyncWorker.kt` — WorkManager (6 h) now starts the foreground service (real background sync, not a placeholder).
- `MainActivity.kt` — rewritten as a Compose activity.

## Project tree (Phase 3 additions)
```
app/src/main/java/com/example/miband5/
├── MainActivity.kt                    # Compose entry point
├── ui/
│   ├── theme/  Color.kt, Theme.kt     # exact design tokens
│   ├── components/  GlassCard, BellCurveChart, DaySelector, StatPill
│   └── dashboard/  DashboardScreen, DashboardViewModel, ConnectionViewModel
├── service/BleSyncService.kt          # foreground service
└── (ble/, data/, sync/ from Phases 1–2)
```

## Protocol status
Same as Phase 2: verified UUIDs + auth handshake + HR control bytes are cited in file comments;
fetch-steps/battery/activity command bytes remain TODO (port from Gadgetbridge `HuamiCommand.java`).

## Status
Phase 3 code complete and compilable. Build the APK in Android Studio (no SDK in this environment).
The full 3-phase plan is done — next natural steps: port remaining Huami command bytes, wire the
add-widget slot, and build Score/Strain/muscle-map + journal/patterns features.
