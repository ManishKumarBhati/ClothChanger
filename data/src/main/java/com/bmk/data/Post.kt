package com.bmk.data

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @JvmField @SerializedName("Teams") val teams: Teams
)

data class Teams(
    @JvmField @SerializedName("6") val jsonMember6: TeamInfo,
    @JvmField @SerializedName("7") val jsonMember7: TeamInfo
)

data class TeamInfo(
    @JvmField @SerializedName("Name_Short") val nameShort: String,
    @JvmField @SerializedName("Name_Full") val nameFull: String,
    @JvmField @SerializedName("Players") val players: Players
)

data class Players(
    @JvmField @SerializedName("28891") val jsonMember28891: PlayersInfo,
    @JvmField @SerializedName("12518") val jsonMember12518: PlayersInfo,
    @JvmField @SerializedName("57458") val jsonMember57458: PlayersInfo,
    @JvmField @SerializedName("64221") val jsonMember64221: PlayersInfo,
    @JvmField @SerializedName("59736") val jsonMember59736: PlayersInfo,
    @JvmField @SerializedName("48191") val jsonMember48191: PlayersInfo,
    @JvmField @SerializedName("4356") val jsonMember4356: PlayersInfo,
    @JvmField @SerializedName("5313") val jsonMember5313: PlayersInfo,
    @JvmField @SerializedName("63611") val jsonMember63611: PlayersInfo,
    @JvmField @SerializedName("3667") val jsonMember3667: PlayersInfo,
    @JvmField @SerializedName("24669") val jsonMember24669: PlayersInfo,
    @JvmField @SerializedName("2734") val jsonMember2734: PlayersInfo,
    @JvmField @SerializedName("59429") val jsonMember59429: PlayersInfo,
    @JvmField @SerializedName("64321") val jsonMember64321: PlayersInfo,
    @JvmField @SerializedName("4038") val jsonMember4038: PlayersInfo,
    @JvmField @SerializedName("64306") val jsonMember64306: PlayersInfo,
    @JvmField @SerializedName("66833") val jsonMember66833: PlayersInfo,
    @JvmField @SerializedName("3472") val jsonMember3472: PlayersInfo,
    @JvmField @SerializedName("63084") val jsonMember63084: PlayersInfo,
    @JvmField @SerializedName("57492") val jsonMember57492: PlayersInfo,
    @JvmField @SerializedName("65739") val jsonMember65739: PlayersInfo,
    @JvmField @SerializedName("64073") val jsonMember64073: PlayersInfo
)

data class PlayersInfo(
    @JvmField @SerializedName("Bowling") val bowling: Bowling,
    @JvmField @SerializedName("Position") val position: String,
    @JvmField @SerializedName("Iscaptain") val Iscaptain: Boolean?,
    @JvmField @SerializedName("Batting") val batting: Batting,
    @JvmField @SerializedName("Name_Full") val nameFull: String
)

data class Bowling(
    @JvmField @SerializedName("Economyrate") val economyrate: String,
    @JvmField @SerializedName("Average") val average: String,
    @JvmField @SerializedName("Style") val style: String,
    @JvmField @SerializedName("Wickets") val wickets: String
)

data class Batting(
    @JvmField @SerializedName("Strikerate") val strikerate: String,
    @JvmField @SerializedName("Average") val average: String,
    @JvmField @SerializedName("Style") val style: String,
    @JvmField @SerializedName("Runs") val runs: String
)