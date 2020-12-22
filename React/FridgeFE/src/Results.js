import React from 'react';
import { Header, List, Item} from 'semantic-ui-react'

class Results extends React.Component {
    constructor(props) {
        super(props);
        const { ingredients } = this.props;
        this.state = {
            ingredients,
            response: [],
        };
    }

    componentWillReceiveProps(props) {
        const { ingredients } = props;
        this.setState({ ingredients });
      }

    componentDidUpdate(prevProps, prevState) {
        if (prevState.ingredients!== this.state.ingredients) {
            this.callApi()
            .then(res => this.setState({ response: res.Recipes }))
            .catch(err => console.log(err));
        }
    }

    // componentDidMount() {
    //     this.callApi()
    //     .then(res => this.setState({ response: res.Recipes }))
    //     .catch(err => console.log(err));
    // }

    callApi = async () => {
        const ingredients = this.state.ingredients
        const ingredientsString = ingredients.join(',')
        const query = 'http://localhost:9000/?query='.concat(ingredientsString)
        
        const response = await fetch(query);
        const body = await response.json();
        if (response.status !== 200) throw Error(body.message);
        
        return body;
      };

    render() {
       const {ingredients, response} = this.state
       
       if (response.length !== 0) {
           return (
           <Item.Group>
               {response.map(recipe => (
               <Item><a href={recipe.link}>
                    <Item.Image size='tiny' src={recipe.thumbnail} />

                    <Item.Content>
                        <Item.Header> {recipe.title} </Item.Header>
                        <Item.Description> {recipe.ingredients} </Item.Description>
                    </Item.Content>
                    </a></Item>
               ))}
           </Item.Group>
           )
       } else if (ingredients.length === 0){
           return(
           <div><h3>____</h3></div>
           )
       } else {
        return(
            <div><h3>로딩중...</h3></div>
            )
       }
    }
}


export default Results;