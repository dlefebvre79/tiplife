<template>
  <div id="shift">
        <form action="">
            <label for="date">Date</label>
            <input type="date" name="date" id="date">
            <br>
            <label for="daypart">Shift</label>
            <select name="daypart" id="daypart">
                <option value="lunch">Lunch</option>
                <option value="dinner">Dinner</option>
            </select>
            <br>
            <label for="tips">Tips</label>
            <input type="text" name="tips" id="tips">
            <br>
            <label for="tipouts">Tipouts</label>
            <input type="text" name="tipouts" id="tipouts">
            <br>
            <label for="hours">Hours</label>
            <input type="number" name="hours" id="hours" step="0.01">
            <br>
            <label for="notes">Notes</label>
            <textarea name="notes" id="notes" cols="30" rows="10"></textarea>
            <br>
            <button>Save</button>
            <button>Cancel</button>
        </form>
  </div>
</template>

<script>
export default {
    name: "Shift",
    
}
</script>

<style>

</style>